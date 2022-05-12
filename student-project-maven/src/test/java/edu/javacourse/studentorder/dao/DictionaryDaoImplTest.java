package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;
import org.junit.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DictionaryDaoImplTest {
    @BeforeClass
    public static void startUp() throws Exception {
        System.out.println("Start Up");
        System.out.println("");
        URL url1 = DictionaryDaoImplTest.class.getClassLoader().getResource("ctreateTable.sql");
        URL url2 = DictionaryDaoImplTest.class.getClassLoader().getResource("InsertTable.sql");

        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));

        String sql1 = str1.stream().collect(Collectors.joining());
        String sql2 = str2.stream().collect(Collectors.joining());

        try (Connection con = ConnectionBuilder.getConnection();
             Statement stmt = con.createStatement()
            )   {
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
        }
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