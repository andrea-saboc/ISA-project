<template>

<section class="bg-dark text-light p-5 text-center text-sm-start">
<div v-if="valid == 'valid'">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
   <h1> Welcome to AdventureLand! You can now <a href="/login">login </a>to our site </h1>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<div v-if="valid == 'not valid'">

    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>Something went wrong. Try registering again. </h1>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

<div v-if="valid == 'already validated'">
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <h1>We already confirmed your registration. </h1>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</div>

</section>

</template>
<script>
import axios from 'axios'
import {
    devServer
} from "../../vue.config";
export default {
name: "AccountActivation",
  data() {
    return {
      valid: ''
    }
  },
  mounted() {

            let url = window.location.href
            var code = url.split('/')[4]   

              axios
                .post(devServer.proxy + '/checkActivationCode', code, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                  this.valid = response.data
                  alert(response.data)
                });          
 
            
  },
  methods: {
   somemethod(){
     alert("Tu sam")
     this.messege = "Neka poruka"
     alert(this.messege)
   } 
  }
}
</script>
<style>

</style>