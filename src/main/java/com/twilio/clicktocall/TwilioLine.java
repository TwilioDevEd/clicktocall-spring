package com.twilio.clicktocall;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TwilioLine {
    private final String twilioNumber;
    private CallFactory twilioServiceCallFactory;

    @Autowired
    public TwilioLine(CallFactory twilioServiceCallFactory, @Value("${TWILIO_NUMBER}") String twilioNumber) {
        this.twilioServiceCallFactory = twilioServiceCallFactory;
        this.twilioNumber = twilioNumber;
    }

    public void call(final String phoneNumber, final String responseUrl) throws TwilioRestException {
        Map<String, String> parameters = new HashMap<String, String>(){{
            put("From", twilioNumber);
            put("To", phoneNumber);
            put("Url", responseUrl);
        }};

        twilioServiceCallFactory.create(parameters);
    }
}
