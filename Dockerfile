FROM anapsix/alpine-java:8_server-jre_unlimited

ADD ./build/libs/*.jar app.jar

CMD ["java", "-jar", "-Xmx128m", "-Dspring.profiles.active=docker", "-Duser.timezone=UTC", "app.jar"]
