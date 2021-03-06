package com.twilio.clicktocall.twilio;

import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.voice.Number;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

public class TwiMLUtil {

    public static String buildVoiceResponseAndDial(String say, String salesPhone) throws TwiMLException {
        Number number = new Number.Builder(salesPhone).build();
        return new VoiceResponse.Builder()
                .say(new Say.Builder(say).build())
                .dial(new Dial.Builder().number(number).build())
                .build()
                .toXml();
    }
}
