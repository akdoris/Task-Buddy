import { createRouter, createWebHistory } from 'vue-router';
import LandingView from '../views/LandingView.vue';
import LoginView from '../views/LoginView.vue';
import SignupView from '../views/SignupView.vue';
import ForgotPasswordView from '../views/ForgotPasswordView.vue';
import DashboardLayout from '../layout/DashboardLayout.vue';
import DashboardOverviewView from '../views/DashboardOverviewView.vue';
import TodoView from '../views/TodoView.vue';
import InProgressView from '../views/InProgressView.vue';
import CompletedView from '../views/CompletedView.vue';

const routes = [
  { path: '/', name: 'home', component: LandingView },
  { path: '/login', name: 'login', component: LoginView },
  { path: '/signup', name: 'signup', component: SignupView },
  { path: '/forgot-password', name: 'forgot-password', component: ForgotPasswordView },
  {
    path: '/dashboard',
    component: DashboardLayout,
    children: [
      { path: 'all', name: 'dashboard-all', component: DashboardOverviewView },
      { path: 'todo', name: 'dashboard-todo', component: TodoView },
      { path: 'in-progress', name: 'dashboard-in-progress', component: InProgressView },
      { path: 'completed', name: 'dashboard-completed', component: CompletedView }
    ]
  },
  { path: '/:pathMatch(.*)*', redirect: '/' }
];

export default createRouter({
  history: createWebHistory(),
  routes
});
