package com.taskbuddy.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String description;

    @NotNull(message = "Priority must not be null")
    private Priority priority;

    @NotNull(message = "Status must not be null")
    private Status status;
}
