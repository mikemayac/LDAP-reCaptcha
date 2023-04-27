package com.example.ldaprecaptcha;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;
import java.util.Properties;

public class LDAPTest {

    public static void main(String[] args) {
        String ldapUrl = "ldap://localhost:389";
        String ldapUsername = "cn=Manager,dc=maxcrc,dc=com";
        String ldapPassword = "secret";

        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, ldapUrl);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, ldapUsername);
        environment.put(Context.SECURITY_CREDENTIALS, ldapPassword);

        try {
            InitialDirContext context = new InitialDirContext(environment);
            System.out.println("Conexión exitosa");
            context.close();
        } catch (AuthenticationException ex) {
            System.out.println("Error de autenticación: " + ex.getMessage());
        } catch (NamingException ex) {
            System.out.println("Error al conectar con el servidor LDAP: " + ex.getMessage());
        }

        System.out.println(authUser("mike", "12345"));
    }

    public static boolean authUser(String user, String password){
        try{
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:389");
            env.put(Context.SECURITY_PRINCIPAL, "cn="+user+",ou=Users,o=MyOrganization,dc=maxcrc,dc=com");
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext con = new InitialDirContext(env);
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
            return false;
        }
    }

}
