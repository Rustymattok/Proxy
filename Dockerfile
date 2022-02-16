FROM gitlab.ubic.my:4567/docker/openjdk-bali/8.0.0.3:latest

VOLUME /tmp

WORKDIR /app

EXPOSE 8080

ENV JAVA_OPT=""
ENV APP_PROP=""

CMD ["/app/mycomproxy.jar"]

COPY target/mycomproxy.jar /app/mycomproxy.jar
