package com.taskbuddy.service;

import com.taskbuddy.exception.TaskNotFoundException;
import com.taskbuddy.model.Task;
import com.taskbuddy.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Returns all tasks from the database.
     */
    public List<Task> findAll() {
        log.debug("Fetching all tasks");
        return taskRepository.findAll();
    }

    /**
     * Returns a single task by ID.
     * Throws TaskNotFoundException if not found.
     */
    public Task findById(String id) {
        log.debug("Fetching task with id: {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Saves a new task (MongoDB assigns the ID automatically).
     */
    public Task save(Task task) {
        log.debug("Saving new task: {}", task.getTitle());
        // Ensure new tasks don't carry a stale id
        task.setId(null);
        return taskRepository.save(task);
    }

    /**
     * Updates an existing task.
     * Throws TaskNotFoundException if the task with the given id does not exist.
     */
    public Task update(String id, Task updatedTask) {
        log.debug("Updating task with id: {}", id);

        // Verify existence before updating
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        // Apply updates
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setPriority(updatedTask.getPriority());
        existing.setStatus(updatedTask.getStatus());

        return taskRepository.save(existing);
    }

    /**
     * Deletes a task by ID.
     * Throws TaskNotFoundException if the task does not exist.
     */
    public void delete(String id) {
        log.debug("Deleting task with id: {}", id);
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}
