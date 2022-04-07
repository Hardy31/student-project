--DDL

--DROP TABLE IF EXISTS st_adress;
--
--CREATE TABLE st_adress (
--	address_id SERIAL,
--	post_code VARCHAR(15),
--	street VARCHAR(100),
--	building VARCHAR(10),
--	extension VARCHAR(10),
--	apartment VARCHAR(10),
--	PRIMARY KEY(address_id )
--);
--
--DROP TABLE IF EXISTS st_adress;


DROP TABLE IF EXISTS jc_student_child;
DROP TABLE IF EXISTS jc_student_order;
DROP TABLE IF EXISTS jc_passport_office;
DROP TABLE IF EXISTS jc_registre_office;
DROP TABLE IF EXISTS jc_country_struct;
DROP TABLE IF EXISTS jc_street;



CREATE TABLE jc_street (
    street_code INTEGER NOT NULL,
    street_name VARCHAR(300),
    PRIMARY KEY (street_code)
);

CREATE TABLE jc_country_struct(
    area_id CHAR(12) NOT NULL,
    area_name VARCHAR(200),
    PRIMARY KEY (area_id)
);

CREATE TABLE jc_passport_office (
    p_office_id INTEGER NOT NULL,
    p_office_area_id CHAR(12) NOT NULL,
    p_office_name VARCHAR(200),
    PRIMARY KEY (p_office_id),
    FOREIGN KEY (p_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE jc_registre_office(
     r_office_id INTEGER NOT NULL,
     r_office_area_id CHAR(12) NOT NULL,
     r_office_name VARCHAR(200),
     PRIMARY KEY (r_office_id),
     FOREIGN KEY (r_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE jc_student_order (
    student_order_id SERIAL,
    student_order_stattus INTEGER NOT NULL,
    student_order_date TIMESTAMP NOT NULL ,

    h_sur_name VARCHAR(100) NOT NULL,
    h_given_name VARCHAR(100) NOT NULL,
    h_patronymic VARCHAR(100) NOT NULL,
    h_date_of_birth DATE NOT NULL,
    h_passport_seria VARCHAR(10) NOT NULL,
    h_passport_number VARCHAR(10) NOT NULL,
    h_passport_date DATE NOT NULL,
    h_passport_office_id INTEGER NOT NULL,
    h_post_index VARCHAR(10),
    h_street_code INTEGER NOT NULL,
    h_building VARCHAR(10) NOT NULL,
    h_extension VARCHAR(10) ,
    h_apartment VARCHAR(10) ,

    w_sur_name VARCHAR(100) NOT NULL,
    w_given_name VARCHAR(100) NOT NULL,
    w_patronymic VARCHAR(100) NOT NULL,
    w_date_of_birth DATE NOT NULL,
    w_passport_seria VARCHAR(10) NOT NULL,
    w_passport_number VARCHAR(10) NOT NULL,
    w_passport_date DATE NOT NULL,
    w_passport_office_id INTEGER NOT NULL,
    w_post_index VARCHAR(10),
    w_street_code INTEGER NOT NULL,
    w_building VARCHAR(10) NOT NULL,
    w_extension VARCHAR(10) ,
    w_apartment VARCHAR(10) ,

    certificate_id VARCHAR(20) NOT NULL,
    register_office_id INTEGER NOT NULL,
    marriage_date DATE NOT NULL,
    PRIMARY KEY (student_order_id),

    FOREIGN KEY (h_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (w_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (register_office_id) REFERENCES jc_registre_office(r_office_id) ON DELETE RESTRICT
);

CREATE TABLE jc_student_child (
    student_child_id SERIAL,
    student_order_id INTEGER NOT NULL,
    c_sur_name VARCHAR(100) NOT NULL,
    c_given_name VARCHAR(100) NOT NULL,
    c_patronymic VARCHAR(100) NOT NULL,
    c_date_of_birth DATE NOT NULL,
    c_sertificate_number VARCHAR(10) NOT NULL,
    c_sertificate_date DATE NOT NULL,
    c_register_office_id INTEGER NOT NULL,
    c_post_index VARCHAR(10),
    c_street_code INTEGER NOT NULL,
    c_building VARCHAR(10) NOT NULL,
    c_extension VARCHAR(10) ,
    c_apartment VARCHAR(10) ,
    PRIMARY KEY (student_child_id),
    FOREIGN KEY (c_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (c_register_office_id) REFERENCES jc_registre_office(r_office_id) ON DELETE RESTRICT
);


INSERT  INTO jc_street (street_code, street_name) VALUES (1, 'Lenina');
INSERT  INTO jc_street (street_code, street_name) VALUES
        (2, 'Karla Marksa'),
        (3, 'Krupskoi'),
        (4, 'Pobeda');
INSERT  INTO jc_street (street_code, street_name) VALUES (5, 'Leninaskoi');
INSERT  INTO jc_street (street_code, street_name) VALUES (6, 'LeninasKoi');
INSERT  INTO jc_street (street_code, street_name) VALUES (7, 'Uplena');

SELECT * FROM jc_street;

UPDATE jc_street SET street_name = 'Nezavisimosty' WHERE street_code = '4';
SELECT * FROM jc_street;
SELECT street_code AS id, street_name AS name FROM jc_street;
SELECT street_code AS id, street_name AS name FROM jc_street WHERE street_code in(2,3);
SELECT street_code AS id, street_name AS name FROM jc_street WHERE street_code in(2,3) ORDER BY street_name;
SELECT street_code AS id, street_name AS name FROM jc_street WHERE street_code in(2,3) ORDER BY street_name DESC;


SELECT street_code AS id, street_name AS name FROM jc_street;
SELECT street_code AS id, street_name AS name FROM jc_street WHERE street_name LIKE '%ina%';
SELECT street_code AS id, street_name AS name FROM jc_street WHERE street_name LIKE '%len%';
SELECT street_code AS id, street_name AS name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%len%');





--"student_order_stattus, student_order_date, h_sur_name, h_given_name, h_patronymic, h_date_of_birth," +
--"h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index, h_street_code," +
--"h_building, h_extension, h_apartment, w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria," +
-- "w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension," +
-- "w_apartment, certificate_id, register_office_id, marriage_date"+