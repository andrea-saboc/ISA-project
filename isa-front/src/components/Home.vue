<template>
  <div id="home">
    <h1>Chose your interests</h1>
    <button v-on:click="RedirectBoats">BOATS</button><br><br>
    <button v-on:click="RedirectMansions">MANSIONS</button><br><br>
    <button v-on:click="RedirectInstructors">INSTRUCTORS</button><br><br>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Home',
  components: {
  },
  methods:{
    RedirectBoats(){
      this.$router.push({name: 'boats'});
    },
    RedirectMansions(){

    },
    RedirectInstructors(){

    }

  }
}
</script>
<style>

#home{   
  margin: 0 auto;
  text-align: center;
}

#home button{
	padding:10px;
	border:none;
	background-color:#2ECC71;
	color:#fff;
	font-weight:600;
	border-radius:20px;
	width:600px;
  height:50px;

}

#home button:hover{
	background: #333;
  }

</style>