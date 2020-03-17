FROM openjdk:8-jdk-alpine
ADD target/product-system-0.0.1-SNAPSHOT.jar /home/
CMD ["java","-jar","/home/product-system-0.0.1-SNAPSHOT.jar"]