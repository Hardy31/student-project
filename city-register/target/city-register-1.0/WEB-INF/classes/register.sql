--City Register - cr

DROP TABLE IF EXISTS cr_address_persone;
DROP TABLE IF EXISTS cr_person;
DROP TABLE IF EXISTS cr_address;
DROP TABLE IF EXISTS cr_district;
DROP TABLE IF EXISTS cr_street;




--Таблица района
CREATE TABLE cr_district (
    district_code integer not null,
    district_name varchar(300),
    PRIMARY KEY (district_code)
);
--Таблица улица
CREATE TABLE cr_street (
    street_code integer not null,
    street_name varchar(300),
    PRIMARY KEY (street_code)
);

--Таблица адрессов
CREATE TABLE cr_address (
    address_id SERIAL,                  --генерируется автоматически
    district_code integer not null,
    street_code integer not null,
    building varchar(10) not null,      --строение
    extension varchar(10) ,     --расширение
    apartment varchar(10) ,     --номер квартиры
    PRIMARY KEY (address_id),
    FOREIGN KEY (district_code) REFERENCES cr_district(district_code) ON DELETE RESTRICT,
    FOREIGN KEY (street_code) REFERENCES cr_street(street_code) ON DELETE RESTRICT
);

--Таблица Person
CREATE TABLE cr_person (
    person_id SERIAL,                        --генерируется автоматически
    sur_name varchar(100) not null,          --Фамилия
    given_name varchar(100) not null,        --собственное имя
    patronymic varchar(100) not null,        --отчество
    date_of_birth date not null,             --дата рождения
    passport_seria varchar(100),             --паспорт серия, может быть нул, так как у ребенка нет паспорта
    passport_number varchar(100),            --паспорт номер может быть нул, так как у ребенка нет паспорта
    passport_date date,                      --дата выдочи паспорта может быть нул, так как у ребенка нет паспорта
    certificate_number varchar(10) null,     --свидетельство о рождении реб , номер может быть нул, так как у ребенка нет паспорта
    certificate_date date null,              --дата выдочи свидетельства о рожд. может быть нул, так как у ребенка нет паспорта
    PRIMARY KEY (person_id)
);

CREATE TABLE cr_address_persone (
    person_address_id SERIAL,
    address_id integer not null,
    person_id integer not null,
    start_date date not null,
    end_date date,
    temporal boolean DEFAULT false,  -- Временная регистрация , по умолчанию ЛОЖНО
    PRIMARY KEY (person_address_id),
    FOREIGN KEY (address_id) REFERENCES cr_address(address_id) ON DELETE RESTRICT,
    FOREIGN KEY (person_id) REFERENCES cr_person(person_id) ON DELETE RESTRICT

);

--1. Район
--2. Улица
--3. Адресс - район , улица. дом, корпус квартира
--4. Персона - ФИО Дата рождения, Паспорт (Серия/Номер/дата выдочи), Сидетельство о рождении (Серия/Номер/дата выдочи)
--5. связи Персоны и адреса - Персоны , Адресса и Диапазоны дат и вид регистрации
--
--DDD - Domain Drive Development (Разработка на основе домена - перевод по словам :Домен Ведомый Разработка)



--Заполняем таблици
INSERT INTO cr_district (district_code, district_name)
VALUES (1, 'Выборгский');

INSERT INTO cr_street (street_code, street_name)
VALUES (1, 'Сампсоневский проспект');

INSERT INTO cr_address (district_code,street_code, building, extension, apartment)
VALUES (1,1,'10','2', '121');
INSERT INTO cr_address (district_code,street_code, building, extension, apartment)
VALUES (1,1,'271', null, '4');

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
    passport_seria, passport_number, passport_date, certificate_number, certificate_date)
    VALUES('Васильев', 'Павел', 'Николаевич', '1995-03-18', '1234', '123456', '2015-04-11', null, null);

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
    passport_seria, passport_number, passport_date, certificate_number, certificate_date)
    VALUES('Васильева', 'Ирина', 'Петровна', '1997-08-21', '4321', '654321', '2017-09-19', null, null);


INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
    passport_seria, passport_number, passport_date, certificate_number, certificate_date)
    VALUES('Васильева', 'Евгения', 'Павловна', '2016-01-11', null, null, null,  '456123', '2016-01-21');

INSERT INTO cr_person (sur_name, given_name, patronymic, date_of_birth,
    passport_seria, passport_number, passport_date, certificate_number, certificate_date)
    VALUES('Васильев', 'Александр', 'Павлович', '2018-10-24', null, null, null,  '4321654', '2018-11-09');

INSERT INTO cr_address_persone (address_id, person_id, start_date, end_date)
VALUES(1, 1, '2014-10-12', null);
INSERT INTO cr_address_persone (address_id, person_id, start_date, end_date)
VALUES(2, 2, '2014-10-12', null);
INSERT INTO cr_address_persone (address_id, person_id, start_date, end_date)
VALUES(1, 3, '2016-02-05', null);
INSERT INTO cr_address_persone (address_id, person_id, start_date, end_date)
VALUES(1, 4, '2018-12-11', null);



