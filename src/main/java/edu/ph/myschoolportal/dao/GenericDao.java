package edu.ph.myschoolportal.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <T extends Serializable> {
    public void save(final T entity) ;
    public void update(final T entity) ;
    public void delete(final T entity);
    public T read(final String id);
    public List<T> findAll() ;
}
