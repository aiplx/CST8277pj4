package org.ac.cst8277.liang.ping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/tokenInfo")
    public String getTokenInfo(Model model, Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName()
        );

        String accessToken = client.getAccessToken().getTokenValue();
        OAuth2User user = oauthToken.getPrincipal();

        model.addAttribute("accessToken", accessToken);
        model.addAttribute("userName", user.getName());
        model.addAttribute("userAttributes", user.getAttributes());

        return "tokenInfo";
    }
}
