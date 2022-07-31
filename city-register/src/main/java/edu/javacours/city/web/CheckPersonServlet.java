package edu.javacours.city.web;

import edu.javacours.city.dao.PersonCheckDao;
import edu.javacours.city.dao.PoolConnectionBuilder;
import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import edu.javacours.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet(name ="CheckPersonServlet", urlPatterns = {"/checkPerson"}, loadOnStartup = 1)
@WebServlet(name ="CheckPersonServlet", urlPatterns = {"/checkPerson"})

public class CheckPersonServlet extends HttpServlet {

    private PersonCheckDao dao;
    private static  final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        logger.info("SERVER - service method call, regues param :  " + req.getParameter("surname"));
    }

    @Override
    public void init() throws ServletException {
        logger.info("SERVER is created");
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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


        try {PersonResponse ps = dao.checkPerson(pr);

            if (ps.isRegistered()){

                resp.getWriter().write("Registered");

            } else {

                resp.getWriter().write("Not registered");
            }

        } catch (PersonCheckException e) {
            e.printStackTrace();
        }

    }



}

