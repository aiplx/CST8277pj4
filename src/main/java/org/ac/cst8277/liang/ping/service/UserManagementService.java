package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.User;
import org.ac.cst8277.liang.ping.entity.Token;
import org.ac.cst8277.liang.ping.repository.UserRepository;
import org.ac.cst8277.liang.ping.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManagementService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to add a new user
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Method to authenticate a user by username and password
    public Token authenticate(String userName, String userPwd) {
        User user = userRepository.findByUserName(userName);
        if (user != null && user.getUserPwd().equals(userPwd)) {
            Token token = new Token();
            token.setUser(user);
            return tokenRepository.save(token);
        }
        return null;
    }

    // Method to validate a token
    public boolean validateToken(String token) {
        Optional<Token> foundToken = tokenRepository.findById(token);
        return foundToken.isPresent() && !foundToken.get().isExpired();
    }

    // Method to load a user by username for Spring Security
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPwd(), new ArrayList<>());
    }

    // Method to load an OAuth2 user and generate a token
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        // Get user details from OAuth2 provider, GitHub
        String githubLogin = oAuth2User.getAttribute("login");
        Long githubId = oAuth2User.getAttribute("id");

        User user = userRepository.findByGithubId(githubId);
        if (user == null) {
            user = new User();
            user.setUserName(githubLogin);  // Set GitHub login as the username
            user.setGithubId(githubId);  // Set GitHub ID as the unique identifier
            user.setUserPwd("");
            userRepository.save(user);
        }

        // Generate and store UUID token
        Token token = new Token();
        token.setUser(user);
        tokenRepository.save(token);

        // Return an OAuth2User with the token information
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("token", token.getToken());

        return new DefaultOAuth2User(
                Collections.singleton(new OAuth2UserAuthority(attributes)),
                attributes,
                "login" // Use 'login' as the username
        );
    }

    // Method to store a token for a user
    public void storeTokenForUser(OAuth2User oAuth2User, String token) {
        // Log all attributes
        Map<String, Object> attributes = oAuth2User.getAttributes();
        attributes.forEach((key, value) -> System.out.println(key + ": " + value));

        // Use the correct key to retrieve the user's email or login name
        String userName = (String) attributes.get("email");
        if (userName == null) {
            userName = (String) attributes.get("login"); // Use 'login' as the username
        }

        if (userName != null) {
            User user = userRepository.findByUserName(userName);
            if (user != null) {
                Token userToken = new Token();
                userToken.setUser(user);
                userToken.setToken(token);
                tokenRepository.save(userToken);
            }
        }
    }
}
