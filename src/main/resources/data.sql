CREATE TABLE IF NOT EXISTS driver (
                                      driverid INT AUTO_INCREMENT,
                                      driver_name VARCHAR(255) ,
                                      joining_date DATE,
                                      password VARCHAR(255) ,
                                      role VARCHAR(255) ,
                                          PRIMARY KEY (driverid )


);
CREATE TABLE IF NOT EXISTS car (
                                   carid INT AUTO_INCREMENT,
                                   convertible boolean ,
                                   engine_type VARCHAR(255) ,
                                   created_at DATE,
                                   license_plate VARCHAR(255) ,
                                   manufacturer VARCHAR(255) ,
                                   rating DOUBLE,
                                   seats INT,
                                   driver_driverid INT,
                                      PRIMARY KEY (carid )



);
# the password is 0000

INSERT INTO driver(driver_name, password,joining_date,role)
VALUES ('ahmad','$2a$10$O9jWFnnr8i/YfMQ/N8uH9ufCPlIk37MdOF0YIOj.vVoZJlgTxXRam',now(),'driver');
INSERT INTO driver(driver_name, password,joining_date,role)
VALUES ('khaleel','$2a$10$O9jWFnnr8i/YfMQ/N8uH9ufCPlIk37MdOF0YIOj.vVoZJlgTxXRam',now(),'driver');


# Creat cars

INSERT INTO car(engine_type, created_at,license_plate,manufacturer,rating,seats,driver_driverid,convertible)
VALUES ('Gas',now(),'123456','BMW',8.5,4,1,true);
INSERT INTO car(engine_type, created_at,license_plate,manufacturer,rating,seats,driver_driverid,convertible)
VALUES ('Hybrid',now(),'852654','Toyota',7.5,4,2,true);

