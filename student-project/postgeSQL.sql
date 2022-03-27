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
