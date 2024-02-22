# Используем базовый образ с Java
FROM tomcat:latest

# Установка рабочей директории внутри контейнера
WORKDIR /app

# Копируем JAR-файл приложения в контейнер
COPY target/FGT-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/FGT.war

# Команда для запуска приложения при старте контейнера
CMD ["java", "-jar", "FGT.jar"]
