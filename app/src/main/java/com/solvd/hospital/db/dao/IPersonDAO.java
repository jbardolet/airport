package com.solvd.hospital.db.dao;

import com.solvd.hospital.db.dao.model.Person;

public interface IPersonDAO extends IDAO<Person> {

    void deletePersonById(Integer id);
    void deletePersonByObject(Person p);
}
