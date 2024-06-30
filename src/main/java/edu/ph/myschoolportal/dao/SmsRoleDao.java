package edu.ph.myschoolportal.dao;

import edu.ph.myschoolportal.model.entity.SmsRole;

public interface SmsRoleDao extends GenericDao<SmsRole>{

    public void create(SmsRole smsRole);
}
