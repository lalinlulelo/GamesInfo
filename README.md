# GamesInfo


Indice
=================
<!--ts-->
   * [Fase 1](#Fase 1)
      * [Descripción de la web](#Descripción de la web)
      * [Entidades principales](#Entidades principales)
      * [Servicio Interno](#Servicio Interno)
   * [Fase 2](#Fase 2)
      * [Diagrama de Navegación](#Diagrama de Navegación)
          * [Pantalla de inicio sin usuario](#Pantalla de inicio sin usuario)
          * [Pantalla de inicio con usuario](#Pantalla de inicio con usuario)
          * [Pantalla de Registro de Nuevo Usuario](#Pantalla de Registro de Nuevo Usuario)
          * [Calendario de Eventos](#Calendario de Eventos)
          * [Evento](#Evento)
          * [Lista de Juegos](#Lista de Juegos)
          * [Juego](#Juego)
          * [Lista de Compañias](#Lista de Compañias)
          * [Compañia](#Compañia)
          * [Perfil](#Perfil)
          * [Perfil modificado](#Perfil modificado)
          * [Listas de Juegos](#Listas de Juegos)
          * [Nueva Lista de Juegos](#Nueva Lista de Juegos)
          * [Añadir Juego a una Lista](#Añadir Juego a una Lista)
          * [Articulo](#Articulo)
      * [Diagrama de Entidad-Relación](#Diagrama de Entidad-Relación)
      * [Diagrama UML de Entidades](#Diagrama UML de Entidades)
      * [Diagrama de clases](#Diagrama de clases)
  * [Fase 3](#Fase 3)
      * [Instrucciones para desplegar la aplicación](#Instrucciones para desplegar la aplicación)
          * [1.- Inicialización de Ubuntu](#1.- Inicialización de Ubuntu)
          * [2.- Elaboración del jar](#2.- Elaboración del jar)
          * [3.- Arranque de los jar](#3.- Arranque de los jar)
          * [4.- Inicio de página web](#4.- Inicio de página web)
  * [Integrantes](#Integrantes)
<!--te-->
 
 
 
# Fase 1 #
## Descripción de la web ##
Este proyecto está dirigido a usuarios aficionados a los videojuegos. Será una web donde encontrarás información acerca de videojuegos como la trama, la compañía, la puntuación pública y profesional. Además podrás ojear los eventos que se celebrarán próximamente y hacerte listas en tu perfil con los juegos o compañías que te interesen.
- **Parte pública**: el usuario podrá navegar por las distintas entidades descritas más adelante.

- **Parte privada**: el usuario deberá iniciar sesión para poder puntuar, comentar los juegos u ojear sus listas. Gracias al servicio de correo electrónico estará al tanto de su juego, evento o compañía favorita.

## Entidades principales ##
-   **Usuario**: se distinguirá entre usuario registrado, no registrado y administrador. Éste portará un Id, un Correo, un nombre de usuario, una Contraseña y su fecha de nacimiento. Cada usuario registrado tendrá su propia lista de juegos.
-   **Juego**: Portará un Id, Nombre, Género, Fecha de Salida, Descripción, Compañía, Puntuación, Imagen y url de la web oficial. También contendrá su correspondiente lista de Comentarios y otra de eventos a los que participa.
-   **Compañía**: Información de la compañía encargada de desarrollar un juego, ya sea indie o empresa grande. Contiene Id, País, Fecha de Fundación y descripción. También tiene una lista de juegos y otra de eventos a los que participa.
-   **Evento**: Contendrá un Id, Fecha, Lugar, Precio y Descripción
-   **Comentario**: cada usuario podrá aportar su opinión sobre cualquier juego, compañía o evento. Este portará un Id, Usuario y el propio texto.
-   **Artículo**: cada usuario podrá escribir artículos. Este portará un Id, Usuario, Título, Cuerpo e Imagen.
-   **Lista**: contiene una serie de juegos al gusto del usuario. Este portará un Id, Nombre y lista de juegos.

## Servicio Interno ##
En todo momento el usuario estará al tanto de información nueva sobre cada juego, así como al tanto de los movimientos de una determinada compañía por medio del correo electrónico.

# Fase 2 #
## Diagrama de Navegación ##
En el siguiente diagrama se puede visualizar desde qué paginas se puede navegar hasta cierta página:

![Diagrama de Navegacion](https://github.com/lalinlulelo/GamesInfo/blob/master/images/diagrama%20de%20flujos.png)

En esta fase no se han diferenciado las partes privada y pública. Sin embargo, el funcionamiento de la parte privada, será la posibilidad de visualizar las listas propias, creadas por el usuario, así como el acceso a su propio perfil para configurarlo o visualizar cierto detalles de él.

A continuación se dispone de la captura de las distintas pantallas citadas en el diagrama:
#### Pantalla de inicio sin usuario ####
Pantalla principal donde se muestra por medio de un carousel imagénes de los juegos más destacados de la actualidad. Debajo del carousel podremos visualizar los últimos artículos escritos. Y en el Navbar están las distintas páginas donde podemos navegar así como los placeholders para hacer login y búsqueda

![Inicio sin Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home.jpg?raw=true)

#### Pantalla de inicio con usuario ####
En caso de que hayamos introducido correctamente los datos de un usuario existente entonces saldrá el nombre del usuario en el Navbar y podremos desplegarlo para acceder a mi perfil para modificar mis datos personales. También podremos acceder a mi lista de juegos o hacer logout para cerrar sesión.

![Inicio con Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home_loged.jpg?raw=true)

#### Pantalla de Registro de Nuevo Usuario ####
Si quisieramos crear un nuevo usuario simplemente tendriamos que rellenar el formulario para registrar un nuevo usuario dentro de la base de datos.

![Nuevo Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/register.jpg?raw=true)

#### Calendario de Eventos ####
Para los eventos de videojuegos hemos implementado un calendario del que nos marcará todos los eventos disponibles en la base de datos.

![Calendario Eventos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/event_calendar.jpg?raw=true)

#### Evento ####
La ficha de evento donde contiene la información esencial de cada evento. En caso de que hayamos iniciado sesión también podremos comentar nuestra opinión sobre dicho evento.

![Evento](https://github.com/lalinlulelo/GamesInfo/blob/master/images/event.jpg?raw=true)

#### Lista de Juegos ####
La lista de juegos muestra todos los juegos registrados y gracias al uso de las consultas en repositorio podremos ordenarlos de distintas maneras.

![Lista Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/games.jpg?raw=true)

#### Juego ####
La ficha de juego contiene la información esencial de cada juego. En caso de que hayamos iniciado sesión también podremos comentar nuestra opinión sobre dicho juego o agregarlo a nuestra propia lista.

![Juego](https://github.com/lalinlulelo/GamesInfo/blob/master/images/game.jpg?raw=true)

#### Lista de Compañias ####
La lista de compañias muestra todas las compañias que han desarollado los juegos expuestos en la página, y gracias al uso de las consultas en repositorio podremos ordenarlos de distintas maneras.

![Lista Compañias](https://github.com/lalinlulelo/GamesInfo/blob/master/images/companies.jpg?raw=true)

#### Compañia ####
La ficha de compañia contiene la información esencial de cada compañia. En caso de que hayamos iniciado sesión también podremos comentar nuestra opinión sobre dicha compañia.

![Compañia](https://github.com/lalinlulelo/GamesInfo/blob/master/images/company.jpg?raw=true)

#### Perfil ####
Aporta la información correspodiente al usuario, siendo su nickname, su contraseña, su cumpleaños y su email. 

![Perfil](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile.jpg?raw=true)

#### Perfil modificado #####
La información del usuario puede ser modificada, pulsando el boton "change" correspondiente al atributo deseado, e insertando la nueva información.

![Perfil modificado](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile_modified.jpg?raw=true)

#### Listas de Juegos ####
Esta seccion del usuario muestra las distintas listas de juego que ha creado el personalmente y por interés.

![Listas de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list.jpg?raw=true)

#### Nueva Lista de Juegos ####
Para poder crear una nueva coleccion de juegos, será necesario crear una nueva lista, insertándole un nombre para identificarla.

![Nueva Lista de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list_modified.jpg?raw=true)

#### Añadir Juego a una Lista ####
para añadir un juego a una lista, el usuario registrado, deberá dirigirse al juego en sí, seleccionar la opción de añadir a lista, y especificar en que lista guardar el juego.

![Juego en Lista](https://github.com/lalinlulelo/GamesInfo/blob/master/images/game_to_list.jpg?raw=true)

#### Articulo ####
Desde la página principal se puede acceder a uno de los articulos expuestos, ampliando la información de la noticia.

![Articulo](https://github.com/lalinlulelo/GamesInfo/blob/master/images/article.jpg?raw=true)

## Diagrama de Entidad-Relación ##
En el siguiente diagrama se puede visualizar un modelo de datos donde se representan las entidades de un sistema de información así como sus interrelaciones y propiedades.

![Diagrama de Entidad-Relación](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaER.png)

## Diagrama UML de Entidades ##
En el siguiente diagrama UML se puede visualizar los distintos atributos de las entidades y como estan relacionados entre sí.

![Diagrama UML de Entidades](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaUML.png?raw=true)

## Diagrama de clases ##
Mediante ObjectAid UML Diagram, se ha desarrollado un diagrama de las clases del proyecto.

![Diagrama de Clases](https://github.com/lalinlulelo/GamesInfo/blob/master/images/Diagrama%20de%20clases.png?raw=true)

# Fase 3 #

## Instrucciones para desplegar la aplicación ##
Para poder desplegar la aplicación, es necesaria la instalación previa de una máquina virtual VMWare con el Sistema Operativo **Ubuntu 14.04**
<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/ubuntu.jpg?raw=true">
</p>

### 1.- Inicialización de Ubuntu ###
Tras su instalación, se le ha de añadir mediante consola
* Java 

  * `sudo apt-get update`
  * `sudo apt-get install default-jre`
  * `sudo apt-get install default-jdk`
  
* mySQL

  * `sudo apt-get update`
  * `sudo apt-get install mysql-server mysql-client`
  * como contraseña se le colocará: `gugus`
  * `sudo mysql_secure_installation`
  * Tras su correcta instalación, se instalará mySQL Workbench
  * `sudo apt-get install mysql-workbench`
  
* Base de Datos

  * se inicia mySQLWorkbench y se crea una nueva conexión con nombre 'gamesinfo_db'
  
  ![mySQLWorkbench_new_conection](https://snag.gy/P7FuKm.jpg)
  
   <p align="center">
    <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/bd.jpg?raw=true">
  </p>
  
  * se crea un nuevo schema con nombre 'gamesinfo_db' aplicando los cambios
  
  ![mySQLWorkbench_new_schema](https://github.com/lalinlulelo/GamesInfo/blob/master/images/new_schema.jpg?raw=true)
  
  ![mySQLWorkbench_schema name](https://github.com/lalinlulelo/GamesInfo/blob/master/images/schema_name.jpg?raw=true)
  
### 2.- Elaboración del jar ###

Para crear los ficheros jar, se ha de iniciar Spring Tool Suite abriendo los proyectos determinados. En él, nos dirigimos en cada proyecto a `Run As > Maven built...` apareciendo la siguiente ventana:

 <p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/maven_built.jpg?raw=true">
</p>

En dicha ventana, en la seccion **Goals** se ha escribir: `clear package` y acto seguido, pulsar en run. Tras la notificación por consola, de la correcta compilación y construcción del 'jar', se puede recoger dentro de la carpeta **target** (situada en la propia carpeta del proyecto):

 <p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/target.jpg?raw=true">
</p>

### 3.- Arranque de los jar ###

Los ficheros jar se pasan a la máquina virtual, mediante un dispositivo de almacenamiento USB, especificando a la máquina virtual (tras introducir los ficheros en el dispositivo) la detección del dispositivo en el entorno: `VM > Removable Devices > USB > Connect (Disconnect from Host)`.

Acto seguido, vía terminales, se arrancan el servicio de mensajería y la propia aplicación mediante el comando `java -jar fichero.jar`:

 ![Arranque de Jars](https://github.com/lalinlulelo/GamesInfo/blob/master/images/arrancar_jar.jpg?raw=true)
 
Tras comprobar el correcto inicio de ambas aplicaciones, nos dirigimos al icono de internet de la barra de tareas, y mediante click derecho a él, seleccionamos `Connection Information`, apareciendo la siguiente ventana:

 <p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/connection%20info.jpg?raw=true">
</p>

### 4.- Inicio de página web ###

Finalmente nos dirigimos a un navegador web (fuera de la máquina virtual) e insertamos la **dirección IPv4** de la maquina virtual seguido del puerto de conexión. En nuestro caso: `https://192.168.42.131:8443/`

![Pagina FInal](https://github.com/lalinlulelo/GamesInfo/blob/master/images/https%20page.jpg?raw=true)

# Integrantes
Doble Grado Diseño y Desarrollo de Videojuegos e Ingeniería de Computadores.
-  **Agustín López Arribas**: 
    -   E-mail: a.lopezarri@alumnos.urjc.es
    -   Github: Agustwin
        
-  **Zhong Hao Lin Chen**:
    -   E-mail: z.linc@alumnos.urjc.es
    -   Github: lalinlulelo
        
-  **Guillermo Meléndez Morales**:
    -   E-mail: g.melendezm@alumnos.urjc.es
    -   Github: guillemelmor
