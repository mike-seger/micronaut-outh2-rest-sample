# micronaut-outh2-rest-sample

A micronaut oauth2 REST sample, derived from this [Blog Article](https://piotrminkowski.com/2020/09/21/micronaut-oauth2-and-security-with-keycloak/).

## Running Keycloak
```
docker run -d --name keycloak -p 8888:8080 -e KEYCLOAK_USER=micronaut \
    -e KEYCLOAK_PASSWORD=micronaut123 -e KEYCLOAK_IMPORT=/tmp/test-realm.json \
    -v $(pwd)/keycloak/test-realm.json:/tmp/test-realm.json jboss/keycloak
```

## Links

[jboss/keycloak](https://hub.docker.com/r/jboss/keycloak/)
