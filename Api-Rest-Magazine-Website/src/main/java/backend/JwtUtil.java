/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.exception.InvalidDataException;
import backend.model.UserType;
import backend.model.dto.Credential;
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
    private String token = null;

    public String generatedToken(String userName, String userType) {

        token = Jwts.builder()
                .setSubject(userName)
                .claim(USER_TYPE, userType)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .compact();
        return token;
    }

    public void validateToken(String tokenToValidate) throws InvalidDataException {
        try {
            // Analizar y validar el token
            token = tokenToValidate;
            getClaims(token);
        } catch (Exception e) {
            // Si hay una excepción, el token no es válido
            throw new InvalidDataException("Token invalido");
        }
    }

    public Credential getCredential() throws InvalidDataException{
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
