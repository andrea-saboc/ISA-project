<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
  <div class = "container pt-100">


<div class="w-50">
   <div v-if="userType == 'Client'">
      <h1 >Number of penalties: {{user.penaltyPoints}}</h1>
   </div>
   <br>
   <br>
   <h3>Change your info :</h3>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">First name</span>
      </div>
      <input type="text" class="form-control" v-model="name">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeName">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">Last name</span>
      </div>
      <input type="text" class="form-control" v-model="surname">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeSurname">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">Country</span>
      </div>
      <input type="text" class="form-control" v-model="country">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeCountry">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">City</span>
      </div>
      <input type="text" class="form-control" v-model="city">
      <button class="btn btn-outline-secondary" type="button"  v-on:click="ChangeCity">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="" v-on:click="ChangeAddress">Address</span>
      </div>
      <input type="text" class="form-control"  v-model="address">
      <button class="btn btn-outline-secondary" type="button">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">Phone number</span>
      </div>
      <input type="text" class="form-control" v-model="phoneNumber">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangePhoneNumber">Change</button>
   </div>
</div>

<br>
<br>
<br>
<br>
<div class="w-50">
   <h3>Change your password :</h3>
      <table>
         <tr>
            <td> <label>Enter your old password:</label></td>
            <td><input class="input-group-text" type="password" v-model="oldPassword"></td>
         </tr>
         <tr>
            <td> <label>Enter your new password:</label></td>
            <td><input class="input-group-text" type="password" v-model="newPassword"></td>
         </tr>
         <tr>
            <td> <label>Reenter your new password:</label></td>
            <td><input class="input-group-text" type="password" v-model="newPasswordRepeated"></td>
         </tr>
         <tr>
            <td></td>
            <td><button class="btn btn-secondary" v-on:click="ChangePassword">Change</button></td>
         </tr>
      </table>

</div>

</div>
<br>
<br>
<br>

<button class = "btn btn-danger btn-lg" data-bs-toggle="modal" data-bs-target="#del">Request deleting account
      </button>
      <div class="modal fade" id="del" tabindex="-1" role="dialog" aria-labelledby="del" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="del">What is the reason for deleting your account?</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <textarea class="form-control"  v-model="deletionReason"></textarea>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-secondary" data-dismiss="modal" v-on:click="SubmitDeletionRequest()">Submit</button>

            </div>
          </div>
        </div>
      </div>

</section>
</template>

<script>
import axios from 'axios'
export default{
    data(){
        return{
         userType: null,
         user : {},
         name : '',
         surname: '',
         address: '',
         city: '',
         country: '',
         phoneNumber: '',
         oldPassword: '',
         newPassword: '',
         newPasswordRepeated: '',
         penalties: '0',
         deletionReason: ''
        }
   },
   mounted(){
      this.userType = this.$store.state.userType
      alert(this.user)
      console.log('hello from profile',this.$store.getters.tokenString)
         axios
         .get('http://localhost:8080/userData',{
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
         console.log(this.user)
         axios
         .post('http://localhost:8080/updateUser',this.user)
         .then(response => {
            console.log(response.data)
            alert('Your info is updated.')
         });
      },
      ChangePassword(){
         
         if(this.oldPassword === this.user.password && this.newPassword === this.newPasswordRepeated){
            this.user.password = this.newPassword;
            this.UpdateUser();
         }
      },
      SubmitDeletionRequest(){
         axios
         .post('http://localhost:8080/createDeletionRequest',this.deletionReason,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            console.log(response.data)
      });

      }

    
    }
}

</script>


<style>

</style>