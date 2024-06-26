/*
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 02/06/2023
 */

package edu.ph.myschoolportal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptUtility {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String passwordEncoder(String v_raw_password){
        return encoder.encode(v_raw_password);
    }

    public boolean passwordMatches(String v_raw_password, String v_encoded_password){
        return encoder.matches(v_raw_password, v_encoded_password);
    }
}
