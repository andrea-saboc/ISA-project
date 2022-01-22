<template>
   <section v-if="user === 'Client' && !makingReservation" class="bg-light text-sm-start">
      <div class="modal fade" id="res" tabindex="-1" role="dialog" aria-labelledby="ress" aria-hidden="true">
         <div class="modal-dialog" role="dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="ress">Making reservation</h5>
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
                     <label class="form-control">Number of days:</label><input v-model="reservationForm.numberOfDays" class="form-control" type="number" min="0"><br>
                  </div>
                  <div class="input-group input-group-lg">
                     <label class="form-control">Number of guests:</label><input v-model="reservationForm.numberOfGuests" class="form-control" type="number" min="1"><br>
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
                  <button type="button" class="btn btn-primary" v-on:click=SearchForReservations() data-dismiss="modal">Search</button>
               </div>
            </div>
         </div>
      </div>
   </section>


   <div v-if="makingReservation" class="card text-white bg-dark ml-100 mb-100" style="max-width: 20rem; position: fixed;bottom: 10px; right: 10px">
      <div class="card-header">Sort search results</div>
      <div class="card-body">
         <div class="input-group  mr-30">
            <div>
            <h4>Search results for:</h4><br>
            <p2>Start date: {{reservationForm.startDate}}</p2><br>
            <p1>Start time: {{reservationForm.startTime}}</p1><br>
            <p1>Number of days: {{reservationForm.numberOfDays}}</p1><br>
            <p1>Number of guests: {{reservationForm.numberOfGuests}}</p1><br>
            <p1>Avg grade: {{reservationForm.avgGrade}}</p1><br>
            <p1>Location: {{reservationForm.location}}</p1><br>
            <br>
            <br>
            </div>
            <h5>Chose sort category:</h5>
            <br><br>
            <div class="dropdown">
               <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown2" data-bs-toggle="dropdown" aria-expanded="true">
               {{sortSearchResult}}
               </button>
               <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdown2">
                  <li><button class="dropdown-item" v-on:click=SortResultByPrice>Prize</button></li>
                  <li><button class="dropdown-item" v-on:click=SortResultByAvgGrade>Average grade</button></li>
               </ul>
            </div>
            <button class = "btn btn-primary" v-on:click=SortResults>Sort</button><br><br>
         </div>
         <br>
         <br>
         <button class = "btn btn-outline-secondary" v-on:click=CancelMakingReservation>Go back </button>
      </div>
   </div>
   <section>
      <div class = "container">
         <div class="row text-center">
            <div class="col-md">
               <div v-for="(value, index) in mansions"
                  :key="index">
                  <div v-if="index % 2 == 0">
                     <div class="card mb-3">
                        <img v-if="!makingReservation" :src=picture(value.id) class="card-img-top img-fluid w-30" v-on:click="ShowMansion(value)">
                        <img v-if="makingReservation" :src=picture(value.entityId) class="card-img-top img-fluid w-30" v-on:click="ShowMansion(value)">
                        <div class="card-body">
                           <div v-on:click="ShowMansion(value)">
                              <h5 class="card-title">{{value.name}}</h5>
                              <p class="card-text">{{value.promoDescription}}</p>
                              <p v-if="!makingReservation" class="card-text"><small class="text-muted">{{value.address.address}},{{value.address.city}},{{value.address.country}}</small></p>
                           </div>
                           <div class="bg-light p-3 text-left" v-if="makingReservation">
                              <p class="card-text">Price: {{value.totalPrice}}</p>
                              <p class="card-text">Average grade: {{value.avgGrade}}</p>
                              
                              <div v-if="value.additionalServices.length != 0">
                              <p>Add additional services to your reservation: </p>
                              <div v-for="(s,index) in value.additionalServices"
                                 :key="index">
                                 <div class="custom-control custom-checkbox mb-3">
                                    <input type="checkbox" class="custom-control-input" :id="value.entityId+index" required>
                                    <label class="custom-control-label" :for="value.entityId+index">{{s}}</label>
                                 </div>
                              </div>
                              </div>
                              <button class="btn btn-primary" v-on:click=MakeBoatReservation(value)>Make a reservation</button>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="col-md">
               <div v-for="(value, index) in mansions"
                  :key="index">
                  <div v-if="index % 2 != 0">
                     <div class="card mb-3" v-on:click="ShowMansion(value)">
                        <img v-if="!makingReservation" :src=picture(value.id) class="card-img-top img-fluid w-30">
                        <img v-if="makingReservation" :src=picture(value.entityId) class="card-img-top img-fluid w-30">
                        <div class="card-body">
                           <h5 class="card-title">{{value.name}}</h5>
                           <p class="card-text">{{value.promoDescription}}</p>
                           <p v-if="!makingReservation" class="card-text"><small class="text-muted">{{value.address.address}},{{value.address.city}},{{value.address.country}}</small></p>
                        </div>
                        <div class="bg-light p-3 text-left" v-if="makingReservation">
                              <p class="card-text">Price: {{value.totalPrice}}</p>
                              <p class="card-text">Average grade: {{value.avgGrade}}</p>

                              <div v-if="value.additionalServices.length != 0">
                              <p>Add additional services to your reservation: </p>
                              <div v-for="(s,index) in value.additionalServices"
                                 :key="index">
                                 <div class="custom-control custom-checkbox mb-3">
                                    <input type="checkbox" class="custom-control-input" :id="value.entityId+index" required>
                                    <label class="custom-control-label" :for="value.entityId+index">{{s}}</label>
                                 </div>
                              </div>
                              </div>
                              <button class="btn btn-primary" v-on:click=MakeBoatReservation(value)>Make a reservation</button>
                           </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section>
   <div v-if="!makingReservation" class="card text-white bg-dark ml-100 mb-100" style="max-width: 20rem; position: fixed;bottom: 10px; right: 10px">
      <div class="card-header"></div>
      <div class="card-body">
      <div v-if="user === 'Client' && !makingReservation">
         <button type="button" class = "btn btn-primary btn-lg " data-toggle="modal" data-target="#res">Make a reservation
         </button>
         <br>
         <br>
         </div>
         <h5>Search or sort mansions</h5>
         <label>Chose search category:</label>
         <div class="dropdown mt-3">
            <button class="btn btn-secondary dropdown-toggle" type="button"  id="dropdown1" data-bs-toggle="dropdown">
            {{search}}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdown1">
               <li><button class="dropdown-item" v-on:click="SearchByName()">Name</button></li>
               <li><button class="dropdown-item" v-on:click="SearchByLocation()">Location</button></li>
               <li><button class="dropdown-item" v-on:click="SearchByGrade()">Grade</button></li>
            </ul>
         </div>
         <div class="input-group mt-2">
            <input type="text" class="form-control" aria-label="Search boats..." v-model="searchValue">
            <button class = "btn btn-primary btn-lg" v-on:click="Search()">Search</button>
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
import moment from 'moment';
import {
    devServer
} from "../../vue.config";
export default {
    name: 'mansions',
    data() {
        return {
            user: null,
            makingReservation: false,
            sortSearchResult: 'Price',
            sort: 'Name',

            search: 'Name',
            searchValue: '',
            reservationForm: {
                'startDate': '',
                'startTime': '',
                'numberOfGuests': '',
                'numberOfDays': '',
                'numberOfHours': 0,
                'location': '',
                'grade': '',
            },
            mansions: []

        }
    },
    mounted() {
        this.user = this.$store.state.userType;
        this.LoadMansions()

    },
    computed: {

    },
    methods: {
        picture(id) {
         return devServer.proxy +'/images/mansion'+id+'.jpg'; },

        LoadMansions() {

            axios
                .get(devServer.proxy + '/mansions', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    this.mansions = response.data;
                });

        },
        SortByName() {
            this.sort = 'Name';
        },
        SortByGrade() {
            this.sort = 'Grade';
        },
        SortByLocation() {
            this.sort = 'Location';
        },
        Sort() {

            if (this.sort == 'Name') {
                this.mansions.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
            } else if (this.sort == 'Grade') {
                this.mansions.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
            } else if (this.sort == 'Location') {
                this.mansions.sort((b, a) => (a.address.address > b.address.address) ? 1 : ((b.address.address > a.address.address) ? -1 : 0));
            }

        },
        SearchByName() {
            this.search = 'Name';
        },
        SearchByGrade() {
            this.search = 'Grade';
        },
        SearchByLocation() {
            this.search = 'Location';
        },
        Search(){

           var searchParams={
              'type': this.search,
              'value': this.searchValue 
           }
            console.log(searchParams)

            axios
                .post(devServer.proxy + '/mansions/search', searchParams, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {

                    console.log(response.data)
                    this.mansions = response.data
                });


           
        },
        Name(){
         var list = []
         
         for(var m of this.mansions){
         if(m.name.toLowerCase().includes(this.searchValue.toLowerCase())){ list.push(m); console.log(m)} 
               }console.log(list)
               this.mansions = list
               return list
        },
        SearchForReservations() {

           if(!this.ReservationFormValid){
              alert('You need the fill the form properly')
              return
           }
           else{
            this.makingReservation = true;
            axios
                .post(devServer.proxy + '/reservations/availableMansions', this.reservationForm, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {

                    console.log(response.data)
                    this.mansions = response.data
                });
           }
        },
         ReservationFormValid(){

           if(this.reservationForm.startDate == ''
           || this.reservationForm.startTime == ''
           || this.numberOfGuests == ''
            || this.numberOfDays == '') { return false }
           

          var date = moment(this.reservationForm.startDate).format("YYYY-MM-DD")
          var now = new Date()

          if (moment(now).isAfter(date)){
          alert('You cannot chose date from the past')
          return false;
          }
          return true
        },
        ShowMansion(value) {
            window.location.href = "/mansion/" + value.id.toString();
        },
        SortResultByAvgGrade() {
            this.sortSearchResult = 'Average grade'
        },
        SortResultByPrice() {
            this.sortSearchResult = 'Price'
        },
        SortResults(){

            if (this.sortSearchResult == 'Price') {
               alert('sorting by price')
                this.mansions.sort((b, a) => (a.totalPrice > b.totalPrice) ? 1 : ((b.totalPrice > a.totalPrice) ? -1 : 0));
            } else{
                this.mansions.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
            }

        },
        CancelMakingReservation() {
            this.makingReservation = false
            this.LoadMansions();
        },
        MakeMansionReservation(b) {

            var mansionReservation = {
                entityId: b.entityId,
                additionalServices: [],
                numberOfGuests: this.reservationForm.numberOfGuests,
                price: 500,
                startDate: this.reservationForm.startDate,
                startTime: this.reservationForm.startTime,
                numberOfDays: this.reservationForm.numberOfDays,
                numberOfHours: this.reservationForm.numberOfHours
            }


            console.log(mansionReservation)
            axios
                .post(devServer.proxy +'/reservations/createMansionReservation', mansionReservation, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    console.log(response.data)
                     alert(response.data)                   
                     this.LoadMansions()
                     this.makingReservation = false
                });


        }


    }

}
</script>

<style>


</style>