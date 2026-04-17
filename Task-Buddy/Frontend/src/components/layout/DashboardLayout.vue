<template>
  <div class="dashboard-layout">
    <DashboardSidebar />
    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
import DashboardSidebar from './DashboardSidebar.vue';
</script>
