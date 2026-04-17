<template>
  <article class="stat-card">
    <p class="stat-title">{{ title }}</p>
    <div class="stat-row">
      <strong :class="{ danger: danger }">{{ value }}</strong>
      <span>{{ caption }}</span>
    </div>
  </article>
</template>

<script setup>
defineProps({
  title: String,
  value: [String, Number],
  caption: String,
  danger: Boolean
});
</script>
