<template>
  <nav class = "navbar navbar-expand-lg bg-dark navbar-dark mainavbar" style="height: 8%">
    <div class="container">
      <a v-if="user === null" href="/home" class="navbar-brand">Adventureland</a>
      <div class="collapse navbar-collapse">
        <ul class ="navbar-nav ms-auto">



         <li v-if="user === 'Administrator'" class="nav-item">
          <a href="/profile" class="nav-link">Profile Admina</a>
        </li>
        <li v-if="user === 'Administrator'" class="nav-item">
          <a href="/viewRegistration" class="nav-link">View registrations users</a>
        </li>
          <li v-if="user === 'BoatOwner'" class="nav-item">
            <a href="/boatOwnerHomePage" class="nav-link">Home page</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
    <router-view/>
</template>

<script>
//import {useStore} from "vuex"
//import axios from 'axios'
//import axios from "axios";
//import {devServer} from "../vue.config";

export default{
  data(){
    return{
      user: null,
      loggedUser: null
    }
  },
  
  mounted(){

  },
  methods:{
    Logout(){
      this.$store.commit('logOut');
      this.$router.push("/home");
      this.user = null 
      console.log(this.user)   
    }
  }


}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.mainavbar{
  position: fixed;
  z-index: 10;
  width: 100%;
  margin-bottom: 1%;
}


</style>
