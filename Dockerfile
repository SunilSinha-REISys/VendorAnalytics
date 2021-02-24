FROM openjdk:8
EXPOSE 8080
ADD target/vendor-analytics.jar vendor-analytics.jar 
ENTRYPOINT ["java","-jar","/vendor-analytics.jar"]