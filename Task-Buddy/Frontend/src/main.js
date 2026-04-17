import { createApp } from 'vue';
import App from './App.vue';
import router from './components/router';
import './components/styles/global.css';

createApp(App).use(router).mount('#app');
