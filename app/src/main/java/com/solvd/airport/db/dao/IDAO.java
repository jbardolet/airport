package com.solvd.airport.db.dao;

import java.util.List;

public interface IDAO <T>{

    //INSERT
    void insert (T t) throws DataConectionExeption;

    //SELECT
    T getById(Long id) throws  DataConectionExeption;
    List<T> getAll() throws DataConectionExeption;

    //DELETE
    void deleteById(Long id) throws DataConectionExeption;
}
