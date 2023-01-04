package com.mahmoudjoe.springtodolist.controllers;

import com.mahmoudjoe.springtodolist.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems",todoItemService.getAll());
        return modelAndView;
    }

}
