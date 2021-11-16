<template>
<div id="registrationForm">
   <h1>Registracija</h1>
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
   <br>
   <input placeholder="Email" class="inputKredencijali" type="text" v-model="email"/>
   <br>
   <input placeholder="Password" class="inputKredencijali" type="password" v-model="password"/>
   <br>
   <input placeholder="Repeat your password" class="inputKredencijali" type="password" v-model="passwordRepeated"/>
   <br>
   <br>
   <br>
   <br>
   <button class="buttonRegister" v-on:click="Register">Register</button>
   <br>
   <br>
   <br>
   <br>
   <br>
</div>
</template>

<script>
import axios from 'axios'
export default{
    data(){
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
            passwordValid : true
        }
    },
    methods:{

      Register(){
      this.CheckIfPassworIsValid();
      this.CheckIfFieldsAreFilled();

      if(this.fieldsFilled && this.passwordValid){
        axios
            .post('http://localhost:8080/clientRegistration',
            {
              "name": this.name,
              "surname": this.surname,
              "address": this.address,
              "city": this.city,
              "country": this.country,
              "phoneNumber": this.phoneNumber,
              "email": this.emaill,
              "password": this.password,
            })
            .then(response => {
              alert(response.data)
              this.messege = response.data
            });
      }else alert('error in filling registration');

      },
      CheckIfFieldsAreFilled(){
          if(this.name === '') this.fieldsFilled = false;
          if(this.surname === '') this.fieldsFilled = false;
          if(this.address === '') this.fieldsFilled = false;
          if(this.city === '') this.fieldsFilled = false
          if(this.country === '') this.fieldsFilled = false
          if(this.phoneNumber === '') this.fieldsFilled = false
          if(this.email === '') this.fieldsFilled = false
      },
      CheckIfPassworIsValid(){
          if(!this.password === this.passwordRepeated) this.passwordValid = false;
      }
    }

}
</script>
<style>

  #registrationForm{
      text-align:center;
  }

  #registrationForm input{
  width: 300px;
  margin:5px 0;
	padding:10px;
	border-radius:20px;
	border: 2px solid #eee;
	box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
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

</style>
