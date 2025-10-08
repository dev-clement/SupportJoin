#!/bin/sh
echo "Waiting for MariaDB to be ready..."
until mysql -h mariadb -uappuser -papppassword -e "SELECT 1" ; do
  sleep 2
done
echo "MariaDB is ready! Starting Spring Boot..."
exec java -jar app.jar
