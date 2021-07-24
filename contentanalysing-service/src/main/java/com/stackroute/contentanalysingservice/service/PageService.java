package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;
import org.jsoup.nodes.Element;

import javax.lang.model.util.Elements;
import java.io.IOException;
import java.util.List;

public interface PageService {


    public List<Response> getTheData(ExternalApiResponse externalApiResponse);
//    public Elements getHtmlPage(String url) throws IOException;
}


