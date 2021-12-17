<template>
  <nav class = "navbar navbar-expand-lg bg-dark navbar-dark">
    <div class="container">
      <a href="/home" class="navbar-brand">Adventureland</a>
      <div class="collapse navbar-collapse">
        <ul class ="navbar-nav ms-auto">

        <li v-if="user === null" class="nav-item">
          <a href="/clientRegistration" class="nav-link">Register</a>
        </li>
        <li v-if="user === Client" class="nav-item">
          <a href="/clientReservations" class="nav-link">Reservations</a>
        </li>
        <li v-if="user === null" class="nav-item">
          <a href="/login" class="nav-link">Login</a>
        </li>
        <li v-if="user !== null" class="nav-item">
          <a href="" class="nav-link">Logout</a>
        </li>
        </ul>
      </div>
    </div>
  </nav>
    <router-view/>
</template>

<script>
import {useStore} from "vuex"
import axios from 'axios'
export default{
  data(){
    return{
    user: null,
    }
  },
  
  mounted(){
    this.$store.dispatch('startSession', null);   
    this.user = this.$store.state.userType;
},
  methods:{
    Logout(){
      this.$store.commit('logOut');
      this.$router.push("/home");    
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


</style>
