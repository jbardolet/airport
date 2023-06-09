package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Person;

import java.sql.SQLException;

public interface IPersonDAO extends IDAO<Person> {

    void deletePersonById(Long id) throws SQLException, InterruptedException;
    void deletePersonByObject(Person p) throws SQLException, InterruptedException;
}
