/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.exception.InvalidDataException;
import backend.model.dto.Credential;

/**
 *
 * @author brigidoalvarado
 */
public class AuthTokenHandler {
    private static final String BEARER_PREFIX = "Bearer ";
    
    private  final JwtUtil jwtUtil = new JwtUtil();
    
    public void authToken(String header) throws InvalidDataException{
            validateToken(getTokenFromHeaders(header));
    }
    // Método para obtener el token de autorización desde los encabezados de la solicitud
    private  String getTokenFromHeaders(String header)throws InvalidDataException{
            // Retorna el token eliminando el prefijo "Bearer "
            try {
            return header.substring(BEARER_PREFIX.length());            
        } catch (Exception e) {
            throw new InvalidDataException("el token no inicia con Bearer");
        }
    }
    
    public Credential getCredential() throws InvalidDataException{
        return jwtUtil.getCredential();
    }
    
    // Método para validar el token
    private  void validateToken(String token) throws InvalidDataException{
        jwtUtil.validateToken(token);
    }
}
