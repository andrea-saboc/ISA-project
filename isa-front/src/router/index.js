import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home.vue'
import About from '../components/About.vue'
import ClientRegistration from '../components/ClientRegistration.vue'
import Login from '../components/Login.vue'
import Profile from '../components/Profile.vue'

import Boats from '../components/Boats.vue'
import Mansions from '../components/Mansions.vue'
import Adventures from '../components/Adventures.vue'
import Listing from '../components/Listing.vue'
import ClientReservations from '../components/ClientReservations.vue'
import ClientSubscriptions from '../components/ClientSubscriptions.vue'
import ViewRegistration from '../components/ViewRegistration.vue'
import ClientHomePage from '../components/ClientHomePage.vue'
import ClientComplains from '../components/ClientComplains.vue'

import MansionOwnerHomePage from "../components/MansionOwnerHomePage";
import BoatOwnerHomePage from "../components/BoatOwnerHomePage";
import BoatView from "../components/BoatView";
import BoatRegistration from "../components/BoatRegistration";
import MansionRegistration from "../components/MansionRegistration";
import MansionView from "../components/MansionView";
import MapView from "../components/MapView";

const routes = [
  {
    path: '/home',
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
   },
   {
    path: '/mansions',
    name: 'mansions',
    component: Mansions
   },
   {
    path: '/adventures',
    name: 'adventures',
    component: Adventures
   },
   {
    path: '/listing',
    name: 'listing',
    component: Listing
   },
   {
    path: '/clientReservations',
    name: 'clientReservations',
    component: ClientReservations
   },
   {
    path: '/viewRegistration',
    name: 'viewRegistration',
    component: ViewRegistration
   },
   {
    path: '/clientSubscriptions',
    name: 'clientSubscriptions',
    component: ClientSubscriptions
   },
   {
    path: '/clientComplains',
    name: 'clientComplains',
    component: ClientComplains
   },
    {
        path: '/mansionOwnerHomePage',
        name: 'mansionOwnerHomePage',
        component: MansionOwnerHomePage
    },
    {
        path: '/clientHomePage',
        name: 'clientHomePage',
        component: ClientHomePage
    },
    {
        path: '/boatOwnerHomePage',
        alias: '/boatOwnerHomePage/boat/:id',
        name: 'BoatOwnerHomePage',
        component: BoatOwnerHomePage
    },
    {
        path: '/boat/:id',
        name: 'BoatView',
        component: BoatView
    },
    {
        path: '/mansion',
        name: 'MansionView',
        component: MansionView
    },
    {
        path: '/boatRegistration',
        name: 'BoatRegistration',
        component: BoatRegistration
    },
    {
        path: '/mansionRegistration',
        name: 'MansionRegistration',
        component: MansionRegistration
    },
    {
        path: '/maptest',
        name: "MapView",
        component: MapView
    }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



export default router

