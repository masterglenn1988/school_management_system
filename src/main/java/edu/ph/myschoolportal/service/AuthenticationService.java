package edu.ph.myschoolportal.service;

import edu.ph.myschoolportal.config.EmailConfigProperties;
import edu.ph.myschoolportal.model.ApiResponse;
import edu.ph.myschoolportal.model.Email;
import edu.ph.myschoolportal.model.SmsUser;
import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.enums.UserStatus;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.repository.SmsUserRepository;
import edu.ph.myschoolportal.util.BCryptUtility;
import edu.ph.myschoolportal.util.CommonStringUtility;
import edu.ph.myschoolportal.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final LoggingService loggingService;
    private final EmailConfigProperties emailConfigProperties;
    private final EmailService emailService;
    private final SmsUserRepository smsUserRepository;
    private final BCryptUtility encoder;

    public ApiResponse authenticate(String username, String password) throws ServiceException {
        ApiResponse apiResponse;
        loggingService.info("", this.getClass().getSimpleName(), "", "username : " + username + " password : " + password);
        SmsUser smsUser = smsUserRepository.findByUsername(username);
        if(!ObjectUtils.isEmpty(smsUser)){
            if(!smsUser.getStatus().equalsIgnoreCase(UserStatus.ACTIVE.toString())){
                throw new ServiceException(CommonStringUtility.ERR_CODE_INACTIVE_SUSPENDED, HttpCode.UNAUTHORIZED.getValue());
            }else{
                if(encoder.passwordMatches(password, smsUser.getPassword())){
                    apiResponse = ApiResponse.builder()
                            .message(Collections.singletonList(CommonStringUtility.SUCCESS_MSG_LOGGED_IN))
                            .build();
                }else{
                    if(smsUser.getAttempts() >= 3){
                        smsUser.setStatus(UserStatus.SUSPENDED.toString());
                        smsUserRepository.save(smsUser);
                        throw new ServiceException(CommonStringUtility.ERR_CODE_LOGIN_TEMPORARY_LOCKED, HttpCode.UNAUTHORIZED.getValue());
                    }
                    int attempts = smsUser.getAttempts() + 1;
                    smsUser.setAttempts(attempts);
                    smsUserRepository.save(smsUser);
                    throw new ServiceException(CommonStringUtility.ERR_CODE_LOGIN_INCORRECT_PASSWORD, HttpCode.UNAUTHORIZED.getValue());
                }
            }
        }else{
            throw new ServiceException(CommonStringUtility.ERR_CODE_LOGIN_USERNAME_NOT_CONNECTED, HttpCode.UNAUTHORIZED.getValue());
        }

        return apiResponse;
    }

    public ApiResponse resetPassword(String email) throws ServiceException {
        loggingService.info("", this.getClass().getSimpleName(), "", "Email : " + email);
        ApiResponse apiResponse;
        try{
            String randomPass = ObjectUtils.randomPasswordGenerator(14);
            String encryptedPass = encoder.passwordEncoder(randomPass);
            SmsUser smsUser = smsUserRepository.findByEmail(email);
            if(!ObjectUtils.isEmpty(smsUser)){
                if(!smsUser.getStatus().equalsIgnoreCase(UserStatus.ACTIVE.toString())){
                    throw new ServiceException(CommonStringUtility.ERR_CODE_INACTIVE_SUSPENDED, HttpCode.UNAUTHORIZED.getValue());
                }
                smsUser.setOldPassword(smsUser.getPassword());
                smsUser.setPassword(encryptedPass);
                smsUserRepository.save(smsUser);
            }else{
                throw new ServiceException(CommonStringUtility.ERR_CODE_LOGIN_USERNAME_NOT_CONNECTED, HttpCode.UNAUTHORIZED.getValue());
            }

            loggingService.info("", this.getClass().getSimpleName(), "", "Random Password : " + randomPass);

            emailService.sendEmail(Email.builder()
                    .from(emailConfigProperties.getUsername())
                    .to(email)
                    .subject("SMS Reset Password")
                    .body("Here is your temporary password : " + randomPass)
                    .build());

            apiResponse = ApiResponse.builder()
                    .message(Collections.singletonList("Successfully reset your password!"))
                    .build();
        }catch(NoSuchAlgorithmException ex){
            throw new ServiceException(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.getValue());
        }
        return apiResponse;
    }

}
