Урок 56

"SELECT * FROM jc_student_order WHERE student_order_status = 0 ORDER BY student_order_date; "

SELECT * FROM jc_student_order
INNER JOIN jc_register_office ON jc_register_office.r_office_id = jc_student_order.register_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;

 Тоже самое через AS
SELECT * FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;

 Тоже самое через AS
SELECT so.*, ro.r_office_area_id, ro.r_office_name FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;

UPDATE jc_student_order set register_office_id = 3 WHERE student_order_id IN (1,3,5,7);

Урок 57
SELECT h_passport_office_idб so.*, ro.r_office_area_id, ro.r_office_name , po.p_office_area_id, po.p_office_name FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
INNER JOIN jc_passport_office AS po ON po.p_office_id = so.register_office_id
INNER JOIN jc_passport_office AS po ON po.p_office_id = so.h_passport_office_id
INNER JOIN jc_passport_office AS po ON po.p_office_id = so.w_passport_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;



выводим муж - код паспортного стола и название, жена - -код паспортного стола и название, Регистратор брака - код закса и название,
SELECT
hpo.p_office_area_id, hpo.p_office_name, wpo.p_office_area_id, wpo.p_office_name, ro.r_office_area_id, ro.r_office_name
FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id
INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;


SELECT so.*, hpo.p_office_area_id, hpo.p_office_name, wpo.p_office_area_id, wpo.p_office_name, ro.r_office_area_id, ro.r_office_name
FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id
INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;


SELECT so.*, ro.r_office_area_id, ro.r_office_name,
hpo.p_office_area_id AS h_p_office_area_id , hpo.p_office_name AS h_p_office_name,
wpo.p_office_area_id AS w_p_office_area_id, wpo.p_office_name AS w_p_office_name
FROM jc_student_order AS so
INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id
INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id
INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id
WHERE student_order_status = 0 ORDER BY student_order_date;

SELECT so.*, ro.r_office_area_id, ro.r_office_name, hpo.p_office_area_id AS h_p_office_area_id , hpo.p_office_name AS h_p_office_name, wpo.p_office_area_id AS w_p_office_area_id, wpo.p_office_name AS w_p_office_name FROM jc_student_order AS so INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id WHERE student_order_status = 0 ORDER BY student_order_date;

