# reactive-mongo

1) Creating Jar
 ./gradlew clean build
 
2) Running on Docker
 docker-compose up
 
 //Creating Manually
 ./gradlew clean build
 java -jar jarName.jar
 
 //Building Docker image Manually
 docker build . -t "appimagename"
 
 //To run the docker image
 docker run -d -p 27000:27017 --name mongo mongo
