package com.telluriumsource.servlet;


import com.telluriumsource.config.Configuration;
import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.recommend.RecommendRequest;
import com.telluriumsource.entity.recommend.RecommendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.util.Map;

public class RecommendServlet extends BaseHttpServlet<RecommendResponse> {

    private static final Logger logger = LoggerFactory.getLogger(RecommendServlet.class);

    protected RecommendServlet() throws JAXBException {
        super(RecommendResponse.class);
    }

    @Override
    protected RecommendResponse getResponse(WorkflowContext context, Map<String, String> params) {
        RecommendRequest request = new RecommendRequest();

        return Configuration.getInstance().getRecommendService().recommend(context, request);
    }

    @Override
    protected RecommendResponse getErrorResponse() {
        return new RecommendResponse();
    }
}
