

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