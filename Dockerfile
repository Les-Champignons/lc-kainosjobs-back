FROM amazoncorretto:21

WORKDIR /

ENV EXPIRATION_TIME=86400

COPY /target/lc-kainosjobs-back-1.0-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "lc-kainosjobs-back-1.0-SNAPSHOT.jar", "server"]