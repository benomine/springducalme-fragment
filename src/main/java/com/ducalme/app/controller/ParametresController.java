package com.ducalme.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParametresController {
    @RequestMapping(value = "/parametres")
    public String getParametres()
    {
        return "/parametres/index";
    }
}
