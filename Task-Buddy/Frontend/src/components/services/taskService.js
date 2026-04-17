const initialTasks = [
  {
    id: 1,
    title: 'Grocery Shopping',
    description: 'Buy milk, eggs, and bread',
    priority: 'Medium',
    dueDate: '2024-11-20',
    status: 'In Progress',
    completed: false
  },
  {
    id: 2,
    title: 'Complete Math Assignment',
    description: 'Chapter 5 exercises 1-10',
    priority: 'High',
    dueDate: '2024-12-01',
    status: 'To Do',
    completed: false
  },
  {
    id: 3,
    title: 'Learn Vue3',
    description: 'Finish routing section',
    priority: 'Low',
    dueDate: '2026-12-20',
    status: 'Completed',
    completed: true
  }
];

function cloneTasks(tasks) {
  // Avoid relying on structuredClone (not available in all browsers/environments).
  return JSON.parse(JSON.stringify(tasks));
}

export function fetchTasks() {
  return Promise.resolve(cloneTasks(initialTasks));
}
