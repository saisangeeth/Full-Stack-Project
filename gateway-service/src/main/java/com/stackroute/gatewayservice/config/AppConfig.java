//package com.stackroute.gatewayservice.config;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r->r.path("/authenticationservice/api/v1/**").uri("http://localhost:8090/"))
//                .route(r->r.path("/userprofileservice/api/v1/**").uri("http://localhost:8096/"))
//                .route(r->r.path("/contentsearchservice/api/v1/**").uri("http://localhost:8085/"))
//                .route(r->r.path("/contentanalysisservice/api/v1/**").uri("http://localhost:8070/"))
//                .route(r->r.path("/graphqueryservice/api/v1/**").uri("http://localhost:8097/"))
//                .route(r->r.path("/graphcommandservice/api/v1/**").uri("http://localhost:8060/"))
//                .route(r->r.path("/questionanswerservice/api/v1/**").uri("http://localhost:8000/"))
//                //mention all the routes of all the services
//                .build();
//    }
//
//    private String allowedOrigin="http://localhost:4200";
//    @Bean
//    public WebMvcConfigurer getCorsConfiguration(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins(allowedOrigin)
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//            }
//        };
//    }
//}
