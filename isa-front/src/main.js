import * as Vue from 'vue'
//import axios from "axios"
//import { createWebHistory, createRouter } from 'vue-router';
import Axios from "axios";
//mport 'bootstrap/scss/bootstrap.scss';
import "bootstrap/dist/css/bootstrap.min.css"
import { createApp } from 'vue'
import router from './router'
import store from './store'
import App from './App.vue'




createApp(App).use(router).use(store).mount('#app')

Axios.defaults.headers.common['Authorization'] = this.$store.getters.tokenString

const app = Vue.createApp({
    components: {
        Index
    },
});
