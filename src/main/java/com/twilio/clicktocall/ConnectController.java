package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwiMLUtil;
import com.twilio.clicktocall.twilio.TwilioRequestValidator;
import com.twilio.twiml.TwiMLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConnectController {

    private final static String SAY_MESSAGE = "If this were a real click to call implementation, you would be " +
            "connected to an agent at this point.";

    private final TwilioRequestValidator requestValidator;

    @Autowired
    public ConnectController(TwilioRequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @RequestMapping("connect")
    public ResponseEntity<String> connect(HttpServletRequest request) throws TwiMLException {
        if (requestValidator.validate(request)) {
            return new ResponseEntity<>(TwiMLUtil.buildVoiceResponse(SAY_MESSAGE), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid twilio request", HttpStatus.BAD_REQUEST);
        }
    }
}
