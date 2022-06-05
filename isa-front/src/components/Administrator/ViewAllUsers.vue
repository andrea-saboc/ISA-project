<template>
    <div class="boat-reservations">
   <br>
   <br>
   <h3>View all MansionOwner :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
           <th scope="col">Email</th>

          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allMansionOwners" :key="user.id" style="align-content: center" data-toggle="modal"  >
          <td v-if="user.deleted==false"> {{index+1}}</td>
          <td v-if="user.deleted==false" style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.surname}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.email}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteUser(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>
       <br>
   <br>
   <br>
   <br>
   <h3>View all Client :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
           <th scope="col">Email</th>

          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allClient" :key="user.id"  style="align-content: center" data-toggle="modal"  >
          <td v-if="user.deleted==false"> {{index+1}}</td>
          <td v-if="user.deleted==false"  style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.surname}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.email}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteUser(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>
       <br>
   <br>
   <h3>View all Instructor :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
           <th scope="col">Email</th>

          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allInstructors" :key="user.id" style="align-content: center" data-toggle="modal"  >
          <td v-if="user.deleted==false" > {{index+1}}</td>
          <td v-if="user.deleted==false" style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.surname}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.email}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteUser(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>


             <br>
   <br>
   <h3>View all BoatOwners :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
           <th scope="col">Email</th>

          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allBoatOwners" :key="user.id" style="align-content: center" data-toggle="modal" >
          <td v-if="user.deleted==false" > {{index+1}}</td>
          <td v-if="user.deleted==false" style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.surname}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.email}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteUser(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>

   <br>
   <br>
   <h3>View all boat :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">Boat owner</th>
           <th scope="col">Type</th>

          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allBoats" :key="user.id" style="align-content: center" data-toggle="modal" >
          <td v-if="user.deleted==false" > {{index+1}}</td>
          <td v-if="user.deleted==false" style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.boatOwner.name}} {{user.boatOwner.surname}}</td>
          <td v-if="user.deleted==false" style="align-content: center">{{user.type}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteBoat(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>

       <br>
   <br>
   <h3>View all mansion :</h3>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Name</th>
          <th scope="col">MansionOwner owner</th>
          <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(user, index) in allMansions" :key="user.id" style="align-content: center" data-toggle="modal"  >
          <td v-if="user.deleted==false"> {{index+1}}</td>
          <td v-if="user.deleted==false" style="align-content: center"> {{user.name}}</td>
          <td v-if="user.deleted==false"  style="align-content: center">{{user.mansionOwner.name}} {{user.mansionOwner.surname}}</td>
          <td >
              <button  v-if="user.deleted==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="deleteMansion(user)">
                Delete
            </button>
          </td>
         
        </tr>
        </tbody>
      </table>
</div>
</template>

<script>

import axios from 'axios'
import {devServer} from "../../../vue.config";
export default {
  name: "viewAllUsers",
  data: function (){
    return{
        loggedUser: null,
        allMansionOwners: [],
        allClient: [],
        allInstructors:[],
        allBoatOwners: [],
        allBoats:[],
        allMansions:[]
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
        this.LoadMansionOwners()
        this.LoadClient()
        this.LoadInstructor()
        this.LoadBoatOwners()
        this.LoadBoat()
        this.LoadMansion()
    })

},
methods:
{
    LoadMansionOwners()
    {
         axios.get(devServer.proxy + "/getAllMansionOwners", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allMansionOwners=response1.data;
             console.log("All allMansionOwners: ",this.allMansionOwners);
         })

    },
      LoadInstructor()
    {
         axios.get(devServer.proxy + "/getAllInstructor", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allInstructors=response1.data;
             console.log("All allInstructors: ",this.allInstructors);
         })

    },
     LoadClient()
    {
         axios.get(devServer.proxy + "/getAllClient", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allClient=response1.data;
             console.log("All allClient: ",this.allClient);
         })

    },
     LoadBoatOwners()
    {
         axios.get(devServer.proxy + "/getAllBoatOwners", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allBoatOwners=response1.data;
             console.log("All allBoatOwners: ",this.allBoatOwners);
         })

    },
      LoadBoat()
    {
         axios.get(devServer.proxy + "/boats", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allBoats=response1.data;
             console.log("All allBoats: ",this.allBoats);
         })

    },
    LoadMansion()
    {
         axios.get(devServer.proxy + "/mansions", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.allMansions=response1.data;
             console.log("All allMansions: ",this.allMansions);
         })

    },
    deleteUser(user)
    { axios.post(devServer.proxy + "/deleteUser",{
        "userId" : user.id
      }, {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
      .then(response => {
          console.log(response.data)
        this.LoadMansionOwners()
        this.LoadClient()
        this.LoadInstructor()
        this.LoadBoatOwners()
        this.LoadBoat()
        this.LoadMansion()
      
    })
 },
 deleteMansion(user)
 {
      axios.post(devServer.proxy + "/deleteMansion",{
        "boatId" : user.id
      }, {
        headers: {
          'Authorization' : 'Bearer ' + this.token
        }
      })
          .then(response => {
            alert("Mansion successfully deleted!", response.data)
             this.LoadMansionOwners()
        this.LoadClient()
        this.LoadInstructor()
        this.LoadBoatOwners()
        this.LoadBoat()
        this.LoadMansion()
          })
 },
 deleteBoat(user)
 {
      axios.post(devServer.proxy + "/deleteBoat",{
        "boatId" : user.id
      }, {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
      .then(response => {
        alert(response.data)
            this.LoadMansionOwners()
        this.LoadClient()
        this.LoadInstructor()
        this.LoadBoatOwners()
        this.LoadBoat()
        this.LoadMansion()
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
