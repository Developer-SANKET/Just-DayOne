package com.example.nike;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController {

    private static final Map<String,String> USERS = Map.of("admin","admin123","user","user123");

    @GetMapping("/")
    public String home() { return "redirect:/login"; }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required=false) String error, Model model){
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, Model model){
        String pw = USERS.get(username);
        if (pw != null && pw.equals(password)) {
            model.addAttribute("username", username);
            model.addAttribute("shoes", sampleShoes());
            return "catalog";
        } else {
            return "redirect:/login?error=invalid";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    private List<Map<String,String>> sampleShoes(){
        List<Map<String,String>> list = new ArrayList<>();
        list.add(Map.of("name","Air Max 1","price","₹9,999"));
        list.add(Map.of("name","Air Force 1","price","₹7,499"));
        list.add(Map.of("name","ZoomX Vaporfly","price","₹19,999"));
        return list;
    }
}
