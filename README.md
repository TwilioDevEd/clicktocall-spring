<a href="https://www.twilio.com">
  <img src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg" alt="Twilio" width="250" />
</a>

# Click to Call Spring

![](https://github.com/TwilioDevEd/clicktocall-spring/workflows/Java-Gradle/badge.svg)

> We are currently in the process of updating this sample template. If you are encountering any issues with the sample, please open an issue at [github.com/twilio-labs/code-exchange/issues](https://github.com/twilio-labs/code-exchange/issues) and we'll try to help you.


An example application implementing Click to Call using Twilio.

[Follow the tutorial in our documentation.](https://www.twilio.com/docs/tutorials/walkthrough/click-to-call/java/spring)

### Run the application

1. Clone the repository and `cd` into it.

1. The application uses Gradle to manage dependencies.

1. Copy the `.env.example` file to `.env`.

1. Edit the sample configuration file `.env` and edit it to match your configuration.

   Once you have edited the `.env` file, if you are using a unix operating system,
   just use the `source` command to load the variables into your environment:

   ```bash
   $ source .env
   ```

   If you are using a different operating system, make sure that all the
   variables from the .env file are loaded into your environment.

   You can find your `TWILIO_ACCOUNT_SID` and `TWILIO_AUTH_TOKEN` under
   your
   [Twilio Account Settings](//www.twilio.com/user/account/settings).
   You can buy Twilio phone numbers at [Twilio numbers](//www.twilio.com/user/account/phone-numbers/search)
   `TWILIO_NUMBER` should be set to the phone number you purchased above.

1. Run the application using Gradle. The `--debug` option is helpful to view full
   request and response logs.

   ```bash
   $ ./gradlew bootRun --debug
   ```

   This will run the application on port 8080.

1. Expose the application to the wider Internet using [ngrok](https://ngrok.com/).
   This step is important because the application won't
   work as expected if you run it through localhost.

   ```bash
   $ ngrok 8080
   ```

1. Next, open the following url in your browser:
   ```
   http://<your-ngrok-subdomain>.ngrok.io/
   ```

### Dependencies

This application uses this Twilio helper library.

* [twilio-java](//github.com/twilio/twilio-java)

### Run the tests

1. Run at the top-level directory.

   ```bash
   $ ./gradlew test
   ```

## Meta

 * No warranty expressed or implied. Software is as is. Diggity.
 * The CodeExchange repository can be found [here](https://github.com/twilio-labs/code-exchange/).
 * [MIT License](http://www.opensource.org/licenses/mit-license.html)
 * Lovingly crafted by Twilio Developer Education.
