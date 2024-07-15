# Stage 1 : Construction de l'application
FROM maven:3.8.4-openjdk-8 AS build

LABEL AUTHOR="TIGOLI FREDERIC"
LABEL VERSION="1.0"

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src ./src

# Télécharger les dépendances et construire le package
RUN mvn clean package -Dspring.profiles.active=development

# Stage 2 : Déploiement sur Tomcat
FROM tomcat:9.0.91-jdk8

LABEL AUTHOR="TIGOLI FREDERIC"
LABEL VERSION="1.0"

WORKDIR /usr/local/tomcat/webapps/

COPY --from=build /app/target/*.war ./app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]

