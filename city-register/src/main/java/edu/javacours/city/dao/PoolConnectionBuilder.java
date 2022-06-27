package edu.javacours.city.dao;

import edu.javacours.city.web.CheckPersonServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder{

    private static  final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);

    private DataSource dataSource;

    public PoolConnectionBuilder() {
        try{
            Context ctx = new InitialContext();
            logger.info(" PullConnectionBuilder. PoolConnectionBuilder() строка 23 - init Context" );
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cityRegister");
            logger.info(" PullConnectionBuilder. PoolConnectionBuilder() строка 25  - dataSource" );

        } catch (NamingException e){
            logger.error(" PullConnectionBuilder 26 ", e);
//            e.printStackTrace();
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
