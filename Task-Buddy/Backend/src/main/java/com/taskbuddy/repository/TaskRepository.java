package com.taskbuddy.repository;

import com.taskbuddy.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    // MongoRepository provides all CRUD operations out of the box:
    // findAll(), findById(), save(), deleteById(), existsById(), count(), etc.
}
