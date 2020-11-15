# micronaut-outh2-rest-sample

A micronaut oauth2 REST sample, derived from this [Blog Article](https://piotrminkowski.com/2020/09/21/micronaut-oauth2-and-security-with-keycloak/).

## Running the Micronaut Application
```
./gradlew run
```

## Running Keycloak
```
docker run -d --name keycloak -p 8888:8080 -e KEYCLOAK_USER=micronaut \
    -e KEYCLOAK_PASSWORD=micronaut123 -e KEYCLOAK_IMPORT=/tmp/test-realm.json \
    -v $(pwd)/keycloak/test-realm.json:/tmp/test-realm.json jboss/keycloak
```
### Checks
### Micronaut  Client (port 8887)
```
curl -L -c /tmp/cookie.txt -b /tmp/cookie.txt -v http://localhost:8887/login -H "Content-Type: application/json" \
    -d "{\"username\":\"test_viewer\",\"password\": \"123456\"}"

curl -L -v -c /tmp/cookie.txt -b /tmp/cookie.txt http://localhost:8887/secure/view
```

### Keycloak Server (port 8888)
```
curl --location --request POST \
    'http://localhost:8888/auth/realms/test/protocol/openid-connect/token' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --data-urlencode 'password=1234567' \
    --data-urlencode 'username=test_admin' \
    --data-urlencode 'client_secret=6faf28d5-ca45-4740-8516-38634510b85e' \
    --data-urlencode 'client_id=micronaut' \
    --data-urlencode 'grant_type=password'
```

## Links

[jboss/keycloak](https://hub.docker.com/r/jboss/keycloak/)
[Apps Developer Blog: Keycloak](https://www.appsdeveloperblog.com/category/keycloak/)
[Keycloak: Requesting Token with Password Grant](https://www.appsdeveloperblog.com/keycloak-requesting-token-with-password-grant/)
