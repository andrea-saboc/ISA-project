<template>
<div class="homeb-view" v-if="loggedUser != null &&  loggedUser.adminType=='admin'">
  <div class="sidebar-container side-bov">
    <div class="sidebar-logo">
      {{loggedUser.name}} {{loggedUser.surname}}
    </div>

    <ul class="sidebar-navigation">
        <li>
        <a v-if="loggedUser.email==='admin@gmail.com'" v-on:click="DisplayAdministratorRegistration()">
          <i class="fa fa-plus" aria-hidden="true"></i> Add new administrator
        </a>
      </li>
      <li>
        <a  v-on:click="DisplayUsersRegistration()">
          <i class="fa fa-cog" aria-hidden="true"></i> View users registration
        </a>
      </li>
       <li class="header">View All request for account deleted</li>
      <li>
        <a href="#" v-on:click = "DisplayRequest()">
          <i class="fa fa-cog" aria-hidden="true"></i>  Request for delete account
        </a>
      </li>
       <li class="header">Loyalty program</li>
      <li>
        <a href="#" v-on:click = "DisplayLoyalty()">
          <i class="fa fa-cog" aria-hidden="true"></i>  Loyalty program
        </a>
      </li>
      <li>
        <a href="#" v-on:click = "DisplayReports()">
          <i class="fa fa-cog" aria-hidden="true"></i>  View reports
        </a>
      </li>
      <li>
        <a href="#" v-on:click = "DisplayComplain()">
          <i class="fa fa-cog" aria-hidden="true"></i>  View complains
        </a>
      </li>
         <li>
        <a href="#" v-on:click = "DisplayAllUsers()">
          <i class="fa fa-cog" aria-hidden="true"></i>  View all users and entity
        </a>
      </li>
      <li>
        <a href="#" v-on:click="DisplayProfile()">
          <i class="fa fa-cog" aria-hidden="true"></i> Profile
        </a>
      </li>
       <li class="header">Log Out</li>
      <li>
        <button type="button" class="btn btn-primary" v-on:click="logOut">Log out</button>
      </li>
       <li class="header">Profile</li>
      
    </ul>
  
 </div>
 <div class="router-elem">
    <div v-if="display == 'administratorRegistration'">
      <AdminRegistration></AdminRegistration>
    </div>
      <div v-if="display == 'profile'">
      <Profile></Profile>
    </div>
    <div v-if="display == 'viewUsers'">
      <ViewUserRegistration></ViewUserRegistration>
    </div>
     <div v-if="display == 'allUsers'">
      <ViewAllUsers></ViewAllUsers>
    </div>
    <div v-if="display == 'request'">
      <ViewAllRequestForDeletedAccount></ViewAllRequestForDeletedAccount>
    </div>
    <div v-if="display == 'loyalty'">
      <LoyaltyProgram></LoyaltyProgram>
    </div>
      <div v-if="display == 'reports'">
      <ViewReports></ViewReports>
    </div>
      <div v-if="display == 'complain'">
      <Complain></Complain>
    </div>
  </div>
</div>

</template>

<script>
import Profile from "../Profile";
import AdminRegistration from "../Administrator/AdminRegistration";
import ViewUserRegistration from "../Administrator/ViewUserRegistration";
import ViewAllRequestForDeletedAccount from "../Administrator/ViewAllRequestForDeletedAccount";
import LoyaltyProgram from "../Administrator/LoyaltyProgram";
import ViewReports from "../Administrator/ViewReports";
import ViewAllUsers from "../Administrator/ViewAllUsers";
import Complain from "../Administrator/Complain";
import axios from "axios";
import {devServer} from "../../../vue.config";


export default{
  name: "administratorHomePage",
    components:{AdminRegistration,Profile,ViewUserRegistration,ViewAllRequestForDeletedAccount,LoyaltyProgram,ViewReports,ViewAllUsers,Complain},



    data: function(){
        return{
          display: 'dashboard',
            loggedUser:null,
            adventures:[],
            search_adventure:'',
            user: null,
            token: null
        }
    },
    mounted(){
        this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

      if(window.location.href.includes('/administrator/')){
      this.display = 'administrator'
    }
  
  
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : 'Bearer ' + this.token,
      }
    })
    .then(response => {
      this.loggedUser =response.data
      console.log("Ovaj user je ulogovan:", this.loggedUser)
    })
        .catch(() => {
          console.log('Login user is unavailable')
          return;
        });
    },
     methods: {
   
    DisplayAdministratorRegistration() {

        this.display = 'administratorRegistration'
    },
    logOut(){
      let router = this.$router;
      localStorage.removeItem('token');
      localStorage.clear();
      router.push("/login");
      return;
      },
         DisplayProfile(){
      this.display = 'profile';
    },
     DisplayUsersRegistration(){
      this.display = 'viewUsers';
    },
     DisplayRequest(){
      this.display = 'request';
    },
    DisplayLoyalty(){
      this.display = 'loyalty';
    },
    DisplayReports()
    {
      this.display='reports'
    },
    DisplayAllUsers()
    {
      this.display='allUsers'
    },
    DisplayComplain()
    {
      this.display='complain'
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
