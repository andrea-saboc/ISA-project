<template>
    <div class="boat-reservations">
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
          <th scope="col">Address</th>
          <th scope="col">City</th>
          <th scope="col">Country</th>
          <th scope="col">Email</th>
           <th scope="col">Reason</th>
          <th scope="col">Decline registration</th>
          <th scope="col">Approve registration</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allUsers" :key="user.id" style="align-content: center" data-toggle="modal"  v-on:click="selectUser(user)">
          <td> {{index+1}}</td>
          <td  style="align-content: center"> {{user.name}}</td>
          <td style="align-content: center">{{user.surname}}</td>
          <td style="align-content: center" >{{user.address}}</td>
          <td style="align-content: center">{{user.city}}</td>
          <td style="align-content: center">{{user.country}}</td>
          <td style="align-content: center">{{user.email}}</td>
          <td style="align-content: center">{{user.reason}}</td>
          <td >
              <button v-if="user.approved==false && user.notapproved==false" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" >
              Unapprove registration
            </button>
          </td>
          <td>
              <button v-if="user.approved==false && user.notapproved==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="approveRegistration(user)">
              Approve registration
            </button>
           </td>
        </tr>
        </tbody>
      </table>
         <div  class="modal fade"  id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
      <div class="modal-dialog" >
        <div class="modal-content">
          <div class="modal-header" >
            <h5 class="modal-title" id="staticBackdropLabel"> Create unapprove report</h5>
          </div>
          <div class="modal-body">
            <div class="form-floating">
              <label for="floatingTextarea2">Comments</label>
              <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" v-model="reportText">
              </textarea>
            </div>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" v-on:click="createReport">Create report</button>
          </div>
        </div>
      </div>
    </div>
    </div>
</template>

<script>

import axios from 'axios'
import {devServer} from "../../../vue.config";
export default {
  name: "viewUserRegistration",
  data: function (){
    return{
        loggedUser: null,
        allUsers: [],
        selectedUser: null,
        reportText: ''
      

    }
  },
  mounted() {
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.loggedUser =response.data
            axios.get(devServer.proxy + "/allUsersWithoutAdmin", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allUsers=response1.data;
             console.log("All users: ",this.allUsers);
         })  
    })
},
methods:
{
    loadUser()
    {
         axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.loggedUser =response.data
            axios.get(devServer.proxy + "/allUsersWithoutAdmin", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allUsers=response1.data;
             console.log("All users: ",this.allUsers);
         })  
    })
    },

     createReport(){
     console.log("Selektovani user je: ",this.selectedUser)
     axios
      .post(devServer.proxy + "/unapproveRegistration", {
        "reportText" : this.reportText,
        "approved" : false,
        "email" : this.selectedUser.email
    
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString
        }
      }
      )
      .then(response =>
      {
      alert("Successfully created!", response)
      this.loadUser()
      
      
     })
      .catch(() =>
      {
        alert("Server error")
      })
     
    }, approveRegistration(user)
     {
       this.selectedUser=user
           console.log("Selektovani user je: ",this.selectedUser)
          axios
      .post(devServer.proxy + "/approveRegistration", {
        "reportText" : this.reportText,
        "approved" : true,
        "email" : this.selectedUser.email
    
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString
        }
      }
      )
      .then(response =>
      {
      alert("Successfully created!", response)
      this.loadUser()
      
      
     })
      .catch(() =>
      {
        alert("Server error")
      })
     },
     selectUser(user){
         console.log("Selektovani user je: ",user)
         this.selectedUser=user
     },
    
     
}
}
</script>

<style scoped>
.boat-reservations{
  margin-bottom: 20%;
  margin-left: 15%;
  margin-right: 10%;
  margin-top: 10%;
}

</style>