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
   <button class="buttonLogin" v-if="show==false" v-on:click="Login">Login</button>
    <br>
    <div v-if="show===true">
     <b-modal  hide-footer scrollable title="Please change password to continue" size="lg" modal-class="b-modal">
            <div modal-class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="background-color:whitesmoke">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Current Password:</label>
                            <input type="password" class="form-control" v-model="oldPassword" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <label>New password:</label>
                            <input type="password" class="form-control" v-model="newPassword" placeholder=" New Password">
                        </div>
                        <div class="form-group">
                            <label>Repeat new password:</label>
                            <input type="password" class="form-control" v-model="newPasswordRepeated" placeholder="Repeat new Password">
                        </div>
                        <button v-on:click = "ChangePassword" class="btn btn-primary">Confirm</button>        
                    </div>                
                </div>
            </div>
        </b-modal>
        </div>
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
            fieldEmpty: false,
            show:false,
             loggedUser:null,
              oldPassword: '',
         newPassword: '',
         newPasswordRepeated: '',
         approveRegistration: false
        }
    },
    mounted(){
    if (this.$store.state.userType !== null) {
      this.$router.push("/")
      }       
    },
    methods:{
       LoadUser(){
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
         axios
         .get(devServer.proxy +'/userData',{
            headers: {
               'Authorization' : 'Bearer ' + token,
            }
         })
         .then(response => {

            this.user = response.data;
            this.name = response.data.name;
            this.surname = response.data.surname;
            this.address = response.data.address;
            this.city = response.data.city;
            this.country = response.data.country;
            this.phoneNumber = response.data.phoneNumber; 
    
      });

      },
      Login(){
        localStorage.removeItem('token');
        localStorage.clear();
        let store = this.$store;
        this.CheckIfEmpty()
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

                    localStorage.setItem('token', JSON.stringify(response.data.accessToken));
                    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
                     axios.get(devServer.proxy + "/userData", {
                    headers: {
                    'Authorization' : 'Bearer ' + token,
                  }
                    })
               .then(response1 => {
                this.loggedUser =response1.data
                    console.log(token)
                    console.log('TU SAM',response1.data);
                    this.approveRegistration=response1.data.approved;

                if(this.loggedUser.loggedIn===false)
                {
                  console.log("I AM HERE")
                  this.show=true;
                }
                if(this.loggedUser.deleted==true)
                {
                  alert('your account is deleted')
                  return;
                }
                store.dispatch('startSession', response.data);
                console.log('User got the token:',this.$store.getters.tokenString)
                this.dispatch(response.data.userType);
                console.log('User type is:')
                console.log(this.$store.state.userType)
              })
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
        return;
        }
      else if(type === 'MansionOwner'){
        if(this.approveRegistration===false)
        {
          alert("You cant registration")
        }
        else
        {
        router.push("/mansionOwnerHomePage")
        }
        return;
      }

       else if(type === 'Administrator') {
         
       if(this.loggedUser.loggedIn===false)
                {
                  console.log("I AM HERE")
                  this.show=true;
                }
 else
 {
 router.push("/administratorHomePage")
          return;
 }
        //window.location.reload()
      }
      else if(type === "BoatOwner"){
        if(this.approveRegistration===false)
        {
          alert("You cant registration")
        }
        else
        {
        router.push("/boatOwnerHomePage")
        return;
        }
      }
      else if(type === 'FishingInstructor'){
        if(this.approveRegistration===false)
        {
          alert("You cant registration")
        }
        else
        {
        router.push("/fishingInstructorHomePage")
        return;
        }
      }

      },
      ChangePassword(){
  let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
  console.log("Usla sam u password",this.loggedUser)
  console.log(this.newPassword)
  console.log( this.newPasswordRepeated)
         if(this.newPassword === this.newPasswordRepeated){

            var passwords={
               newPassword: this.newPassword,
               oldPassword: this.oldPassword
            }  
            console.log(passwords)
            axios
            .post(devServer.proxy + '/changePassword',passwords,{
            headers: {
            'Authorization' : 'Bearer ' + token,
            'Content-Type': 'application/json'
            }
            })
            .then(response => {
               console.log(response.data)
               alert(response.data)
                let router = this.$router;
               this.LoadUser();
               this.oldPassword ="";
               this.newPassword="";
               this.newPasswordRepeated="";

               router.push("/administratorHomePage")
               return;
            });        
         }else{
            alert('Repeated password not right')
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