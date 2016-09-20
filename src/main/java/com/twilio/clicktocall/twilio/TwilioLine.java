package com.twilio.clicktocall.twilio;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

public class TwilioLine {
    private String twilioNumber;
    private TwilioRestClient restClient;

    public TwilioLine(TwilioRestClient restClient, String twilioNumber) {
        this.restClient = restClient;
        this.twilioNumber = twilioNumber;
    }

    public void call(final String phoneNumber, final String responseUrl)  {
        CallCreator callCreator = new CallCreator(new PhoneNumber(phoneNumber), new PhoneNumber(twilioNumber), responseUrl);
        callCreator.execute(restClient);
    }
}
