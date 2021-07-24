FROM openjdk:11-jre-slim
EXPOSE 8090
WORKDIR usr/src
ADD ./target/authentication-service-0.0.1-SNAPSHOT.jar /usr/src/authentication-service-0.0.1-SNAPSHOT.jar
# ENV MYSQL_URI MYSQL_URI:jdbc:mysql://localhost:3306/authuser
# ENV MYSQL_USERNAME root
# ENV MYSQL_PASSWORD root

ENTRYPOINT ["java", "-jar", "/usr/src/authentication-service-0.0.1-SNAPSHOT.jar"]