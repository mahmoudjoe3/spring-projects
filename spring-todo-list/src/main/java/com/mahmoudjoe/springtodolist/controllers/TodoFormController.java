package com.mahmoudjoe.springtodolist.controllers;

import com.mahmoudjoe.springtodolist.entities.TodoItem;
import com.mahmoudjoe.springtodolist.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class TodoFormController {
    @Autowired
    private TodoItemService todoItemService;
    @GetMapping("/create-todo")
    public String showCreatForm(TodoItem todoItem){
        return "new-todo-item-form";
    }
    @PostMapping("/create")
    public String createTodoItem(@Validated TodoItem todoItem, BindingResult result, Model model){
        todoItemService.saveItem(todoItem);
        return "redirect:/api/v1/index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id,Model model){
        TodoItem todoItem = todoItemService.getItemById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:"+id+"not found "));
        model.addAttribute("todo",todoItem);
        return "edit-todo-item-form";
    }
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable Long id, @Validated TodoItem todoItem, BindingResult result, Model model){
        TodoItem item = todoItemService.getItemById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id:"+id+"not found "));
        item.setIsCompleted(todoItem.getIsCompleted());
        item.setDescription(todoItem.getDescription());
        todoItemService.saveItem(item);
        return "redirect:/api/v1/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id,Model model){
        todoItemService.deleteItemById(id);
        return "redirect:/api/v1/index";
    }

}
