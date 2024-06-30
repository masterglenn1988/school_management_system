package edu.ph.myschoolportal.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
public class LoginResponseDto {

    private String username;
    private String token;
    private String message;
}
