<template>

<div id="profileInfo">
<h3>Change your info :</h3>
<table>
   <tr>
      <td> <label>Name:</label></td>
      <td><input type="text" v-model="name"></td>
      <td><button v-on:click="ChangeName">Change</button></td>
   </tr>
   <tr>
      <td> <label>Surname:</label></td>
      <td><input type="text" v-model="surname"></td>
      <td><button v-on:click="ChangeSurname">Change</button></td>
   </tr>
   <tr>
      <td> <label>Address:</label></td>
      <td><input type="text" v-model="address"></td>
      <td><button v-on:click="ChangeAddress">Change</button></td>
   </tr>
   <tr>
      <td> <label>City:</label></td>
      <td><input type="text" v-model="city"></td>
      <td><button v-on:click="ChangeCity">Change</button></td>
   </tr>
   <tr>
      <td> <label>Country:</label></td>
      <td><input type="text" v-model="country"></td>
      <td><button v-on:click="ChangeCountry">Change</button></td>
   </tr>
   <tr>
      <td> <label>Phone number:</label></td>
      <td><input type="text" v-model="phoneNumber"></td>
      <td><button v-on:click="ChangePhoneNumber">Change</button></td>
   </tr>
</table>
<br>
<br>
<br>
<h3>Change your password :</h3>
<table>
   <tr>
      <td> <label>Enter your old password:</label></td>
      <td><input type="password" v-model="oldPassword"></td>
   </tr>
   <tr>
      <td> <label>Enter your new password:</label></td>
      <td><input type="password" v-model="newPassword"></td>
   </tr>
   <tr>
      <td> <label>Reenter your new password:</label></td>
      <td><input type="password" v-model="newPasswordRepeated"></td>
   </tr>
   <tr>
      <td></td>
      <td><button v-on:click="ChangePassword">Change</button></td>
   </tr>
</table>
</div>
</template>

<script>
import axios from 'axios'
export default{
    data(){
        return{
         user : {},
         name : '',
         surname: '',
         address: '',
         city: '',
         country: '',
         phoneNumber: '',
         oldPassword: '',
         newPassword: '',
         newPasswordRepeated: ''
        }
   },
   mounted(){
      console.log('hello from profile',this.$store.getters.tokenString)
         axios
         .post('http://localhost:8080/userData', this.user.email,{
            headers: {
               'Authorization' : this.$store.getters.tokenString
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
    methods:{

      ChangeName(){
         alert('name changed')
         this.user.name = this.name;
         this.UpdateUser();
      },
      ChangeSurname(){
         this.user.surname = this.surname;
         this.UpdateUser();
      },
      ChangeAddress(){
         this.user.address = this.address;
         this.UpdateUser();
      },
      ChangeCity(){
         this.user.city = this.city;
         this.UpdateUser();
      },
      ChangeCountry(){
         this.user.country = this.country;
         this.UpdateUser();
      },
      ChangePhoneNumber(){
         this.user.country = this.country;
         this.UpdateUser();
      },
      UpdateUser(){

         axios
         .post('http://localhost:8080/updateUser',this.user)
         .then(response => {
            alert('user is updated')
         });
      },
      ChangePassword(){
         
         if(this.oldPassword === user.password && this.newPassword === this.newPasswordRepeated){
            this.user.password = this.newPassword;
            this.UpdateUser();
         }
      }

    
    }
}

</script>


<style>

  #profileInfo{
      text-align:center;
  }

  #profileInfo input{
  width: 300px;
	padding:10px 10px 10px 5px;
  display:block;
  width:300px;
  border:none;
  border-bottom:1px solid #757575;
  }

  #profileInfo table{
    margin-left: auto;
    margin-right: auto;
  }

  #profileInfo button{
   padding:10px;
	border:none;
	background-color:#2ECC71;
	color:#fff;
	font-weight:600;
	border-radius:20px;
	width:100px;
  }

  #profileInfo button:hover{
	background: #333;
  }


</style>