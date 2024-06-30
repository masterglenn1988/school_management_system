package edu.ph.myschoolportal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Schema(example = "glenmark1018@gmail.com")
    private String username;

    @Schema(example = "p@ssw0rd1234")
    private String password;
}
