<template>
  <section>
    <div class="stats-grid">
      <StatCard title="TODAY'S TASKS" :value="store.stats.value.dueToday" caption="Due Today" />
      <StatCard title="UPCOMING" :value="store.stats.value.upcoming" caption="Scheduled" />
      <StatCard title="OVERDUE" :value="store.stats.value.overdue" caption="Needs Attention" danger />
      <StatCard title="COMPLETION" :value="`${store.stats.value.completionRate}%`" caption=" " />
    </div>

    <div class="title-row">
      <div>
        <h1>Dashboard</h1>
        <p>Here's a list of your tasks for this week.</p>
      </div>
      <button class="btn-dark" @click="store.openModal">+ Create Task</button>
    </div>

    <section v-if="!activeCards.length" class="empty-state">
      <h3>Welcome! Your workspace is empty.</h3>
      <p>Start by creating your first task and building your personal board from scratch.</p>
      <button class="btn-dark" @click="store.openModal">Create First Task</button>
    </section>

    <div v-else class="cards-grid">
      <TaskCard
        v-for="task in activeCards"
        :key="task.id"
        :task="task"
        @status-change="store.updateStatus"
        @complete="store.toggleComplete"
        @delete="store.removeTask"
      />
    </div>

    <p class="share-hint">Double-click a card to share.</p>

    <CreateTaskModal
      v-if="store.state.modalOpen"
      @close="store.closeModal"
      @create="createTask"
    />
  </section>
</template>

<script setup>
import { computed, onMounted } from 'vue';
import { loadTasks, useTaskStore } from '../stores/taskStore';
import StatCard from '../reusable/StatCard.vue';
import TaskCard from '../reusable/TaskCard.vue';
import CreateTaskModal from '../reusable/CreateTaskModal.vue';

const store = useTaskStore();
onMounted(loadTasks);

const activeCards = computed(() => store.all.value.filter((task) => !task.completed).slice(0, 4));

function createTask(payload) {
  store.addTask(payload);
  store.closeModal();
}
</script>
