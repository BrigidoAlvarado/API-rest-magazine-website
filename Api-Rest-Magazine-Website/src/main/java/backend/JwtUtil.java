/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.exception.AccessException;
import backend.model.UserType;
import backend.model.dto.Credential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author brigidoalvarado
 */
public class JwtUtil {

    private static final String SECRET_KEY = "ipc-2";
    private static final String USER_TYPE = "userType";
    private static final long EXPIRATION_TIME_MS = 900000; // 15 minutos en milisegundos
    private String token = null;

    public String generatedToken(String userName, String userType) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME_MS);
        
        token = Jwts.builder()
                .setSubject(userName)
                .claim(USER_TYPE, userType)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
        return token;
    }

    public void validateToken(String tokenToValidate) throws AccessException {
        try {
            // Analizar y validar el token
            token = tokenToValidate;
            getClaims(token);
        }  catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Capturar excepción de token expirado
            throw new AccessException("El token ha expirado");
        } catch (Exception e) {
            // Si hay una excepción diferente, el token no es válido
            throw new AccessException("Token inválido");
        }
    }

    public Credential getCredential() throws AccessException {
        Credential credential = new Credential();
        credential.setUserName(getUserName(token));
        credential.setUserType(UserType.valueOf(getUserType(token)));
        return credential;
    }

    private String getUserName(String token) {
        return getClaims(token).getSubject();
    }

    private String getUserType(String token) {
        return getClaims(token).get(USER_TYPE, String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
