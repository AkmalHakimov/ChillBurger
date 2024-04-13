package com.bot.chillburger.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class testController {

    @GetMapping
    public void testIt(){
        System.out.println("salom");
    }

    @PostMapping
    public void sdfbdsbf(){
        System.out.println("salom");
    }
}
