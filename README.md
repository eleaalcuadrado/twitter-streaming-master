Programas necesarios para la ejecución del proyecto
=======================================================================

JDK 8

-Descargar JDK 8 desde el siguiente link:
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

-Instalar el archivo ejecutable.

-Configurar Variables de Entorno:
 ->Dar click derecho en Equipo.
 ->Pulsar Propiedades.
 ->Luego Configuración avanzada del sistema.
 ->Ir a Variables de entorno.
 ->En Variables del sistema pulsar nueva.
 ->Poner como nombre "JAVA_HOME" y en valor de la varible el directorio en donde se instalo JDk8, en este caso será
  "C:\Program Files\Java\jdk1.8.0_111".
 ->Luego editar la variable "path" y agregar lo siguiente "%JAVA_HOME%\bin".
 ->Guardar cambios, dando click a aceptar.

-Para probar la correcta instalación escribir en la consola "javac -version".


=======================================================================

GRADLE 3.0

-Descargar Gradle desde el siguiente link:
https://services.gradle.org/distributions/gradle-3.0-all.zip

-Descomprimir archivo "gradle-3.0-all.zip" en una ubicación a su preferencia (En este caso será copiar la carpeta "gradle-3.0" directamente en el disco c).

-Configurar Variables de Entorno y agregar la carpeta "bin" ubicada en "gradle-3.0". 
 ->Dar click derecho en Equipo.
 ->Pulsar Propiedades.
 ->Luego Configuración avanzada del sistema.
 ->Ir a Variables de entorno.
 ->En Variables del sistema buscar "Path" y pulsar editar.
 ->Agregar la ruta de la carpeta bin, en el caso de este ejemplo "C:\gradle-3.0\bin".
 ->Guardar cambios, dando click a aceptar.

-Para probar la correcta instalación escribir en la consola "gradle -version".


=======================================================================

GLASSFISH 4.1 (Full Platform)

-Descargar GlassFish desde el siguiente link:
http://download.java.net/glassfish/4.1/release/glassfish-4.1.zip

-Descomprimir archivo "glassfish-4.1.zip" en una ubicación a su preferencia (En este caso será copiar la carpeta "glassfish4" directamente en el disco c).
-Configurar Variables de Entorno:
 ->Dar click derecho en Equipo.
 ->Pulsar Propiedades.
 ->Luego Configuración avanzada del sistema.
 ->Ir a Variables de entorno.
 ->En Variables del sistema pulsar nueva.
 ->Poner como nombre "GLASSFISH_HOME" y en valor de la varible el directorio en donde se copió GlasshFish, en este caso será
  "C:\glassfish4".
 ->Luego editar la variable "path" y agregar lo siguiente "%GLASSFISH_HOME%\bin".
 ->Guardar cambios, dando click a aceptar.

-Para probar la correcta instalación escribir en la consola "asadmin start-domain".

-Acceder desde el navegador a la dirección "localhost:4848", lo que mostrará la consola de administración.

=======================================================================

MONGODB 

-Descargar MongoDB desde el siguiente link:
https://www.mongodb.com/download-center?jmp=nav

-Instalar el archivo ejecutable, seleccionando instalación completa cuando se solicite.

-Configurar Variables de Entorno. 
 ->Dar click derecho en Equipo.
 ->Pulsar Propiedades.
 ->Luego Configuración avanzada del sistema.
 ->Ir a Variables de entorno.
 ->En Variables del sistema buscar "Path" y pulsar editar.
 ->Agregar la ruta de instalación de MongoDb, en el caso de este ejemplo "C:\Program Files\MongoDB\Server\3.2\bin".
 ->Guardar cambios, dando click a aceptar.

-Para probar la correcta instalación escribir en la consola "mongod".
