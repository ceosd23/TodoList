package com.example.TodoList.services;

import com.example.TodoList.models.User;
import com.example.TodoList.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService
{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        Optional<User> userOptional=userRepository.findByUsername(userName);
        User user=userOptional.orElseThrow(()->new UsernameNotFoundException("No Account Present with"+userName));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
            user.isEnabled(),true,true,true,getAuthoritites("USER"));
    }
    private Collection<? extends GrantedAuthority> getAuthoritites(String role)
    {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
