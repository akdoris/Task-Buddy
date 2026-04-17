<template>
  <section>
    <h1>Completed</h1>

    <p v-if="!store.completed.value.length" class="empty-message">
      No completed tasks yet.
    </p>

    <div class="cards-grid one-col">
      <TaskCard
        v-for="task in store.completed.value"
        :key="task.id"
        :task="task"
        :show-strike="false"
        @status-change="store.updateStatus"
        @complete="store.toggleComplete"
        @delete="store.removeTask"
      />
    </div>
  </section>
</template>

<script setup>
import { onMounted } from 'vue';
import { loadTasks, useTaskStore } from '../stores/taskStore';
import TaskCard from '../reusable/TaskCard.vue';

const store = useTaskStore();
onMounted(loadTasks);
</script>
