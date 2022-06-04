<template>
    <div class="boat-reservations">
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
           <th scope="col">Reason</th>
          <th scope="col">Decline request</th>
          <th scope="col">Approve request</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allUsers" :key="user.id" style="align-content: center" data-toggle="modal"  v-on:click="selectUser(user)">
          <td> {{index+1}}</td>
          <td  style="align-content: center"> {{user.name}}</td>
          <td style="align-content: center">{{user.surname}}</td>
          <td style="align-content: center">{{user.reason}}</td>
          <td >
              <button v-if="user.deleted==false && user.notdeleted==false" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" >
              Unapprove request
            </button>
          </td>
          <td>
              <button v-if="user.deleted==false && user.notdeleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="approveRegistration(user)">
              Approve request
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
            axios.get(devServer.proxy + "/allRequestForDeleteAccount", {
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
   selectUser(user){
         console.log("Selektovani user je: ",user)
         this.selectedUser=user
     },
    loadUser()
    {
         axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.loggedUser =response.data
            axios.get(devServer.proxy + "/allRequestForDeleteAccount", {
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
      .post(devServer.proxy + "/unapproveRequest", {
        "reportText" : this.reportText,
        "id" : this.selectedUser.id
    
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
    approveRegistration(user)
     {
         this.selectedUser=user

           console.log("Selektovani user je: ",this.selectedUser)
          axios
      .post(devServer.proxy + "/approveRequest", {
       "reportText" : this.reportText,
        "id" : this.selectedUser.id

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
     }
    
     
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