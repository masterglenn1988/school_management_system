package edu.ph.myschoolportal.service;

import edu.ph.myschoolportal.dao.SmsRoleDao;
import edu.ph.myschoolportal.dao.SmsUserDao;
import edu.ph.myschoolportal.model.common.RestApiResponse;
import edu.ph.myschoolportal.model.entity.SmsRole;
import edu.ph.myschoolportal.model.entity.SmsUser;
import edu.ph.myschoolportal.exception.DaoException;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.util.BCryptUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final LoggingService loggingService;
    private final SmsUserDao smsUserDao;
    private final SmsRoleDao smsRoleDao;
    private final BCryptUtility bCryptUtility;

    @Transactional(propagation = Propagation.REQUIRED)
    public RestApiResponse registerAccount(SmsUser smsUser) throws ServiceException{
        loggingService.info("", this.getClass().getSimpleName(), "", "SmsUser : " + smsUser.toString());
        try{
            SmsUser userCreated = SmsUser.smsUser(smsUser);
            userCreated.setPassword(bCryptUtility.passwordEncoder(userCreated.getPassword()));
            smsUserDao.create(userCreated);

            SmsRole roleCreated = SmsRole.smsRole(new SmsRole(), userCreated);
            loggingService.info("", this.getClass().getSimpleName(), "", "SmsRole : " + roleCreated.toString());
            smsRoleDao.create(roleCreated);
        }catch(DaoException ex){
            throw new ServiceException(ex.getMessage(), ex.getStatus());
        }

        RestApiResponse restApiResponse = new RestApiResponse();
        restApiResponse.setMessage(Collections.singletonList(smsUser));
        return restApiResponse;
    }
}
