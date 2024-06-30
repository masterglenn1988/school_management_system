package edu.ph.myschoolportal.controller.rest;

import edu.ph.myschoolportal.model.common.RestApiResponse;
import edu.ph.myschoolportal.dto.request.SmsUserDto;
import edu.ph.myschoolportal.model.entity.SmsUser;
import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.service.LoggingService;
import edu.ph.myschoolportal.service.RegistrationService;
import edu.ph.myschoolportal.util.BCryptUtility;
import edu.ph.myschoolportal.util.ObjectUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Tag(name = "Registration Controller")
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final LoggingService loggingService;
    private final BCryptUtility bCryptUtility;

    /** Returns a ApiResponse object if the user
     * successfully register to the Student Management System web application
     * @param smsUserDto smsUserDto
     * @param httpServletRequest httpServletRequest
     * @throws ResponseStatusException ex
     * @return ApiResponse response
     * @see #registerAccount(SmsUserDto, HttpServletRequest)
     */

    @Operation(summary = "Register Account")
    @PostMapping(path = "/register-account")
    public ResponseEntity<RestApiResponse> registerAccount(@Valid @RequestBody SmsUserDto smsUserDto, HttpServletRequest httpServletRequest){
        try{
            SmsUser smsUser = ObjectUtils.copyAs(smsUserDto, SmsUser.class);
            RestApiResponse response = registrationService.registerAccount(smsUser);
            loggingService.info("", this.getClass().getSimpleName(), "", response.toString());
            return new ResponseEntity<>(RestApiResponse.apiSuccessResponse(
                    response.getMessage(),
                    httpServletRequest.getRequestURI(),
                    HttpCode.CREATED.getValue()),
                    HttpStatus.CREATED);
        }catch(ServiceException ex){
            loggingService.error("", this.getClass().getSimpleName(), ex.getMessage(), ex.getStatus());
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage(), ex);
        }
    }
}
