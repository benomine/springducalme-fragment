package com.ducalme.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccueilController {

    @RequestMapping(value = "/")
    public String welcomeToDuCalme() {
        return "index";
    }
}
