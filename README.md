# otel-poc

Take a look for comments like

```
!interesting: these are relevant parts of the otel config
```

## pre-reqs

- install [sdkman](https://sdkman.io/)
- run `sdk env`

## running the jvm-app

`./gradlew jvm-app:bootRun`

## running zipkin

`docker run -d -p 9411:9411 openzipkin/zipkin`
Visit http://localhost:9411/zipkin/


## Config Opentelemetry-collector
`vi otel-collector/otel-local-config.yaml`
## running opentelemetry-collector

```
cd otel-collector
./collector-docker-run.sh
```

### calling the jvm-app

`curl -v 'http://localhost:8080/whats-my-name?name=test'`

## Improvements.
NodeJs Project and Python Project.
Use docker compose to running App project, opentelemetry-collector and zipkin locally.