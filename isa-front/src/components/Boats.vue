<template>
<section>
<button class = "btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#res">Make a reservation
</button>
<div class="modal fade" id="res" tabindex="-1" role="dialog" aria-labelledby="res" aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="res">Making reservation</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
            <div class="modal-body">
               <div class="input-group input-group-lg">
                  <label class="form-control">Select start date:</label><input v-model="reservationForm.startDate" class="form-control" placeholder="Select date" type="date" id="example"><br>
               </div>
               <div class="input-group input-group-lg">
                  <label class="form-control">Select start time:</label><input v-model="reservationForm.startTime" class="form-control" placeholder="Select time" type="time" id="example"><br>
               </div>
               <div class="input-group input-group-lg">
                  <label class="form-control">Number of days:</label><input v-model="reservationForm.numberOfDays" class="form-control" type="number"><br>
               </div>
               <div class="input-group input-group-lg">
                  <label class="form-control">Number of guests:</label><input v-model="reservationForm.numberOfGuests" class="form-control" type="number"><br>
               </div>
               <br>
               <label>Optional:</label><br>
               <div class="input-group input-group">
                  <label class="form-control">Location:</label><input v-model="reservationForm.location" class="form-control" type="text"><br>
               </div>
               <div class="input-group input-group">
                  <label class="form-control">Grade:</label><input v-model="reservationForm.grade" class="form-control" type="number"><br>
               </div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
               <button type="button" class="btn btn-primary" v-on:click=SearchForReservations>Search</button>
            </div>
      </div>
   </div>
</div>
</section>
<section class="p-5">
   <div class = "container">
      <div class="row text-center">
         <div class="col-md">
            <div v-for="(value, index) in boats">
              <p>{{index}}</p>
               <div v-if="index % 2 != 0">
                  <div class="card mb-3" v-on:click="showboat(value)">
                     <img src="../assets/yacht.jpg" class="card-img-top img-fluid w-30">
                     <div class="card-body">
                        <h5 class="card-title">{{value.name}}</h5>
                        <p class="card-text">{{value.promoDescription}}</p>
                        <p class="card-text"><small class="text-muted">bilo sta1</small></p>
                     </div>
                  </div>
               </div>
                 <div v-if="index % 2 == 0">
                   <div class="card mb-3" v-on:click="showboat(value)">
                     <img src="../assets/yacht.jpg" class="card-img-top img-fluid w-30">
                     <div class="card-body">
                       <h5 class="card-title">{{value.name}}</h5>
                       <p class="card-text">{{value.promoDescription}}</p>
                       <p class="card-text"><small class="text-muted">bilo sta</small></p>
                     </div>
                   </div>
                 </div>
            </div>
         </div>
      </div>
   </div>
</section>
<div v-if="!makingReservation" class="card text-white bg-dark ml-100 mb-100" style="max-width: 20rem; position: fixed;bottom: 10px; right: 10px">
   <div class="card-header">Search or sort boats</div>
   <div class="card-body">
      <label>Chose search category:</label>
      <div class="dropdown mt-3">
         <button class="btn btn-secondary dropdown-toggle" type="button"  id="dropdown1" data-bs-toggle="dropdown">
         Name
         </button>
         <ul class="dropdown-menu" aria-labelledby="dropdown1">
            <li><button class="dropdown-item">Name</button></li>
            <li><button class="dropdown-item">Location</button></li>
            <li><button class="dropdown-item">Grade</button></li>
         </ul>
      </div>
      <div class="input-group mt-2">
         <input type="text" class="form-control" aria-label="Search boats...">
         <button class = "btn btn-primary btn-lg">Search</button>
      </div>
   </div>
   <div class="card-body">
      <label>Chose sort category:</label>
      <br>
      <div class="input-group">
         <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown2" data-bs-toggle="dropdown" aria-expanded="true">
            {{sort}}
            </button>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdown2">
               <li><button class="dropdown-item" v-on:click=SortByName>Name</button></li>
               <li><button class="dropdown-item" v-on:click=SortByLocation>Location</button></li>
               <li><button class="dropdown-item" v-on:click=SortByGrade>Grade</button></li>
            </ul>
         </div>
         <button class = "btn btn-primary" v-on:click=Sort>Sort</button>
      </div>
   </div>
</div>
</template>

<script>
import axios from 'axios'
import Popper from 'popper.js'
export default{
    name: 'boats',
    data: function(){
        return{

            makingReservation: false,
            sort: 'Name',
            reservationForm:{
               'startDate': '',
               'startTime': '',
               'numberOfClients': '',
               'numberOfDays': '',
               'location': '',
               'grade': '',

            },
            boats : []
            }
        },
    mounted(){
        axios
            .get('http://localhost:8080/boats')
            .then(response => {
                this.boats = response.data;
                alert(this.boats);
        });   

    },
    methods:{
        SortByName(){
            this.sort = 'Name';
        },
        SortByGrade(){
            this.sort = 'Grade';
        },
        SortByLocation(){
            this.sort = 'Location';
        },
        Sort(){

            if (this.sort == 'Name') {
				this.boats.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sort == 'Grade') {
				this.boats.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
			} else if (this.sort == 'Location') {
				this.boats.sort((b, a) => (a.address > b.address) ? 1 : ((b.address > a.address) ? -1 : 0));
			}
        },
        SearchForReservations(){

           this.makingReservation = true;
           console.log(this.reservationForm.startDate)
           console.log(this.reservationForm.startTime)
            axios
            .post('http://localhost:8080/reservations/availableBoats',this.reservationForm)
            .then(response => {
               this.boats = response.data
               console.log(response.data)
            });
        },
     showboat(value){
       window.location.href = "/boat/"+value.id.toString();
     }

    }
}

</script>

<style>

</style>