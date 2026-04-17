<template>
  <form class="auth-form" @submit.prevent="requestReset">
    <p class="auth-eyebrow">Password recovery</p>
    <h1>Forgot your password?</h1>
    <p class="auth-subtitle">Enter your email and we will send a reset link.</p>

    <label for="forgot-email">Email address</label>
    <input id="forgot-email" v-model="email" type="email" placeholder="you@example.com" required />

    <button class="btn-dark" type="submit">Send reset link</button>

    <p v-if="sent" class="auth-success">Reset instructions were sent to {{ email }}.</p>

    <p class="auth-switch">
      Back to
      <RouterLink to="/login">Sign in</RouterLink>
    </p>
  </form>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';

const email = ref('');
const sent = ref(false);

function requestReset() {
  sent.value = true;
}
</script>
