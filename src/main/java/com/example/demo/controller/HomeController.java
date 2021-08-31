package com.example.demo.controller;


import com.example.demo.config.SessionMember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class HomeController {


    @RequestMapping("main")
    public String main(){

        return "main";
    }

    @RequestMapping("test")
    public String test(HttpSession httpSession){

        SessionMember socialMember = (SessionMember) httpSession.getAttribute("socialMember");
        SessionMember loginMember =(SessionMember) httpSession.getAttribute("loginMember");

        if (loginMember == null && socialMember == null){
            return "redirect:/login";
        }

        return "investment/test";
    }


}
