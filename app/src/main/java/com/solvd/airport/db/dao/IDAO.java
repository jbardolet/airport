package com.solvd.airport.db.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO <T>{

    //INSERT
    void insert (T t) throws SQLException, InterruptedException;

    //SELECT
    T getById(Integer id) throws SQLException, InterruptedException;
    List<T> getAll() throws SQLException, InterruptedException;

    //UPDATE

    //DELETE
    void deletePersonById(Long id) throws SQLException, InterruptedException;
}
