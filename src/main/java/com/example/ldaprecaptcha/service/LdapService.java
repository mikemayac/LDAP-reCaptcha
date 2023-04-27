//package com.example.ldaprecaptcha.service;
//
//import javax.naming.*;
//import javax.naming.directory.*;
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//
//@Stateless
//public class LdapService {
//    @Resource(lookup = "java:/ldap/myLdapConnection")
//    private DirContext ldapContext;
//
//    public boolean authenticate(String username, String password) {
//        try {
//            // Buscar el DN del usuario
//            String searchFilter = "(&(uid=" + username + "))";
//            SearchControls searchControls = new SearchControls();
//            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//            NamingEnumeration<SearchResult> searchResults = ldapContext.search("<your-base-dn>", searchFilter, searchControls);
//
//            if (searchResults.hasMore()) {
//                SearchResult searchResult = searchResults.next();
//                String userDn = searchResult.getNameInNamespace();
//
//                // Validar la contraseña
//                Hashtable<String, Object> env = new Hashtable<>(ldapContext.getEnvironment());
//                env.put(Context.SECURITY_PRINCIPAL, userDn);
//                env.put(Context.SECURITY_CREDENTIALS, password);
//
//                new InitialDirContext(env); // Intentar establecer una conexión con las credenciales del usuario
//                return true;
//            } else {
//                return false; // El usuario no se encontró en el servidor LDAP
//            }
//        } catch (AuthenticationException e) {
//            return false; // La contraseña no es válida
//        } catch (NamingException e) {
//            // Manejar errores de conexión u otros errores de LDAP
//            // ...
//            throw new RuntimeException("Error en la conexión LDAP o en la consulta", e);
//        }
//    }
//
//    // Agrega aquí otros métodos para el registro de usuarios y otras operaciones relacionadas con LDAP si es necesario
//}
