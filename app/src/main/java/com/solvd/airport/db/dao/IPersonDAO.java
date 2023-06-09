package com.solvd.airport.db.dao;

import com.solvd.airport.db.dao.model.Person;

import java.sql.SQLException;

public interface IPersonDAO extends IDAO<Person> {


    void deletePersonByObject(Person p) throws SQLException, InterruptedException;
}
