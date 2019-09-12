FROM anapsix/alpine-java:8_server-jre_unlimited

ADD ./build/libs/*.jar app.jar

ENTRYPOINT java -jar -Xmx128m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=UTC app.jar