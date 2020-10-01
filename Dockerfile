FROM openjdk:8-alpine

COPY target/uberjar/bookshelf.jar /bookshelf/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/bookshelf/app.jar"]
