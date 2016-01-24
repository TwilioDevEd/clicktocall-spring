package com.twilio.clicktocall;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConnectControllerTest {

    private HttpServletRequest fakeServletRequest;
    private RequestValidator mockedRequestValidator;

    @Before
    public void setUp() {
        fakeServletRequest = mock(HttpServletRequest.class);
        mockedRequestValidator = mock(RequestValidator.class);
    }

    @Test
    @Ignore
    public void shouldReturnTheMessage() {
        ConnectController connectController = new ConnectController(mock(RequestValidator.class));

        ResponseEntity<String> response = connectController.connect(fakeServletRequest);

        assertThat(response.getBody(), containsString("erro"));
    }

    @Test
    public void shouldNotGenerateTwiMLWhenRequestIsNotValid() {
        when(mockedRequestValidator.validate(fakeServletRequest)).thenReturn(false);
        ConnectController connectController = new ConnectController(mockedRequestValidator);

        ResponseEntity<String> result = connectController.connect(fakeServletRequest);

        assertThat(result.getBody(), containsString("Invalid twilio request"));
    }
}
