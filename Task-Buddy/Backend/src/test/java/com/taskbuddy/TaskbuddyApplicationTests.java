package com.taskbuddy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/taskdb_test"
})
class TaskbuddyApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that the Spring application context starts up correctly
    }
}
