package com.example.TodoList.controllers;


import com.example.TodoList.dtos.AuthenticationResponse;
import com.example.TodoList.dtos.LoginRequest;
import com.example.TodoList.dtos.RefreshTokenRequest;
import com.example.TodoList.dtos.RegisterRequest;
import com.example.TodoList.services.RefreshTokenService;
import com.example.TodoList.services.UserAuth;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserAuthController
{
    private final UserAuth userAuth;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) throws Exception {
        return new ResponseEntity<>(userAuth.signup(registerRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws Exception {
        userAuth.verifyAccount(token);
        return new ResponseEntity<>("Account Message Successfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest)
    {

        return ResponseEntity.status(HttpStatus.OK).body(userAuth.login(loginRequest));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return new ResponseEntity<>("Successfully Logged Out",HttpStatus.OK);
    }




}
