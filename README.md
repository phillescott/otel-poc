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

### calling the jvm-app

`curl -v http://localhost:8080/whats-my-name?name=test`