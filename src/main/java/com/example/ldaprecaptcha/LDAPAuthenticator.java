package com.example.ldaprecaptcha;

import javax.naming.*;
import javax.naming.directory.*;
import javax.naming.ldap.*;
import java.util.Hashtable;

/**
 * LDAPAuthenticator es una clase que permite autenticar a un usuario y validar su contraseña
 * contra un servidor LDAP.
 */
public class LDAPAuthenticator {
    private String ldapUrl;
    private String baseDn;

    /**
     * Constructor de LDAPAuthenticator.
     *
     * @param ldapUrl La URL del servidor LDAP, incluyendo el protocolo y el puerto (por ejemplo, "ldap://ldap.example.com:389").
     * @param baseDn  La base DN (Distinguished Name) donde se buscarán los usuarios en el servidor LDAP (por ejemplo, "ou=users,dc=example,dc=com").
     */
    public LDAPAuthenticator(String ldapUrl, String baseDn) {
        this.ldapUrl = ldapUrl;
        this.baseDn = baseDn;
    }

    /**
     * Autentica a un usuario y valida su contraseña contra el servidor LDAP.
     *
     * @param username El nombre de usuario a autenticar.
     * @param password La contraseña del usuario a validar.
     * @return True si la autenticación es exitosa y la contraseña es válida, false en caso contrario.
     */
    public boolean authenticate(String username, String password) {
        try {
            // Conectar al servidor LDAP
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapUrl);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            // Credenciales de conexión, si es necesario
            // env.put(Context.SECURITY_PRINCIPAL, "<Bind DN>");
            // env.put(Context.SECURITY_CREDENTIALS, "<Bind Password>");

            DirContext ctx = new InitialDirContext(env);

            // Buscar el DN del usuario
            String searchFilter = "(&(uid=" + username + "))";
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> searchResults = ctx.search(baseDn, searchFilter, searchControls);

            if (searchResults.hasMore()) {
                SearchResult searchResult = searchResults.next();
                String userDn = searchResult.getNameInNamespace();

                // Validar la contraseña
                env.put(Context.SECURITY_PRINCIPAL, userDn);
                env.put(Context.SECURITY_CREDENTIALS, password);

                new InitialDirContext(env); // Intentar establecer una conexión con las credenciales del usuario
                return true;
            } else {
                return false; // El usuario no se encontró en el servidor LDAP
            }
        } catch (AuthenticationException e) {
            return false; // La contraseña no es válida
        } catch (NamingException e) {
            // Manejar errores de conexión u otros errores de LDAP
            // ...
            throw new RuntimeException("Error en la conexión LDAP o en la consulta", e);
        }
    }
}
