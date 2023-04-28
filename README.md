# LDAP-reCaptcha

## Descripción general del proyecto

Este proyecto es una aplicación web basada en Jakarta EE que utiliza JBoss como servidor de aplicaciones. La aplicación permite a los usuarios autenticarse a través de un sistema LDAP y acceder a las funciones protegidas de la aplicación. Además, se implementa un filtro para asegurar que solo los usuarios autenticados puedan acceder a ciertas páginas.

## Estructura del proyecto

El proyecto sigue la estructura estándar de un proyecto Jakarta EE:

- `src/main/java`: Contiene todas las clases Java, incluidos los controladores, servicios y componentes de la aplicación.
- `src/main/resources`: Contiene archivos de recursos como archivos de configuración y propiedades.
- `src/main/webapp`: Contiene los archivos JSP, imágenes, hojas de estilo y archivos JavaScript de la aplicación.

## Requisitos previos

Para poder ejecutar el proyecto, necesitarás instalar un servidor local de LDAP y crear usuarios según la organización que manejes. De acuerdo con esta organización, deberás añadir la información correspondiente en el código para poder acceder correctamente y validar a los usuarios.
