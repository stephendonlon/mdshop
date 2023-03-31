-- Create cart schema
CREATE DATABASE IF NOT EXISTS cart;
GRANT ALL PRIVILEGES ON cart.* TO 'myuser'@'%' IDENTIFIED BY 'myuserpassword';
GRANT ALL PRIVILEGES ON cart.* TO 'myuser'@'localhost' IDENTIFIED BY 'mypasswd';


-- Create cart_command schema
CREATE DATABASE IF NOT EXISTS cart_command;
GRANT ALL PRIVILEGES ON cart_command.* TO 'myuser'@'%' IDENTIFIED BY 'myuserpassword';
GRANT ALL PRIVILEGES ON cart_command.* TO 'myuser'@'localhost' IDENTIFIED BY 'mypasswd';


-- Flush privileges to apply changes
FLUSH PRIVILEGES;
