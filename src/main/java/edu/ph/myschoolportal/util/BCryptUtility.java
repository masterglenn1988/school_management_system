/*
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 02/06/2023
 */

package edu.ph.myschoolportal.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;

@Component
@RequiredArgsConstructor
public class BCryptUtility {

    private final PasswordEncoder encoder;

    public String passwordEncoder(String password){
        return encoder.encode(CharBuffer.wrap(password));
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword){
        return encoder.matches(CharBuffer.wrap(rawPassword), encodedPassword);
    }
}
