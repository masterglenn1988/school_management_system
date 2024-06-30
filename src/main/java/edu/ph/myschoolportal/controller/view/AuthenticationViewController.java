package edu.ph.myschoolportal.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cdsga-sms/v1")
public class AuthenticationViewController {

    @GetMapping(value = "/login")
    public String login(){
        return "/mainpage/sms-login-mainpage";
    }

    @GetMapping(value = "/forgot-password")
    public String forgotPassword(){
        return "/mainpage/sms-forgot-password-mainpage";
    }
}
