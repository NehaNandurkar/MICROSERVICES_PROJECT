#Start with a lightweight setup that already has Java 17 JDK installed.
#dependency 1
FROM openjdk:17-jdk-slim

#Who maintains this image?
LABEL maintainer="eazycode.com"

#Take the accounts jar from my target folder and copy the same jar in my docker image
#dependency 2
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

#Whem someone is trying to generate a container from my docker image execute the below command 
#java -jar target/accounts-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","loans-0.0.1-SNAPSHOT.jar"]
