# Usa una imagen base de Java adecuada
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/ms-app-ws-demop.jar app.jar

# Expone el puerto en el que corre la aplicación
EXPOSE 8080

# Define el comando de ejecución de la aplicación
CMD ["java", "-jar", "app.jar"]
