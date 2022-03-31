package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.wedding.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface DictionaryDao {

    List<Street> findStreets(String mask) throws DaoException;
//    public List<Street> findStreets(String mask)  throws DaoException {
////        List<Street> findStreey = new LinkedList<>();
//    };
}
