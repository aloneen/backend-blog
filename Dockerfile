FROM eclipse-temurin:21-jdk
MAINTAINER dias
COPY build/libs/blog-0.0.1-SNAPSHOT.jar blog.jar
ENTRYPOINT ["java", "-jar", "blog.jar"]