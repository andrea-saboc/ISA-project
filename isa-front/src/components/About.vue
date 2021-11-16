<template>
  <div class="about">
    <h1>This is an about page</h1>
    <p>e{{messege}}</p>
    <button v-on:click ="somemethod">Klikni </button>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: "About",
  data() {
    return {
      messege: ''
    }
  },
  mounted() {
    axios
        .get('http://localhost:8080/proba')
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