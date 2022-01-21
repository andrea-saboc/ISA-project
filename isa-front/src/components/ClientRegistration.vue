<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
<div id="registrationForm">
<br>
<br>
   <h1>{{picked}} Registration</h1>
  <br>
  <div class="radio">
  <input type="radio" id="clientreg" value="Client" name="selecttype" v-model="picked">
  <label for="clientreg">Client</label>
  <input type="radio" id="ownerreg" value="Owner" name="selecttype" v-model="picked">
  <label for="ownerreg">Advertiser</label>
  </div>
  <br>
  <br>
    <div class="selection" v-if="picked=='Owner'">
      <select v-model="otype">
        <option value="none" disabled selected hidden>Select what you want to advertise</option>
        <option value="boat" >Boats</option>
        <option value="mansion">Mansions</option>
        <option value="fishing">Fishing classes</option>
      </select>

    </div>
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
  <textarea  name="textar" v-if="picked=='Owner'"
             placeholder="Give a short description about what you do and why you want to advertise yourself here..."
              v-model="reasonadv">

  </textarea>
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
</section>
</template>

<script>
import axios from 'axios'
import {devServer} from "../../vue.config";

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
            passwordValid : true,
          picked: 'Client',
          otype: 'none',
          reasonadv: ''
        }
    },
    methods:{

      Register(){
      this.CheckIfPassworIsValid();
      this.CheckIfFieldsAreFilled();

      if(this.fieldsFilled && this.passwordValid && this.picked=='Client'){
        axios
            .post(devServer.proxy + '/register/client',
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
      } else if (this.fieldsFilled && this.passwordValid && this.picked=='Owner'){
        axios
      .post(devServer.proxy +'/register/advertiser',
            {
              "name": this.name,
              "surname": this.surname,
              "address": this.address,
              "city": this.city,
              "country": this.country,
              "phoneNumber": this.phoneNumber,
              "email": this.email,
              "password": this.password,
              "type": this.otype,
              "reason": this.reasonadv
            })
            .then(response => {
              alert(response.data)
              this.message = response.data
            });
        this.ClearAllFields();
      } else alert('Error in filling registration');

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
          if(this.picked == 'Owner' && (this.otype=='none' || this.reasonadv =='')){
            alert("Fill out the fields!")
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
        this.reasonadv ="";
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
