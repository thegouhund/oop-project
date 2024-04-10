package com.project.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
    protected Connection connection;

    public DAO(Connection connection) {
        this.connection = connection;
    }

    public abstract T getById(int id);

    public abstract ArrayList<T> getAll();

    public abstract void update(T entity, int id);

    public abstract void delete(int id);

    public abstract void add(T entity);

}

