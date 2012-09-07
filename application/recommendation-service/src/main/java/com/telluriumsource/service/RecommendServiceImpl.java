package com.telluriumsource.service;


import com.telluriumsource.config.Configuration;
import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.recommend.RecommendRequest;
import com.telluriumsource.entity.recommend.RecommendResponse;

import javax.annotation.PostConstruct;

public class RecommendServiceImpl implements RecommendService {

    @PostConstruct
    public void setup(){
        Configuration.getInstance().setRecommendService(this);
    }

    @Override
    public RecommendResponse recommend(WorkflowContext context, RecommendRequest request) {
        RecommendResponse response = new RecommendResponse();

        return response;
    }
}
