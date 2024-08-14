package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.User;
import org.ac.cst8277.liang.ping.entity.Token;
import org.ac.cst8277.liang.ping.repository.UserRepository;
import org.ac.cst8277.liang.ping.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserManagementService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Token authenticate(String userName, String userPwd) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getUserPwd().equals(userPwd)) {
            Token token = new Token();
            token.setUser(user);
            // The Token entity now handles token generation and expiration time setting
            return tokenRepository.save(token);
        }
        return null;
    }

    public boolean validateToken(String token) {
        Token foundToken = tokenRepository.findById(token).orElse(null);
        if (foundToken != null) {
            if (foundToken.isExpired()) {
                tokenRepository.delete(foundToken);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPwd(), new ArrayList<>());
    }
}