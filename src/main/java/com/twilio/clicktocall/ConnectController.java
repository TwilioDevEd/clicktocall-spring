package com.twilio.clicktocall;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class ConnectController {
    private final RequestValidator requestValidator;

    public ConnectController(RequestValidator requestValidator) {
        this.requestValidator = requestValidator;
    }

    @RequestMapping("connect")
    public ResponseEntity<String> connect(HttpServletRequest request) {
        if (requestValidator.validate(request)) {
            return new ResponseEntity<>("test", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid twilio request", HttpStatus.BAD_REQUEST);
        }
    }
}
