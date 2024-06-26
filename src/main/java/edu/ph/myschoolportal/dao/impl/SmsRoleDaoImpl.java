package edu.ph.myschoolportal.dao.impl;

import edu.ph.myschoolportal.dao.SmsRoleDao;
import edu.ph.myschoolportal.model.SmsRole;
import edu.ph.myschoolportal.enums.IdPrefix;
import edu.ph.myschoolportal.service.LoggingService;
import edu.ph.myschoolportal.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsRoleDaoImpl extends GenericDaoImpl<SmsRole> implements SmsRoleDao {

    private final LoggingService loggingService;
    private final IdGenerator idGenerator;

    @Override
    public void create(SmsRole smsRole) {
        loggingService.info("", this.getClass().getSimpleName(), "", "SmsUser : " + smsRole.toString());
        smsRole.setRoleId(idGenerator.generateCustomId(IdPrefix.RS.getId()));
        this.save(smsRole);
    }
}
