# Application d'Utilisateurs Aléatoires

## Description

L'application d'Utilisateurs Aléatoires est une application Spring Boot qui récupère et affiche des informations
aléatoires sur les utilisateurs en utilisant l'API [https://randomuser.me/](https://randomuser.me/). L'application
récupère les données des utilisateurs et les affiche dans un format convivial.

## Fonctionnalités

- Récupérer une liste d'utilisateurs aléatoires.
- Option de trier les utilisateurs en fonction de critères spécifiques comme le nom et la date de naissance.
- Affichage de 10 utilisateurs.

## Technologies Utilisées

- Java 21
- Spring Boot 3.3.0
- Thymeleaf (pour le templating)

## Prérequis

- [SDK Java 21](https://www.oracle.com/in/java/technologies/downloads/#jdk21-windows)
- [Maven](https://maven.apache.org/download.cgi)

## Installation

1. Cloner le projet :
   ```bash
   git clone https://github.com/Deathwong/randomUser
   cd randomUser
   ```
2. Construire le projet avec Maven :
   ```bash
   mvn clean install
   ```
3. Exécuter l'application :
   ```bash
   mvn spring-boot:run
   ```
4. Ouvrir votre navigateur et accéder à `http://localhost:8080`.
5. Utiliser votre IDE préféré pour ouvrir l'application.

### Points de Terminaison

- **GET /users** - Récupérer et afficher une liste d'utilisateurs (20).
    - Paramètres :
        - `results` (optionnel, par défaut : 20) : Nombre d'utilisateurs à récupérer.
        - `sortBy` (optionnel) : Trier les utilisateurs par nom (name) et par date de naissance (dob).

## Exemple d'Utilisation

- Pour voir la liste par défaut de 20 utilisateurs, ouvrez `http://localhost:8080/users`.
- Pour voir une liste de 10 utilisateurs, ouvrez `http://localhost:8080/users?results=10`.
- Pour voir une liste d'utilisateurs triée par leur nom, ouvrez `http://localhost:8080/users?sortBy=name`.

## Structure du Projet

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── jefrido
│   │           └── randomuser
│   │               ├── configuration
│   │               │   └── Configuration.java
│   │               ├── controller
│   │               │   └── UserController.java
│   │               ├── domain
│   │               │   ├── ApiResponse.java
│   │               │   ├── Dob.java
│   │               │   ├── Location.java
│   │               │   ├── Login.java
│   │               │   ├── Name.java
│   │               │   ├── Picture.java
│   │               │   └── User.java
│   │               ├── service
│   │               │   ├── UserService.java
│   │               │   └── UserServiceImpl.java
│   │               └── RandomUserApplication.java
│   └── resources
│       ├── static
│       │   ├── css
│       │   │   └── styles.css
│       │   └── js
│       │       └── scripts.js
│       ├── templates
│       │   └── user-cards.html
│       └── application.properties
```

## Dépannage

- Si le port 8080 est déjà utilisé, vous pouvez changer le port dans `src\main\resources\application.properties` en ajoutant `server.port=8081`.
- Assurez-vous que votre connexion internet fonctionne, car l'application consomme l'API publique randomuser.me.
- En cas d'erreur de compilation liée à Lombok, vérifiez que l'annotation processing est activée dans votre IDE.