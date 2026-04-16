# WeatherApi Project

This is a weather API I created that returns weather data for a given city.
The Api was put onto a Digital Ocean droplet using Docker and could be curled remotely:

![working curl to remote server](working%20curl.png)

## Built With:

- Spring boot Framework
- Java
- Gradle
- Docker
- Yaml
- Nginx

## What I learned:

I learned more about Spring's Dependency Injection (DI) and more Spring Annotations.
Learning how to use Docker and push it to Docker Hub was a bit of a struggle, but it was cool
in the end watching my curl actually work! I also learned how to use Nginx and Bucket4j to implement
rate limiting and reverse proxying.

## API Source

This project uses the Weatherapi.com to get weather data.

[Weatherapi](https://www.weatherapi.com/)
