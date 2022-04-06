<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<div id="loginForm">

    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
   <h1>Login</h1>
   <br>
   <input placeholder="Email" class="inputKredencijali" type="text" v-model="email"/>
   <br>
   <input placeholder="Password" class="inputKredencijali" type="password" v-model="password"/>
   <br>
   <br>
   <br>
   <button class="buttonLogin" v-on:click="Login">Login</button>
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
import {devServer} from "../../vue.config";
//import {onBeforeMount} from "vue";
export default{
    data(){
        return{
            userType: '',
            email: '',
            password: '',
            fieldEmpty: false
        }
    },
    mounted(){
    if (this.$store.state.userType !== null) {
      this.$router.push("/")
      }       
    },
    methods:{
      Login(){
        let store = this.$store;
        if(!this.fieldEmpty){
          axios
              .post(devServer.proxy +'/login',
              {
                "email": this.email,
                "password": this.password
              })
              .then(response => {
                if(!response.data){
                  alert('Bad username or password')
                  return
                }

                store.dispatch('startSession', response.data);
                console.log('User got the token:',this.$store.getters.tokenString)
                this.dispatch(response.data.userType);
                console.log('User type is:')
                console.log(this.$store.state.userType)
              })
              .catch(error=>{
                alert('Wrong email or password')
                console.log(error)
              })




        }else alert('error in filling form');

      },

      CheckIfEmpty(){
        if(this.email === '' || this.password === '') this.fieldEmpty = true;
      },

      dispatch(type) {
      console.log('User type after login:', type)
      let router = this.$router;
      if (type === 'Client') {
        //window.location.reload()
        router.push("/clientHomePage");
        return
        }
      else if(type === 'MansionOwner'){
        router.push("/mansionOwnerHomePage")
        return;
      }

       else if(type === 'Administrator') {
        router.push("/")
        window.location.reload()
      }
      else if(type === "BoatOwner"){
        router.push("/boatOwnerHomePage")
        return;
      }

      }
    }
}
</script>
<style>

  h1{
    text-align: center;
  }

  #loginForm{
      text-align:center;
  }

  #loginForm input{
    
  width: 300px;
	margin:5px 0;
	padding:10px;
	border-radius:20px;
	border: 2px solid #eee;
	box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
  }

  input:focus{
	background: #FCFCFC;
	outline: none;
	
  }
  
 .buttonLogin {
	padding:10px;
	border:none;
	background-color:#2ECC71;
	color:#fff;
	font-weight:600;
	border-radius:20px;
	width:300px;
  }
  .buttonLogin:hover{
	background: #333;
  }
</style>