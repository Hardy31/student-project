package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements  DictionaryDao {
//    static final String DB_URL = "jdbc:postgresql://localhost:5432/sammy";
//    static final String USER = "sammy";
//    static final String PASS = "12345";

    private  static  final String GET_STREET = "SELECT street_code AS id, street_name AS name FROM jc_street " +
            "WHERE UPPER(street_name) LIKE UPPER(?) ";
private  static  final String GET_PASSPORT = "SELECT p_office_id AS id, p_office_area_id AS code , p_office_name AS name FROM jc_passport_office " +
        "WHERE p_office_area_id = ? ";
private  static  final String GET_REGISTER = "SELECT r_office_id AS id, r_office_area_id AS code , r_office_name AS name FROM jc_registre_office " +
        "WHERE r_office_area_id = ? ";

//    select * from jc_country_struct where area_id like '02___0000000' and area_id <> '020000000000';
//private  static  final String GET_AREA = "SELECT area_id AS id, area_name AS name  FROM jc_country_struct " +
//        "WHERE area_id LIKE ? AND area_id <> ?";
private  static  final String GET_AREA = "SELECT * FROM jc_country_struct " +
        "WHERE area_id LIKE ? AND area_id <> ?";

    public List<Street> findStreets(String mask)  throws DaoException{
        List<Street> resultList = new LinkedList<>();
        try(Connection con = getConnection();
           PreparedStatement stmt = con.prepareStatement(GET_STREET)){
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
            Connection connection = DriverManager.getConnection(
                    Config.getProperty(Config.DB_URL),
                    Config.getProperty(Config.DB_LOGIN),
                    Config.getProperty(Config.DB_PASSWORD));

            return connection;
    }

    @Override
    public List<PassportOffice> findPassportOffice(String areaID) throws DaoException {
        List<PassportOffice> resultList = new LinkedList<>();

        try(Connection con = getConnection();

            PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)){
            stmt.setString(1, areaID );
            ResultSet result =  stmt.executeQuery();
            while (result.next()){
                PassportOffice str = new PassportOffice(
                        result.getLong("id") ,
                        result.getString("code"),
                        result.getString("name"));
                resultList.add(str);

//            Так как в запросе мы написали street_code AS id, street_name AS name.
//            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!

                System.out.println();
                System.out.println(result.getString("id")+" : "+
                        result.getString("name"));
            }

        } catch (SQLException e){
            throw new DaoException(e);

        }
        return  resultList;
    }

    @Override
    public List<RegisterOffice> findRegisterOffice(String areaID) throws DaoException {
        List<RegisterOffice> resultList = new LinkedList<>();

        try(Connection con = getConnection();

            PreparedStatement stmt = con.prepareStatement(GET_REGISTER)){
            stmt.setString(1, areaID );
            ResultSet result =  stmt.executeQuery();
            while (result.next()){
                RegisterOffice regOf = new RegisterOffice(
                        result.getLong("id") ,
                        result.getString("code"),
                        result.getString("name"));
                resultList.add(regOf);

//            Так как в запросе мы написали street_code AS id, street_name AS name.
//            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!

                System.out.println();
                System.out.println(result.getLong("id") +" : "+
                        result.getString("code")+" : "+
                        result.getString("name"));
            }

        } catch (SQLException e){
            throw new DaoException(e);

        }
        return  resultList;
    }

    @Override
    public List<CountryArea> findArea(String areaId) throws DaoException {
        List<CountryArea> resultList = new LinkedList<>();

        try(Connection con = getConnection();
//SELECT area_id AS id, area_named AS name  FROM jc_country_struct WHERE area_id LIKE ? AND area_id <> ?";
            PreparedStatement stmt = con.prepareStatement(GET_AREA)){

            String param1 = bildParam(areaId);
            String param2 = areaId;
            stmt.setString(1, param1 );
            stmt.setString(2, param2 );

            System.out.println(stmt.toString());
            ResultSet result =  stmt.executeQuery();
            while (result.next()){
                CountryArea CountryArea = new CountryArea(
                        result.getString("area_id"),
                        result.getString("area_name"));
                resultList.add(CountryArea);

//            Так как в запросе мы написали street_code AS id, street_name AS name.
//            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!

                System.out.println();
                System.out.println(result.getLong("area_id") +" : "+
                        result.getString("area_name"));
            }

        } catch (SQLException e){
            throw new DaoException(e);

        }
        return  resultList;
    }

    private String bildParam(String areaId) {
        String result = "";
        if (areaId == null || areaId.trim().isEmpty()){
            result =  "__0000000000";
        }
        else if (areaId.endsWith("0000000000"))  {
            result =   areaId.substring(0,2) + "___0000000";
        } else if (areaId.endsWith("0000000")) {
            result=   areaId.substring(0,5) + "___0000";
        }else if (areaId.endsWith("0000")) {
            result =   areaId.substring(0,8) + "____";
        }
        return result;
    }


}


//    SELECT street_code AS id, street_name AS name FROM jc_street WHERE UPPER(street_name) LIKE UPPER('%len%');