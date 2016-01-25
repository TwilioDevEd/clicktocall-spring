package com.twilio.clicktocall;


import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class TwilioLineTest {

    @Test
    public void shouldUserTwilioConfigInformationToCallProvidedNumber() throws TwilioRestException {
        CallFactory mockedCallFactory = mock(CallFactory.class);
        final String twilioNumber = "567897";
        TwilioLine twilioLine = new TwilioLine(mockedCallFactory, twilioNumber);

        twilioLine.call("123455", "http://host/connect");

        Map<String, String> paramemtros = new HashMap(){{
            put("From", twilioNumber);
            put("To", "123455");
            put("Url", "http://host/connect");
        }};

        verify(mockedCallFactory, times(1)).create(paramemtros);
    }

}
