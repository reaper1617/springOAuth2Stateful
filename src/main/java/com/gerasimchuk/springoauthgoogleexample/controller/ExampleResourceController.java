package com.gerasimchuk.springoauthgoogleexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExampleResourceController {

    @GetMapping("/unsecured")
    public String getUnsecuredResource() {
        return "unsecured.html";
    }

    @GetMapping("/secured")
    public String getSecuredResource() {
        return "secured.html";
    }


}
