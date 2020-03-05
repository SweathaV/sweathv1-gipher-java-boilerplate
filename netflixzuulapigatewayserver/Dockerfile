FROM java:8-jre
WORKDIR usr/src
ADD ./target/zuulapigatewayservice-1.0.jar /usr/src/zuulapigatewayservice-1.0.jar
RUN chmod +x /usr/src/zuulapigatewayservice-1.0.jar
EXPOSE 8765
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/zuulapigatewayservice-1.0.jar"]