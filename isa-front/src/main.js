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
import VueGoogleMaps from '@fawmi/vue-google-maps'
import OpenLayersMap from '.'




createApp(App).use(router).use(store).use(VCalendar, {}).use(OpenLayersMap).use(VueGoogleMaps, {
    load: {
        key: 'AIzaSyAd9zBEy1OT75PhlflBVGM0NsXds6PagY8',
    },
}).mount('#app')
//rs.common['Authorization'] = this.$store.getters.tokenString


//Axios.defaults.heade



