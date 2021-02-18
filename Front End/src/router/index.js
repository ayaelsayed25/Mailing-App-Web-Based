import {createWebHistory,createRouter} from 'vue-router'
import Register from '../views/Register.vue'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register

  },
  {
    path: '/home',
    name: 'Home',
    component : Home
  }
]

const router = createRouter({
  history:createWebHistory(process.env.BASE_URL),
  routes
})

export default router
