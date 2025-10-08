#!/bin/bash
set -e

host="mariadb"
user="root"
password="rootpassword"
db="supportjoin"

echo "Waiting for MariaDB to be ready at $host..."

until mysql -h "$host" -u"$user" -p"$password" -e "use $db;" > /dev/null 2>&1; do
  echo "MariaDB not ready yet. Retrying in 5s..."
  sleep 5
done

echo "MariaDB is up. Starting application..."
exec java -jar app.jar
