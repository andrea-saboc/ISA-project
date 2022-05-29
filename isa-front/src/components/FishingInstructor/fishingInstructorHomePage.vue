<template>
<div class="homeb-view" v-if="loggedUser != null &&  loggedUser.advertiserType=='fishing'">
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
      <li class="header">Adventures</li>
      <li>
        <input type="search" class="form-control form-control-dark" placeholder="Search..."  aria-label="Search" v-model="search_adventure">
      </li>
      <li>
        <a v-on:click="DisplayAdventureRegistration()">
          <i class="fa fa-plus" aria-hidden="true"></i> Add new
        </a>
      </li>
      <li v-for="adventure in adventures" :key="adventure.id" >
        <div  v-if="AdventureSearch(adventure.name)">
          <a v-on:click="DisplayAdventure(adventure.id)">
            <i class="fa fa-ship" aria-hidden="true"></i> {{adventure.name}}
          </a>
        </div>
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
          <i class="fa fa-cog" aria-hidden="true"></i> Profile
        </a>
      </li>
      
       <li class="header">Log Out</li>
      <li>
        <button type="button" class="btn btn-primary" v-on:click="logOut">Log out</button>

      </li>
    </ul>
  
 </div>
 <div class="router-elem">
    <div v-if="display == 'adventureRegistration'">
      <AdventureRegistration></AdventureRegistration>
    </div>
      <div v-if="display=='dashboard'">
      <FishingInstructorDashboard></FishingInstructorDashboard>
    </div>
     <div v-if="display == 'adventure'">
      <AdventureView></AdventureView>
    </div>
    <div v-if="display=='reservations'">
      <AdventureReservation></AdventureReservation>
    </div>
    <div v-if="display == 'profile'">
      <Profile></Profile>
    </div>
    <div v-if="display=='change'">
      <ChangeAdventureInformation></ChangeAdventureInformation>
    </div>
  </div>
</div>

</template>
<script>
import Profile from "../Profile";
import AdventureRegistration from "../FishingInstructor/AdventureRegistration";
import AdventureView from "../FishingInstructor/AdventureView";
import AdventureReservation from "../FishingInstructor/AdventureReservation";
import FishingInstructorDashboard from "../FishingInstructor/FishingInstructorDashboard";
import axios from "axios";
import {devServer} from "../../../vue.config";

export default{
  name: "fishingInstructorHomePage",
  components:{AdventureRegistration,AdventureView,Profile,AdventureReservation,FishingInstructorDashboard},
    data: function(){
        return{
          display: 'dashboard',
            loggedUser:null,
            adventures:[],
            search_adventure:'',
            user: null
        }
    },
    mounted(){
    
      if(window.location.href.includes('/adventure/')){
      this.display = 'adventure'
    }
   let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   console.log('toke je',token)
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : 'Bearer ' + token,
      }
    })
    .then(response => {
      this.loggedUser =response.data
      console.log("Ovaj user je ulogovan:", this.loggedUser)
      if(this.loggedUser.advertiserType == 'fishing'){
        axios.get(devServer.proxy+"/ownersAdventures", {
          headers: {
            'Authorization' :'Bearer ' + token,
          }
        })
            .then(response1 => {
              console.log("avanture vlasnika", response1.data)
              this.adventures = response1.data;
            })
      }
    })
        .catch(() => {
          console.log('Login user is unavailable')
          return;
        });
    },
     methods: {
   
    DisplayAdventureRegistration() {

        this.display = 'adventureRegistration'
    },
     DisplayProfile(){
      this.display = 'profile';
    },
     AdventureSearch(adventureName){
      return adventureName.includes(this.search_adventure)
    },
     DisplayAdventure(id){
      var path = window.location.href
      if (path.includes('/adventure/')){
        path = path.split('/adventure/')[0]
      }

      window.location.href =path+ "/adventure/"+id.toString();
    },
    DisplayReservations(){
      this.display = 'reservations';
    },  
     DisplayDashboard(){
     this.display= 'dashboard'
    },
    logOut(){
      let router = this.$router;
      localStorage.removeItem('token');
      localStorage.clear();
      router.push("/login");
      return;
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
