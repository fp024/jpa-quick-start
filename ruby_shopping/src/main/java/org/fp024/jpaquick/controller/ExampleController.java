package org.fp024.jpaquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class ExampleController {

    @GetMapping("/")
    public String test (Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("serverTime", LocalDateTime.now());
        return "index";
    }
}
