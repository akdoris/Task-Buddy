import axios from "axios";

const API_URL = "http://localhost:8080/api/tasks";

export async function fetchTasks() {
  try {
    const response = await axios.get(API_URL);

    return response.data.map(task => ({
      id: task.id,
      title: task.title || "",
      description: task.description || "",
      priority: task.priority || "Medium",
      dueDate: task.dueDate || new Date().toISOString(),
      status: task.status || "To Do",
      completed: task.completed ?? false
    }));

  } catch (error) {
    console.error("Error fetching tasks:", error);
    return [];
  }
}