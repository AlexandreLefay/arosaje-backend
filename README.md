# Projet Spring Boot

## Description

Ce projet est un backend développé avec Spring Boot dans le cadre d'un projet d'étude. L'objectif initial était de créer une API RESTful pour une application web. 
Etant donné que le projet a été rapidement, de nombreuses améliorations sont nécessaires pour le rendre plus robuste et maintenable.

## Objectifs du Projet

- **Apprentissage de Spring Boot** : Utiliser Spring Boot pour créer une application web backend.
- **Création d'API REST** : Développer des endpoints RESTful pour gérer les ressources de l'application.
- **Refactorisation** : Améliorer le code existant pour le rendre plus propre, plus efficace et plus facile à maintenir.
- 

## Structure du Projet

Le projet contient les éléments suivants :

- **src/** : Dossier contenant le code source de l'application Spring Boot.
- **pom.xml** : Fichier de configuration Maven pour gérer les dépendances du projet.
- **Dockerfile** : Fichier pour containeriser l'application avec Docker.
- **README.md** : Ce fichier, expliquant le but et les étapes de déploiement du projet.

## Instructions de Déploiement

### Pré-requis

- Java 17 installé sur votre machine
- Maven installé sur votre machine
- Docker installé si vous souhaitez containeriser l'application
- Un compte sur [Render](https://render.com) si vous souhaitez déployer sur Render

### Étapes de Déploiement

1. **Cloner le dépôt** :

    ```bash
    git clone https://github.com/votre-utilisateur/projet-spring-boot.git
    cd projet-spring-boot
    ```

2. **Configurer l'application** :

   Modifiez les fichiers de configuration (par exemple, `application.properties` dans `src/main/resources`) pour adapter les paramètres de la base de données et d'autres configurations spécifiques.

3. **Construire le projet** :

   Utilisez Maven pour construire le projet :

    ```bash
    mvn clean install
    ```

4. **Lancer l'application localement** :

    ```bash
    mvn spring-boot:run
    ```

5. **Containeriser l'application (optionnel)** :

   Si vous souhaitez exécuter l'application dans un conteneur Docker, construisez l'image Docker :

    ```bash
    docker build -t projet-spring-boot .
    ```

   Puis lancez un conteneur à partir de cette image :

    ```bash
    docker run -p 8080:8080 projet-spring-boot
    ```

6. **Déployer sur Render** :

    - Connectez-vous à votre compte Render.
    - Créez un nouveau service Web et sélectionnez le repository Git cloné.
    - Configurez le type de service comme Docker et fournissez le chemin vers le `Dockerfile`.
    - Cliquez sur **Create Web Service** pour lancer le déploiement.

### Points à Améliorer

- **Tests Unitaires et d'Intégration** : Ajouter des tests pour assurer la fiabilité du code.
- **Sécurité** : Mettre en place des mécanismes de sécurité comme OAuth2 ou JWT pour protéger les endpoints.
- **Documentation API** : Utiliser Swagger pour documenter les endpoints de l'API.
- **Refacto de l'API** : Simplifier et optimiser les endpoints de l'API pour les rendre plus efficaces.
- **Gestion des Erreurs** : Ajouter des gestionnaires d'exceptions pour gérer les erreurs de manière appropriée.
- **Refaire l'Architecture** : Repenser l'architecture de l'application pour la rendre plus modulaire et évolutive.
- **Optimisation des Performances** : Identifier et corriger les goulots d'étranglement pour améliorer les performances.


---

Ce projet est en cours de refactorisation pour le rendre plus robuste et performant.
