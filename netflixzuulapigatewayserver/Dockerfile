FROM java:8-jre
WORKDIR usr/src
ADD ./target/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar /usr/src/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar
EXPOSE 8765
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar"]