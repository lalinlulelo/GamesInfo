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
  * [Instrucciones para la instalacion de HAProxy](#instrucciones-para-la-instalacion-de-haproxy)
    + [1.- Instalacion PPA](#1--instalacion-ppa)
    + [2.- Actualizacion del sistema](#2--actualizacion-del-sistema)
    + [3.- Instalacion de HAProxy](#3--instalacion-de-haproxy)
    + [4.- Generacion de Certificado SSL](#4--generacion-de-certificado-ssl)
    + [5.- Configuracion de HAProxy](#5--configuracion-de-haproxy)
    + [6.- Inicio de HAProxy](#6--inicio-de-haproxy)
    + [7.- Inicio de HAProxy en Navegador Web](#7--inicio-de-haproxy-en-navegador-web)
  * [Instalacion e Implementacion de Hazelcast](#instalacion-e-implementacion-de-hazelcast)
    + [1.- Instalacion de Hazelcast](#1--instalacion-de-hazelcast)
    + [2.- Implementacion de Hazelcast](#2--implementacion-de-hazelcast)
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

# Fase 4 #

## Instalacion de Vagrant ##
Para poder duplicar y separar los distintos servicios de la aplicación web (servicio web, servicio interno y bases de datos), fue necesaria la instalación de Vagrant. Para ello fue necesaria la descarga de [Vagrant](https://www.vagrantup.com/downloads.html), así como la descarga e instalación de [Virtual Box](https://www.virtualbox.org/wiki/Downloads), todo ello en el Sistema Operativo Host. Tras la ejecución del ejecutable .msi de la propia página de vagrant, se han de realizar los siguientes pasos:

### 1.- Instalacion de Ubuntu ###
Lo primero a realizar será crear una carpeta con nombre `vagrant` en un directorio escogido (en nuestro caso Documents), donde más adelante se creará el vagrant file. A continuación, nos ponemos a instalar Ubuntu 14.04, para ello ejecutamos el siguiente comando desde una ventana de comandos (`Ejecutar > cmd`):

* `vagrant box add ubuntu/trusty64`

Tras la correcta instalación se ejecuta el siguiente comando:

* `vagrant init ubuntu/trusty64`

Y finalmente, tras completar la iniciación, se realiza:

* `vagrant up`

Creando el vagrantfile, siendo notificado por consola.

### 2.- Configuracion del vagrantfile ###
Para declarar una IP privada, es necesario descomentar la línea del fichero vagrantfile, para ello nos dirigimos a la carpeta `vagrant` anteriormente creada y abrimos el fichero con un editor de texto como `notepad++`:

* `# config.vm.network "private_network", ip: "192.168.33.10”`

Tras guardar el fichero, se puede comprobar su correcto funcionamiento mediante el comando:

* `ping 192.168.33.10`

### 3.- Inicializacion ###
A continuación, nos dispondremos a configurar el Sistema Operativo creado para tener los programas necesarios para la correcta ejecución de los servicios:
* Arrancamos el sistema operativo mediante el siguiente comando

 * `vagrant ssh`

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
  
### 4.- Division de Servicios ###
Para poder realizar la división de servicios en distintas máquinas virtuales, es necesario repetir el anterior proceso tres veces, teniendo una máquina para cada servicio, y en el fichero `vagrantfile` declarando distintas direcciones IP, en nuestro caso se han declarado las siguientes direcciones:

* Servicio Web 1: `192.168.33.10`

* Servicio Interno 1: `192.168.33.11`

* Base de Datos 1: `192.168.33.12`

* Servicio Web 2: `192.168.33.13`

* Servicio Interno 2: `192.168.33.14`

* Base de Datos 2: `192.168.33.15`

* Balanceador: `192.168.33.16`

Una vez creadas las tres máquinas virtuales, es necesario configurar ciertas funcionalidades en las distintas máquinas virtuales.

#### 4.1.- Maquina Virtual de Base de Datos ####
Debido a que se ha separado la base de datos de la aplicación web, se ha de configurarla para que sea accesible mediante su dirección IP privada, y permitiendo al servicio web acceder a ella. Para ello accedemos inicialmente al archivo `my.cnf` localizado en `/vagrant/etc/mysql/`permitiendo cualquier tipo de modificación en el fichero:

* `sudo chmod +rwx my.cnf`

Y accediendo mediante el editor:

* `sudo nano my.cnf`

Cambiamos la línea `bind address` por la IP privada de la propia máquina virtual, en nuestro caso `192.168.33.12`, quedando como en la siguiente imagen:

<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/vagrant%20my_cnf.jpg?raw=true">
</p>

Tras su correcto guardado mediante `Ctrl + X` y asegurando el guardado y sobreescribiedolo, reiniciamos el servicio mediante:

* `sudo service mysql service`

Una vez el servicio se ha reiniciado, accedemos a la consola de **mysql** mediante el siguiente comando:

* mysql -u root -p

Y una vez tenemos como path `mysql>` se dispone a crear el usuario, recomendamos que sea el mismo que se tiene declarado en el fichero properties del proyecto del servicio web, en nuestro caso `root`con contraseña `gugus`:

<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/vagrant%20mysql%20properties.jpg?raw=true">
</p>

Para ello colocamos en la consola el siguiente comando:

* `create user usuario@direccion_ip_privada_maquina_servicio_web identified by 'contraseña'`

En nuestro caso el comando sería el siguiente:

* `create user root@192.168.33.10 identified by 'gugus';`

Tras ello, se crea la base de datos (con el mismo nombre que la primera vez que se hizo) con el comando:

* `create database gamesinfo_db;`

Se le aporta todos los permisos posibles al servicio web, sobre dicha base de datos con los comandos:

* `grant all privileges on gamesinfo_db.* to root@192.168.33.10;`
* `flush privileges;`

Y por si se desea comprobar la correcta creación del usuario, se puede ejecutar el siguiente comando:

* `select user, host, password from mysql.user;`

Saliendo la siguiente información por consola:

<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/vagrant%20mysql%20create%20user.jpg?raw=true">
</p>

Finalmente salimos de la consola de mysql con el siguiente comando:

* `exit`

#### 4.2.- Maquina Virtual de Servicio Interno ####
A diferencia de la máquina de bases de datos, aquí simplemente se tiene que realizar el archivo jar para ello se pueden seguir los pasos indicados en el punto 2 de la Fase 3: [Elaboracion del jar](#2--elaboracion-del-jar).

Una vez creado el fichero `.jar` se copia y se pega en el mismo directorio en el que se encuentra el archivo 'vagrantfile' de esta máquina virtual, y se procede a su ejecución. Para ello nos dirigimos al directorio principal mediante el comando:

* `cd /vagrant`

Y en el ejecutamos el .jar con el comando:

* `java -jar mailService-0.0.1-SNAPSHOT.jar`

#### 4.3.- Maquina Virtual de Servicio Web ####
Lo primero que debemos realizar puesto que la base de datos se encuentra en una máquina virtual distinta a la del servicio web, es cambiar en el fichero properties del proyecto, la línea:

* `spring.jpa.hibernate.ddl-auto=update`

A `none`:

* `spring.jpa.hibernate.ddl-auto=none`

Y construir como en el anterior apartado el debido fichero .jar. Tras su construcción se copia y pega en el mismo directorio que el archvo `vagrantfile` de esta máquina virtual y se procede a su ejecución con el siguiente comando donde se detallan ciertas propiedades de la aplicación (recordemos que el usuario declarado en la base de datos fue `root` y su contraseña `gugus`, que la dirección IP de la máquina virtual de la base de datos fue `192.168.33.12`y que la base de datos creada se llama `gamesinfo_db`):

* `java -jar prueba_servidor-0.0.1-SNAPSHOT.jar --spring.datasource.url="jdbc:mysql://192.168.33.12:3306/gamesinfo_db?verifyServerCertificate=false&useSSL=true" --spring.datasource.username="root" --spring.datasource.password="gugus" --spring.jpa.hibernate.ddl-auto="update"`

## Instrucciones para la instalacion de HAProxy ##

### 1.- Instalacion PPA ###
Debido a que Ubuntu 14.04 no soporta la versión estable de HAProxy (v 1.5), se emplea una PPA (Personal Package Archives) para poder realizar la instalación con `apt-get`:

* `add-apt-repository ppa:vbernat/haproxy-1.5`

### 2.- Actualizacion del sistema ###
El siguiente paso es actualizar el sistema:

* `apt-get update`
* `apt-get dist-upgrade`

### 3.- Instalacion de HAProxy ###
Tras la correcta actualización, se instala HAProxy:

* `apt-get install haproxy`

### 4.- Generacion de Certificado SSL ###
Debido a que nuestros servidores web emplean certificado y por tanto protocolo https, es necesario generar un certificado para que haproxy permita la redirección a dichos servidores. Para ello lo primero que se hará es crear un directorio donde guardar las claves y certificados:

* `sudo mkdir /etc/ssl/xip.io`

A continuación nos dirigimos a dicho directorio creado para ello realizamos esta serie de comandos:

* `cd`
* `cd /vagrant`
* `cd /etc`
* `cd ssl`
* `cd xip.io`

Y creamos el fichero que contiene la clave privada:

* `sudo openssl genrsa -out xip.io.key 1024`

Tras su creación, se crea el primer certificado con el siguiente comando:

* `sudo openssl req -new -key xip.io.key \-out xip.io.csr`

El cual nos mostrará un formulario que se completará como se ve a continuación:
<br>
`> Country Name (2 letter code) [AU]:US`<br>
`> State or Province Name (full name) [Some-State]:Connecticut`<br>
`> Locality Name (eg, city) []:New Haven`<br>
`> Organization Name (eg, company) [Internet Widgits Pty Ltd]:SFH`<br>
`> Organizational Unit Name (eg, section) []:`<br>
`> Common Name (e.g. server FQDN or YOUR name) []:*.xip.io`<br>
`> Email Address []:gamesinfourjc@gmail-com`<br>
`> Please enter the following 'extra' attributes to be sent with your certificate request`<br>
`> A challenge password []:gugus`<br>
`> An optional company name []:URJC`<br>

Tras ello, creamos el segundo certificado:

* `sudo openssl x509 -req -days 365 -in xip.io.csr \-signkey xip.io.key \-out xip.io.crt`

Tras su finalización se podrá comprobar mediante el comando 'dir' que se tienen los siguientes ficheros en el directorio:
  * `xip.io.key`
  * `xip.io.csr`
  * `xip.io.crt`

Finalmente se crea el certificado necesario para haproxy, creado a partir de `xip.io.key`y `xip.io.crt`, mediante el comando:

* `sudo -s cat xip.io.crt xip.io.key \ | sudo tee xip.io.pem`

Completando este último comando, se puede comprobar que además de contener los tres ficheros anteriores, ahora también se contiene a `xip.io.pem`.

En el caso de que se haya perdido un paso, tiene más información en este [enlace](https://serversforhackers.com/c/using-ssl-certificates-with-haproxy) y en este [enlace](https://www.youtube.com/watch?v=PsZ6MOQXRGM).

### 5.- Configuracion de HAProxy ###
Una vez se ha notificado la correcta instalación, nos disponemos a configurar HAProxy. Para ello nos dirigimos a `/etc/haproxy` y allí, se aprueban los permisos del archivo `haproxy.cfg`:

* `chmod +rwx haproxy.cfg`

Y se procede a editarlo:

* `sudo nano haproxy.cfg`

En él se añaden las siguientes líneas:

* Debajo de daemon:
  * `maxconn 3072`
  
* En la sección defaults:
  * `option forwardfor`
  * `option http-server-close`
  
* Y se crea una nueva sección añadiendo:
  * `listen haproxy`<br>
       `bind 0.0.0.0:443 ssl crt /etc/ssl/xip.io/xip.io.pem`<br>
       `mode http`<br>
       `stats enable`<br>
       `stats uri /haproxy?stats`<br>
       `balance roundrobin`<br>
       `option http-server-close`<br>
       `option forwardfor`<br>
       `reqadd X-Forwarded-Proto:\ https`<br>
       `reqadd X-Forwarded-Port:\ 443`<br>
       `option forwardfor if-none`<br>
       `option abortclose`<br>
       `server nombre1 direccionIP:Puerto`<br>
       `server nombre2 direccionIP:Puerto`<br>
       `...`
       
HAProxy ofrece principalmente tres algoritmos de balanceo:
  
  * **Basado en Round Robin**: el balanceador selecciona a los distintos servidores por turnos
    * `balance roundrobin`
  * **Basado en el numero de conexiones**: el balanceador selecciona el servidor con menor número de conexiones, empleando Round Robin en aquellos empatados
    * `balance leastconn`
  * **Basado en la IP origen y/o destino**: el balanceador selecciona el servidor en función del seleccionado inicialmente a un usuario.
    * `balance source`
    
Puesto que en esta aplicación web se realiza el uso de **tokens**, para eviar la pérdida de ellos, se empleará el algoritmo basado en la IP origen y/o destino, sustituyendo por tanto el `roundrobin` por `source`. 

El archivo debería quedar como se observa en la imagen de a continuación:

<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/terminal_haproxy.jpg?raw=true">
</p>

Finalmente se guarda el archivo mediante `Ctrl + X`, afirmando que se está seguro de guardar, y sobreescribiendo el archivo. Y se reinicia el servicio:

* `sudo service haproxy restart`

### 6.- Inicio de HAProxy ###
Tras la notificación del correcto reinicio, se procede a arrancar HAProxy:

* `sudo service haproxy start`

### 7.- Inicio de HAProxy en Navegador Web ###
Una vez el terminal notifica su inicio, ya se puede uno dirigir a un navegador y colocar la dirección local seguida de `/haproxy?stats`  en nuestro caso sería `https://192.168.33.16/haproxy?stats` para poder observar los datos del balanceador:

 ![Arranque de HAProxy Web](https://github.com/lalinlulelo/GamesInfo/blob/master/images/haproxy_web.png?raw=true)
 
## Instalacion e Implementacion de Hazelcast ##
Para evitar perder la sesión durante la caída de un servidor se emplea la replicación de sesión mediante Hazelcast, empleando una caché distribuida entre los servidores presentes en un balanceador:

<p align="center">
  <img src="https://github.com/lalinlulelo/GamesInfo/blob/master/images/Hazelcast%20Explanation.png?raw=true">
</p>

### 1.- Instalacion de Hazelcast ###
Para poder emplear Hazelcast en nuestro proyecto de la aplicación web, es necesario insertar las siguientes dependencias en el fichero `pom.xml`:

  * Jackson Core:<br>
    `<dependency>`<br>
    `    <groupId>com.fasterxml.jackson.core</groupId>`<br>
    `    <artifactId>jackson-databind</artifactId>`<br>
    `    <version>2.5.3</version>`<br>
    `</dependency>`<br>
  * Jackson Datatype:<br>
    `<dependency>`<br>
    `    <groupId>com.fasterxml.jackson.datatype</groupId>`<br>
    `    <artifactId>jackson-datatype-jsr310</artifactId>`<br>
    `    <version>2.5.3</version>`<br>
    `</dependency>`<br>
  * Springframework Session:<br>
    `<dependency>`<br>
    `    <groupId>org.springframework.session</groupId>`<br>
    `    <artifactId>spring-session</artifactId>`<br> 
    `</dependency>`<br>
  * Hazelcast:<br>
    `<dependency>`<br>
    `    <groupId>com.hazelcast</groupId>`<br>
    `    <artifactId>hazelcast</artifactId>`<br>
    `    <version>3.9.3</version>`<br>
    `</dependency>`<br>
  * Hazelcast Spring:<br>
    `<dependency>`<br>
    `    <groupId>com.hazelcast</groupId>`<br>
    `    <artifactId>hazelcast-spring</artifactId>`<br>
    `    <version>${hazelcast.version}</version>`<br>
    `</dependency>`<br>
  * Hazelcast WM<br>
    `<dependency>`<br>
    `    <groupId>com.hazelcast</groupId>`<br>
    `    <artifactId>hazelcast-wm</artifactId>`<br>
    `    <version>${hazelcast.version}</version>`<br>
    `</dependency>`<br>

### 2.- Implementacion de Hazelcast ###
Para hacer funcional Hazelcast en nuestro proyecto, despues de haberlo actualizado mediante `Maven > Maven Update`, nos dirigimos a la clase principal del proyecto e insertamos el siguiente `@Bean`, donde las dos direcciones IP presentes son las de los dos servidores que portarán esta aplicación:
<br>
`@Bean`<br>
`public Config config() {`<br>
`   Config config = new Config();`<br>
`   JoinConfig joinConfig = config.getNetworkConfig().getJoin();`  
`   joinConfig.getMulticastConfig().setEnabled(false);`<br>
`   joinConfig.getTcpIpConfig().addMember( "192.168.33.13" ).addMember( "168.192.33.10" ).setEnabled( true );`<br>
`   return config;`<br>
`}`<br>

# Integrantes
Doble Grado Diseño y Desarrollo de Videojuegos e Ingeniería de Computadores.
-  **Agustín López Arribas**: 
    -   E-mail: a.lopezarri@alumnos.urjc.es
    -   Github: [Agustwin](https://github.com/Agustwin)
        
-  **Zhong Hao Lin Chen**:
    -   E-mail: z.linc@alumnos.urjc.es
    -   Github: [lalinlulelo](https://github.com/lalinlulelo)
        
-  **Guillermo Meléndez Morales**:
    -   E-mail: g.melendezm@alumnos.urjc.es
    -   Github: [guillemelmor](https://github.com/guillemelmor)
