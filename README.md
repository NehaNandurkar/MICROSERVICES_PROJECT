# Swagger url accounts Service 
http://localhost:8080/swagger-ui/index.html

# Swagger url loans Service 
http://localhost:8090/swagger-ui/index.html

#Java Command to run the application 
java -jar target/accounts-0.0.1-SNAPSHOT.jar


#To create a new jar file 
mvnw clean install

#Builds a new Docker image from the Dockerfile in the current folder.
#neha12062001 → your Docker Hub username , accounts → project/image name , s4 → tag (like version v4).
docker build . -t neha12062001/accounts:s4

#Run the compose 
docker-compose up -d

#Look the running containers 
docker ps

#Deleting compose 
docker-compose down