<template>
  <div class="about">
    <h1>This is an about page</h1>
    <p>e{{messege}}</p>
    <button v-on:click ="somemethod">Klikni </button>
  </div>
</template>
<script>
import axios from 'axios'
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  name: "About",
  data() {
    return {
      messege: '',
      user: null
    }
  },
  mounted() {
        this.user = this.$store.state.userType;

    alert(this.user)
    axios
        .get('/proba',{
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          alert(response.data)
          this.messege = response.data
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

<style scoped>

</style>