package com.mahmoudjoe.springtodolist.repo;

import com.mahmoudjoe.springtodolist.entities.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepo extends JpaRepository<TodoItem,Long> {

}
