package com;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFReader;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class LdapConfig {

  private InMemoryDirectoryServer inMemoryDirectoryServer;

  @PostConstruct
  public void init() {
    System.out.println("===Starting App===");

    try {
      InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=example,dc=com");
      //config.addAdditionalBindCredentials("cn=Directory Manager", "password");
      config.setListenerConfigs(InMemoryListenerConfig.createLDAPConfig("default", 10389));
      config.setSchema(null);

      inMemoryDirectoryServer = new InMemoryDirectoryServer(config);
      //LDI Reader:
      LDIFReader ldifFilePath = new LDIFReader(this.getClass().getResourceAsStream("/config.ldif"));
      inMemoryDirectoryServer.importFromLDIF(true, ldifFilePath);
      inMemoryDirectoryServer.startListening();
    } catch (LDAPException e) {
      System.out.println("Exception on LDAP");
      e.printStackTrace();
    }
  }

  @PreDestroy
  public void destroy() {
    inMemoryDirectoryServer.shutDown(true);
    System.out.println("==Stoping App==");
  }
}
