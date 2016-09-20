package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwilioLine;
import com.twilio.clicktocall.twilio.TwilioRequestValidator;
import com.twilio.http.TwilioRestClient;
import com.twilio.security.RequestValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@ComponentScan
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TwilioRestClient twilioRestClient(@Value("${TWILIO_ACCOUNT_SID}") String accountSid,
                                      @Value("${TWILIO_AUTH_TOKEN}") String authToken){
        return new TwilioRestClient.Builder(accountSid, authToken).build();
    }

    @Bean
    public TwilioRequestValidator twilioRequestValidator(@Value("${TWILIO_AUTH_TOKEN}") String authToken) {
        return new TwilioRequestValidator(new RequestValidator(authToken));
    }

    @Bean
    public TwilioLine twilioLine(TwilioRestClient restClient, @Value("${TWILIO_NUMBER}") String twilioNumber) {
        return new TwilioLine(restClient, twilioNumber);
    }
}
