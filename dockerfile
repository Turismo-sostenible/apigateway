
# Usamos una imagen de Maven con JDK 17 para compilar la aplicación.
FROM maven:3.8.5-openjdk-17 AS builder

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# 1. Copia solo el pom.xml
COPY pom.xml .
# 2. Descarga todas las dependencias.
# Si no cambias el pom.xml, este paso se salta en futuros builds.
RUN mvn dependency:go-offline

# 3. Ahora copia el resto del código fuente.
COPY src ./src

# 4. Compila la aplicación y empaquétala en un JAR. Saltamos los tests.
RUN mvn package -DskipTests


# Usamos una imagen base de JRE mucho más ligera, optimizada para correr Java.
FROM eclipse-temurin:17-jre-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia ÚNICAMENTE el JAR compilado desde la etapa "builder".
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto en el que correrá el API Gateway
EXPOSE 8088

# Comando para ejecutar la aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]