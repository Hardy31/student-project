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

import edu.javacours.city.dao.PersonCheckDao;
import edu.javacours.city.domain.PersonRequest;
import edu.javacours.city.domain.PersonResponse;
import edu.javacours.city.exception.PersonCheckException;

import java.io.*;
import java.time.LocalDate;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name ="CheckPersonServlet", urlPatterns = {"/checkPerson"})
//@WebServlet("/checkPerson")
public class CheckPersonServlet extends HttpServlet {
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


        try {
            PersonCheckDao dao = new PersonCheckDao();
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



}

