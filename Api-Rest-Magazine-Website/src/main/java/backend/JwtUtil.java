/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Base64;

/**
 *
 * @author brigidoalvarado
 */
public class JwtUtil {

    private static final String SECRET_KEY = "ipc-2";
    private static final String USER_TYPE = "userType";
    private String token;

    public String generatedToken(String userName, String userType) {

        token = Jwts.builder()
                .setSubject(userName)
                .claim(USER_TYPE, userType)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
        return token;
    }

    public String getUserName() {
        return getClaims().getSubject();
    }
    
    public String getUserType(){
        return getClaims().get(USER_TYPE, String.class);
    }

    private Claims getClaims( ) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
