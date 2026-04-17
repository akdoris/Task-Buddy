package com.taskbuddy.controller;

import com.taskbuddy.model.Task;
import com.taskbuddy.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")   // belt-and-suspenders alongside CorsConfig
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * GET /api/tasks
     * Returns all tasks — 200 OK
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("GET /api/tasks");
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    /**
     * GET /api/tasks/{id}
     * Returns one task — 200 OK or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        log.info("GET /api/tasks/{}", id);
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * POST /api/tasks
     * Creates a new task — 201 Created or 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        log.info("POST /api/tasks — title: {}", task.getTitle());
        Task created = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/tasks/{id}
     * Updates an existing task — 200 OK, 400 Bad Request, or 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable String id,
            @Valid @RequestBody Task task) {
        log.info("PUT /api/tasks/{}", id);
        Task updated = taskService.update(id, task);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/tasks/{id}
     * Deletes a task — 204 No Content or 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        log.info("DELETE /api/tasks/{}", id);
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
