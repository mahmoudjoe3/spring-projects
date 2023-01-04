package com.mahmoudjoe.springtodolist.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo_table")
@Entity
public class TodoItem  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Boolean isCompleted;
    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
