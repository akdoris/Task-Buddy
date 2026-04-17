import { computed, reactive } from 'vue';
import { fetchTasks } from '../services/taskService';

const state = reactive({
  loaded: false,
  tasks: [],
  currentUser: {
    name: 'student',
    email: 'student@taskbuddy.edu',
    program: '',
    year: '',
    bio: ''
  },
  modalOpen: false,
  justRegistered: false
});

let nextId = 100;

export async function loadTasks() {
  if (state.loaded) return;

  try {
    state.tasks = await fetchTasks();
  } catch {
    state.tasks = [];
  }

  state.loaded = true;
}

export function useTaskStore() {
  const all = computed(() => state.tasks);
  const todo = computed(() => state.tasks.filter((task) => task.status === 'To Do' && !task.completed));
  const inProgress = computed(() => state.tasks.filter((task) => task.status === 'In Progress' && !task.completed));
  const completed = computed(() => state.tasks.filter((task) => task.completed || task.status === 'Completed'));

  const stats = computed(() => {
    const now = new Date();
    now.setHours(0, 0, 0, 0);
    let overdue = 0;
    let dueToday = 0;

    state.tasks.forEach((task) => {
      const due = new Date(task.dueDate);
      due.setHours(0, 0, 0, 0);
      if (!task.completed && due < now) overdue += 1;
      if (!task.completed && due.getTime() === now.getTime()) dueToday += 1;
    });

    const completionRate = state.tasks.length
      ? Math.round((completed.value.length / state.tasks.length) * 100)
      : 0;

    return {
      dueToday,
      upcoming: todo.value.length + inProgress.value.length,
      overdue,
      completionRate
    };
  });

  function addTask(payload) {
    state.tasks.unshift({
      id: nextId++,
      title: payload.title.trim(),
      description: payload.description.trim(),
      priority: payload.priority,
      dueDate: payload.dueDate,
      status: 'To Do',
      completed: false
    });
  }

  function toggleComplete(id) {
    const task = state.tasks.find((item) => item.id === id);
    if (!task) return;
    task.completed = !task.completed;
    task.status = task.completed ? 'Completed' : 'To Do';
  }

  function removeTask(id) {
    state.tasks = state.tasks.filter((task) => task.id !== id);
  }

  function updateStatus(id, status) {
    const task = state.tasks.find((item) => item.id === id);
    if (!task) return;
    task.status = status;
    task.completed = status === 'Completed';
  }

  function openModal() {
    state.modalOpen = true;
  }

  function closeModal() {
    state.modalOpen = false;
  }

  function registerNewUser(payload) {
    state.currentUser = {
      name: payload.name.trim(),
      email: payload.email.trim(),
      program: '',
      year: '',
      bio: ''
    };
    state.tasks = [];
    state.loaded = true;
    state.justRegistered = true;
  }

  function markRegistrationHandled() {
    state.justRegistered = false;
  }

  function updateProfile(payload) {
    state.currentUser = {
      ...state.currentUser,
      ...payload
    };
  }

  return {
    state,
    all,
    todo,
    inProgress,
    completed,
    stats,
    addTask,
    toggleComplete,
    removeTask,
    updateStatus,
    openModal,
    closeModal,
    registerNewUser,
    markRegistrationHandled,
    updateProfile
  };
}
