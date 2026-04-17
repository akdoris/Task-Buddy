<template>
  <form class="auth-form" @submit.prevent="createAccount">
    <p class="auth-eyebrow">Create account</p>
    <h1>Join Task-Buddy</h1>
    <p class="auth-subtitle">Set up your workspace and start organizing tasks faster.</p>

    <label for="signup-name">Full name</label>
    <input id="signup-name" v-model="name" type="text" placeholder="Your name" required />

    <label for="signup-email">Email address</label>
    <input id="signup-email" v-model="email" type="email" placeholder="you@example.com" required />

    <label for="signup-password">Password</label>
    <input id="signup-password" v-model="password" type="password" placeholder="Create a password" required />

    <button class="btn-dark" type="submit">Create account</button>

    <p class="auth-switch">
      Already have an account?
      <RouterLink to="/login">Sign in</RouterLink>
    </p>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import { useTaskStore } from '../stores/taskStore';

const router = useRouter();
const store = useTaskStore();
const name = ref('');
const email = ref('');
const password = ref('');

function createAccount() {
  store.registerNewUser({
    name: name.value,
    email: email.value
  });
  router.push('/login?registered=1');
}
</script>
