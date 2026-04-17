<template>
  <div>
    <AppHeader />
    <section class="hero">
      
      <h1>Manage tasks with clarity</h1>
      <p>A simple, accessible, and open-source task manager for students and educators.</p>
      <div class="hero-actions">
        <RouterLink class="btn-dark" to="/login">Get Started</RouterLink>
        <a class="btn-light" href="#gallery">View Gallery</a>
      </div>
    </section>

    <section id="features" class="feature-section">
      <h2>Powerful Features</h2>
      <p>Everything you need to stay organized and productive.</p>
      <div class="feature-grid">
        <article><h3>Task Management</h3><p>Create, organize, and track your tasks with ease.</p></article>
        <article><h3>Smart Dashboard</h3><p>See upcoming, overdue, and today’s tasks at a glance.</p></article>
        <article><h3>Instant Updates</h3><p>Built with Vue 3 for lightning-fast interactivity.</p></article>
      </div>
    </section>

    <section id="gallery" class="gallery-section">
      <div class="gallery-head">
        <h2>Product Gallery</h2>
        <p>Preview how Task-Buddy helps you plan, focus, and finish work.</p>
      </div>
      <div class="gallery-grid">
        <article class="gallery-card">
          <h3>Weekly Overview</h3>
          <p>Track due dates and priorities from one dashboard.</p>
          <div class="gallery-preview preview-1"></div>
        </article>
        <article class="gallery-card">
          <h3>Clean Todo Board</h3>
          <p>Start from an empty workspace and build your own system.</p>
          <div class="gallery-preview preview-2"></div>
        </article>
        <article class="gallery-card">
          <h3>Accessible Forms</h3>
          <p>Readable UI and keyboard-friendly inputs for every user.</p>
          <div class="gallery-preview preview-3"></div>
        </article>
      </div>
    </section>

    <section id="about" class="about">
      <h2>About Task-Buddy</h2>
      <p>
        Task-Buddy is an open-source education project built with accessibility in mind (WCAG 2.1 AA).
      </p>
    </section>
  </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import AppHeader from '../layout/AppHeader.vue';
</script>
