package com.example.TodoList.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider
{
    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationMillis;
    @PostConstruct
    public void init() throws Exception {
        try
        {
            keyStore=KeyStore.getInstance("JKS");
            InputStream resourceStream=getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceStream,"secret".toCharArray());
        }
        catch(KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e)
        {

            throw new Exception("Exception In Key Store");
        }
    }
    public String generateToken(Authentication authentication) throws Exception {
        User principal=(User)authentication.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMillis)))
                .compact();
    }
    private PrivateKey getPrivateKey() throws Exception {
        try
        {
            return (PrivateKey) keyStore.getKey("springblog","secret".toCharArray());

        }catch(KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e)
        {
            throw new Exception("Exception Occured while Retriving Public Key");
        }
    }
    public boolean  validateToken(String jwt) throws Exception {
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }
    private PublicKey getPublickey() throws Exception {
        try
        {
            return keyStore.getCertificate("springblog").getPublicKey();
        }catch(KeyStoreException e)
        {
            throw new Exception("Exception Occured while Public key retrival");
        }
    }
    public String getUsernameFromJwt(String token) throws Exception {
        Claims claims=parser()
                .setSigningKey(getPrivateKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public long getJwtExpirationInMillis()
    {
        return jwtExpirationMillis;
    }

    public String generateTokenWithUsername(String username) throws Exception {
        return Jwts.builder().setSubject(username)
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMillis)))
                .compact();
    }
}
