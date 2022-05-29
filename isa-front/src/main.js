//import * as Vue from 'vue'
//import axios from "axios"
//import { createWebHistory, createRouter } from 'vue-router';
//import Axios from "axios";
//mport 'bootstrap/scss/bootstrap.scss';
//import "bootstrap/dist/css/bootstrap.min.css"
import { createApp } from 'vue'
import router from './router'
import store from './store'
import App from './App.vue'
import 'bootstrap';
import VCalendar from 'v-calendar';
import OpenLayersMap from '.'



window.$ = window.jQuery = require('jquery');




createApp(App).use(router).use(store).use(VCalendar, {}).use(OpenLayersMap).mount('#app')
//rs.common['Authorization'] = this.$store.getters.tokenString


//Axios.defaults.heade



