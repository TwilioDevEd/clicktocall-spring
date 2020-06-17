package com.twilio.clicktocall.twilio;

import com.twilio.clicktocall.CallException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.net.URISyntaxException;

public class TwilioLine {
    private String twilioNumber;
    private TwilioRestClient restClient;

    public TwilioLine(TwilioRestClient restClient, String twilioNumber) {
        this.restClient = restClient;
        this.twilioNumber = twilioNumber;
    }

    public void call(final String phoneNumber, final String responseUrl)  {
        try {
            CallCreator callCreator = new CallCreator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioNumber),
                new URI(responseUrl));
            callCreator.create(restClient);
        } catch (URISyntaxException e) {
            throw new CallException(e);
        }
    }
}
