package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements  DictionaryDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/sammy";
    static final String USER = "sammy";
    static final String PASS = "12345";

    public List<Street> findStreets(String mask)  throws DaoException{
        List<Street> resultList = new LinkedList<>();
        String sql = "SELECT street_code AS id, street_name AS name FROM jc_street " +
                "WHERE UPPER(street_name) LIKE UPPER(?) ";
        try(Connection con = getConnection();

           PreparedStatement stmt = con.prepareStatement(sql)){

//            Statement stmt = con.createStatement() ){
            stmt.setString(1, "%"+mask+"%" );
            ResultSet result =  stmt.executeQuery();
            while (result.next()){
                Street str = new Street(result.getLong("id") , result.getString("name"));
                resultList.add(str);

//            Так как в запросе мы написали street_code AS id, street_name AS name.
//            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!
                System.out.println(result.getLong("id") +" : "+ result.getString("name"));
            }

        } catch (SQLException e){
            throw new DaoException(e);

        }
        return  resultList;
    }

    private Connection getConnection() throws SQLException{
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
    }
}


//    SELECT street_code AS id, street_name AS name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%len%');