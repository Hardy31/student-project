package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.wedding.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DirectoriDao  {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/sammy";
    static final String USER = "sammy";
    static final String PASS = "12345";

    public List<Street> findStreets(String mask)  throws SQLException{
        List<Street> resultList = new LinkedList<>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet result =  stmt.executeQuery("SELECT street_code AS id, street_name AS name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%"+mask+"%') ");
        while (result.next()){
            Street str = new Street(result.getLong("id") , result.getString("name"));
            resultList.add(str);

//            Так как в запросе мы написали street_code AS id, street_name AS name.
//            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!
            System.out.println(result.getLong("id") +" : "+ result.getString("name"));
        }
        return  resultList;
    }

    private Connection getConnection() throws SQLException{
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
    }
}


//    SELECT street_code AS id, street_name AS name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%len%');