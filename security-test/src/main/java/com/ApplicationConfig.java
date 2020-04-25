package com;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

//Form based authentication
@FormAuthenticationMechanismDefinition(
	//Login with an index page and a error page.
    loginToContinue = @LoginToContinue(loginPage = "/index.xhtml", errorPage = "/error.xhtml")
)

@LdapIdentityStoreDefinition(
	//URL where the LDAP server can be reached.
    url = "ldap://localhost:10389",
    //Search base for looking up callers (e.g., "ou=caller,dc=jsr375,dc=net"). 
    callerSearchBase = "ou=Users,dc=example,dc=com",
    //Search base for looking up groups (e.g., "ou=group,dc=jsr375,dc=net"). 
    groupSearchBase = "ou=Groups,dc=example,dc=com",
    //Search filter to find groups when groupSearchBase is set. The search is performed starting from the groupSearchBase DN with the scope specified by groupSearchScope.
    groupSearchFilter = "(&(member=%s)(objectClass=groupOfNames))"
)
//Faces Config:
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {

}
