# define base docker image
FROM openjdk:11
EXPOSE 8080
LABEL maintainer="pijush"
ADD target/librarymanagement.jar librarymanagement.jar
ENTRYPOINT ["java", "-jar", "librarymanagement.jar"]