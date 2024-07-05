package com.omercangoktas.flight_search_api.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Kullanıcının adını al
        String role = authentication.getAuthorities().toString(); // Kullanıcının rollerini al

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        return "index";
    }
}