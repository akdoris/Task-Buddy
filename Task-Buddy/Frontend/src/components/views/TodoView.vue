<template>
  <section>
    <div class="title-row">
      <div>
        <h1>Todo Tasks</h1>
        <p>Here's a list of your tasks for this week.</p>
      </div>
      <button class="btn-dark" @click="store.openModal">+ Create Task</button>
    </div>

    <p v-if="!store.todo.value.length" class="empty-message">
      No todo tasks yet. Add one to get started.
    </p>

    <div class="cards-grid one-col">
      <TaskCard
        v-for="task in store.todo.value"
        :key="task.id"
        :task="task"
        @status-change="store.updateStatus"
        @complete="store.toggleComplete"
        @delete="store.removeTask"
      />
    </div>

    <CreateTaskModal v-if="store.state.modalOpen" @close="store.closeModal" @create="createTask" />
  </section>
</template>

<script setup>
import { onMounted } from 'vue';
import { loadTasks, useTaskStore } from '../stores/taskStore';
import TaskCard from '../reusable/TaskCard.vue';
import CreateTaskModal from '../reusable/CreateTaskModal.vue';

const store = useTaskStore();
onMounted(loadTasks);

function createTask(payload) {
  store.addTask(payload);
  store.closeModal();
}
</script>
