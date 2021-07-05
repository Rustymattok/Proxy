FROM gitlab.ubic.tech:4567/docker/openjdk-bali/8.0.0.3:latest

VOLUME /tmp

WORKDIR /app

EXPOSE 8080

ENV JAVA_OPT=""
ENV APP_PROP=""

CMD ["/app/mycom-proxy.jar"]

COPY target/mycom-proxy.jar /app/mycom-proxy.jar
