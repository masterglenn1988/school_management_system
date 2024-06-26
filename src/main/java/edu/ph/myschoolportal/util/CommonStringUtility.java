package edu.ph.myschoolportal.util;

public class CommonStringUtility {

    /*==========================CUSTOM SUCCESS MESSAGES FOR LOGIN ========================*/
    public static final String  SUCCESS_MSG_LOGGED_IN = "Logged in successfully";

    /*==========================CUSTOM ERROR MESSAGES FOR LOGIN ========================*/
    public static final String  ERR_CODE_INACTIVE_SUSPENDED = "Your account has been inactive/suspended. Please contact the administrator to resolve this issue.";
    public static final String  ERR_CODE_LOGIN_USERNAME_NOT_CONNECTED = "The username you entered is not connected to an account.";
    public static final String  ERR_CODE_LOGIN_INCORRECT_PASSWORD = "The password you have entered is incorrect.";
    public static final String  ERR_CODE_LOGIN_TEMPORARY_LOCKED = "You have entered an incorrect password too many times and your account is temporarily locked. Please contact the administrator to resolve this issue.";

    public static final String  ERR_CODE_EMAIL_IS_ALREADY_REGISTERED = "Email %s is already registered.";

    public static final String  ERR_CODE_USER_NOT_UPDATED = "User not updated : %s";
    public static final String  ERR_CODE_ROLE_NOT_UPDATED = "Role not updated : %s";

    public static final String EQ_LOGIN_ACCT = "Login";

    public static final String  ERR_CODE_EARTHQUAKE_READING_NOT_CREATED = "Earthquake Reading not created : %s";
    public static final String  ERR_CODE_EARTHQUAKE_LOG_NOT_CREATED = "Earthquake Log not created : %s";

    public static final String ERR_MSG = "Error : ";

    CommonStringUtility(){
        throw new IllegalArgumentException("");
    }
}
