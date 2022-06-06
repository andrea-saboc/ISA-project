<template>
    <div class="boat-reservations">
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Report Text</th>
          <th scope="col">Decline request</th>
          <th scope="col">Approve request</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(report, index) in reports" :key="report.id" style="align-content: center" data-toggle="modal"  v-on:click="selectReport(report)">
          <td> {{index+1}}</td>
          <td  style="align-content: center"> {{report.reportText}}</td>
          <td >
              <button v-if="report.notApproved==false && report.approved==false" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" >
              Unapprove request
            </button>
          </td>
          <td>
              <button v-if="report.notApproved==false && report.approved==false" class="btn btn-primary" data-bs-toggle="modal" v-on:click="approveReport(report)">
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
  name: "viewReports",
  data: function (){
    return{
        loggedUser: null,
        reports: [],
        selectedReport: null,
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
            axios.get(devServer.proxy + "/getAllReports", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.reports=response1.data;
             console.log("All reports: ",this.reports);
         })  
    })
},
methods:
{
   selectReport(report){
         console.log("Selektovani report je: ",report)
         this.selectedReport=report
     },
    loadReports()
    {
        
       axios.get(devServer.proxy + "/getAllReports", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
              }
         })
         .then(response1 =>{
             this.reports=response1.data;
       
         })  
    },

     createReport(){
     console.log("Selektovani user je: ",this.selectedReport)
     axios
      .post(devServer.proxy + "/unapproveComment", {
        "reportText" : this.reportText,
        "idReservation" : this.selectedReport.id
    
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString
        }
      }
      )
      .then(response =>
      {
      alert("Successfully created!", response)
      this.loadReports()
      
      
     })
     
    }, 
    approveReport(report)
     {
         this.selectedReport=report

          axios
      .post(devServer.proxy + "/approveComment", {
       "reportText" : this.reportText,
        "idReservation" : this.selectedReport.id

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