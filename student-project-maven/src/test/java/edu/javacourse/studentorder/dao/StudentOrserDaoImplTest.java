package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class StudentOrserDaoImplTest extends TestCase {

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Test
    public void testSaveStudentOrder() throws DaoException {
        StudentOrder soTest = buildStudentOrder(10);
        Long id = new StudentOrserDaoImpl().saveStudentOrder(soTest);
    }

//    @Test
//    public void testSaveStudentOrderError() {
////        Первый Вариант
//        try{
//            StudentOrder soTestE = buildStudentOrder(10);
//            soTestE.getHusband().setSurName(null);
//            Long idE = new StudentOrserDaoImpl().saveStudentOrder(soTestE);
//            Assert.fail(" SurName не может быть NULL");
//        } catch (DaoException e){
//        }
//    }

    @Test(expected =  DaoException.class)
    public void testSaveStudentOrderError() throws  DaoException  {
//        Второй Вариант
        try{
            StudentOrder soTestE = buildStudentOrder(10);
            soTestE.getHusband().setSurName(null);
            Long idE = new StudentOrserDaoImpl().saveStudentOrder(soTestE);
            Assert.fail(" SurName не может быть NULL");
        } catch (DaoException e){
        }

    }

    @Test
    public void testGetStudentOrders() throws DaoException {
        List<StudentOrder> list = new StudentOrserDaoImpl().getStudentOrders();
    }


    public static StudentOrder buildStudentOrder(long id) {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        so.setMarriageCertificateId("" + (123456000 + id));
        so.setMarriageDate(LocalDate.of(2016, 7, 4));

        RegisterOffice ro = new RegisterOffice(1L, "", "");
        so.setMarriageOffice(ro);

        Street street = new Street(5,"First");

        Address address = new Address("195000", street, "12", "", "142");

        // Муж
        Adult husband = new Adult("Петров", "Виктор", "Сергеевич", LocalDate.of(1997, 8, 24));
        husband.setPassportSeria("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueDate(LocalDate.of(2017, 9, 15));
        husband.setIssueDepartment(new PassportOffice(4l,"020010010001", "Паспортный стол  область 1 посеение 1 "));
        husband.setStudentId("" + (100000 + id));
        husband.setAddress(address);
        husband.setUnivesity(new University(2L, ""));
        husband.setStudentId("HH12345");

        // Жена
        Adult wife = new Adult("Петрова", "Вероника", "Алекссевна", LocalDate.of(1998, 3, 12));
        wife.setPassportSeria("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueDate(LocalDate.of(2018, 4, 5));
        wife.setIssueDepartment(new PassportOffice(8l,"", ""));
        wife.setStudentId("" + (200000 + id));
        wife.setAddress(address);
        wife.setUnivesity(new University(1L, ""));
        wife.setStudentId("WW12345");

        // Ребенок
        Child child1 = new Child("Петрова", "Ирина", "Викторовна", LocalDate.of(2018, 6, 29));
        child1.setCertificateNumber("" + (300000 + id));
        child1.setIssueDate(LocalDate.of(2018, 7, 19));
        child1.setIssueDepartment( new RegisterOffice(7L,"",""));
        child1.setAddress(address);
        // Ребенок
        Child child2 = new Child("Петров", "Евгений", "Викторович", LocalDate.of(2018, 6, 29));
        child2.setCertificateNumber("" + (400000 + id));
        child2.setIssueDate(LocalDate.of(2018, 7, 19));
        child2.setIssueDepartment(new RegisterOffice(5L,"020010010002", "ЗАГС Область 1 поселение 2"));
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);

        return so;
    }
}