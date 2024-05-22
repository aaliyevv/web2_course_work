package com.student_management.student_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller class handles HTTP requests related to the home page.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page.
     *
     * @return View name for the home page
     */
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

}