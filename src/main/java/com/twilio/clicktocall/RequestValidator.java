package com.twilio.clicktocall;

import com.twilio.sdk.TwilioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestValidator {

    private final TwilioUtils twilioUtils;

    @Autowired
    public RequestValidator(TwilioUtils twilioUtils) {
        this.twilioUtils = twilioUtils;
    }

    public boolean validate(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        Map<String, String> params = extractParametersFrom(request);

        String signature = request.getHeader("X-Twilio-Signature");

        return twilioUtils.validateRequest(signature, url, params);
    }

    private Map<String, String> extractParametersFrom(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String currentName = names.nextElement();
            params.put(currentName, request.getParameter(currentName));
        }
        return params;
    }
}
