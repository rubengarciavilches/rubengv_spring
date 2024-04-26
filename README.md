# rubengv_spring
 Spring backend

Deploy to Fly.io:
./mvnw clean
./mvnw install
del app.jar
move ./target/*.jar ./app.jar
fly deploy
