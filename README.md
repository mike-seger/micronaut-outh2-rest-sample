# micronaut-outh2-rest-sample

A micronaut oauth2 REST sample, derived from this [Blog Article](https://piotrminkowski.com/2020/09/21/micronaut-oauth2-and-security-with-keycloak/).

## Running the Application
```
./gradlew run
```

## Running Keycloak
```
docker run -d --name keycloak -p 8888:8080 -e KEYCLOAK_USER=micronaut \
    -e KEYCLOAK_PASSWORD=micronaut123 -e KEYCLOAK_IMPORT=/tmp/test-realm.json \
    -v $(pwd)/keycloak/test-realm.json:/tmp/test-realm.json jboss/keycloak
```
### Check
```
curl -L -c /tmp/cookie.txt -b /tmp/cookie.txt -v http://localhost:8887/login -H "Content-Type: application/json" \
    -d "{\"username\":\"test_viewer\",\"password\": \"123456\"}"

curl -L -v -c /tmp/cookie.txt -b /tmp/cookie.txt http://localhost:8887/secure/view
```

## Links

[jboss/keycloak](https://hub.docker.com/r/jboss/keycloak/)
