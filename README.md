# Click to Call Spring
[![Build Status](https://travis-ci.org/TwilioDevEd/clicktocall-spring.svg?branch=master)](https://travis-ci.org/TwilioDevEd/clicktocall-spring)

An example application implementing Click to Call using Twilio.

[Follow the tutorial in our documentation.])(https://www.twilio.com/docs/tutorials/walkthrough/click-to-call/java/spring)

### Run the application

1. Clone the repository and `cd` into it.

1. The application uses Gradle to manage dependencies.

1. Edit the sample configuration file `.env.example` and edit it to match your configuration.

   Once you have edited the `.env.example` file, if you are using a unix operating system,
   just use the `source` command to load the variables into your environment:

   ```bash
   $ source .env.example
   ```

   If you are using a different operating system, make sure that all the
   variables from the .env.example file are loaded into your environment.

   You can find your `TWILIO_ACCOUNT_SID` and `TWILIO_AUTH_TOKEN` under
   your
   [Twilio Account Settings](//www.twilio.com/user/account/settings).
   You can buy Twilio phone numbers at [Twilio numbers](//www.twilio.com/user/account/phone-numbers/search)
   `TWILIO_NUMBER` should be set to the phone number you purchased above.

1. Configure Twilio to call your webhooks

   You will also need to configure Twilio to call your application when calls are received.

   You will need to provision at least one Twilio number with voice capabilities
   so the application's users can trigger phone calls. You can buy a number [right
   here](//www.twilio.com/user/account/phone-numbers/search). Once you have
   a number you need to configure it so that it works with your application. Open
   [the number management page](//www.twilio.com/user/account/phone-numbers/incoming)
   and open the number's configuration by clicking on it.

   Remember that the number where you change the voice webhooks must be the same one you set on
   the `TWILIO_NUMBER` environment variable.

1. Run the application using Gradle. The `--debug` option is helpful to view full
   request and response logs.

   ```bash
   $ ./gradlew bootRun --debug
   ```

   This will run the application on port 8080.

1. Expose the application to the wider Internet using [ngrok](https://ngrok.com/)

   ```bash
   $ ngrok 8080
   ```

   Configure your Twilio's number voice URL to match your ngrok URL. Also, be sure to
   set the Webhook method to `GET` in your Twilio Console.
   It should look something like this:

   ```
   http://<your-ngrok-subdomain>.ngrok.io/connect
   ```

1. Next, open the following url in your browser:
   ```
   http://<your-ngrok-subdomain>.ngrok.io/index.html
   ```

### Dependencies

This application uses this Twilio helper library.

* [twilio-java](//github.com/twilio/twilio-java)

### Run the tests

1. Run at the top-level directory.

   ```bash
   $ ./gradlew test
   ```
