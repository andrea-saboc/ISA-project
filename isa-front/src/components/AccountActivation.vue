<template>

<div>
Welcome to AdventureLand! You can now login to our site: 
</div>




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
      message: ''
    }
  },
  mounted() {

            alert(window.location.href)
            let url = window.location.href
            var code = url.split('/')[4]
            alert(code)
            
            axios
                .post(devServer.proxy + '/checkActivationCode', code, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                  this.message = response.data
                  alert('submited', response)
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