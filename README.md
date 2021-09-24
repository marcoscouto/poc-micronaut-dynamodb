# crud micronaut with dynamodb - poc

## How to start

1 - Install [aws-cli](https://aws.amazon.com/pt/cli/)

2 - Configure aws environment with command **aws configure**

2.1 - If you'll use dynamodb local with docker-compose file configure with that credentials:

      AWS_REGION: "sa-east-1"
      AWS_ACCESS_KEY_ID: "fake"
      AWS_SECRET_ACCESS_KEY: "fake"

3 - Start dynamodb local with the command **docker-compose up -d**

3.1 - Don't know docker or docker compose? Access [official docs](https://www.docker.com/)

4 - Build and start the application with command **gradle build && gradle run**

4.1 - Haven't gradle installed? Don't worry, use gradle wrapper with command **./gradlew build && ./gradlew run**

## Endpoints

Get - /users/{identifier}

    Identifier (string)
    Reference for user identifier

Post - /users

    {
        "name": string,
        "age": int
    }

Put - /users

    {
        "identifier": string
        "name": string,
        "age": int
    }

Delete - /users/{identifier}

    Identifier (string)

## References

- https://towardsaws.com/micronaut-application-on-aws-38ee7f029980
- https://dzone.com/articles/async-rest-client-to-dynamodb-using-micronaut-mave
- https://byegor.github.io/2020/04/10/micronaut-dynamodb-async.html
- https://github.com/aaronshaf/dynamodb-admin