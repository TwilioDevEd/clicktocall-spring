<a  href="https://www.twilio.com">
<img  src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg"  alt="Twilio"  width="250"  />
</a>

# Click to Call Spring

![](https://github.com/TwilioDevEd/clicktocall-spring/workflows/Java-Gradle/badge.svg)

> We are currently in the process of updating this sample template. If you are encountering any issues with the sample, please open an issue at [github.com/twilio-labs/code-exchange/issues](https://github.com/twilio-labs/code-exchange/issues) and we'll try to help you.

## About

An example application implementing Click to Call using Twilio.

[Follow the tutorial in our documentation.](https://www.twilio.com/docs/voice/tutorials/click-to-call)

Implementations in other languages:

| .NET | Python | Node | Ruby | PHP |
| :--- | :--- | :----- | :-- | :--- |
| [Done](https://github.com/TwilioDevEd/clicktocall-csharp)  | [Done](https://github.com/TwilioDevEd/clicktocall-flask)  | [Done](https://github.com/TwilioDevEd/clicktocall-node)  | [Done](https://github.com/TwilioDevEd/clicktocall-rails) | [Done](https://github.com/TwilioDevEd/clicktocall-php)  |

<!--
### How it works

**TODO: Describe how it works**
-->

## Set up

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 11 or later.
- [ngrok](https://ngrok.com)
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config&nbsp;Value | Description                                                                                                                                                  |
| :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Account&nbsp;Sid  | Your primary Twilio account identifier - find this [in the Console](https://www.twilio.com/console).                                                         |
| Auth&nbsp;Token   | Used to authenticate - [just like the above, you'll find this here](https://www.twilio.com/console).                                                         |
| Phone&nbsp;number | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming) |

### Local development

After the above requirements have been met:

1. Clone this repository and `cd` into it

    ```bash
    git clone git@github.com:TwilioDevEd/clicktocall-spring.git
    cd clicktocall-spring
    ```

2. Set your environment variables

    ```bash
    cp .env.example .env
    ```
    See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

    If you are using a UNIX operating system, load the environment variables before the application starts.

    ```bash
    source .env
    ```

    _If you are using a different operating system, make sure that all the variables from the `.env` file are loaded into your environment._

3. Run the application

    ```bash
    make serve
    ```
    **NOTE:** If you are using a dedicated Java IDE like Eclipse or IntelliJ, you can start the application within the IDE and it will start in development mode, which means any changes on a source file will be automatically reloaded.

That's it!

### Docker

If you have [Docker](https://www.docker.com/) already installed on your machine, you can use our `docker-compose.yml` to setup your project.

1. Make sure you have the project cloned.
2. Setup the environmental variables in the `docker-compose.yml` file, see the [Twilio Account Settings](#twilio-account-settings).
3. Run `docker-compose --env-file /dev/null up`.
4. Follow the steps in [Configure Twilio](#configure-twilio) section on how to expose your port to Twilio using a tool like [ngrok](https://ngrok.com/) and configure the remaining parts of your application.

### Tests

You can run the tests locally by typing:

```bash
./gradlew test
```

### Local Development

Expose the application to the wider Internet. [We recommend using ngrok to solve this problem](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html). This step is important because the application won't work as expected if you run it through localhost.

To start using `ngrok` in our project you'll have to execute the following line in the _command prompt_.

```
ngrok http 8080 -host-header="localhost:8080"
```

Next, open the following url in your browser:
```
http://<your-ngrok-subdomain>.ngrok.io/
```

### Cloud deployment

Additionally to trying out this application locally, you can deploy it to a variety of host services. Here is a small selection of them.

Please be aware that some of these might charge you for the usage or might make the source code for this application visible to the public. When in doubt research the respective hosting service first.

| Service                           |                                                                                                                                                                                                                           |
| :-------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [Heroku](https://www.heroku.com/) | [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/TwilioDevEd/clicktocall-spring/tree/master)                                                                                                                                       |

**Some notes:** 
- For Heroku, please [check this](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku) to properly configure the project for deployment.
- You can also follow [this guide](https://vaadin.com/blog/how-to-deploy-your-java-app-to-the-cloud) to deploy the application to several other cloud services including Google Cloud, Oracle Cloud, etc.

## Resources

- The CodeExchange repository can be found [here](https://github.com/twilio-labs/code-exchange/).

## Contributing

This template is open source and welcomes contributions. All contributions are subject to our [Code of Conduct](https://github.com/twilio-labs/.github/blob/master/CODE_OF_CONDUCT.md).

## License

[MIT](http://www.opensource.org/licenses/mit-license.html)

## Disclaimer

No warranty expressed or implied. Software is as is.

[twilio]: https://www.twilio.com
