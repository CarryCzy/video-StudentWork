package com.example.controller;

import com.example.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ActorController {
    @Autowired
    private ActorService actorService;
}
