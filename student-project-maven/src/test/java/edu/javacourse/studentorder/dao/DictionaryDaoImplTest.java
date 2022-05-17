package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DictionaryDaoImplTest {

    private static  final Logger logger = LoggerFactory.getLogger(DictionaryDaoImplTest.class);




    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }
    @AfterClass
    public static void afterUp(){
        System.out.println("After Up");
    }

    @Before

    public void startTest(){
        System.out.println("Start Test- Befo All Tests");
    }

    @After
    public void afterTest(){
        System.out.println("After Test- After All Tests");
        System.out.println("");
    }

    @Test
    public void  testStreet  () throws DaoException {
//        Быстрее
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("Test {} {}" , dt1, dt2);

//        не правильно!
//        LocalDateTime dt3 = LocalDateTime.now();
//        LocalDateTime dt4 = LocalDateTime.now();
//        logger.info("Test2 "+ dt3 + " " + dt4);

        System.out.println("Test Street");
        List<Street> d = new DictionaryDaoImpl().findStreets("про");
        Assert.assertTrue(d.size() == 2);
    }
    @Test
    public void  testPassportOffice  () throws DaoException {

        System.out.println("Test Passport Office");
        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffice("010020000000");
        Assert.assertTrue(po.size() == 2);
    }

    @Test
    public void  testRegisterOffice  () throws DaoException {

        System.out.println("Test Register Office");
        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffice("010010000000");
        Assert.assertTrue(ro.size() == 2);
    }

    @Test
    public void  testFindArea  () throws DaoException {

        System.out.println("Test Find Area");

        List<CountryArea> ca = new DictionaryDaoImpl().findArea("");
        Assert.assertTrue(ca.size() == 2);

        List<CountryArea> ca1 = new DictionaryDaoImpl().findArea("020000000000");
        Assert.assertTrue(ca1.size() == 2);

        List<CountryArea> ca2 = new DictionaryDaoImpl().findArea("020010000000");
        Assert.assertTrue(ca2.size() == 2);

        List<CountryArea> ca3 = new DictionaryDaoImpl().findArea("020020000000");
        Assert.assertTrue(ca3.size() == 2); //Для Проверки можно поставить точку прерывания и  запустить отладку
    }

}