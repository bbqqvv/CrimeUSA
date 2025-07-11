package com.backend.authservice.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
public class JwksController {

    private final RSAPublicKey publicKey;

    public JwksController(@Value("${rsa.key.public}") RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        RSAKey key = new RSAKey.Builder(publicKey)
                .keyID("auth-key-id")
                .build();

        return new JWKSet(key).toJSONObject();
    }
}