package com.twilio.clicktocall.twilio;

import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

public class TwiMLUtil {

    public static String buildVoiceResponse(String say) throws TwiMLException {
        return new VoiceResponse.Builder()
                .say(new Say.Builder(say).build())
                .build()
                .toXml();
    }
}
