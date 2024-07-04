package com.omercangoktas.flight_search_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Available endpoints:\n<a href=\"/flights\">Flights</a>\n<a href=\"/airports\">Airports</a>";
    }
}