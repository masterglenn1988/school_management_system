package edu.ph.myschoolportal.dao.impl;

import edu.ph.myschoolportal.dao.SmsUserDao;
import edu.ph.myschoolportal.model.entity.SmsUser;
import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.enums.IdPrefix;
import edu.ph.myschoolportal.exception.DaoException;
import edu.ph.myschoolportal.repository.SmsUserRepository;
import edu.ph.myschoolportal.service.LoggingService;
import edu.ph.myschoolportal.util.CommonStringUtility;
import edu.ph.myschoolportal.util.IdGenerator;
import edu.ph.myschoolportal.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsUserDaoImpl extends GenericDaoImpl<SmsUser> implements SmsUserDao {

    private final SmsUserRepository smsUserRepository;
    private final LoggingService loggingService;
    private final IdGenerator idGenerator;

    @Override
    public void create(SmsUser smsUser) throws DaoException {
        loggingService.info("", this.getClass().getSimpleName(), "", "SmsUser : " + smsUser.toString());
        SmsUser user = smsUserRepository.findByEmail(smsUser.getEmail());
        if(!ObjectUtils.isEmpty(user)){
            loggingService.error("", this.getClass().getSimpleName(), String.format(CommonStringUtility.ERR_CODE_EMAIL_IS_ALREADY_REGISTERED, user.getUsername()),
                    HttpCode.CONFLICT.getValue());
            throw new DaoException(String.format(CommonStringUtility.ERR_CODE_EMAIL_IS_ALREADY_REGISTERED, user.getUsername()), HttpCode.CONFLICT.getValue());
        }

        smsUser.setUserId(idGenerator.generateCustomId(IdPrefix.SMS.getId()));
        this.save(smsUser);
    }
}
