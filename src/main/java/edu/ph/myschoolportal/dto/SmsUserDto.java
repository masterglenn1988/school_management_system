package edu.ph.myschoolportal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SmsUserDto {

    @Schema(hidden = true)
    private String userId;
    @Schema(example = "Glenn Mark")
    private String firstName;
    @Schema(example = "Trampe")
    private String middleName;
    @Schema(example = "Anduiza")
    private String lastName;
    @Schema(example = "10-18-1988")
    private String dob;
    @Schema(example = "Male")
    private String gender;
    @Schema(example = "09106121529")
    private String contactNo;
    @Schema(example = "Java Developer")
    private String occupation;
    @Schema(example = "XXX-1234-YY")
    private String schoolId;
    @Schema(example = "ROLE_SUPER_ADMIN")
    private String role;
    @Schema(example = "glenmark1018@gmail.com")
    private String email;
    @Schema(example = "glenmark1018@gmail.com")
    private String username;
    @Schema(example = "p@ssw0rd1234")
    private String password;
    @Schema(hidden = true)
    private String oldPassword;
    @Schema(hidden = true)
    private int attempts;
    @Schema(hidden = true)
    private boolean isActive;
    @Schema(hidden = true)
    private String status;
}
