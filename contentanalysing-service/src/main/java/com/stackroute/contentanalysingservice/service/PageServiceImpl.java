package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;
import com.stackroute.contentanalysingservice.repository.TermsRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PageServiceImpl implements PageService {
    TermsRepository termsRepository;
    int intentCount = 0;

    int img = 0, vid = 0;

    RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey2}")
    private String routingkey;


//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void listen(ExternalApiResponse externalApiResponse) {
//        System.out.println(externalApiResponse);
//        getTheData(externalApiResponse);
//    }


    @Autowired
    public PageServiceImpl(TermsRepository termsRepository, RabbitTemplate rabbitTemplate) {
        this.termsRepository = termsRepository;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public List<Response> getTheData(ExternalApiResponse externalApiResponse) {

        int count = 0;
        List<Response> list = new ArrayList<>();
        Response response;


        if (externalApiResponse.getType().equals("page")) {
            Map<String, Integer> intent = new HashMap<>();
            // Added all the keywords with which we will be able to identify the category of the page

            List<String> begin = termsRepository.getAllKeywordsBasedOnCategory("BEGINNER"); // initializing the array as a list
            List<String> medium = termsRepository.getAllKeywordsBasedOnCategory("INTERMEDIATE");
            List<String> last = termsRepository.getAllKeywordsBasedOnCategory("EXPERT");
            log.info("ALL THE INTENT KEYWORD FOR BEGINNER",termsRepository.getAllKeywordsBasedOnCategory("BEGINNER"));
            log.info("Intermediate keywords",termsRepository.getAllKeywordsBasedOnCategory("INTERMEDIATE"));

            try {//connecting to the particular website using jsoup
                Document doc = Jsoup.connect(externalApiResponse.getUrls().get(0).getLink()).get();
                Elements images = doc.getElementsByTag("img");
                for (Element tag : images) { // finding the number of images present in the page
                    img++;
                }
                Elements meta = doc.select("meta[property=og:video]"); // css query for extracting if any videos is present in the page
                for (Element video : meta
                ) {
                    vid++; //incrementing the value if any video is present
                }


                String[] data = doc.text().split("[^0-9a-zA-Z:,]+");
                for (String s : data
                ) {
                    count++;
                }


                intent.put("BEGINNER", 0);
                intent.put("EXPERT", 0);
                intent.put("INTERMEDIATE", 0);
                if ((int) (count / 8) < 125) {
                    count = 1;
                }
                count = (int) (count / 20);

                for (String word : data) {
                    if (begin.contains(word) || medium.contains(word) || last.contains(word)) { //checking if the particular word is present in any of them
                        String node = termsRepository.getCategoryNameBasedOnKeyword(word);// check the parent of the node
//                    increment the value with 1 if the word belongs to the particular parent
                        intent.put(node, intent.get(node) + 1);
                    }
                }

                log.info(max(intent.get("BEGINNER"),intent.get("EXPERT"),intent.get("INTERMEDIATE")));
                response = new Response(externalApiResponse.getType(), externalApiResponse.getUrls().get(0).getTitle(), externalApiResponse.getDomain(), externalApiResponse.getConcept(), externalApiResponse.getUrls().get(0).getLink(), max(intent.get("BEGINNER"),intent.get("EXPERT"),intent.get("INTERMEDIATE")), confidenceScore(intent.get("BEGINNER"), intent.get("INTERMEDIATE"), intent.get("EXPERT")), Integer.toString(count), vid, img);
                vid = 0;
                img = 0;
                if (Integer.parseInt(response.getDuration()) != 0) {
                    rabbitTemplate.convertAndSend(exchange, routingkey, response);
                }
                intent.clear();
                list.add(response);
                System.out.println(response);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return list;
    }
//
//    @Override
//    public Elements getHtmlPage(String url) throws IOException {
//        Document doc = Jsoup.connect(url).get();
//        System.out.println("document from jsoup -------->"+doc);
//        Elements body = doc.getElementsByTag("body");
//        System.out.println("*******************BODY*********************");
//        return body;
//    }


    //temporary function for intent

//    public String intentResult(int counter) {
//        if (counter > 2 && counter < 5) {
//            return "INTERMEDIATE";
//        } else if (counter>=5&& counter<=7) {
//            return "EXPERT";
//        }
//        return "BEGINNER";
//    }

    public String max(int num1, int num2, int num3) {
        try {
            if (num1 >= num2 && num1 >= num3) {
                return "BEGINNER";
            } else if (num2 >= num1 && num2 >= num3)
                return "INTERMEDIATE";
        } catch (Exception exception) {
            return "INTERMEDIATE";
        }
        return "EXPERT";
    }

    private int confidenceScore(int first, int second, int third) {
        int max = maxValue(first, second, third);
        int compute = 0;
        try {
            compute = (int) (10.0 * (max / (first + second + third)));
        } catch (Exception exception) {
            compute = 6;
        }

        return compute;
    }

    public int maxValue(int num1, int num2, int num3) {
        if (num1 >= num2 && num1 >= num3)
            return num1;

        else if (num2 >= num1 && num2 >= num3)
            return num2;

        return num3;
    }


}
