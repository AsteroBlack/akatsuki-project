#stage1
FROM maven:3.8.4-openjdk-8 as build

LABEL AUTHOR = TIGOLI FREDERIC
LABEL VERSION = 1.0

WORKDIR /app
COPY . .

#télécharger les dependances
RUN mvn dependency:go-offline -B
 
#build le code 
RUN mvn clean package -Dspring.profiles.active=production


#stage 2
FROM tomcat:9.0.91-jdk8

WORKDIR /usr/local/tomcat/webapps/

COPY --from=build app/target/*war ./app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]


