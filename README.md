# Demo Sor

### Deploying

Build native image locally with:
```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.profile=prod
```

Deploy local native image to flyio:
```shell
flyctl deploy
```