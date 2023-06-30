package com.mis.miIndex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MiIndexController {
    @RequestMapping(value = "/")
    public String GoToIndex(){
        return "redirect:/index.html";
    }
}
