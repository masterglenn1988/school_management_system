package edu.ph.myschoolportal.dao.impl;

import edu.ph.myschoolportal.dao.GenericDao;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    /**
     * getSession() from EntityManager which is already configured in spring-boot-starter-data-jpa.
     * I inject EntityManager instead of SessionFactory
     */
    @PersistenceContext
    private EntityManager entityManager;

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    protected GenericDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return this.entityManager.unwrap(Session.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(T entity) {
        currentSession().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T entity) {
        currentSession().update(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T read(String id) {
        return currentSession().get(this.clazz, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findAll() {
        CriteriaQuery<T> cq = currentSession().getCriteriaBuilder().createQuery(this.clazz);
        cq.from(this.clazz);
        return currentSession().createQuery(cq).getResultList();
    }
}
