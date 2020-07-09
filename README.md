# ma_watchlist_de_films!


Application Watcher permet de créer une Playist de film à regarder

## Contributors

  - Eva Sobaco-Morison
  - Noblesse Kasa
  - Paul Pauwels
  - Cyril VUILLEMIN
  - Livio Chan-Tung
  
## Liens 
#### Scalingo
https://watcher.osc-fr1.scalingo.io/
#### API des Films
https://www.themoviedb.org/

  
## Initialisation du projet

Tout d'abord il configurer la base de données local.
Creer le fichier **application.properties** en copiant le fichier **application-local.properties**

Installer les dépendances
```sh
$ mvn install
```

Run le projet
```sh
$ mvn spring-boot:run
```

## Architecture
Basé sur le pattern Modèle - Vue - Contrôleur car c'est celui que nous connaissons le mieux et qui nous permet de gagner du temps.

#### Back

Java Spring Boot

Jackson : traitement du json de l'API de films

Jpa : Traitement de la base de données

Liquibase : Pour les migrations

#### Front
CSS, Bootstrap, Jquery, Javascript

## Convention
#### Nommage des variables
camelCase exemple : compteurDeGirafe

#### Donner un nom explicite
compteurDeGirafe => compte des girafes

#### Nommage des constantes
Tout en majuscule
Séparer les mots par underscore '_'
Donner des noms simples et descriptifs

#### Nommage fichier
Les noms des classes doivent respecter les conventions suivantes 
1ère lettre en majuscule + camelCase
Donner des noms simples et descriptifs

#### Nommage des commits GIT
Dire ce que fait le commit clairement.

#### Nommage des branches
Chaque branche correspond à une issue.
Chaque issue possède un numéro
Chaque branche se nomme : mwdf-issue/etape-numero_issue
