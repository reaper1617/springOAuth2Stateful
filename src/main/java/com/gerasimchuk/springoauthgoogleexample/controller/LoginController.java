package com.gerasimchuk.springoauthgoogleexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static final String GOOGLE_REGISTRATION_ID = "google";
    private static final String AUTH_REQUEST_BASE_URI = "oauth2/authorization";


    private final ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public LoginController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/oauth_login")
    public String getLoginPage(Model model) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(GOOGLE_REGISTRATION_ID);
        if (clientRegistration == null) {
            throw new IllegalStateException("Google preconfigured client expected but not exists");
        }
        Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
        oauth2AuthenticationUrls.put(clientRegistration.getClientName(), AUTH_REQUEST_BASE_URI + "/" + clientRegistration.getRegistrationId());
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "oauth_login_page.html";
    }

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }

    @GetMapping("/failure")
    public String failure() {
        return "failure.html";
    }


}
