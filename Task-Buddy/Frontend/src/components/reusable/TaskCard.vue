<template>
  <article class="task-card" :class="priorityClass">
    <span class="badge">{{ task.priority.toUpperCase() }}</span>
    <h3 :class="titleClasses">{{ task.title }}</h3>
    <p :class="descClasses">{{ task.description }}</p>

    <div class="task-footer">
      <span class="overdue">Overdue {{ formatDate(task.dueDate) }}</span>
      <select :value="task.status" @change="onStatusChange">
        <option>To Do</option>
        <option>In Progress</option>
        <option>Completed</option>
      </select>
    </div>

    <div class="task-actions">
      <button type="button" @click="$emit('complete', task.id)">Complete</button>
      <button type="button" class="delete" @click="$emit('delete', task.id)">Delete</button>
    </div>
  </article>
</template>

<script setup>
import { computed } from 'vue';
import { formatDate } from '../Helper/date';

const props = defineProps({
  task: {
    type: Object,
    required: true
  },
  showStrike: {
    type: Boolean,
    default: true
  }
});

const emit = defineEmits(['status-change', 'complete', 'delete']);

const priorityClass = computed(() => props.task.priority.toLowerCase());
const titleClasses = computed(() => ({ done: props.showStrike && props.task.completed }));
const descClasses = computed(() => ({ done: props.showStrike && props.task.completed }));

function onStatusChange(event) {
  emit('status-change', props.task.id, event.target.value);
}
</script>
