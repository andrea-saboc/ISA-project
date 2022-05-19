import { createRouter, createWebHistory } from 'vue-router'
import Home from '../components/Home.vue'
import About from '../components/About.vue'
import ClientRegistration from '../components/ClientRegistration.vue'
import Login from '../components/Login.vue'
import Profile from '../components/Profile.vue'
import BoatReservationOffers from '../components/BoatReservationOffers.vue'
import MansionReservationOffers from '../components/MansionReservationOffers.vue'
import Boats from '../components/Boats.vue'
import Mansions from '../components/Mansions.vue'
import Adventures from '../components/Adventures.vue'
import Listing from '../components/Listing.vue'
import ClientReservations from '../components/ClientReservations.vue'
import ClientSubscriptions from '../components/ClientSubscriptions.vue'
import ViewRegistration from '../components/ViewRegistration.vue'
import ClientHomePage from '../components/ClientHomePage.vue'
import ClientComplains from '../components/ClientComplains.vue'
import AccountActivation from '../components/AccountActivation.vue'
import BoatOwnerHomePage from "../components/BoatOwnerHomePage";
import BoatView from "../components/BoatView";
import BoatRegistration from "../components/BoatRegistration";
import MansionRegistration from "../components/Mansion/MansionRegistration";
import MansionView from "../components/Mansion/MansionView";
import MapView from "../components/MapView";
import MansionOwnerHomePage from "../components/Mansion/MansionOwnerHomePage"
import FishingInstrucotrHomePage from "../components/FishingInstructor/fishingInstructorHomePage"
import AdventureRegistration from "../components/FishingInstructor/AdventureRegistration"
import AdventureView from "../components/FishingInstructor/AdventureView"

const routes = [
{
    path: '/',
    name: 'home',
    component: Home
},
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
      path: '/mansionOwnerHomePage',
      alias: '/mansionOwnerHomePage/mansion/:id',
      name: 'MansionOwnerHomePage',
        component: MansionOwnerHomePage
    },
    {
        path: '/boat/:id',
        name: 'BoatView',
        component: BoatView
    },
    {
        path: '/mansion/:id',
        name: 'MansionView',
        component: MansionView
    },
    {
        path: '/boatReservationOffers/:id',
        name: 'BoatReservationOffers',
        component: BoatReservationOffers
    },
    {
        path: '/mansionReservationOffers/:id',
        name: 'MansionReservationOffers',
        component: MansionReservationOffers
    },
    {
        path: '/activation/:code',
        name: 'AccountActivation',
        component: AccountActivation
    },
    {
        path: '/mansion/:id',
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
    },
    {
        path: '/fishingInstructorHomePage',
        alias: '/fishingInstructorHomePage/adventure/:id',
        name: 'fishingInstrucotorHomePage',
        component: FishingInstrucotrHomePage
    },
    {
        path: '/adventureRegistration',
        name: 'AdventureRegistration',
        component: AdventureRegistration
    },
    {
        path: '/adventure/:id',
        name: 'AdventureView',
        component: AdventureView
    }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})



export default router

