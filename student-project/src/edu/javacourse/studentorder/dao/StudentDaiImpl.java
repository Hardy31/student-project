package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.Address;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.domain.StudentOrderStatus;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
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
        Long result = -1l;

         try(Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String []{"student_order_id"})){
                    //Header
                    stmt.setInt(1, StudentOrderStatus.START.ordinal());
                    stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                    writingParanToStatement(stmt, 3, so.getHusband());
                    writingParanToStatement(stmt, 16, so.getWife());

                    stmt.setString(29, so.getMarriageCertificateId());
                    stmt.setLong(30, so.getMarriageOffice().getOfficeId());
                    stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));

                    stmt.executeUpdate();
                    ResultSet gkRs =   stmt.getGeneratedKeys();
                    if(gkRs.next()) {
                        result = gkRs.getLong(1);
                    }

                } catch (SQLException e){
                throw new DaoException(e);

                }
                return  result;





    }

    private void writingParanToStatement(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        stmt.setString(start, adult.getSurName());
        stmt.setString(start+1, adult.getGivenName());
        stmt.setString(start+2, adult.getPatronymic());
        stmt.setDate(start+3, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start+4, adult.getPassportSeria());
        stmt.setString(start+5, adult.getPassportNumber());
        stmt.setDate(start+6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start+7, adult.getIssueDepartment().getOfficeId());
        //Huasbend Adress
        Address h_adress = adult.getAddress();
        stmt.setString(start+8, h_adress.getPostCode());
        stmt.setLong(start+9, h_adress.getStreet().getStreetCode());
        stmt.setString(start+10, h_adress.getBuilding());
        stmt.setString(start+11, h_adress.getExtension());
        stmt.setString(start+12, h_adress.getApartment());
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
