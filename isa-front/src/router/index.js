import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import ClientRegistration from '../views/ClientRegistration.vue'
import Login from '../views/Login.vue'
import Profile from '../views/Profile.vue'

import Boats from '../views/Boats.vue'


const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
   },
   {
    path: '/clientRegistration',
    name: 'clientRegistration',
    component: ClientRegistration
   },
   {
    path: '/login',
    name: 'login',
    component: Login
   },
   {
    path: '/profile',
    name: 'profile',
    component: Profile
   },
   {
    path: '/boats',
    name: 'boats',
    component: Boats
   }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
