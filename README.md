# GamesInfo

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
La ficha de juego donde contiene la información esencial de cada juego. En caso de que hayamos iniciado sesión también podremos comentar nuestra opinión sobre dicho juego o agregarlo a nuestra propia lista.
![Juego](https://github.com/lalinlulelo/GamesInfo/blob/master/images/game.jpg?raw=true)

#### Lista de Compañias ####
![Lista Compañias](https://github.com/lalinlulelo/GamesInfo/blob/master/images/companies.jpg?raw=true)

#### Compañia ####
![Compañia](https://github.com/lalinlulelo/GamesInfo/blob/master/images/company.jpg?raw=true)

#### Perfil ####
![Perfil](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile.jpg?raw=true)

#### Perfil modificado #####
![Perfil modificado](https://github.com/lalinlulelo/GamesInfo/blob/master/images/profile_modified.jpg?raw=true)

#### Listas de Juegos ####
![Listas de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list.jpg?raw=true)

#### Nueva Lista de Juegos ####
![Nueva Lista de Juegos](https://github.com/lalinlulelo/GamesInfo/blob/master/images/my_list_modified.jpg?raw=true)

#### Añadir Juego a una Lista ####
![Juego en Lista](https://github.com/lalinlulelo/GamesInfo/blob/master/images/game_to_list.jpg?raw=true)

## Diagrama de Entidad-Relación ##
En el siguiente diagrama se puede visualizar un modelo de datos donde se representan las entidades de un sistema de información así como sus interrelaciones y propiedades.

![Diagrama de Entidad-Relación](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaER.png)


## Diagrama UML de Entidades ##
![Diagrama UML de Entidades](https://github.com/lalinlulelo/GamesInfo/blob/master/images/DiagramaUML.png?raw=true)
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
