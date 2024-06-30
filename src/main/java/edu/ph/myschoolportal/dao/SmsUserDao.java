package edu.ph.myschoolportal.dao;

import edu.ph.myschoolportal.model.entity.SmsUser;
import edu.ph.myschoolportal.exception.DaoException;

import java.util.List;

public interface SmsUserDao extends GenericDao<SmsUser>{
    public void create(SmsUser smsUser) throws DaoException;
    public SmsUser read(String id);
    public void update(SmsUser smsUser);
    public void delete(SmsUser smsUser);
    public List<SmsUser> findAll();
}
