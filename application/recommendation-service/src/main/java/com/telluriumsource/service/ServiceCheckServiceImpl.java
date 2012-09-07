package com.telluriumsource.service;


import com.telluriumsource.config.Configuration;
import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.service.ServiceCheckResponse;
import com.telluriumsource.entity.service.Status;

import javax.annotation.PostConstruct;

public class ServiceCheckServiceImpl implements ServiceCheckService {

    @PostConstruct
    public void setup(){
        Configuration.getInstance().setServiceCheckService(this);
    }

    @Override
    public ServiceCheckResponse serviceCheck(WorkflowContext context) {
        ServiceCheckResponse response = new ServiceCheckResponse();

        Status status = new Status();
        status.setTotalRequest(Configuration.getInstance().total());
        response.setStatus(status);
        response.setSuccessful(true);

        return response;
    }
}
