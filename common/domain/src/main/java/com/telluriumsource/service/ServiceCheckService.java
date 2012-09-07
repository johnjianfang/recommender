package com.telluriumsource.service;


import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.service.ServiceCheckResponse;

public interface ServiceCheckService {

    ServiceCheckResponse serviceCheck(WorkflowContext context);
}
