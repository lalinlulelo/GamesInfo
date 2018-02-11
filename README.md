# GamesInfo

# Fase 1 #
## Descripción de la web ##
Este proyecto está dirigido a usuarios aficionados a los videojuegos. Será una web donde encontrarás información acerca de videojuegos como la trama, la compañía, la puntuación pública y profesional. Además podrás ojear los eventos que se celebrarán próximamente y hacerte listas en tu perfil con los juegos o compañías que te interesen.
- **Parte pública**: el usuario podrá navegar por las distintas entidades descritas más adelante.

- **Parte privada**: el usuario deberá iniciar sesión para poder puntuar, comentar los juegos u ojear sus listas. Gracias al servicio de correo electrónico estará al tanto de su juego, evento o compañía favorita.

## Entidades principales ##
-   **Usuario**: se distinguirá entre usuario registrado, no registrado y administrador. Éste portará un Id, un Correo, un nombre de usuario, una Contraseña y otros datos menos relevantes.
-   **Juego**: Portará un Id, el Nombre, Género, Fecha de Salida, Descripción, Id compañía, Puntuación, lista de Comentarios y otra de eventos a los que participa.
-   **Compañía**: Información de la compañía encargada de desarrollar el juego ya sea indie o empresa grande. Contiene Id, País, Fecha de Fundación y descripción. También tiene una lista de juegos y otra de eventos.
-   **Comentario**: cada usuario podrá aportar su opinión sobre cualquier juego. Este portará un Id, Usuario, el texto y el juego.
-   **Evento**: Relación N:M con juego y compañía. Contendrá un Id, Fecha, Lugar, Precio, Descripcion y las listas de juegos y compañías que están presentes.

## Servicio Interno ##
En todo momento el usuario estará al tanto de información nueva sobre cada juego, así como al tanto de los movimientos de una determinada compañía por medio del correo electrónico.

# Fase 2 #
## Diagrama de Navegación ##
En el siguiente diagrama se puede visualizar desde qué paginas se puede navegar hasta cierta página:

![Diagrama de Navegacion](https://github.com/lalinlulelo/GamesInfo/blob/master/images/diagrama%20de%20flujos.png)

En esta fase no se han diferenciado las partes privada y pública. Sin embargo, el funcionamiento de la parte privada, será la posibilidad de visualizar las listas propias, creadas por el usuario, así como el acceso a su propio perfil para configurarlo o visualizar cierto detalles de él.

A continuación se dispone de la captura de las distintas pantallas citadas en el diagrama:
#### Pantalla de inicio sin usuario ####
![Inicio sin Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home.jpg?raw=true)

#### Pantalla de inicio con usuario ####
![Inicio con Usuario](https://github.com/lalinlulelo/GamesInfo/blob/master/images/home_loged.jpg?raw=true)

## Diagrama de Entidad-Relación ##
En el siguiente diagrama se puede visualizar un modelo de datos donde se representan las entidades de un sistema de información así como sus interrelaciones y propiedades.

![Diagrama de Entidad-Relación](https://github.com/lalinlulelo/GamesInfo/blob/master/images/ERDia.png)

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
