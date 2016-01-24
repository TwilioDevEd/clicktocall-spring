package com.twilio.clicktocall;

import com.twilio.sdk.TwilioRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        String phoneNumber = request.getParameter("phone");

        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return new ResponseEntity<>("The phone number field can't be empty", HttpStatus.BAD_REQUEST);
        } else {
            twilioLine.call(phoneNumber, buildResponseUrl(request));
            return new ResponseEntity<>("Phone call incoming!", HttpStatus.ACCEPTED);
        }
    }

    private String buildResponseUrl(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/connect";
    }
}
