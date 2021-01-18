package com.example.TodoList.services;

import com.example.TodoList.dtos.LoginRequest;
import com.example.TodoList.dtos.RefreshTokenRequest;
import com.example.TodoList.dtos.RegisterRequest;
import com.example.TodoList.models.NotificationEmail;
import com.example.TodoList.models.User;
import com.example.TodoList.models.VerificationToken;
import com.example.TodoList.repositories.UserRepository;
import com.example.TodoList.repositories.VerificationTokenRepository;
import com.example.TodoList.dtos.AuthenticationResponse;
import com.example.TodoList.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserAuth {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    @Transactional
    public String signup(RegisterRequest registerRequest) throws Exception {
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return "UserName already Present";
        }
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return "Email already Used by another user";
        }
        User user= new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEnabled(false);
        userRepository.save(user);
        String token= generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Welcome To ToDoList ",user.getEmail()," Verify Your Account " +
                "http://localhost:8080/api/auth/accountVerification/"+token+" "));
        return "Registration Successful";
    }
    private String generateVerificationToken(User user)
    {
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
    @Transactional
    public void verifyAccount(String token) throws Exception {
        Optional<VerificationToken> verificationToken= verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()->new Exception("Invalid Token"));
        fetchUserAndEnable(verificationToken.get().getUser().getUserId());
        verificationTokenRepository.deleteById(verificationToken.get().getId());

    }
    @Transactional
    public void fetchUserAndEnable(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User Not Found Sorry SingUp Again"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken
                    (loginRequest.getUsername(),
                            loginRequest.getPassword()));

            System.out.println("No Error After Authentication");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("No Error on security context");
            String signedIn = jwtProvider.generateToken(authentication);
            System.out.println("No Error on token genetation");
            return new AuthenticationResponse().builder()
                    .authenticateToken(signedIn)
                    .message("")
                    .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                    .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                    .username(loginRequest.getUsername())
                    .build();
        }
        catch (Exception e)
        {
            return new AuthenticationResponse()
                    .builder()
                    .message(e.getMessage())
                    .build();
        }
    }
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token=jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticateToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }



}