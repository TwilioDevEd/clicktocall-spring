package com.twilio.clicktocall;

import com.twilio.sdk.verbs.Hangup;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConnectController {
    private final RequestValidator requestValidator;

    @Autowired
    public ConnectController(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @RequestMapping("connect")
    public ResponseEntity<String> connect(HttpServletRequest request) {
        if (requestValidator.validate(request)) {
            TwiMLResponse twimlResponse = new TwiMLResponse();

            Say sayMessage = new Say(
                    "If this were a real click to call implementation, you would be connected to an agent at this point.");
            Hangup hangup = new Hangup();

            try {
                twimlResponse.append(sayMessage);
                twimlResponse.append(hangup);
            } catch (TwiMLException e) {
                System.out.println("Twilio's response building error");
            }

            return new ResponseEntity<>(twimlResponse.toXML(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid twilio request", HttpStatus.BAD_REQUEST);
        }
    }
}
