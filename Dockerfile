FROM openjdk:17

WORKDIR /usr/app

COPY target/Online_Compiler_app.jar /usr/app

ENTRYPOINT ["java", "-jar", "Online_Compiler_app.jar"]