# Keycloak Maintenance

## Initial Configuration

This configuration is already contained within test-realm.json.
This section describes how it has been produced.

### Create Realm and Client
1. Open: http://localhost:8888/
1. Go to: *Administration Console*
1. Create a new realm *test*
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
   
### Check configuration
http://localhost:8888/auth/realms/master/.well-known/openid-configuration

List openid-connect endpoints using this command:
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

### Creating Realm, Users and Roles
1. Add realm: test
1. Go to menu: Roles
1. Add role  
   Role name: viewer
   Save
1. Go to menu: Users
1. Add user test_viewer
   Set names and e-mail
   Assign *viewer* role
   Credentials / Password: 123456
1. Add user test_admin
   Set names and e-mail
   Assign *admin* and *viewer* role
   Credentials / Password: 1234567
 
## Maintenance

### Exporting the test realm
Manual changes in http://localhost:8888/ can be exported in order to be tracked by GIT and re-used when rebuilding the docker container.

The command
```
keycloak/export-realm.sh
```
will update keycloak/test-realm.json.
