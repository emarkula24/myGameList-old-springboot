package game.backlog.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private  final SecretKey key = Jwts.SIG.HS256.key().build();

    public String createToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();

    }

    public String validateToken(String username, String accesstoken) {
        try {
            assert Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(accesstoken)
                    .getPayload()
                    .getSubject()
                    .equals(username);
        } catch (
                JwtException ex) {
            return "JWT parsing failed";
        }
        return "Parsed jwt";
    }
}
