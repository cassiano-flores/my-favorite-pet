# Stage 1: Compilar o frontend
FROM node:14-alpine as frontend
WORKDIR /app
COPY ./app .
RUN npm install && npm run build

# Stage 2: Compilar o backend
FROM maven:3.8.4-openjdk-11-slim as backend
WORKDIR /app
COPY ./api .
RUN mvn clean install -DskipTests
#RUN mvn package

# Stage 3: Construir a imagem final
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=backend /app/target/*.jar .
COPY --from=frontend /app/build ./my-favorite-pet/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","my-favorite-pet.jar"]
