package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;

import java.io.IOException;
import java.util.Optional;

public interface VideoService {

    void getVideoDetails(ExternalApiResponse externalApiResponse);


}
