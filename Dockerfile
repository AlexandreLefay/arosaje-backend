# Étape 1 : Construction
FROM maven:3.9.2-eclipse-temurin-17-alpine AS builder

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers source et le fichier pom.xml
COPY ./pom.xml .
COPY ./src ./src

# Exécuter la commande Maven pour compiler le projet
RUN mvn clean package -DskipTests

# Étape 2 : Exécution
FROM eclipse-temurin:17-jre-alpine

# Copier le fichier JAR généré depuis l'étape de build
COPY --from=builder /app/target/*.jar app.jar

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar", "--server.port=8080"]
