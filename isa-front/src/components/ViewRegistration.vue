<template>
  <h1>View registration:</h1>
<div  v-for="res in registrationsUsers" v-bind:key="res">
<ul class="lista">
    <li class="element1">
        <div  class="content-left-alight">
            <p><b>Reason: {{res.reason}} </b></p>
            <p>Email: {{res.email}} </p>
        </div>
        <div  class="button-div">
            <button class="button is-info w3-hover-green">Approved</button>
        </div>   
    </li>
</ul>
</div>
</template>

<script>

import axios from 'axios'
export default {
  data: function(){
      return{
        registrationsUsers:[]
      }
  },
    mounted() {
    axios
        .get('http://localhost:8080/getAllAdvertisers')
        .then(response => {
          this.registrationsUsers = response.data;
        })
  },
  methods:{
   

  }
}
</script>

<style>

.lista{
	margin: 0 auto;
	text-align: center;
}

.element1{
    border: 8px solid #EFB0A1;
    width: 80%;
    margin-bottom: 10px;
    margin-left: 10%;
  }

 .content-left-alight{
   text-align: left;
   width: 80%;
 }

 .button-div{
   width: 20%;
 }

</style>