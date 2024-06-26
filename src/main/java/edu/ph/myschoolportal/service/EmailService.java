package edu.ph.myschoolportal.service;

import edu.ph.myschoolportal.enums.HttpCode;
import edu.ph.myschoolportal.exception.ServiceException;
import edu.ph.myschoolportal.model.ApiResponse;
import edu.ph.myschoolportal.model.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailService {

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private final JavaMailSender javaMailSender;
    private final LoggingService loggingService;

    /*
     * This JavaMailSender Interface is used to send Mail in Spring Boot. This
     * JavaMailSender extends the MailSender Interface which contains send()
     * function. SimpleMailMessage Object is required because send() function uses
     * object of SimpleMailMessage as a Parameter
     */
    public ApiResponse sendEmail(Email email) throws ServiceException {
        loggingService.info("", this.getClass().getSimpleName(), "", "Email : " + email.toString());
        ApiResponse apiResponse;
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email.getTo());
            mail.setFrom(email.getFrom());
            mail.setSubject(email.getSubject());
            mail.setText(email.getBody());
            javaMailSender.send(mail);

            apiResponse = ApiResponse.builder()
                    .message(Collections.singletonList("Congratulations! Your mail has been sent to " + email.getTo()))
                    .build();
        }catch (MailException ex){
            throw new ServiceException(ex.getMessage(), HttpCode.UNAUTHORIZED.getValue());
        }
        return apiResponse;
    }

    /**
     * This fucntion is used to send mail that contains a attachment.
     *
     * @param email email
     * @throws MailException
     * @throws MessagingException
     */
    public void sendEmailWithAttachment(Email email) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email.getTo());
        helper.setFrom(email.getFrom());
        helper.setSubject(email.getSubject());
        helper.setText(email.getBody());

        ClassPathResource classPathResource = new ClassPathResource(email.getAttachment());
        helper.addAttachment(Objects.requireNonNull(classPathResource.getFilename()), classPathResource);

        javaMailSender.send(mimeMessage);
    }

}
