package com.solvd.hospital.db.dao;

import java.util.List;

public interface IDAO <T>{
    void insert (T t);
    T getById(Integer id);
    List<T> getAll();
}
