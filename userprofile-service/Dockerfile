FROM openjdk:11-jre-slim
WORKDIR usr/src
# ENV MONGO_URI mongodb://localhost:27017/userprofiledb
EXPOSE 8096
ADD ./target/userprofile-service-0.0.1-SNAPSHOT.jar /usr/src/userprofile-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/usr/src/userprofile-service-0.0.1-SNAPSHOT.jar"]