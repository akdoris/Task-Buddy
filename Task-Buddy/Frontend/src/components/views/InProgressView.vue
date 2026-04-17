<template>
  <section>
    <h1>In Progress</h1>

    <p v-if="!store.inProgress.value.length" class="empty-message">
      Nothing in progress right now.
    </p>

    <div class="cards-grid one-col">
      <TaskCard
        v-for="task in store.inProgress.value"
        :key="task.id"
        :task="task"
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
