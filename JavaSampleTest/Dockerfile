FROM openjdk:17

COPY target/sb_docker_name.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "sb_docker_name.jar"]