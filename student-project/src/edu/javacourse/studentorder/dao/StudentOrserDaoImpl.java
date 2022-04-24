package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentOrserDaoImpl implements StudentOrderDao{
//  старый
//    private  static  final String SELECT_ORDERS = "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
//            "hpo.p_office_area_id AS h_p_office_area_id , hpo.p_office_name AS h_p_office_name," +
//            " wpo.p_office_area_id AS w_p_office_area_id, wpo.p_office_name AS w_p_office_name " +
//            "FROM jc_student_order AS so INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id " +
//            "INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id " +
//            "INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id " +
//            "WHERE student_order_status = 0 ORDER BY student_order_date;";

    private  static  final String SELECT_ORDERS = "SELECT so.*, ro.r_office_area_id, ro.r_office_name, " +
            "hpo.p_office_area_id AS h_p_office_area_id , hpo.p_office_name AS h_p_office_name," +
            " wpo.p_office_area_id AS w_p_office_area_id, wpo.p_office_name AS w_p_office_name " +
            "FROM jc_student_order AS so INNER JOIN jc_register_office AS ro ON ro.r_office_id = so.register_office_id " +
            "INNER JOIN jc_passport_office AS hpo ON hpo.p_office_id = so.h_passport_office_id " +
            "INNER JOIN jc_passport_office AS wpo ON wpo.p_office_id = so.w_passport_office_id " +
            "WHERE student_order_status = ? ORDER BY student_order_date;";

    private  static final String SELECT_CHILD =
            "SELECT soc.*, ro.r_office_area_id, ro.r_office_name FROM jc_student_child AS soc " +
            "INNER JOIN jc_register_office AS ro ON ro.r_office_id = soc.c_register_office_id " +
            "WHERE soc.student_order_id IN ";



    private  static  final String INSERT_ORDER = "INSERT  INTO jc_student_order ( " +
            "student_order_status, student_order_date," +
            " h_sur_name, h_given_name, h_patronymic, h_date_of_birth," +
            "h_passport_seria, h_passport_number, h_passport_date, h_passport_office_id, h_post_index, h_street_code," +
            "h_building, h_extension, h_apartment, h_university_id, h_student_number, w_sur_name, w_given_name, w_patronymic, w_date_of_birth, w_passport_seria," +
            "w_passport_number, w_passport_date, w_passport_office_id, w_post_index, w_street_code, w_building, w_extension," +
            "w_apartment,w_university_id, w_student_number, certificate_id, register_office_id, marriage_date )"+
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?  ); ";

    private static  final String  INSERT_CHILD ="INSERT INTO jc_student_child (" +
            "     student_order_id, c_sur_name, c_given_name, c_patronymic," +
            "    c_date_of_birth, c_certificate_number, c_certificate_date, c_register_office_id, c_post_index," +
            "    c_street_code, c_building, c_extension, c_apartment)" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";


    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1l;

         try(Connection con = getConnection();

                PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String []{"student_order_id"})){
             System.out.println(" PSQL Connect");

             con.setAutoCommit(false);
             try {

                         //Header
                         stmt.setInt(1, StudentOrderStatus.START.ordinal());
                         stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                         writingParanToStatement(stmt, 3, so.getHusband());
                         writingParanToStatement(stmt, 18, so.getWife());

                         stmt.setString(33, so.getMarriageCertificateId());
                         stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                         stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

                         stmt.executeUpdate();

                         ResultSet gkRs =   stmt.getGeneratedKeys();

                         if(gkRs.next()) {
                             result = gkRs.getLong(1);
                             System.out.println("сделана запись: "+ result);
                         }

                         saveChild( con,  so, result );
                         con.commit();  // Завершение транзакции и записывает в БД

             } catch(SQLException ex) {
                 con.rollback();
                 throw ex;
             }

//

         } catch (SQLException e){
                throw new DaoException(e);
                }
                return  result;
    }

    private void saveChild(Connection con, StudentOrder so, Long soId) throws SQLException {
        Long res = -1l;
        try( PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)){
            for(Child child : so.getChildren()){
                stmt.setLong(1, soId);
                setParamsFroChild (stmt , child);
//                stmt.executeUpdate();
                stmt.addBatch();
            }
            stmt.executeBatch();
            ResultSet rs =   stmt.getGeneratedKeys();
            if(rs.next()) {
                res = rs.getLong(1);
                System.out.println("Child  запись: "+ res);
            }
        }
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
        stmt.setLong(start+13, adult.getUnivesity().getUniversityId());
        stmt.setString(start+14, adult.getStudentId());
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));

        return connection;
    }

    private void setParamsFroChild (PreparedStatement stmt, Child child) throws SQLException{
        setParamsFroPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddres( stmt, 9,  child);
    };

    private void setParamsFroPerson(PreparedStatement stmt, int sts, Child child) throws SQLException {
        stmt.setString(sts, child.getSurName());
        stmt.setString(sts+1, child.getGivenName());
        stmt.setString(sts+2, child.getPatronymic());
        stmt.setDate(sts+3, Date.valueOf(child.getDateOfBirth()));

    }

    private void setParamsForAddres(PreparedStatement stmt, int sts, Person person) throws SQLException {
        //Huasbend Adress
        Address p_adress = person.getAddress();
        stmt.setString(sts , p_adress.getPostCode());
//        stmt.setLong(sts +1, p_adress.getStreet().getStreetCode());
        stmt.setLong(sts +1, 1);
        stmt.setString(sts +2, p_adress.getBuilding());
        stmt.setString(sts +3, p_adress.getExtension());
        stmt.setString(sts +4, p_adress.getApartment());
    }



    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        List<StudentOrder> resulr = new LinkedList<>();
        try(Connection con = getConnection();

            PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)){
            stmt.setInt(1, StudentOrderStatus.START.ordinal() );
            ResultSet rs = stmt.executeQuery();
//            List<Long> ids = new LinkedList<>();

                while (rs.next()){

                    StudentOrder so = new StudentOrder();
                    fillStudentOrder(rs, so);
                    fillMarriage(rs, so);

                    Adult husbend = fillAdult(rs, "h_");
                    Adult wife = fillAdult(rs, "w_");
                    so.setHusband(husbend);
                    so.setWife(wife);


                    resulr.add(so);


//                    ids.add(so.getStudentOrderId());
                }
//                StringBuilder sb = new StringBuilder("(");
//                    for(Long id: ids){
//                        sb.append((sb.length()>1 ? ",": "") + String.valueOf(id) );
//                    }
//                sb.append(")");

            findChild(con, resulr);

            rs.close();


        } catch (SQLException ex) {
            throw new DaoException(ex);
        }

        return resulr;
    }


    private  void findChild(Connection con, List<StudentOrder> result) throws SQLException {
        String cl  = "(" + result.stream().map(so-> String.valueOf(so.getStudentOrderId())).collect(Collectors.joining(",")) + ")";

        try (PreparedStatement stmt = con.prepareStatement(SELECT_CHILD+cl)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println( "NTCN    " +rs.getLong(1 ) + rs.getString(3));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValues(rs.getInt("student_order_status")));
    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());

        Long roid = rs.getLong("register_office_id");
        String roCode = rs.getString("r_office_area_id");
        String  roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roid, roCode, roName);
        so.setMarriageOffice(ro);

    }
    private Adult fillAdult(ResultSet rs, String p) throws SQLException, DaoException{
        String surName = rs.getString(p+"sur_name");
        String givenName = rs.getString(p+"given_name");
        String patronymic = rs.getString(p+"patronymic");
        LocalDate dateOfBirth = rs.getDate(p+"date_of_birth").toLocalDate();
        String passportSeria  = rs.getString(p+"passport_seria");
        String passportNumber  = rs.getString(p+"passport_number");
        LocalDate passportDate = rs.getDate(p+"passport_date").toLocalDate();

        String pastIndex  = rs.getString(p+"post_index");
        Long streetCode = rs.getLong(p+"street_code");
//        String building  = rs.getString(p+"building");
//        String extension  = rs.getString(p+"extension");
//        String apartment  = rs.getString(p+"apartment");
        Long universityId = rs.getLong(p+"university_id");
        String studentNumber  = rs.getString(p+"student_number");

        Adult adult= new Adult( surName,  givenName,  patronymic,  dateOfBirth) ;
        adult.setPassportSeria(passportSeria);
        adult.setPassportNumber(passportNumber);
        adult.setIssueDate(passportDate);

        //passport Adalt

        Long poId = rs.getLong(p+"passport_office_id");
        String poAreaCod = rs.getString( p +"p_office_area_id");
        String poName = rs.getString( p +"p_office_name");


        PassportOffice adaltPasportOffice = new PassportOffice( poId, poAreaCod, poName) ;
        adult.setIssueDepartment(adaltPasportOffice);

//        Street  street = new Street(streetCode, "streetName");

        University university = new University(universityId, "university Name");
        adult.setUnivesity(university);
        adult.setStudentId(studentNumber);
        return adult;
    }

}
