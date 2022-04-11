package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Address;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.StudentOrderStatus;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?   ); ";



    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        try(Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_ORDER)){
                    //Header
                    stmt.setInt(1, StudentOrderStatus.START.ordinal());
                    stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                    //Huasbend
                    Adult husbend = so.getHusband();
                    stmt.setString(3, husbend.getSurName());
                    stmt.setString(4, husbend.getGivenName());
                    stmt.setString(5, husbend.getPatronymic());
                    stmt.setDate(6, java.sql.Date.valueOf(husbend.getDateOfBirth()));
                    stmt.setString(7, husbend.getPassportSeria());
                    stmt.setString(8, husbend.getPassportNumber());
                    stmt.setDate(9, java.sql.Date.valueOf(husbend.getIssueDate()));
                    stmt.setLong(10, husbend.getIssueDepartment().getOfficeId());
                    //Huasbend Adress
                    Address h_adress = husbend.getAddress();
                    stmt.setString(11, h_adress.getPostCode());
                    stmt.setLong(12, h_adress.getStreet().getStreetCode());
                    stmt.setString(13, h_adress.getBuilding());
                    stmt.setString(14, h_adress.getExtension());
                    stmt.setString(15, h_adress.getApartment());

                    //Wife
                    Adult wife = so.getWife();
                    stmt.setString(16, wife.getSurName());
                    stmt.setString(17, wife.getGivenName());
                    stmt.setString(18, wife.getPatronymic());
                    stmt.setDate(19, java.sql.Date.valueOf(wife.getDateOfBirth()));
                    stmt.setString(20, wife.getPassportSeria());
                    stmt.setString(21, wife.getPassportNumber());
                    stmt.setDate(22, java.sql.Date.valueOf(wife.getIssueDate()));
                    stmt.setLong(23, wife.getIssueDepartment().getOfficeId());
                    //Huasbend Adress
                    Address w_adress = wife.getAddress();
                    stmt.setString(24, w_adress.getPostCode());
                    stmt.setLong(25, w_adress.getStreet().getStreetCode());
                    stmt.setString(26, w_adress.getBuilding());
                    stmt.setString(27, w_adress.getExtension());
                    stmt.setString(28, w_adress.getApartment());


                    stmt.setString(29, so.getMarriageCertificateId());
                    stmt.setLong(30, so.getMarriageOffice().getOfficeId());
                    stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));

                    stmt.executeUpdate();

                } catch (SQLException e){
                throw new DaoException(e);

                }
                return  0l;





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
