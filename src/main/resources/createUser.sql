CREATE USER 'cs548_user'@'localhost' IDENTIFIED BY 'shopping';

GRANT ALL PRIVILEGES ON shopping.* TO 'cs548_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'cs548_user'@'localhost';