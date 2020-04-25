package com;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

@FormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(loginPage = "/index.xhtml", errorPage = "/error.xhtml")
)
@LdapIdentityStoreDefinition(
    url = "ldap://localhost:8080",
    callerSearchBase = "ou=Users,dc=example,dc=com",
    groupSearchBase = "ou=Groups,dc=example,dc=com",
    groupSearchFilter = "(&(member=%s)(objectClass=groupOfNames))"
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}
