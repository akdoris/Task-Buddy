<template>
  <header class="top-nav">
    <div class="brand">
      <span class="brand-icon">T</span>
      <strong>Task-Buddy</strong>
    </div>
    <nav class="links" aria-label="Main navigation">
      <RouterLink to="/#features">Features</RouterLink>
      <RouterLink to="/#about">About</RouterLink>
      <RouterLink to="/signup">Sign up</RouterLink>
      <RouterLink class="btn-dark" to="/login">Login</RouterLink>
    </nav>
  </header>
</template>

<script setup>
import { RouterLink } from 'vue-router';
</script>
