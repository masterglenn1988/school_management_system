package edu.ph.myschoolportal.component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ph.myschoolportal.model.entity.SmsUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(SmsUser smsUser, List<String> rolesList, String issuer) {
        Date now = new Date(System.currentTimeMillis());
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withClaim("userId", smsUser.getUserId())
                .withClaim("firstName", smsUser.getFirstName())
                .withClaim("username", smsUser.getUsername())
                .withClaim("roles", rolesList)
                .withIssuer(issuer)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

}
