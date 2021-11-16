import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home.vue'
import About from '../components/About.vue'
import ClientRegistration from '../components/ClientRegistration.vue'
import Login from '../components/Login.vue'
import Profile from '../components/Profile.vue'

import Boats from '../components/Boats.vue'


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
