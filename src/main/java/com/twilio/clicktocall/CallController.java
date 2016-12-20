package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwilioLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class CallController {

    private TwilioLine twilioLine;

    @Autowired
    public CallController(TwilioLine twilioLine) {
        this.twilioLine = twilioLine;
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("call")
    public ResponseEntity<String> call(HttpServletRequest request) {
        String userPhone = request.getParameter("userPhone");
        String salesPhone = request.getParameter("salesPhone");

        if (isBlank(userPhone) || isBlank(salesPhone)) {
            return new ResponseEntity<>("Both user and sales phones must be provided", HttpStatus.BAD_REQUEST);
        } else {
            return tryToCallTwilioUsing(userPhone, buildResponseUrl(salesPhone, request));
        }
    }

    private boolean isBlank(String userPhone) {
        return userPhone == null || userPhone.isEmpty();
    }

    private ResponseEntity<String> tryToCallTwilioUsing(String userPhone, String responseUrl) {
        ResponseEntity<String> response = new ResponseEntity<>("Phone call incoming!", HttpStatus.ACCEPTED);

        try {
            twilioLine.call(userPhone, responseUrl);
        } catch (Exception e) {
            String errorMessage = "Problem while processing request: "+
                    e.getMessage();
            response = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    private String buildResponseUrl(String salesPhone, HttpServletRequest request) {
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        try {
            return host + "/connect/" + URLEncoder.encode(salesPhone, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CallException(e);
        }
    }

}
