FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./target/*.jar app.jar

ENTRYPOINT java -jar -Xmx128m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=UTC app.jar
