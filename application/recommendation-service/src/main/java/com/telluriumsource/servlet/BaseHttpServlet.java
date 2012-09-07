package com.telluriumsource.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telluriumsource.config.Configuration;
import com.telluriumsource.context.WorkflowContext;
import com.telluriumsource.entity.base.Response;
import com.telluriumsource.entity.code.Format;
import com.telluriumsource.utils.XMLMarshaller;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.*;

public abstract class BaseHttpServlet<T extends Response> extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(BaseHttpServlet.class);

    protected static final String XML_CONTENT = "text/xml";

    protected static final String JSON_CONTENT = "application/json";

    protected static final String TRACE = "trace".toUpperCase();

    protected static final String FORMAT = "fmt".toUpperCase();

    public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public static final String ERROR_XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Successful>false</Successful><ErrorMessages><ErrorMessage>";
    public static final String ERROR_XML_TAIL = "</ErrorMessage></ErrorMessages></Response>";

    public static final String ERROR_JSON = "{\"Successful\":false}";

    protected Gson gson;

    protected XMLMarshaller<T> marshaller;

    protected final Class<T> clazz;

    protected BaseHttpServlet(Class<T> clazz) throws JAXBException {
        this.clazz = clazz;
        marshaller = new XMLMarshaller<T>(clazz);
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    protected abstract T getResponse(WorkflowContext context, Map<String, String> params);

    protected abstract T getErrorResponse();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        Map<String, String> paramMap = new HashMap<String, String>();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                paramMap.put(paramName.toUpperCase(), paramValues[0]);
            } else {
                paramMap.put(paramName.toUpperCase(), StringUtils.join(paramValues, ','));
            }
        }

        boolean isXML = (Configuration.getInstance().getEnvironment().getFormat() == Format.XML);
        String fmt = paramMap.get(FORMAT);
        if (fmt != null) {
            isXML = Format.XML.name().equalsIgnoreCase(fmt.trim());
        }

        //If content type is defined on request, the response content type should be consistent with the request
        String contentType = request.getContentType();
        if(contentType != null){
            if(XML_CONTENT.equalsIgnoreCase(contentType)){
                isXML = true;
            } else {
                isXML = false;
            }
        }

        if (logger.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder(64);
            Set<String> keys = paramMap.keySet();
            sb.append("[");
            boolean first = true;
            for (String key : keys) {
                if (!first) {
                    sb.append(", ");
                } else {
                    first = false;
                }

                sb.append(key).append(": ").append(paramMap.get(key));
            }
            sb.append("]");

            logger.debug("Request URL String: " + sb.toString());
        }
        try {
            if(!isXML){

                WorkflowContext context = new WorkflowContext();
                context.setRequestId(Configuration.getInstance().next());
                Response result = getResponse(context, paramMap);
                if(context.isHasError()){
                    result.setSuccessful(false);
                    if(Configuration.getInstance().getEnvironment().isExposeError()){
                        result.setErrorMessages(context.getErrors());
                    }
                } else{
                    result.setSuccessful(true);
                }

                String resp = gson.toJson(result);
                response.setContentType(JSON_CONTENT);
                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(resp);

            } else {
                WorkflowContext context = new WorkflowContext();
                context.setRequestId(Configuration.getInstance().next());
                T result = getResponse(context, paramMap);
                if(context.isHasError()){
                    result.setSuccessful(false);
                    if(Configuration.getInstance().getEnvironment().isExposeError()){
                        result.setErrorMessages(context.getErrors());
                    }
                } else{
                    result.setSuccessful(true);
                }

                String resp = marshaller.marshall(result);

                if (!resp.startsWith(XML_HEADER)) {
                    resp = XML_HEADER + resp;
                }
                response.setContentType(XML_CONTENT);
                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(resp);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            T result = getErrorResponse();
            result.setSuccessful(false);
            List<String> errors = new ArrayList<String>();
            errors.add(e.getMessage());
            result.setErrorMessages(errors);
            if(!isXML){
                String resp = gson.toJson(result);
                response.setContentType(JSON_CONTENT);
                response.setStatus(HttpServletResponse.SC_OK);

                response.getWriter().write(resp);
            } else {
                String resp = null;
                try {
                    resp = marshaller.marshall(result);
                    if (!resp.startsWith(XML_HEADER)) {
                        resp = XML_HEADER + resp;
                    }
                    response.setContentType(XML_CONTENT);
                    response.setStatus(HttpServletResponse.SC_OK);

                    response.getWriter().write(resp);
                } catch (JAXBException e1) {
                    resp = ERROR_XML_HEADER + e1.getMessage() + ERROR_XML_TAIL;
                    response.setContentType(XML_CONTENT);
                    response.setStatus(HttpServletResponse.SC_OK);

                    response.getWriter().write(resp);

                } catch (XMLStreamException e1) {
                    resp = ERROR_XML_HEADER + e1.getMessage() + ERROR_XML_TAIL;
                    response.setContentType(XML_CONTENT);
                    response.setStatus(HttpServletResponse.SC_OK);

                    response.getWriter().write(resp);
                }
            }
        }
    }
}
