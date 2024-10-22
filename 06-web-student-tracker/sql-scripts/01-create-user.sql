# Nota: Si ejecutamos MySQL en un contenedor de Docker, sustituir el host "localhost" por "172.17.0.1".
CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';

GRANT ALL PRIVILEGES ON * . * TO 'demo'@'localhost';
