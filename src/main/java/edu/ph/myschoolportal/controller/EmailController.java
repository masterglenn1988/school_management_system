package edu.ph.myschoolportal.controller;

import edu.ph.myschoolportal.dto.EmailDto;
import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.model.ApiResponse;
import edu.ph.myschoolportal.model.Email;
import edu.ph.myschoolportal.service.EmailService;
import edu.ph.myschoolportal.service.LoggingService;
import edu.ph.myschoolportal.util.ObjectUtils;
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
@Tag(name = "Email Controller")
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final LoggingService loggingService;

    /** Returns a ApiResponse object if the user
     * successfully send an email message
     * @param emailDto emailDto
     * @param httpServletRequest httpServletRequest
     * @throws ResponseStatusException ex
     * @return ApiResponse response
     * @see #sendEmail(EmailDto, HttpServletRequest)
     */
    @PostMapping(value = "send-email")
    public ResponseEntity<ApiResponse> sendEmail(@Valid @RequestBody EmailDto emailDto,  HttpServletRequest httpServletRequest) {
        loggingService.info("", this.getClass().getSimpleName(), "", "EmailDto : " + emailDto.toString());
        try {
            Email email = ObjectUtils.copyAs(emailDto, Email.class);
            ApiResponse response = emailService.sendEmail(email);
            return new ResponseEntity<>(ApiResponse.apiSuccessResponse(
                    response.getMessage(),
                    httpServletRequest.getRequestURI(),
                    HttpCode.OK.getValue()),
                    HttpStatus.OK);
        } catch (ServiceException ex) {
            loggingService.error("", this.getClass().getSimpleName(), ex.getMessage(), ex.getStatus());
            throw new ResponseStatusException(ex.getStatus(), ex.getMessage(), ex);
        }
    }

}
