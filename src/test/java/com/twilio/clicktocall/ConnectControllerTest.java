package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwilioRequestValidator;
import com.twilio.twiml.TwiMLException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectControllerTest {

    private HttpServletRequest fakeServletRequest;
    private TwilioRequestValidator mockedRequestValidator;

    @Before
    public void setUp() {
        fakeServletRequest = mock(HttpServletRequest.class);
        mockedRequestValidator = mock(TwilioRequestValidator.class);
    }

    @Test
    public void shouldReturnResponseMessageWhenRequestIsValid() throws TwiMLException {
        ConnectController connectController = new ConnectController(mockedRequestValidator);
        when(mockedRequestValidator.validate(fakeServletRequest)).thenReturn(true);

        ResponseEntity<String> response = connectController.connect("+123", fakeServletRequest);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), containsString(
                "Thanks for contacting our sales department. Our " +
                        "next available representative will take your call."));
        assertThat(response.getHeaders().getContentType(), is(MediaType.APPLICATION_XML));
    }

    @Test
    public void shouldNotGenerateTwiMLWhenRequestIsNotValid() throws TwiMLException {
        when(mockedRequestValidator.validate(fakeServletRequest)).thenReturn(false);
        ConnectController connectController = new ConnectController(mockedRequestValidator);

        ResponseEntity<String> response = connectController.connect("+123", fakeServletRequest);

        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(response.getBody(), containsString("Invalid twilio request"));
    }
}
