FROM yologger1013/java:corretto-17.0.8

COPY build/libs/* application.jar

CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "application.jar"]