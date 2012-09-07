package com.telluriumsource.service;


import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.recommend.RecommendRequest;
import com.telluriumsource.entity.recommend.RecommendResponse;

public interface RecommendService {

    RecommendResponse recommend(WorkflowContext context, RecommendRequest request);
}
