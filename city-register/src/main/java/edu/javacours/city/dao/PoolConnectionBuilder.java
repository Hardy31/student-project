package edu.javacours.city.dao;

import org.postgresql.ds.common.BaseDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;






public class PoolConnectionBuilder implements ConnectionBuilder{


    private DataSource dataSource;
//    private BaseDataSource dataSource;
    private static  final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);

    public PoolConnectionBuilder() {
        try{




            Context ctx = new InitialContext();
            logger.info(" PullConnectionBuilder. PoolConnectionBuilder() строка 22 - init Context" );
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cityRegisterPool");
//            dataSource = (BaseDataSource) ctx.lookup("java:comp/env/jdbc/cityRegister");





            logger.info(" PullConnectionBuilder. PoolConnectionBuilder() строка 24  - dataSource" );
            logger.info(" PullConnectionBuilder. "   );

        } catch (NamingException e){
            logger.error(" PullConnectionBuilder 27 ", e);
//            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        logger.info(" PullConnectionBuilder. getConnection()  строка 43  - перед созданием Соединения" );
//        Connection res = dataSource.getConnection();
        Connection res = dataSource.getConnection();
        logger.info(" PullConnectionBuilder. getConnection()  строка 45  - после создания Соединения" );
        return res;
    }



}
