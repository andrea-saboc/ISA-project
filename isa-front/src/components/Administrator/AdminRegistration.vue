<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<div id="registrationForm">
<br>
<br>
   <h1>Registration</h1>
  <br>

   <br>
   <input placeholder="Name" class="inputKredencijali" type="text" v-model="name"/>            
   <br>
   <input placeholder="Surname" class="inputKredencijali" type="text" v-model="surname"/>
   <br> 
   <input placeholder="Address" class="inputKredencijali" type="text" v-model="address"/>
   <br>
   <input placeholder="City" class="inputKredencijali" type="text" v-model="city"/>
   <br>
   <input placeholder="Country" class="inputKredencijali" type="text" v-model="country"/>
   <br>
   <input placeholder="Phone number" class="inputKredencijali" type="text" v-model="phoneNumber"/>
   <br>
   <input placeholder="Email" class="inputKredencijali" type="text" v-model="email"/>
   <br>
   <input placeholder="Password" class="inputKredencijali" type="password" v-model="password"/>
   <input placeholder="Repeat your password" class="inputKredencijali" type="password" v-model="passwordRepeated"/>
   <br>
   <br>
   <br>
   <br>
   <button class="buttonRegister" type="submit" @click="Register">Register</button>
   <br>
   <br>
   <br>
   <br>
   <br>
</div>
</section>
</template>
<script>
import axios from "axios";
import {devServer} from "../../../vue.config";

export default{
  name:"adminRegistration",
    data: function(){
        return{
            name: '',
            surname: '',
            address: '',
            city: '',
            country: '',
            phoneNumber: '',
            email: '',
            password: '',
            passwordRepeated: '',
            fieldsFilled : true,
            passwordValid : true,
         
        }
    },
     mounted()
    {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   console.log('toke je',token)
    },
    methods:
    {
      Register()
      {
         this.CheckIfPassworIsValid();
      this.CheckIfFieldsAreFilled();
if(this.fieldsFilled && this.passwordValid){
         axios.post(devServer.proxy + '/register/administrator',
            {
              "name": this.name,
              "surname": this.surname,
              "address": this.address,
              "city": this.city,
              "country": this.country,
              "phoneNumber": this.phoneNumber,
              "email": this.email,
              "password": this.password,
            })
            .then(response => {
              alert(response.data)
              this.message = response.data;
            });
            this.ClearAllFields();
            }

      },
      CheckIfFieldsAreFilled(){
          if(this.name === ''){
            this.fieldsFilled = false;
            alert("Enter your name")
            return;
          }
          if(this.surname === '') {
            alert("Enter your surname")
            this.fieldsFilled = false;
            return;
          }
          if(this.address === '' || this.city === '' || this.country === '') {
            alert("Enter your address")
            this.fieldsFilled = false;
            return;
          }
          if(this.phoneNumber === '') {
            alert("Enter your phoneNumber")
            this.fieldsFilled = false
            return;
          }
          if(this.email === '') {
            alert("Enter your email")
            this.fieldsFilled = false
            return;
          }
          
          this.fieldsFilled = true
      },
      CheckIfPassworIsValid(){
          if(!this.password === this.passwordRepeated) {
            this.passwordValid = false;
            alert("Repeat your password correctly")
          }
          else{
            this.passwordValid = true;
          }
      },
      ClearAllFields(){
        this.name = "";
        this.surname = "";
        this.email = "";
        this.address = "";
        this.city = "";
        this.country = "";
        this.password= "";
        this.passwordRepeated= "";
        this.phoneNumber = "";
        this.passwordValid = false;
        this.fieldsFilled = false;

      }
    }
}
</script>


<style>

  #registrationForm{
      width: 30%;
      margin-left: 30% ;
      text-align:center;
  }

  #registrationForm input{
  width: 60%;
  margin:5px 0;
	padding:10px;
	border-radius:20px;
	border: 2px solid #eee;
	box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
  }

  #registrationForm .radio{
    display: flex;
    flex-direction: row;
    padding: 10px;
    border-radius:20px;
    background: #2ECC71;
  }

  #registrationForm input[type='radio']{
    width: 30%;
    margin: 0;
    border-color: purple;
  }



  #registrationForm input:focus{
	background: #FCFCFC;
	outline: none;
	
  }
  
 .buttonRegister {
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

  #registrationForm textarea{
    font-family: inherit;
    color: #bbbbbb;
    width: 70%;
    height: 200px;
    padding: 10px;
    border-radius: 20px;
  }

</style>