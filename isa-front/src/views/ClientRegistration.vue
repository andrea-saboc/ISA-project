<template>
<div class="registrationForm">
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
   <button class="buttonLogin" v-on:click="Register">Register</button>
   <br>
   <br>
   <br>
   <br>
   <br>
</div>
</template>

<script>
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
            fieldsFillled : true
        }
    },
    Register(){},
    CheckFields(){
        if(this.name === '') this.fieldsFillled = false;
        if(this.surname === '') this.fieldsFillled = false;
        if(this.address === '') this.fieldsFillled = false;
        if(this.city === '') this.fieldsFillled = false
        if(this.country === '') this.fieldsFillled = false
        if(this.phoneNumber === '') this.fieldsFillled = false
        if(this.email === '') this.fieldsFillled = false
    }


}
</script>
<style>
  h1{
    text-align: center;
  }

  .registrationForm{
      text-align:center;
  }

  input{
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
