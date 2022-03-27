--DDL

DROP TABLE IF EXISTS st_adress;

CREATE TABLE st_adress (
	address_id SERIAL,
	post_code VARCHAR(15),
	street VARCHAR(100),
	building VARCHAR(10),
	extension VARCHAR(10),
	apartment VARCHAR(10),
	PRIMARY KEY(address_id )
);
