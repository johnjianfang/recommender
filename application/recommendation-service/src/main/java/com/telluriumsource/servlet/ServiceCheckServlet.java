package com.telluriumsource.servlet;


import com.telluriumsource.config.Configuration;
import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.service.ServiceCheckResponse;

import javax.xml.bind.JAXBException;
import java.util.Map;

public class ServiceCheckServlet extends BaseHttpServlet<ServiceCheckResponse> {

    protected ServiceCheckServlet() throws JAXBException {
        super(ServiceCheckResponse.class);
    }

    @Override
    protected ServiceCheckResponse getResponse(WorkflowContext context, Map<String, String> params) {

        return Configuration.getInstance().getServiceCheckService().serviceCheck(context);
    }

    @Override
    protected ServiceCheckResponse getErrorResponse() {
        return new ServiceCheckResponse();
    }
}
