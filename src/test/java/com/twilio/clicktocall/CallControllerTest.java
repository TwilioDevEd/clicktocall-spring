package com.twilio.clicktocall;

import com.twilio.clicktocall.twilio.TwilioLine;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;

public class CallControllerTest {

    private TwilioLine mockedTwilioLine;
    private HttpServletRequest mockedRequest;

    @Before
    public void setUp() throws Exception {
        mockedTwilioLine = mock(TwilioLine.class);
        mockedRequest = mock(HttpServletRequest.class);
        when(mockedRequest.getRequestURL()).thenReturn(new StringBuffer(""));
        when(mockedRequest.getRequestURI()).thenReturn("");
    }

    @Test
    public void shouldInformWhenTelephoneNumberIsNotProvidedWhenParameterIsNull() {
        CallController controller = new CallController(mockedTwilioLine);
        when(mockedRequest.getParameter("phone")).thenReturn(null);

        ResponseEntity<String> result = controller.call(mockedRequest);

        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), containsString("The phone number field can't be empty"));
    }

    @Test
    public void shouldInformWhenTelephoneNumberIsNotProvidedWhenParameterIsEmptyString() {
        CallController controller = new CallController(mockedTwilioLine);
        when(mockedRequest.getParameter("phone")).thenReturn("");

        ResponseEntity<String> result = controller.call(mockedRequest);

        assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), containsString("The phone number field can't be empty"));
    }

    @Test
    public void shouldInformeWhenThePhoneCallIsIncoming(){
        CallController controller = new CallController(mockedTwilioLine);
        when(mockedRequest.getParameter("phone")).thenReturn("12345");

        ResponseEntity<String> result = controller.call(mockedRequest);

        assertThat(result.getStatusCode(), is(HttpStatus.ACCEPTED));
        assertThat(result.getBody(), containsString("Phone call incoming!"));
    }

    @Test
    public void shouldRequestTwilioToCallPhoneNumberProvided() {
        CallController controller = new CallController(mockedTwilioLine);
        when(mockedRequest.getParameter("phone")).thenReturn("123456");
        when(mockedRequest.getRequestURL()).thenReturn(new StringBuffer("http://host/call"));
        when(mockedRequest.getRequestURI()).thenReturn("/call");

        controller.call(mockedRequest);

        verify(mockedTwilioLine, times(1)).call("123456", "http://host/connect");
    }

    @Test
    public void shouldReturnAnErrorMessageWhenItsNotPossibleToCallTwilio() {
        String errorMessagge = "test exception";
        doThrow(new RuntimeException(errorMessagge)).when(mockedTwilioLine).call(anyString(), anyString());
        CallController controller = new CallController(mockedTwilioLine);
        when(mockedRequest.getParameter("phone")).thenReturn("123456");

        ResponseEntity<String> response = controller.call(mockedRequest);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        assertThat(response.getBody(), containsString("Problem while processing request: "+ errorMessagge));
    }

}
