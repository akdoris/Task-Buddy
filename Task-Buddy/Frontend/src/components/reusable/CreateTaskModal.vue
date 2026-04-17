<template>
  <div class="overlay" @click.self="$emit('close')">
    <form class="modal" @submit.prevent="submit">
      <h2>Create New Task</h2>
      <p>Add a new task to your list.</p>

      <label>Title</label>
      <input v-model="form.title" placeholder="Task title" required />

      <label>Description</label>
      <textarea v-model="form.description" placeholder="Task description" rows="4" required />

      <div class="row-2">
        <div>
          <label>Priority</label>
          <select v-model="form.priority">
            <option>Low</option>
            <option selected>Medium</option>
            <option>High</option>
          </select>
        </div>
        <div>
          <label>Due Date</label>
          <input v-model="form.dueDate" type="date" required />
        </div>
      </div>

      <div class="btn-row">
        <button type="button" class="btn-light" @click="$emit('close')">Cancel</button>
        <button type="submit" class="btn-dark">Create Task</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

const emit = defineEmits(['create', 'close']);

const form = reactive({
  title: '',
  description: '',
  priority: 'Medium',
  dueDate: ''
});

function submit() {
  emit('create', { ...form });
}
</script>
