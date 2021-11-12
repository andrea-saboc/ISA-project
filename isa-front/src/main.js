import * as Vue from 'vue'
import axios from "axios"
import { createApp } from 'vue'
import router from './router'

import About from './components/About'
import App from './App.vue'


createApp(App).use(router).mount('#app')
const app = Vue.createApp(App);
app.config.globalProperties.$http = axios;

const routes =[
    {path: '/about', component: About}

]