<template>
<div class="homeb-view">
  <div class="b-example-divider">

  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
    <span class="fw-semibold" style="font-size: 20px; margin-bottom: 4%;">Boat owner home page</span>
    <ul class="list-unstyled ps-0" style="margin-top: 4%">
      <li class="mb-1">
        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
          Boats
        </button>
        <div class="collapse show" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li v-for="boat in boats"
                :key="boat.id"><a  v-on:click="DisplayBoat(boat.id)" class="link-dark rounded">{{boat.name}}</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
          Dashboard
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-dark rounded">Overview</a></li>
            <li><a href="#" class="link-dark rounded">Weekly</a></li>
            <li><a href="#" class="link-dark rounded">Monthly</a></li>
            <li><a href="#" class="link-dark rounded">Annually</a></li>
          </ul>
        </div>
      </li>
      <li class="mb-1">
        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          Orders
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="#" class="link-dark rounded">New</a></li>
            <li><a href="#" class="link-dark rounded">Processed</a></li>
            <li><a href="#" class="link-dark rounded">Shipped</a></li>
            <li><a href="#" class="link-dark rounded">Returned</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          Account
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a v-on:click="DisplayBoatRegistration" class="link-dark rounded">New boat...</a></li>
            <li><a v-on:click="DisplayProfile" class="link-dark rounded">Profile</a></li>
            <li><a href="#" class="link-dark rounded">Settings</a></li>
            <li><a href="#" class="link-dark rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
  </div>
  <div class="router-elem">
    <div v-if="display == 'boatRegistration'">
      <BoatRegistration></BoatRegistration>
    </div>
    <div v-if="display=='profile'">
      <Profile></Profile>
    </div>
    <div v-if="display=='boat'">
      <BoatView></BoatView>
    </div>
  </div>
</div>

</template>

<script>
import BoatRegistration from "./BoatRegistration";
import Profile from "./Profile";
import BoatView from "./BoatView";
import axios from "axios";
import {devServer} from "../../vue.config";
export default {
  name: "BoatOwnerHomePage",
  components: {BoatRegistration, Profile, BoatView},
  data: function(){
    return{
      display: 'boatRegistration',
      boats: [],
      idBoat: null
    }
  },
  mounted() {
    if(window.location.href.includes('/boat/')){
      this.display = 'boat'
    }
    axios.get(devServer.proxy+"/ownersBoats", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.boats = response.data;
        })
  },
  methods: {
    DisplayBoat(id){
      var path = window.location.href
      if (path.includes('/boat/')){
        path = path.split('/boat/')[0]
      }
      window.location.href =path+ "/boat/"+id.toString();

    },
    DisplayBoatRegistration() {

        this.display = 'boatRegistration'
    },
    DisplayProfile(){
      this.display = 'profile';
    }
  }
}
</script>

<style scoped>
.homeb-view {
  display: flex;
  flex-direction: row;
  height: 100%;
  overflow-x: auto;
  overflow-y: hidden;
  margin-left: 5%;
}

b-example-divider {
  background-color: #ada9a9;

}


.nav-flush .nav-link {
  border-radius: 0;
}

.btn-toggle {
  display: inline-flex;
  align-items: center;
  padding: .25rem .5rem;
  font-weight: 600;
  color: rgba(0, 0, 0, .65);
  background-color: transparent;
  border: 0;
}
.btn-toggle:hover,
.btn-toggle:focus {
  color: rgba(0, 0, 0, .85);
  background-color: #d2f4ea;
}

.btn-toggle::before {
  width: 1.25em;
  line-height: 0;
  content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
  transition: transform .35s ease;
  transform-origin: .5em 50%;
}

.btn-toggle[aria-expanded="true"] {
  color: rgba(0, 0, 0, .85);
}
.btn-toggle[aria-expanded="true"]::before {
  transform: rotate(90deg);
}

.btn-toggle-nav a {
  display: inline-flex;
  padding: .1875rem .5rem;
  margin-top: .125rem;
  margin-left: 1.25rem;
  text-decoration: none;
}
.btn-toggle-nav a:hover,
.btn-toggle-nav a:focus {
  background-color: #d2f4ea;
}



.fw-semibold { font-weight: 600; }
.lh-tight { line-height: 1.25; }

.router-elem{
  align-self: center;
  width: 100%;
  background-color: #eeeeee;
}

</style>