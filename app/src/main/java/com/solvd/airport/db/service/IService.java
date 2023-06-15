package com.solvd.airport.db.service;

import com.solvd.airport.db.dao.DataConectionExeption;
import com.solvd.airport.db.dao.model.Airplane;

import java.util.List;

public interface IService<T>{

    T getById(Long id) throws DataConectionExeption;
    List<T> getAll() throws DataConectionExeption;
    void insert(T t) throws DataConectionExeption;
    void deleteById(Long id) throws DataConectionExeption;
}
