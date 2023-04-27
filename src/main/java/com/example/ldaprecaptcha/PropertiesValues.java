package com.example.ldaprecaptcha;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PropertiesValues {

    @Resource(lookup = "java:comp/env/ldap.context.factory")
    private String ldapContextFactory;

    @Resource(lookup = "java:comp/env/ldap.url")
    private String ldapUrl;

    public String getLdapContextFactory() {
        return ldapContextFactory;
    }

    public String getLdapUrl() {
        return ldapUrl;
    }
}
