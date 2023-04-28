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

## Cómo ejecutar el proyecto

### Maven

1. Limpia el proyecto con Maven:

mvn clean


2. Compila el proyecto con Maven:

mvn compile


### IntelliJ IDEA

1. Importa el proyecto como un proyecto Maven en IntelliJ IDEA.
2. Asegúrate de que JBoss esté configurado correctamente en IntelliJ IDEA.
3. Selecciona el perfil de JBoss en la configuración de ejecución.
4. Haz clic en el botón de ejecución (el ícono de reproducción verde) para compilar y desplegar el proyecto en JBoss.

### JBoss

Para compilar y desplegar el proyecto en JBoss desde la línea de comandos, sigue estos pasos:

1. Navega al directorio `bin` dentro de la instalación de JBoss.
2. Inicia el servidor JBoss ejecutando el siguiente comando:


### IntelliJ IDEA

1. Importa el proyecto como un proyecto Maven en IntelliJ IDEA.
2. Asegúrate de que JBoss esté configurado correctamente en IntelliJ IDEA.
3. Selecciona el perfil de JBoss en la configuración de ejecución.
4. Haz clic en el botón de ejecución (el ícono de reproducción verde) para compilar y desplegar el proyecto en JBoss.

### JBoss

Para compilar y desplegar el proyecto en JBoss desde la línea de comandos, sigue estos pasos:

1. Navega al directorio `bin` dentro de la instalación de JBoss.
2. Inicia el servidor JBoss ejecutando el siguiente comando:

./standalone.sh (Linux y macOS)
standalone.bat (Windows)


3. Compila y crea el archivo WAR del proyecto utilizando Maven:

mvn clean package


4. Copia el archivo WAR generado en la carpeta `deployments` de JBoss:


### Levantar el servidor LDAP

Para levantar un servidor LDAP local, sigue estos pasos:

1. Descarga e instala un servidor LDAP, como [OpenLDAP](http://www.openldap.org/) o [Apache Directory Server](https://directory.apache.org/apacheds/).
2. Configura el servidor LDAP de acuerdo con la documentación del servidor específico.
3. Crea la estructura de directorios y usuarios según la organización que manejes.
4. Inicia el servidor LDAP siguiendo las instrucciones proporcionadas por la documentación del servidor específico.

Una vez que hayas configurado y levantado el servidor LDAP, asegúrate de actualizar la configuración en el código del proyecto para que coincida con tu configuración de LDAP.


Los puntos generales 3. y 4. no fueron necesarios en mi mi caso porque todo lo hice en local.