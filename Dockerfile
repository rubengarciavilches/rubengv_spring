FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
#COPY target/*.jar app.jar
COPY *.jar app.jar

# https://spring.io/guides/topicals/spring-boot-docker
# Need to pass these as arguments, f.ex:
# docker build --build-arg URL="url_here" --build-arg USER="user_here" --build-arg PASS="pass_here" -t rubengv/spring_backend .
# or if you have them as env variables already:
# docker build --build-arg URL=$env:P_BACK_URL --build-arg USER=$env:P_BACK_USER --build-arg PASS=$env:P_BACK_PASS -t rubengv/spring_backend .

# ARG URL=""
# ARG USER=""
# ARG PASS=""

# ENV P_BACK_URL=${URL}
# ENV P_BACK_USER=${USER}
# ENV P_BACK_PASS=${PASS}

ENTRYPOINT ["java","-jar","/app.jar"]