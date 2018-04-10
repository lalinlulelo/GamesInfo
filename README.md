# GamesInfo :cat:
:blush::gun:

Indice
=================
- [Fase 1](#fase-1)
  * [Descripcion de la web](#descripcion-de-la-web)
  * [Entidades principales](#entidades-principales)
  * [Servicio Interno](#servicio-interno)
- [Fase 2](#fase-2)
  * [Diagrama de Navegacion](#diagrama-de-navegacion)
      - [Pantalla de inicio sin usuario](#pantalla-de-inicio-sin-usuario)
      - [Pantalla de inicio con usuario](#pantalla-de-inicio-con-usuario)
      - [Pantalla de Registro de Nuevo Usuario](#pantalla-de-registro-de-nuevo-usuario)
      - [Calendario de Eventos](#calendario-de-eventos)
      - [Evento](#evento)
      - [Lista de Juegos](#lista-de-juegos)
      - [Juego](#juego)
      - [Lista de Compañias](#lista-de-compa-ias)
      - [Compañia](#compa-ia)
      - [Perfil](#perfil)
      - [Perfil modificado](#perfil-modificado)
      - [Listas de Juegos](#listas-de-juegos)
      - [Nueva Lista de Juegos](#nueva-lista-de-juegos)
      - [Añadir Juego a una Lista](#a-adir-juego-a-una-lista)
      - [Articulo](#articulo)
    + [Pantalla de administrador](#pantalla-de-administrador)
  * [Diagrama de Entidad-Relacion](#diagrama-de-entidad-relacion)
  * [Diagrama UML de Entidades](#diagrama-uml-de-entidades)
  * [Diagrama de clases](#diagrama-de-clases)
- [Fase 3](#fase-3)
  * [Instrucciones para desplegar la aplicacion](#instrucciones-para-desplegar-la-aplicacion)
    + [1.- Inicializacion de Ubuntu](#1--inicializacion-de-ubuntu)
    + [2.- Elaboracion del jar](#2--elaboracion-del-jar)
    + [3.- Arranque de los jar](#3--arranque-de-los-jar)
    + [4.- Inicio de pagina web](#4--inicio-de-pagina-web)
  * [Servicio interno de correos](#servicio-interno-de-correos)
      - [Pantalla de relleno de datos](#pantalla-de-relleno-de-datos)
      - [Pantalla de recibo de correo](#pantalla-de-recibo-de-correo)
      - [Pantalla de usuario previamente registrado](#pantalla-de-usuario-previamente-registrado)
- [Fase 4](#fase-4)
  * [Instalacion de Vagrant](#instalacion-de-vagrant)
    + [1.- Instalacion de Ubuntu](#1--instalacion-de-ubuntu)
    + [2.- Configuracion del vagrantfile](#2--configuracion-del-vagrantfile)
    + [3.- Inicializacion](#3--inicializacion)
    + [4.- Division de Servicios](#4--division-de-servicios)
      - [4.1.- Maquina Virtual de Base de Datos](#41--maquina-virtual-de-base-de-datos)
      - [4.2.- Maquina Virtual de Servicio Interno](#42--maquina-virtual-de-servicio-interno)
      - [4.3.- Maquina Virtual de Servicio Web](#43--maquina-virtual-de-servicio-web)
  * [Instrucciones para la instalacion de HAProxy para Servicio Web](#instrucciones-para-la-instalacion-de-haproxy-para-servicio-web)
    + [1.- Instalacion PPA](#1--instalacion-ppa)
    + [2.- Actualizacion del sistema](#2--actualizacion-del-sistema)
    + [3.- Instalacion de HAProxy](#3--instalacion-de-haproxy)
    + [4.- Generacion de Certificado SSL](#4--generacion-de-certificado-ssl)
    + [5.- Configuracion de HAProxy](#5--configuracion-de-haproxy)
    + [6.- Inicio de HAProxy](#6--inicio-de-haproxy)
    + [7.- Inicio de HAProxy en Navegador Web](#7--inicio-de-haproxy-en-navegador-web)
  * [Instrucciones para la instalacion de HAProxy para Servicio Interno](#instrucciones-para-la-instalacion-de-haproxy-para-servicio-interno)
    + [1.- Instalacion PPA](#1--instalacion-ppa-1)
    + [2.- Actualizacion del sistema](#2--actualizacion-del-sistema-1)
    + [3.- Instalacion de HAProxy](#3--instalacion-de-haproxy-1)
    + [4.- Configuracion de HAProxy](#4--configuracion-de-haproxy)
    + [5.- Inicio de HAProxy](#5--inicio-de-haproxy)
    + [6.- Inicio de HAProxy en Navegador Web](#6--inicio-de-haproxy-en-navegador-web)
  * [Instrucciones para la instalacion de HAProxy para Bases de datos](#instrucciones-para-la-instalacion-de-haproxy-para-bases-de-datos)
    + [1.- Instalacion PPA](#1--instalacion-ppa-2)
    + [2.- Actualizacion del sistema](#2--actualizacion-del-sistema-2)
    + [3.- Instalacion de HAProxy](#3--instalacion-de-haproxy-2)
    + [4.- Configuracion de HAProxy](#4--configuracion-de-haproxy-1)
    + [5.- Configuracion de mysql](#5--configuracion-de-mysql)
    + [6.- Estructuracion de Servidores de Bases de Datos en Maestro-Esclavo](#6--estructuracion-de-servidores-de-bases-de-datos-en-maestro-esclavo)
      - [6.1.- Servidor Maestro](#61--servidor-maestro)
      - [6.2.- Servidor Esclavo](#62--servidor-esclavo)
    + [7.- Inicio de HAProxy](#7--inicio-de-haproxy)
    + [8.- Inicio de HAProxy en Navegador Web](#8--inicio-de-haproxy-en-navegador-web)
  * [Instalacion e Implementacion de Hazelcast](#instalacion-e-implementacion-de-hazelcast)
    + [1.- Instalacion de Hazelcast](#1--instalacion-de-hazelcast)
    + [2.- Implementacion de Hazelcast](#2--implementacion-de-hazelcast)
  * [Fichero Batch de Arranque](#fichero-batch-de-arranque)
  * [Diagrama de la Infraestructura desplegada](#diagrama-de-la-infraestructura-desplegada)
- [Fase 5](#fase-5)
  * [1.- Creacion de una Maquina Virtual Vagrant](#1--creacion-de-una-maquina-virtual-vagrant)
  * [2.- Configuracion de Ansible](#2--configuracion-de-ansible)
  * [3.- Creacion de la clave privada](#3--creacion-de-la-clave-privada)
  * [4.- Comprobacion de conexion](#4--comprobacion-de-conexion)
  * [5.- Demostracion del Correcto Funcionamiento](#5--demostracion-del-correcto-funcionamiento)
  * [6.- Creacion de Playbooks](#6--creacion-de-playbooks)
    + [6.1 Java](#61-java)
    + [6.2 MySQL](#62-mysql)
    + [6.3 HaProxy](#63-haproxy)
    + [6.4 Playbook](#64-playbook)
- [Integrantes](#integrantes)


# Fase 1 #
## Descripcion de la web ##
Este proyecto está dirigido a usuarios aficionados a los videojuegos. Se trata de una web donde encontrarás información relativa a cualquier juegos como puede ser su trama, la compañía o la puntuación que les ha otorgado los usuarios. Además podrás ojear los eventos de videojuegos que se celebrarán próximamente y hacerte tus propias listas en tu perfil con los juegos que te interesen.
- **Parte pública**: El usuario podrá navegar por las entidades de Juego, Compañía, Evento o Artículo.

- **Parte privada**: Para acceder a esta parte, primero se creará una cuenta del que se recibirá una notificación por el servicio de correo electrónico para confirmar el registro. Una vez iniciada la sesión, el usuario tendrá dispopnible la parte privada para poder puntuar o comentar los juegos/compañías/eventos/artículo, así como ojear o modificar sus listas de juegos. También tendrá el usuario su perfil en el que podrá cambiar de correo electrónico o contraseña.
En cuanto al administrador, podrá crear fichas de juegos, compañías, eventos o artículos.

## Entidades principales ##
-   **Usuario**: Se distinguirá entre usuario registrado, no registrado y administrador. Éste portará un Id, un Correo, un Nombre de usuario, una Contraseña, una Fecha de Nacimiento, una Foto de perfil y su Rol. Cada usuario registrado tendrá sus propias listas de juegos.
-   **Juego**: Portará un Id, Nombre, Género, Fecha de Salida, Descripción, Compañía, Puntuación, Imagen y Url de la web oficial. También contendrá su correspondiente lista de comentarios.
-   **Compañía**: Información de la compañía encargada de desarrollar juegos, ya sea indie o empresa grande. Contiene Id, País, Fecha de Fundación y Descripción. Tiene su propia lista de juegos y comentarios.
-   **Evento**: Contendrá un Id, Fecha, Lugar, Precio, Descripción y una Imagen. Puede contener comentarios realizados por los usuarios.
-   **Comentario**: Cada usuario podrá aportar su opinión sobre cualquier juego, compañía, evento o artículo. Éste portará un Id, Usuario y el propio Texto.
-   **Artículo**: Éste portará un Id, Usuario, Título, Copete, Cuerpo e Imagen. Todos los usuarios registrados podrán comentar en ellas, y los administradores podrán crear artículos.
-   **Lista**: Contiene una serie de juegos al gusto del usuario. Éste portará un Id, Nombre y Lista de juegos.

## Servicio Interno ##
El servicio interno implementado consiste en que nuestra aplicación web se comunique con nuestro servicio de correos interno mediante API REST. Su función es simplemente enviar un correo de bienvenida y confirmación a los nuevos usuarios registrados.

# Fase 2 #
## Diagrama de Navegacion ##
En el siguiente diagrama se puede visualizar la accesibilidad entre las distintas páginas:

![Diagrama de Navegacion](https://github.com/lalinlulelo/GamesInfo/blob/master/images/diagrama%20de%20flujos.png)

En esta fase no se ha diferenciado las partes privada y pública. Sin embargo, el funcionamiento de la parte privada, será la posibilidad de visualizar las listas propias, creadas por el usuario, así como el acceso a su propio perfil para configurarlo o visualizar ciertos detalles de él.

A continuación se dispone de la captura de las distintas pantallas citadas en el diagrama:
#### Pantalla de inicio sin usuario ####
Pantalla principal donde se muestra por medio de un carousel imagénes de los juegos más destacados de la actualidad. Debajo del carousel podremos visualizar los últimos artículos escritos y los juegos más populares de la web. Y en el Navbar están las distintas páginas donde podemos navegar así como los placeholders para hacer login y búsqueda.

![Inicio sin Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home.jpg?raw=true)
![Inicio sin Usuario2](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home2.jpg?raw=true)

#### Pantalla de inicio con usuario ####
En caso de que hayamos introducido correctamente los datos de un usuario existente entonces saldrá el nombre del usuario en el Navbar y podremos desplegarlo para acceder a mi perfil para modificar mis datos personales. También podremos acceder a mi lista de juegos o hacer logout para cerrar sesión.

![Inicio con Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home_loged.jpg?raw=true)

#### Pantalla de Registro de Nuevo Usuario ####
Si quisieramos crear un nuevo usuario simplemente tendríamos que rellenar el formulario para registrar un nuevo usuario dentro de la base de datos.

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
La lista de compañías muestra todas las compañias que han desarollado los juegos expuestos en la página, y gracias al uso de las consultas en repositorio podremos ordenarlos de distintas maneras.

![Lista Compañias](https://github.com/lalinlulelo/GamesInfo/blob/master/images/companies.jpg?raw=true)

#### Compañia ####
La ficha de compañía contiene la información esencial de cada compañia. En caso de que hayamos iniciado sesión también podremos comentar nuestra opinión sobre dicha compañía.

![Compañia](https://github.com/lalinlulelo/GamesInfo/blob/master/images/company.jpg?raw=true)

#### Perfil ####
Aporta la información correspodiente al usuario, siendo su nickname, su contraseña, su cumpleaños y su email. 

![Perfil](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile.jpg?raw=true)

#### Perfil modificado #####
La información del usuario puede ser modificada, pulsando el boton "change" correspondiente al atributo deseado, e insertando la nueva información.

![Perfil modificado](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile_modified.jpg?raw=true)

#### Listas de Juegos ####
Esta sección del usuario muestra las distintas listas de juegos que ha creado personalmente a su interés.

![Listas de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list.jpg?raw=true)

#### Nueva Lista de Juegos ####
Para poder crear una nueva colección de juegos, será necesario crear una nueva lista, insertándole un nombre para identificarla.

![Nueva Lista de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list_modified.jpg?raw=true)

#### Añadir Juego a una Lista ####
Para añadir un juego a una lista, el usuario registrado, deberá dirigirse al juego en sí, seleccionar la opción de añadir a lista, y especificar en que lista guardar el juego.

![Juego en Lista](https://github.com/lalinlulelo/GamesInfo/blob/master/images/game_to_list.jpg?raw=true)

#### Articulo ####
Desde la página principal se puede acceder a uno de los artículos expuestos, ampliando la información de la noticia. Podrá comentar en caso de haber iniciado sesión.

![Articulo](https://github.com/lalinlulelo/GamesInfo/blob/master/images/article.jpg?raw=true)

### Pantalla de administrador ###
Si el administrador inicia sesión en su cuenta, en la sección desplegable de su usuario tiene la opción **Admin Features** donde se le dirige a la pantalla expuesta en la imagen. En esta página mediante un formulario puede añadir un juego, compañía, evento, artículo o eliminar un elemento del tipo de entidad expuesto ya existente.

![admin](https://github.com/lalinlulelo/GamesInfo/blob/master/images/admin.jpg?raw=true)

## Diagrama de Entidad-Relacion ##
En el siguiente diagrama se puede visualizar un modelo de datos donde se representan las entidades de un sistema de información así como sus interrelaciones y propiedades.

![Diagrama de Entidad-Relacion](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaER.png)

## Diagrama UML de Entidades ##
En el siguiente diagrama UML se puede visualizar los distintos atributos de las entidades y cómo están relacionados entre sí.

![Diagrama UML de Entidades](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaUML.png?raw=true)

## Diagrama de clases ##
Mediante ObjectAid UML Diagram,se ha adquirido un esquema inicial, que posteriormente ha sido simplificado y complementado con los templates. Su leyenda sería la siguiente:

![#8faadc](https://github.com/lalinlulelo/GamesInfo/blob/master/images/claseJava.png?raw=true) Clases Java:
* ____Controller: clase de tipo **@Controller**
* ____Configuration: clase de tipo **@Configuration**
* ____Repository: clase de tipo **@Repository**
* UserRepositoryAuthenticationProvider: clase de tipo **@Component**
* Event, Company, Article, Comment, User, MyList & Comment: clase de tipo **@Entity**

![#f4b183](https://github.com/lalinlulelo/GamesInfo/blob/master/images/claseHTML.png?raw=true) Clases HTML
![Diagrama de Clases](https://github.com/lalinlulelo/GamesInfo/blob/master/images/Diagrama%20de%20clases.png?raw=true)

# Fase 3 #

## Instrucciones para desplegar la aplicacion ##
Para poder desplegar la aplicación, es necesaria la instalación previa de una máquina virtual VMWare con el Sistema Operativo **Ubuntu 14.04**
<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/ubuntu.jpg?raw=true">
</p>

### 1.- Inicializacion de Ubuntu ###
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
  
### 2.- Elaboracion del jar ###

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

### 4.- Inicio de pagina web ###

Finalmente nos dirigimos a un navegador web (fuera de la máquina virtual) e insertamos la **dirección IPv4** de la máquina virtual seguido del puerto de conexión. En nuestro caso: `https://192.168.42.131:8443/`

![Pagina Final](https://github.com/lalinlulelo/GamesInfo/blob/master/images/https%20page.jpg?raw=true)

## Servicio interno de correos ##
En esta sección inicializamos tanto el MailService como la propia aplicación web siguiendo los pasos indicados en el apartado anterior. Tras arrancar la página web en el navegador, si uno se dirige a Register nos llevará al html new_user y rellenamos los datos.

#### Pantalla de relleno de datos ####
Se rellenan los datos del usuario que se desea registrar, empleando en este caso un correo electrónico generado por Fake Mail Generator para realizar la demostración.

![Rellenar datos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/register_fill.JPG?raw=true)

#### Pantalla de recibo de correo ####
Tras el correcto registro, se puede observar, que efectivamente se ha creado el usuario y además se ha recibido un correo.

![Recibo correos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/mail_confirm.JPG?raw=true)

#### Pantalla de usuario previamente registrado ####
En caso de que el nombre de usuario o el correo ya existan previamente, te redirigirá de nuevo a la página de nuevo usuario sin completar el registro previo.

![registro previo](https://github.com/lalinlulelo/GamesInfo/blob/master/images/already_exist.JPG?raw=true)
