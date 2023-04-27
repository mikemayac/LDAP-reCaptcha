package com.example.ldaprecaptcha;

public class AuthenticationService {
    private LDAPAuthenticator ldapAuthenticator;

    public AuthenticationService() {
        String ldapUrl = Config.getProperty("ldap.url");
        String baseDn = Config.getProperty("ldap.baseDn");
        this.ldapAuthenticator = new LDAPAuthenticator(ldapUrl, baseDn);
    }

    public boolean authenticate(String username, String password) {
        return ldapAuthenticator.authenticate(username, password);
    }
}