<template>
<div class="homeb-view" v-if="loggedUser != null &&  loggedUser.advertiserType=='mansion'">
  <div class="sidebar-container side-bov">
    <div class="sidebar-logo">
      {{loggedUser.name}} {{loggedUser.surname}}
    </div>
    <li>
      <a v-on:click="DisplayDashboard()">
        <i class="fa fa-tachometer" aria-hidden="true"></i>
        Dashboard
      </a>
    </li>
    <ul class="sidebar-navigation">
      <li class="header">Mansions</li>
      <li>
        <input type="search" class="form-control form-control-dark" placeholder="Search..." v-model="search_mansion" aria-label="Search">
      </li>
      <li>
        <a v-on:click="DisplayMansionRegistration()">
          <i class="fa fa-plus" aria-hidden="true"></i> Add new
        </a>
      </li>
      <li v-for="mansion in mansions" :key="mansion.id" >
        <div  v-if="MansionSearch(mansion.name)">
          <a v-on:click="DisplayMansion(mansion.id)">
            <i class="fa fa-home" aria-hidden="true"></i> {{mansion.name}}
          </a>
        </div>
      </li>
      <li>
        <a href="#" v-on:click = "DisplayChangeMansionInformations()">
          <i class="fa fa-cog" aria-hidden="true"></i>
          Change boats
        </a>
      </li>
      <li class="header">Reservations</li>
      <li>
        <a href="#" v-on:click = "DisplayReservations()">
          <i class="fa fa-calendar-check-o" aria-hidden="true"></i>  Reservations
        </a>
      </li>
      <li class="header">Profile</li>
      <li>
        <a href="#" v-on:click="DisplayProfile()">
          <i class="fa fa-cog" aria-hidden="true"></i> Settings
        </a>
      </li>
      <li>
        <a href="#">
          <i class="fa fa-info-circle" aria-hidden="true"></i> Information
        </a>
      </li>
    </ul>
  </div>
  <div class="router-elem">
    <div v-if="display == 'mansionRegistration'">
      <MansionRegistration></MansionRegistration>
    </div>
    <div v-if="display=='profile'">
      <Profile></Profile>
    </div>
    <div v-if="display=='mansion'">
      <MansionView></MansionView>
    </div>
    <div v-if="display=='reservations'">
      <MansionReservations></MansionReservations>
    </div>
    <div v-if="display=='dashboard'">
      <MansionOwnerDashboard></MansionOwnerDashboard>
    </div>
    <div v-if="display=='change'">
      <ChangeMansionInformation></ChangeMansionInformation>
    </div>
  </div>
</div>
</template>

<script>
import MansionRegistration from "./MansionRegistration";
import Profile from "../Profile"
import MansionView from "./MansionView";
import MansionReservations from "./MansionReservations";
import MansionOwnerDashboard from "./MansionOwnerDashboard";
import ChangeMansionInformation from "./ChangeMansionInformation";
import axios from "axios";
import {devServer} from "../../../vue.config";

export default {
name: "MansionOwnerHomePage",
  components: {MansionRegistration, Profile, MansionView, MansionReservations, MansionOwnerDashboard, ChangeMansionInformation},
data: function(){
  return{
    display: 'dashboard',
    mansions: [],
    idMansion: null,
    loggedUser : null,
    search_mansion : '',
    searchStartDate : '',
    searchEndDate: '',
    serchStatus: ''
  }
},
  mounted(){
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.loggedUser =response.data
          console.log("Ovaj user je ulogovan:", this.loggedUser)
          if(this.loggedUser.advertiserType == 'mansion'){
            console.log("Trying to find mansions!")
            axios.get(devServer.proxy+"/ownersMansions", {
              headers: {
                'Authorization' : this.$store.getters.tokenString
              }
            })
                .then(response1 => {
                  console.log("Mansions of the owner: ", response1.data)
                  this.mansions = response1.data;
                })
                .catch(() => {
                  console.log('mansions are unavaliable')
                  return;
                });
          }
        })
        .catch(() => {
          console.log('Login user is unavailable')
          return;
        });
  },
  methods: {
    DisplayMansion(id){
      var path = window.location.href
      if (path.includes('/boat/')){
        path = path.split('/boat/')[0]
      }
      window.location.href =path+ "/boat/"+id.toString();

    },
    DisplayMansionRegistration() {

      this.display = 'mansionRegistration'
    },
    DisplayProfile(){
      this.display = 'profile';
    },
    DisplayReservations(){
      this.display = 'reservations';
    },
    DisplayDashboard(){
      this.display= 'dashboard'
    },
    DisplayChangeMansionInformations(){
      this.display = 'change'
    },
    MansionSearch(boatName){
      return boatName.includes(this.search_mansion)
    },
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
  background-color: #EFB0A1;
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

.sidebar-container {
  position: fixed;
  z-index: 9;
  width: 220px;
  height: 100%;
  padding-top: 2%;
  left: 0;
  overflow-x: hidden;
  overflow-y: auto;
  background: #1a1a1a;
  color: #fff;
}

.content-container {
  padding-top: 20px;
}

.sidebar-logo {
  padding: 10px 15px 10px 30px;
  font-size: 20px;
  background-color: #2574A9;
}

.sidebar-navigation {
  padding: 0;
  margin: 0;
  list-style-type: none;
  position: relative;
}

.sidebar-navigation li {
  background-color: transparent;
  position: relative;
  display: inline-block;
  width: 100%;
  line-height: 20px;
}

.sidebar-navigation li a {
  padding: 10px 15px 10px 30px;
  display: block;
  color: #fff;
}

.sidebar-navigation li .fa {
  margin-right: 10px;
}

.sidebar-navigation li a:active,
.sidebar-navigation li a:hover,
.sidebar-navigation li a:focus {
  text-decoration: none;
  outline: none;
}

.sidebar-navigation li::before {
  background-color: #2574A9;
  position: absolute;
  content: '';
  height: 100%;
  left: 0;
  top: 0;
  -webkit-transition: width 0.2s ease-in;
  transition: width 0.2s ease-in;
  width: 3px;
  z-index: -1;
}

.sidebar-navigation li:hover::before {
  width: 100%;
}

.sidebar-navigation .header {
  font-size: 12px;
  text-transform: uppercase;
  background-color: #151515;
  padding: 10px 15px 10px 30px;
}

.sidebar-navigation .header::before {
  background-color: transparent;
}

.content-container {
  padding-left: 220px;
}

.side-bov{
  position: fixed;
  margin-top: 3%;
}

</style>