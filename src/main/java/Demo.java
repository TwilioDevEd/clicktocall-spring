import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class Demo {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC123";
    public static final String AUTH_TOKEN = "xxx";

    public static void main(String[] args) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Create parameters to send a new message
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Body",
                "Jenny please?! I love you <3"));
        params.add(new BasicNameValuePair("To", "+14159352345"));
        params.add(new BasicNameValuePair("From", "+14158141829"));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
}