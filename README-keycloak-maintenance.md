# Keycloak Maintenance

## Initial Configuration
1. Open: http://localhost:8888/
1. Go to: *Administration Console*
1. Enter credentials used in *docker* command above:  
   micronaut / micronaut123
1. Choose menu Clients
1. Create a client with Client ID: micronaut  
   (leave the other fields at their defaults)
1. Save
1. Fill in the client details:  
   Access Type: confidential  
   Direct Access Grants Enabled: On
   Valid Redirect URIs: /secure/anonymous
1. Switch to the tab: *Credentials*
1. Copy the client secret value into: resources/application.yml  
   at: micronaut.security.oauth2.clients.keycloak.client-secret
   
## Check configuration
http://localhost:8888/auth/realms/master/.well-known/openid-configuration

Filter openid-connect endpoints using this command:
```
$ curl -s "http://localhost:8888/auth/realms/master/.well-known/openid-configuration" | \
    jq . | grep http | tr -d '", '
authorization_endpoint:http://localhost:8888/auth/realms/master/protocol/openid-connect/auth
check_session_iframe:http://localhost:8888/auth/realms/master/protocol/openid-connect/login-status-iframe.html
end_session_endpoint:http://localhost:8888/auth/realms/master/protocol/openid-connect/logout
introspection_endpoint:http://localhost:8888/auth/realms/master/protocol/openid-connect/token/introspect
issuer:http://localhost:8888/auth/realms/master
jwks_uri:http://localhost:8888/auth/realms/master/protocol/openid-connect/certs
registration_endpoint:http://localhost:8888/auth/realms/master/clients-registrations/openid-connect
token_endpoint:http://localhost:8888/auth/realms/master/protocol/openid-connect/token
userinfo_endpoint:http://localhost:8888/auth/realms/master/protocol/openid-connect/userinfo
```

This application makes use of the following endpoints:
- token_endpoint
- introspection_endpoint
- jwks_uri

## Creating Realm, Users and Roles
1. Add realm: test
1. Go to menu: Roles
1. Add role  
   Role name: admin
   Save
1. Add role  
   Role name: viewer
   Save
1. Go to menu: Users
1. Add user test_viewer
   Assign *viewer* role
   Credentials / Password: 123456
1. Add user test_admin
   Assign *admin* and *viewer* role
   Credentials / Password: 1234567
  
## Exporting the test realm
```
docker exec -it keycloak /opt/jboss/keycloak/bin/standalone.sh \
-Djboss.socket.binding.port-offset=100 -Dkeycloak.migration.action=export \
-Dkeycloak.migration.provider=singleFile \
-Dkeycloak.migration.realmName=test \
-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
-Dkeycloak.migration.file=/tmp/test-realm.json
```
