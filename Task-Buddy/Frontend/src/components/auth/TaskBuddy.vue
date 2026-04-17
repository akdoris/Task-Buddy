<template>
  <main id="main-content" class="app" aria-labelledby="title">
    <h1 id="title">TaskBuddy</h1>

    <form class="add-row" @submit.prevent="addTask">
      <label for="task-input" class="sr-only">New task</label>
      <input
        id="task-input"
        v-model="taskInput"
        type="text"
        placeholder="New Task"
        autocomplete="off"
      />
      <button type="submit" class="add-btn">Add Task</button>
    </form>

    <ul class="task-list" role="list">
      <li v-for="item in items" :key="item.id" class="task-row">
        <div class="left">
          <p class="task-title" :class="{ done: item.completed }">{{ item.title }}</p>
          <span v-if="item.type === 'reminder'" class="tag">Reminder</span>
        </div>

        <div class="actions">
          <button
            type="button"
            class="link complete"
            :aria-pressed="item.completed"
            @click="toggleComplete(item.id)"
          >
            Complete
          </button>
          <button type="button" class="link delete" @click="removeItem(item.id)">Delete</button>
        </div>
      </li>
    </ul>

    <p class="count">Count: {{ pendingCount }}</p>

    <div class="expansion">
      <button
        type="button"
        class="increase-btn"
        @click="toggleExpand"
        :aria-expanded="showExpandOptions"
      >
        Increase +
      </button>

      <div v-if="showExpandOptions" class="expand-panel">
        <button type="button" class="option" @click="openReminderInput">Add Reminder</button>

        <form v-if="showReminderInput" class="reminder-form" @submit.prevent="addReminder">
          <label for="reminder-input" class="sr-only">Reminder title</label>
          <input
            id="reminder-input"
            v-model="reminderInput"
            type="text"
            placeholder="Reminder text"
            autocomplete="off"
          />
          <button type="submit" class="add-btn">Save Reminder</button>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  name: 'TaskBuddyApp',
  data() {
    return {
      taskInput: '',
      reminderInput: '',
      showExpandOptions: false,
      showReminderInput: false,
      idCounter: 5,
      items: [
        { id: 1, title: 'Learn Vue3', type: 'task', completed: false },
        { id: 2, title: 'Build TaskBuddy', type: 'task', completed: false },
        { id: 3, title: 'Deploy', type: 'task', completed: false },
        { id: 4, title: 'assignment 2', type: 'task', completed: false }
      ]
    };
  },
  computed: {
    pendingCount() {
      return this.items.filter((item) => !item.completed).length;
    }
  },
  methods: {
    createItem(title, type = 'task') {
      return {
        id: this.idCounter++,
        title: title.trim(),
        type,
        completed: false
      };
    },
    addTask() {
      if (!this.taskInput.trim()) return;
      this.items.push(this.createItem(this.taskInput, 'task'));
      this.taskInput = '';
    },
    addReminder() {
      if (!this.reminderInput.trim()) return;
      this.items.push(this.createItem(this.reminderInput, 'reminder'));
      this.reminderInput = '';
      this.showReminderInput = false;
      this.showExpandOptions = false;
    },
    toggleComplete(id) {
      const item = this.items.find((entry) => entry.id === id);
      if (!item) return;
      item.completed = !item.completed;
    },
    removeItem(id) {
      this.items = this.items.filter((entry) => entry.id !== id);
    },
    toggleExpand() {
      this.showExpandOptions = !this.showExpandOptions;
    },
    openReminderInput() {
      this.showReminderInput = true;
    }
  }
};
</script>

<style>
:root {
  --green: #1f8b4c;
  --green-dark: #16663a;
  --border: #d5d5d5;
  --text: #1f1f1f;
  --danger: #b84b4b;
  --bg: #ffffff;
}

* {
  box-sizing: border-box;
}

body {
  margin: 0;
  padding: 0;
  font-family: Arial, Helvetica, sans-serif;
  color: var(--text);
  background: var(--bg);
}

.app {
  max-width: 980px;
  margin: 40px auto;
  padding: 0 20px;
}

h1 {
  margin: 0 0 12px;
  font-size: 40px;
  color: #243d95;
}

.add-row {
  display: flex;
  gap: 6px;
  margin-bottom: 10px;
}

input {
  height: 36px;
  padding: 0 10px;
  border: 1px solid var(--border);
  font-size: 16px;
}

#task-input {
  width: 220px;
}

.add-btn,
.increase-btn,
.option {
  height: 36px;
  border: 0;
  background: var(--green);
  color: #fff;
  padding: 0 14px;
  font-weight: 700;
  cursor: pointer;
}

.add-btn:hover,
.increase-btn:hover,
.option:hover {
  background: var(--green-dark);
}

.task-list {
  list-style: none;
  margin: 0;
  padding: 0;
}

.task-row {
  border: 1px solid var(--border);
  min-height: 48px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 8px 12px;
  margin-bottom: 6px;
}

.left {
  display: flex;
  align-items: center;
  gap: 8px;
}

.task-title {
  margin: 0;
  font-size: 21px;
}

.task-title.done {
  text-decoration: line-through;
}

.tag {
  font-size: 12px;
  border: 1px solid #9db0e9;
  color: #3852a6;
  border-radius: 10px;
  padding: 1px 8px;
}

.actions {
  display: flex;
  gap: 12px;
}

.link {
  border: 0;
  background: transparent;
  font-size: 30px;
  font-weight: 700;
  cursor: pointer;
}

.complete {
  color: #2f7a53;
}

.delete {
  color: var(--danger);
}

.count {
  margin: 30px 0 10px;
  font-size: 26px;
}

.expansion {
  display: inline-block;
}

.expand-panel {
  margin-top: 8px;
  border: 1px solid var(--border);
  padding: 10px;
  width: 320px;
}

.reminder-form {
  margin-top: 8px;
  display: flex;
  gap: 6px;
}

#reminder-input {
  width: 170px;
}

button:focus-visible,
input:focus-visible {
  outline: 3px solid #1e6fff;
  outline-offset: 2px;
}

.sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border: 0;
}
</style>
