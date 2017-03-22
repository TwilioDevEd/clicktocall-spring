package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwilioRequestValidator;
import com.twilio.clicktocall.twilio.TwiMLUtil;
import com.twilio.twiml.TwiMLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConnectController {

    private final static String SAY_MESSAGE = "Thanks for contacting our sales department. Our " +
            "next available representative will take your call.";

    private final TwilioRequestValidator requestValidator;

    @Autowired
    public ConnectController(TwilioRequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @RequestMapping(value = "connect/{salesPhone}", produces = "application/xml")
    public ResponseEntity<String> connect(@PathVariable String salesPhone, HttpServletRequest request) throws TwiMLException {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_XML);

        if (requestValidator.validate(request)) {
            return new ResponseEntity<>(
              TwiMLUtil.buildVoiceResponseAndDial(SAY_MESSAGE, salesPhone),
              httpHeaders,
              HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>("Invalid twilio request", HttpStatus.BAD_REQUEST);
        }
    }
}
