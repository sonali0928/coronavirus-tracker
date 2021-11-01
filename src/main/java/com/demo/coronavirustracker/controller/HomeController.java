package com.demo.coronavirustracker.controller;

import com.demo.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("localStats",coronaVirusDataService.getAllStats());
        return "home";
    }
}
