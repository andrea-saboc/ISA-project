<template>
  <nav class = "navbar navbar-expand-lg bg-dark navbar-dark">
    <div class="container">
      <a v-if="user === null" href="/home" class="navbar-brand">Adventureland</a>
      <a v-if="user === 'Client' || user == 'MansionOwner' || user === 'BoatOwner'" href="/boats" class="navbar-brand">Adventureland</a>
      <div class="collapse navbar-collapse">
        <ul class ="navbar-nav ms-auto">
        <li v-if="user === 'MansionOwner'" class ="nav-item" >
          <a href="/mansionOwnerHomePage" class="nav-link">Home</a>

        </li>
        <li v-if="user === null" class="nav-item">
          <a href="/clientRegistration" class="nav-link">Register</a>
        </li>
        <li v-if="user === 'Client'" class="nav-item">
          <a href="/clientReservations" class="nav-link">Reservations</a>
        </li>
        <li v-if="user === 'Client'" class="nav-item">
          <a href="/clientSubscriptions" class="nav-link">Subscriptions</a>
        </li>
        <li v-if="user === 'Client'" class="nav-item">
          <a href="/clientComplains" class="nav-link">Complains</a>
        </li>
         <li v-if="user === 'Administrator'" class="nav-item">
          <a href="/profile" class="nav-link">Profile Admina</a>
        </li>
        <li v-if="user === 'Administrator'" class="nav-item">
          <a href="/viewRegistration" class="nav-link">View registrations users</a>
        </li>
        <li v-if="user === 'Client'" class="nav-item">
          <a href="/profile" class="nav-link">Profile</a>
        </li>
          <li v-if="user === 'BoatOwner'" class="nav-item">
            <a href="/boatOwnerHomePage" class="nav-link">Home page</a>
          </li>
        <li v-if="user === null" class="nav-item">
          <a href="/login" class="nav-link">Login</a>
        </li>
        <li v-if="user !== null" class="nav-item">
          <button class="btn btn-danger" v-on:click="Logout">Logout</button>
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


</style>
