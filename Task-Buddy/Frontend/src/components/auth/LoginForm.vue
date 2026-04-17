<template>
  <form class="auth-form" @submit.prevent="login">
    <p class="auth-eyebrow">Welcome back</p>
    <h1>Sign in to Task-Buddy</h1>
    <p class="auth-subtitle">Manage your tasks, priorities, and reminders in one place.</p>

    <p v-if="showSuccess" class="auth-success">Registration successful. Please log in to continue.</p>

    <label for="login-email">Email address</label>
    <input id="login-email" v-model="email" type="email" placeholder="you@example.com" required />

    <div class="password-row">
      <label for="login-password">Password</label>
      <RouterLink class="auth-inline-link" to="/forgot-password">Forgot password?</RouterLink>
    </div>
    <input id="login-password" v-model="password" type="password" placeholder="Enter password" required />

    <button class="btn-dark" type="submit">Sign in</button>

    <p class="auth-switch">
      Don't have an account?
      <RouterLink to="/signup">Sign up</RouterLink>
    </p>
  </form>
</template>

<script setup>
import { computed, ref } from 'vue';
import { RouterLink, useRoute, useRouter } from 'vue-router';
import { useTaskStore } from '../stores/taskStore';

const router = useRouter();
const route = useRoute();
const store = useTaskStore();

const email = ref(store.state.currentUser.email || '');
const password = ref('');

const showSuccess = computed(() => route.query.registered === '1' || store.state.justRegistered);

function login() {
  store.markRegistrationHandled();
  router.push('/dashboard/all');
}
</script>
