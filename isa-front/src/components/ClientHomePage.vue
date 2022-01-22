<template>
<section class ="bg-dark text-light p-5 text-center text-sm-start">
<br>
<br>
<br>
<h1>Client home page:</h1>

<div class="btn-group btn-group-justified" role="group" aria-label="...">
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" v-on:click="ShowListing">Offers</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" v-on:click="ShowReservations">Reservations</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" v-on:click="ShowComplains">Complains</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" v-on:click="ShowSubscriptions">Subscriptions</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-default" v-on:click="ShowProfile">Profile</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-danger" v-on:click="Logout">Logout</button>
  </div>
</div>
</section>

<div v-if="display == 'Listing'">
  <Listing></Listing>
</div>
<div v-if="display == 'Reservations'">
  <ClientReservations></ClientReservations>
</div>
<div v-if="display == 'Complains'">
  <ClientComplains></ClientComplains>
</div>
<div v-if="display == 'Subscriptions'">
  <ClientSubscriptions></ClientSubscriptions>
</div>
<div v-if="display == 'Profile'">
  <Profile></Profile>
</div>
</template>

<script>
//import axios from 'axios'
import Listing from './Listing.vue'
import ClientReservations from './ClientReservations.vue'
import ClientComplains from './ClientComplains.vue'
import ClientSubscriptions from './ClientSubscriptions.vue'
import Profile from './Profile.vue'
export default {
  name: 'Home',
  components: { Listing,ClientReservations,ClientComplains,ClientSubscriptions,Profile
  },
  data: function() {
    return{
      user: null,
      display:'Listing'
    }
  },
  mounted(){
    this.user = this.$store.state.userType;
  },
  methods:{
    ShowListing(){
      this.display = 'Listing'
    },
    ShowReservations(){
      this.display = 'Reservations'
    },
    ShowComplains(){
      this.display = 'Complains'
    },
    ShowSubscriptions(){
      this.display = 'Subscriptions'
    },
    ShowProfile(){
      this.display = 'Profile'
    },
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
</style>