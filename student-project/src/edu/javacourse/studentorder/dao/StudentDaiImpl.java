package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.StudentOrderStatus;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDaiImpl implements StudentOrderDao{

    private  static  final String GET_STUDENT_ORDER = "SELECT * FROM jc_student_order " +
            "WHERE stident  ";
    private  static  final String INSERT_ORDER = "INSERT  INTO jc_student_order ( " +
            "student_order_stattus, student_order_date," +
            " h_sur_name, h_given_name, h_patronymic, h_date_of_birth," +
            "h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index, h_street_code," +
            "h_building, h_extension, h_apartment, w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria," +
            "w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension," +
            "w_apartment, certificate_id, register_office_id, marriage_date )"+
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?   ); ";



    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        try(Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_ORDER)){
                    stmt.setInt(1, StudentOrderStatus.START.ordinal());
                    stmt.setTimestamp(2, java.sql.Timestamp.valueOf(so.getStudentOrderDate()));
                    stmt.setString(3, so.getHusband().getSurName());
                    stmt.setString(4, so.getHusband().getGivenName());
                    stmt.setString(5, so.getHusband().getPatronymic());
                    stmt.setDate(6, java.sql.Date.valueOf(so.getHusband().getDateOfBirth()));
                } catch (SQLException e){
                throw new DaoException(e);

                }
                return  resultList;




        return null;
    }
    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));

        return connection;
    }
}

//    List<Street> resultList = new LinkedList<>();
//        try(Connection con = getConnection();
//                PreparedStatement stmt = con.prepareStatement(GET_STREET)){
//                stmt.setString(1, "%"+mask+"%" );
//                ResultSet result =  stmt.executeQuery();
//                while (result.next()){
//                Street str = new Street(result.getLong("id") , result.getString("name"));
//                resultList.add(str);
//
////            Так как в запросе мы написали street_code AS id, street_name AS name.
////            То и из Setaмы должны обращаться к столдбцам как id и Name а не как названия строк в таблице!
//                System.out.println(result.getLong("id") +" : "+ result.getString("name"));
//                }
//
//                } catch (SQLException e){
//                throw new DaoException(e);
//
//                }
//                return  resultList;
