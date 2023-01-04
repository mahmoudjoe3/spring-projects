package com.mahmoudjoe.springtodolist.services;

import com.mahmoudjoe.springtodolist.entities.TodoItem;
import com.mahmoudjoe.springtodolist.repo.TodoItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    @Autowired
    private TodoItemRepo repo;

    //let’s make some crud

    public List<TodoItem> getAll(){
        return repo.findAll();
    }
    public Optional<TodoItem> getItemById(Long id){
        return repo.findById(id);
    }
    public TodoItem saveItem(TodoItem todoItem){
        TodoItem item = new TodoItem();
        item.setDescription(todoItem.getDescription());
        item.setIsCompleted(false);

        if(item.getId()==null){
            item.setCreatedAt(Instant.now());
        }
        item.setUpdatedAt(Instant.now());
        return repo.save(item);
    }
    public void deleteItemById(Long id){
        getItemById(id).orElseThrow(() -> new IllegalArgumentException("TodoItem id:"+id+"not found "));
        repo.deleteById(id);
    }



}
