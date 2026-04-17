# TaskBuddy — Spring Boot Backend

A production-ready REST API backend for the Personal Task Manager frontend (Vue 3 + Pinia + Axios).

---

## Project Structure

```
com.taskbuddy
├── TaskbuddyApplication.java          ← Entry point
├── controller/
│   └── TaskController.java            ← REST endpoints
├── service/
│   └── TaskService.java               ← Business logic
├── repository/
│   └── TaskRepository.java            ← MongoDB access
├── model/
│   ├── Task.java                      ← MongoDB document
│   ├── Priority.java                  ← Enum: LOW, MEDIUM, HIGH
│   └── Status.java                    ← Enum: TODO, IN_PROGRESS, DONE
├── exception/
│   ├── TaskNotFoundException.java     ← Custom 404 exception
│   ├── ErrorResponse.java             ← JSON error body
│   └── GlobalExceptionHandler.java    ← @ControllerAdvice handler
└── config/
    └── CorsConfig.java                ← Allows http://localhost:5173
```

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java | 17+ |
| Maven | 3.8+ |
| MongoDB | 6+ (running locally) |
| IntelliJ IDEA | Any recent version |

---

## Step-by-Step: Run in IntelliJ IDEA

### 1. Start MongoDB

```bash
# macOS (Homebrew)
brew services start mongodb-community

# Windows
net start MongoDB

# Linux
sudo systemctl start mongod
```

Verify it's running:
```bash
mongosh
# Should show: connecting to mongodb://127.0.0.1:27017
```

### 2. Open the Project

- Open IntelliJ → **File → Open** → select the `taskbuddy` folder
- Wait for Maven to download dependencies (bottom status bar)

### 3. Enable Annotation Processing (Lombok)

- **File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors**
- ✅ Check **"Enable annotation processing"**
- Click OK

### 4. Run the Application

- Open `TaskbuddyApplication.java`
- Click the green ▶ **Run** button (or Shift+F10)
- You should see in the console:
  ```
  Started TaskbuddyApplication in X.XXX seconds
  ```

### 5. Verify it's Working

```bash
curl http://localhost:8080/api/tasks
# Expected: []  (empty array on first run)
```

---

## API Reference

### JSON Schema

All requests and responses use this structure:

```json
{
  "id": "string (MongoDB ObjectId, auto-generated)",
  "title": "string (required, not blank)",
  "description": "string (optional)",
  "priority": "LOW | MEDIUM | HIGH",
  "status": "TODO | IN_PROGRESS | DONE"
}
```

### Endpoints

| Method | URL | Description | Success Code |
|--------|-----|-------------|-------------|
| GET | `/api/tasks` | Get all tasks | 200 OK |
| GET | `/api/tasks/{id}` | Get task by ID | 200 OK |
| POST | `/api/tasks` | Create new task | 201 Created |
| PUT | `/api/tasks/{id}` | Update task | 200 OK |
| DELETE | `/api/tasks/{id}` | Delete task | 204 No Content |

### Error Responses

```json
{
  "message": "Task not found with id: abc123",
  "status": 404
}
```

| Status | Cause |
|--------|-------|
| 400 | Validation failed (blank title, missing priority/status) |
| 404 | Task ID does not exist |
| 500 | Unexpected server error |

---

## Postman / curl Examples

### GET All Tasks
```bash
curl -X GET http://localhost:8080/api/tasks
```

### GET One Task
```bash
curl -X GET http://localhost:8080/api/tasks/64f1a2b3c4d5e6f7a8b9c0d1
```

### POST — Create Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Buy groceries",
    "description": "Milk, bread, eggs",
    "priority": "MEDIUM",
    "status": "TODO"
  }'
```
**Response (201 Created):**
```json
{
  "id": "64f1a2b3c4d5e6f7a8b9c0d1",
  "title": "Buy groceries",
  "description": "Milk, bread, eggs",
  "priority": "MEDIUM",
  "status": "TODO"
}
```

### PUT — Update Task
```bash
curl -X PUT http://localhost:8080/api/tasks/64f1a2b3c4d5e6f7a8b9c0d1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Buy groceries",
    "description": "Milk, bread, eggs, butter",
    "priority": "HIGH",
    "status": "IN_PROGRESS"
  }'
```

### DELETE — Delete Task
```bash
curl -X DELETE http://localhost:8080/api/tasks/64f1a2b3c4d5e6f7a8b9c0d1
# Response: 204 No Content (empty body)
```

### Validation Error Example
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "", "priority": "HIGH", "status": "TODO"}'
```
**Response (400 Bad Request):**
```json
{
  "message": "Title must not be blank",
  "status": 400
}
```

---

## Frontend Integration (Axios)

Your Axios instance should work as-is:

```javascript
const API = axios.create({
  baseURL: "http://localhost:8080/api/tasks"
});

// Fetch all
API.get("/")

// Fetch one
API.get(`/${id}`)

// Create
API.post("/", { title, description, priority, status })

// Update
API.put(`/${id}`, { title, description, priority, status })

// Delete
API.delete(`/${id}`)
```

> ✅ CORS is configured to allow `http://localhost:5173` (Vite dev server).

---

## Common Errors & Fixes

### ❌ `Connection refused: localhost:27017`
**Cause:** MongoDB is not running.
**Fix:** Start MongoDB — see Step 1 above.

### ❌ `Cannot find symbol: @Data` / Lombok errors
**Cause:** Annotation processing disabled in IntelliJ.
**Fix:** Enable in Settings → Compiler → Annotation Processors.

### ❌ `Field 'title' is required` from Axios
**Cause:** Sending request without `Content-Type: application/json` header.
**Fix:** Axios sets this automatically. Verify you're not overriding headers.

### ❌ `400 Bad Request` when using enum values
**Cause:** Wrong case — enums are case-sensitive.
**Fix:** Use uppercase exactly: `"LOW"`, `"MEDIUM"`, `"HIGH"`, `"TODO"`, `"IN_PROGRESS"`, `"DONE"`.

### ❌ `404` on DELETE but task exists
**Cause:** ID in URL has extra whitespace or truncated.
**Fix:** Ensure the full MongoDB ObjectId string is passed (24 hex characters).

### ❌ CORS error in browser
**Cause:** Frontend is not running on port 5173.
**Fix:** Either update `CorsConfig.java` with your actual port, or start Vite on 5173:
```bash
vite --port 5173
```

---

## Build for Production

```bash
mvn clean package -DskipTests
java -jar target/taskbuddy-1.0.0.jar
```
