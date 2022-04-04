package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface DictionaryDao {

    List<Street> findStreets(String mask) throws DaoException;
    List<PassportOffice> findPassportOffice(String areaID) throws DaoException;
    List<RegisterOffice> findRegisterOffice(String areaID) throws DaoException;

}
