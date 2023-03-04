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

# Stage 3: Construir a imagem final
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=backend /app/target/myFavoritePet-0.0.1-SNAPSHOT.jar /usr/local/lib/myFavoritePet-0.0.1.jar
COPY --from=frontend /app/build /usr/local/lib
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/myFavoritePet-0.0.1.jar"]
