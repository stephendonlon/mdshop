-- Create user
CREATE USER 'myuser'@'%' IDENTIFIED BY 'mypassword';

-- Create cart schema
CREATE DATABASE IF NOT EXISTS cart;
GRANT ALL PRIVILEGES ON cart.* TO 'myuser'@'%' WITH GRANT OPTION;

-- Create cart_command schema
CREATE DATABASE IF NOT EXISTS cart_command;
GRANT ALL PRIVILEGES ON cart_command.* TO 'myuser'@'%' WITH GRANT OPTION;

-- Flush privileges to apply changes
FLUSH PRIVILEGES;
