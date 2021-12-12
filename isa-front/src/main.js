import * as Vue from 'vue'
import axios from "axios"
import { createApp } from 'vue'
import router from './router'
import { createWebHistory, createRouter } from 'vue-router';
import Axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css"

import store from './store'
import App from './App.vue'




createApp(App).use(router).use(store).mount('#app')
const app = Vue.createApp({
    components: {
        Index
    },
});
app.use(router);
app.use(store);
app.config.globalProperties.$http = Axios;
app.mount('#app');