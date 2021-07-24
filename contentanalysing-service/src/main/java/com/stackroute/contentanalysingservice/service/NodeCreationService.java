package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.Intent;
import com.stackroute.contentanalysingservice.model.Terms;

import java.util.List;

public interface NodeCreationService {
    public List<Terms> getAllKeywords();

    public void createAndRelateNodes(Intent intent);
}
