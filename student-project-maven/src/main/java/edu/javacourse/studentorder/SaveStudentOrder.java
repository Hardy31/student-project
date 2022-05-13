package edu.javacourse.studentorder;


import edu.javacourse.studentorder.dao.StudentOrderDao;
import edu.javacourse.studentorder.dao.StudentOrserDaoImpl;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.wedding.Street;

import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder
{
//    static final String DB_URL = "jdbc:postgresql://localhost:5432/sammy";
//    static final String USER = "sammy";
//    static final String PASS = "12345";



    public static void main(String[] args) throws Exception {

//        StudentOrder s = buildStudentOrder(25);
        StudentOrderDao dao = new StudentOrserDaoImpl();
//        Long id = dao.saveStudentOrder(s);


        List<StudentOrder> soList = dao.getStudentOrders();
        for(StudentOrder so : soList){
            System.out.println(so.getStudentOrderId());
        }

    }


//    public static StudentOrder buildStudentOrder(long id) {
//        StudentOrder so = new StudentOrder();
//        so.setStudentOrderId(id);
//        so.setMarriageCertificateId("" + (123456000 + id));
//        so.setMarriageDate(LocalDate.of(2016, 7, 4));
//
//        RegisterOffice ro = new RegisterOffice(1L, "", "");
//        so.setMarriageOffice(ro);
//
//        Street street = new Street(5,"First");
//
//        Address address = new Address("195000", street, "12", "", "142");
//
//        // Муж
//        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
//        husband.setPassportSeria("" + (1000 + id));
//        husband.setPassportNumber("" + (100000 + id));
//        husband.setIssueDate(LocalDate.of(2017, 9, 15));
//        husband.setIssueDepartment(new PassportOffice(4l,"020010010001", "Паспортный стол  область 1 посеение 1 "));
//        husband.setStudentId("" + (100000 + id));
//        husband.setAddress(address);
//        husband.setUnivesity(new University(2L, ""));
//        husband.setStudentId("HH12345");
//
//        // Жена
//        Adult wife = new Adult("Петрова", "Вероника", "Алекссевна", LocalDate.of(1998, 3, 12));
//        wife.setPassportSeria("" + (2000 + id));
//        wife.setPassportNumber("" + (200000 + id));
//        wife.setIssueDate(LocalDate.of(2018, 4, 5));
//        wife.setIssueDepartment(new PassportOffice(8l,"", ""));
//        wife.setStudentId("" + (200000 + id));
//        wife.setAddress(address);
//        wife.setUnivesity(new University(1L, ""));
//        wife.setStudentId("WW12345");
//
//        // Ребенок
//        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
//        child1.setCertificateNumber("" + (300000 + id));
//        child1.setIssueDate(LocalDate.of(2018, 7, 19));
//        child1.setIssueDepartment( new RegisterOffice(7L,"",""));
//        child1.setAddress(address);
//        // Ребенок
//        Child child2 = new Child("Петров", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
//        child2.setCertificateNumber("" + (400000 + id));
//        child2.setIssueDate(LocalDate.of(2018, 7, 19));
//        child2.setIssueDepartment(new RegisterOffice(5L,"020010010002", "ЗАГС Область 1 поселение 2"));
//        child2.setAddress(address);
//
//        so.setHusband(husband);
//        so.setWife(wife);
//        so.addChild(child1);
//        so.addChild(child2);
//
//        return so;
//    }
}
