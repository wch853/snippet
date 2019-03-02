DROP DATABASE IF EXISTS security;

CREATE DATABASE security;

CREATE USER 'security_rw'@'localhost' IDENTIFIED BY 'security_rw';
GRANT ALL PRIVILEGES ON security.* TO 'security_rw'@'localhost';
FLUSH PRIVILEGES;

