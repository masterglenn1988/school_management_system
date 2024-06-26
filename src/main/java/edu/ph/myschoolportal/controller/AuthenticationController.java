package edu.ph.myschoolportal.controller;

import edu.ph.myschoolportal.model.ApiResponse;
import edu.ph.myschoolportal.dto.LoginDto;
import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.service.AuthenticationService;
import edu.ph.myschoolportal.service.LoggingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Tag(name = "Authentication Controller")
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final LoggingService loggingService;

    /** Returns a ApiResponse object if the user
     * successfully login to the Student Management System web application
     * @param loginDto loginDto
     * @param httpServletRequest httpServletRequest
     * @throws ResponseStatusException ex
     * @return ApiResponse response
     * @see #authenticate(LoginDto, HttpServletRequest)
     */
    @Operation(summary = "Login Account")
    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> authenticate(@Valid @RequestBody LoginDto loginDto,  HttpServletRequest httpServletRequest) {
        try{
            ApiResponse response = authenticationService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            loggingService.info("", this.getClass().getSimpleName(), "", response.toString());
            return new ResponseEntity<>(ApiResponse.apiSuccessResponse(
                    response.getMessage(),
                    httpServletRequest.getRequestURI(),
                    HttpCode.OK.getValue()),
                    HttpStatus.OK);
        }catch(ServiceException ex){
            loggingService.error("", this.getClass().getSimpleName(), ex.getMessage(), ex.getStatus());
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage(), ex);
        }

    }

    /** Returns a ApiResponse object if the user wants to reset the password
     * The system will send a temporary password to be able to login and change the password
     * @param email email
     * @param httpServletRequest httpServletRequest
     * @throws ResponseStatusException ex
     * @return ApiResponse response
     * @see #resetPassword(String, HttpServletRequest)
     */
    @Operation(summary = "Reset Password")
    @PostMapping(path = "/reset-password/{email}")
    public ResponseEntity<ApiResponse> resetPassword(@PathVariable("email") String email, HttpServletRequest httpServletRequest) {
        try{
            ApiResponse response = authenticationService.resetPassword(email);
            loggingService.info("", this.getClass().getSimpleName(), "", response.toString());
            return new ResponseEntity<>(ApiResponse.apiSuccessResponse(
                    response.getMessage(),
                    httpServletRequest.getRequestURI(),
                    HttpCode.OK.getValue()),
                    HttpStatus.OK);
        }catch(ServiceException ex){
            loggingService.error("", this.getClass().getSimpleName(), ex.getMessage(), ex.getStatus());
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage(), ex);
        }
    }
}
