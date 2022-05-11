package edu.javacourse.studentorder.dao;

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

        try (Connection con = ConnectionBuilder.getConnection();
             Statement stmt = con.createStatement()
            )   {
            stmt.executeUpdate(sql1);
        }

        String sql2 = str2.stream().collect(Collectors.joining());

        try (Connection con = ConnectionBuilder.getConnection();
             Statement stmt = con.createStatement()
        )   {
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
    public void  testExample1 (){
        System.out.println("TEST 1");
    }

    @Test
//    @Ignore
    public void  testExample2 (){
        System.out.println("TEST 2");
    }

    @Test
    public void  testExample3 (){
        System.out.println("TEST 3");
    }
}