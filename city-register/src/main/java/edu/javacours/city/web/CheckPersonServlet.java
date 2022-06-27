package edu.javacours.city.web;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;

import edu.javacours.city.dao.PoolConnectionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.javacours.city.dao.PersonCheckDao;
import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import edu.javacours.city.exception.PersonCheckException;

import java.io.*;
import java.time.LocalDate;
//import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet(name ="CheckPersonServlet", urlPatterns = {"/checkPerson"}, loadOnStartup = 1)
@WebServlet(name ="CheckPersonServlet", urlPatterns = {"/checkPerson"} )
public class CheckPersonServlet extends HttpServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        logger.info("SERVER - service method call, regues param :  " + req.getParameter("surname"));
    }

    private PersonCheckDao dao;
    private static  final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);


    @Override
    public void init() throws ServletException {
//        super.init();
        logger.info("SERVER is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

        req.setCharacterEncoding("UTF-8");
        String surname = req.getParameter("surname");

        System.out.println(surname);

        PersonRequest pr = new PersonRequest();
        pr.setSurName(surname);
        pr.setGivenName("Павел");
        pr.setPatronymic("Николаевич");
        pr.setDateOfBird(LocalDate.of(1995, 3, 18));
        pr.setStreetCode(1);
        pr.setBuilding("10");
        pr.setExtension("2");
        pr.setApartment("121");






//
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();

        try {

            PersonResponse ps = dao.checkPerson(pr);

            if (ps.isRegistered()){

                resp.getWriter().write("Registered");

            } else {

                resp.getWriter().write("Not registered");
            }

        } catch (PersonCheckException e) {
            e.printStackTrace();
        }




    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletRequestvletResponse resp) throws ServletException, IOException {
////        super.doGet(req, resp);
//        resp.getWriter().println("Get CheckPerson - called");
//    }


}

