FROM java:8-jre
WORKDIR /usr/lib
ENV MYSQL_DATABASE=gipheruserdb
ENV MYSQL_URL=jdbc:mysql://localhost:3306/gipheruserdb
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ADD ./target/accountmanager-1.0.jar /usr/lib/accountmanager-1.0.jar
ENTRYPOINT ["java","-jar","accountmanager-1.0.jar"]
