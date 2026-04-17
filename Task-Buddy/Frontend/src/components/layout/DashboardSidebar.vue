<template>
  <aside class="sidebar">
    <h2>Task-Buddy</h2>
    <nav>
      <RouterLink to="/dashboard/all" class="menu" active-class="active">Dashboard</RouterLink>
      <RouterLink to="/dashboard/todo" class="menu" active-class="active">To Do</RouterLink>
      <RouterLink to="/dashboard/in-progress" class="menu" active-class="active">In Progress</RouterLink>
      <RouterLink to="/dashboard/completed" class="menu" active-class="active">Completed</RouterLink>
    </nav>

    <section class="account">
      <p>ACCOUNT</p>
      <div class="avatar-row">
        <div class="avatar">{{ initial }}</div>
        <span>{{ store.state.currentUser.name }}</span>
      </div>

      <button type="button" class="profile-toggle" @click="editing = !editing">
        {{ editing ? 'Close Profile' : 'Edit Profile' }}
      </button>

      <form v-if="editing" class="profile-form" @submit.prevent="saveProfile">
        <label for="profile-name">Name</label>
        <input id="profile-name" v-model="form.name" type="text" required />

        <label for="profile-email">Email</label>
        <input id="profile-email" v-model="form.email" type="email" required />

        <label for="profile-program">Program</label>
        <input id="profile-program" v-model="form.program" type="text" placeholder="Computer Science" />

        <label for="profile-year">Year</label>
        <input id="profile-year" v-model="form.year" type="text" placeholder="Year 2" />

        <label for="profile-bio">Bio</label>
        <textarea id="profile-bio" v-model="form.bio" rows="3" placeholder="Tell us about your goals"></textarea>

        <button type="submit" class="btn-dark">Save</button>
      </form>

      <RouterLink class="logout" to="/">Log out</RouterLink>
    </section>
  </aside>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import { RouterLink } from 'vue-router';
import { useTaskStore } from '../stores/taskStore';

const store = useTaskStore();
const editing = ref(false);
const form = reactive({
  name: store.state.currentUser.name,
  email: store.state.currentUser.email,
  program: store.state.currentUser.program,
  year: store.state.currentUser.year,
  bio: store.state.currentUser.bio
});

const initial = computed(() => (store.state.currentUser.name?.[0] || 'S').toUpperCase());

function saveProfile() {
  store.updateProfile({ ...form });
  editing.value = false;
}
</script>
