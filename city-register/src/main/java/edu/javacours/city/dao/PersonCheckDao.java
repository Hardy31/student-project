package edu.javacours.city.dao;

import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import edu.javacours.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PersonCheckDao implements ConnectionBuilder {
    private static  final Logger logger = LoggerFactory.getLogger(PersonCheckDao.class);


    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public Connection getConnection() throws SQLException {

//        Должно быть так !
//        return connectionBuilder.getConnection();

//        ЗАХАРДКОДИЛ
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){

        }
        return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/cr", "cr", "123");

    }

    //    public PersonCheckDao(){
//        try {
//            Class.forName("org.postgresql.Driver");
//        }catch (Exception e){
//
//        }
//    }

    private  static  final String SQL_REQUEST = "SELECT temporal FROM cr_address_persone AS ap\n" +
            "INNER JOIN cr_person AS p ON p.person_id = ap.person_id\n" +
            "INNER JOIN cr_address AS a ON a.address_id = ap.address_id\n" +
            "INNER JOIN cr_street AS s ON a.street_code = s.street_code\n" +
            "WHERE\n" +
            "CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <= ap.end_date OR ap.end_date IS NULL)\n" +
            "and UPPER(p.sur_name) = UPPER(?)\n" +
            "and UPPER(p.given_name) = UPPER(?)\n" +
            "and UPPER(p.patronymic) = UPPER(?)\n" +
            "and p.date_of_birth = ?\n" +
            "and a.street_code = ?\n" +
            "and UPPER(a.building) = UPPER(?)\n"
            ;



    public PersonResponse checkPerson(PersonRequest request) throws  PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;
        if (request.getExtension() != null) {
            sql +=  "and UPPER(a.extension) = UPPER(?)\n";
        } else {
            sql += "and a.extension is null\n";
        }
        if (request.getApartment() != null) {
            sql +=  "and UPPER(a.apartment) = UPPER(?)\n";
        }else {
            sql += "and a.extension is null\n";
        }
        sql += ";";
//        System.out.println(sql);

        try (Connection con = getConnection();
//             logger.info(" PullConnectionBuilder. PoolConnectionBuilder() строка 23 - init Context" );
             PreparedStatement stmtm = con.prepareStatement(sql);

        ) {
//            logger.info("++ SQL +++ " + sql);
//            System.out.println("Соединение с БД");
            int count = 1;
            stmtm.setString(count++, request.getSurName());
            stmtm.setString(count++, request.getGivenName());
            stmtm.setString(count++, request.getPatronymic());
            stmtm.setDate(count++, java.sql.Date.valueOf(request.getDateOfBird()));
            stmtm.setInt(count++, request.getStreetCode());
            stmtm.setString(count++, request.getBuilding());

            if (request.getExtension() != null ){
                stmtm.setString(count++, request.getExtension());
            }

            if (request.getApartment() != null ){
                stmtm.setString(count++, request.getApartment());
            }


            ResultSet rs = stmtm.executeQuery();

            if (rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex) {
            throw new PersonCheckException(ex);
        }
        return  response;
    }



}
