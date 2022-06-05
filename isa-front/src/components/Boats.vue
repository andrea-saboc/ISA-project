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
                     <label class="form-control">Select start date:</label><input v-model="reservationForm.startDate" class="form-control" placeholder="Select date" type="date" name="dt" id="dt"><br>
                  </div>
                  <div class="input-group input-group-lg">
                     <label class="form-control">Select start time:</label><input v-model="reservationForm.startTime" class="form-control" placeholder="Select time" type="time" id="example"><br>
                  </div>
                  <div class="input-group input-group-lg">
                     <label class="form-control">Number of days:</label><input v-model="reservationForm.numberOfDays" class="form-control" type="number" min="0"><br>
                  </div>
                  <div class="input-group input-group-lg">
                     <label class="form-control">Number of hours:</label><input v-model="reservationForm.numberOfHours" class="form-control" type="number" min="2"><br>
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
         <div class="input-group mr-30">
            <div>
               <h4>Search results for:</h4>
               <br>
               <p2>Start date: {{reservationForm.startDate}}</p2>
               <br>
               <p1>Start time: {{reservationForm.startTime}}</p1>
               <br>
               <p1>Number of days: {{reservationForm.numberOfDays}}</p1>
               <br>
               <p1>Number of hours: {{reservationForm.numberOfHours}}</p1>
               <br>
               <p1>Number of guests: {{reservationForm.numberOfGuests}}</p1>
               <br>
               <p1>Avg grade: {{reservationForm.avgGrade}}</p1>
               <br>
               <p1>Location: {{reservationForm.location}}</p1>
               <br>
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
            <button class = "btn btn-primary">Sort</button><br><br>
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
               <div v-for="(value, index) in boats"
                  :key="index">
                  <div v-if="index % 2 == 0">
                     <div class="card mb-3">
                        <img v-if="!makingReservation" :src=picture(value.id) class="card-img-top img-fluid w-30">
                        <img v-if="makingReservation" :src=picture(value.entityId) class="card-img-top img-fluid w-30">
                        <div class="card-body">
                           <div>
                              <h5 class="card-title">{{value.name}}</h5>
                              <p class="card-text">{{value.promoDescription}}</p>
                              <p v-if="!makingReservation" class="card-text"><small class="text-muted">{{value.address.address}},{{value.address.city}},{{value.address.country}}</small></p>
                              <div v-if="user == 'Client'"> <button class="btn btn-sm" v-on:click=SubscribeClient(value)>Subscribe</button></div>
                              <div v-if="user == 'Client' && !makingReservation">
                                 <button class="btn btn-sm" v-on:click="ShowOffers(value.id)">Show reservation offers</button>
                                 <div v-if="showingOffers==value.id">
                                    <br>
                                    <h4>Reservation offers: </h4>
                                    <div v-if="reservations.length == 0">
                                       <br><br><br>
                                       <h2 class="card-title text-center">No offers at the moment</h2>
                                       <br><br><br>
                                    </div>
                                    <div v-for="m in reservations" :key="m">
                                       <div class="card mb-3">
                                          <div class="card-body">
                                             <h4 class="card-title">Discount: {{m.percentageOfDiscount}}% !</h4>
                                             <h4 class="card-title">Price with discount: {{m.priceWithDiscount}}</h4>
                                             <p class="card-text"> Before discount: {{m.priceWithoutDiscount }}</p>
                                             <p class="card-text"> Start date: {{format_date(new Date(m.startDate))}}</p>
                                             <p class="card-text"> End date: {{  format_date(new Date(m.endDate)) }}</p>
                                             <p class="card-text"> Number of guests: {{  m.numberOfGuests}}</p>
                                             <button class = "btn btn" v-on:click = "MakeReservation(m,value)">Make a reservation</button>              
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="bg-light p-3 text-left" v-if="makingReservation">
                              <p class="card-text">Price: {{value.totalPrice}}</p>
                              <p class="card-text">Average grade: {{value.avgGrade}}</p>
                              <p class="card-text">Cancellation policy: {{value.cancellationPolicy}}</p>
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
               <div v-for="(value, index) in boats"
                  :key="index">
                  <div v-if="index % 2 != 0">
                     <div class="card mb-3">
                        <img v-if="!makingReservation" :src=picture(value.id) class="card-img-top img-fluid w-30">
                        <img v-if="makingReservation" :src=picture(value.entityId) class="card-img-top img-fluid w-30">
                        <div class="card-body">
                           <div>
                              <h5 class="card-title">{{value.name}}</h5>
                              <p class="card-text">{{value.promoDescription}}</p>
                              <p v-if="!makingReservation" class="card-text"><small class="text-muted">{{value.address.address}},{{value.address.city}},{{value.address.country}}</small></p>
                              <div v-if="user == 'Client'"> <button class="btn btn-sm" v-on:click=SubscribeClient(value)>Subscribe</button></div>
                           </div>
                           <div v-if="user == 'Client' && !makingReservation">
                              <button class="btn btn-sm" v-on:click="ShowOffers(value.id)">Show reservation offers</button>
                              <div v-if="showingOffers==value.id">
                                 <br>
                                 <h4>Reservation offers: </h4>
                                 <div v-if="reservations.length == 0">
                                    <br><br><br>
                                    <h2 class="card-title text-center">No offers at the moment</h2>
                                    <br><br><br>
                                 </div>
                                 <div v-for="m in reservations" :key="m">
                                    <div class="card mb-3">
                                       <div class="card-body">
                                          <h4 class="card-title">Discount: {{m.percentageOfDiscount}}% !</h4>
                                          <h4 class="card-title">Price with discount: {{m.priceWithDiscount}}</h4>
                                          <p class="card-text"> Before discount: {{m.priceWithoutDiscount }}</p>
                                          <p class="card-text"> Start date: {{format_date(new Date(m.startDate))}}</p>
                                          <p class="card-text"> End date: {{  format_date(new Date(m.endDate)) }}</p>
                                          <p class="card-text"> Number of guests: {{  m.numberOfGuests}}</p>
                                          <button class = "btn btn" v-on:click = "MakeReservation(m,value)">Make a reservation</button>              
                                       </div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                           <div class="bg-light p-3 text-left" v-if="makingReservation">
                              <p class="card-text">Price: {{value.totalPrice}}</p>
                              <p class="card-text">Average grade: {{value.avgGrade}}</p>
                              <p class="card-text">Cancellation policy: {{value.cancellationPolicy}}</p>
                              <div v-if="value.additionalServices.length == 0" class="text-light">
                                 <br><br><br><br>
                                 <p class="text-center">No additional services</p>
                                 <br><br><br><br>
                              </div>
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
         <h5>Search or sort boats</h5>
         <label>Chose search category:</label>
         <div class="dropdown mt-3">
            <button class="btn btn-secondary dropdown-toggle" type="button"  id="dropdown1" data-bs-toggle="dropdown">
            {{search}}
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdown1">
               <li><button class="dropdown-item" v-on:click=SearchByName>Name</button></li>
               <li><button class="dropdown-item" v-on:click=SearchByLocation>Location</button></li>
               <li><button class="dropdown-item" v-on:click=SearchByGrade>Grade</button></li>
            </ul>
         </div>
         <div class="input-group mt-2">
            <input type="text" class="form-control" aria-label="Search boats..." v-model="searchValue">
            <button class = "btn btn-primary btn-lg" v-on:click=Search>Search</button>
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
axios.defaults.baseURL = process.env.VUE_APP_URL;
import moment from 'moment';
import {
    devServer
} from "../../vue.config";
export default {
    name: 'boats',
    data: function() {
        return {
            showingOffers: 'None',
            reservations: [],
            user: null,
            makingReservation: false,
            sortSearchResult: 'Prize',
            sort: 'Name',
            search: 'Name',
            searchValue: '',
            reservationForm: {
                'startDate': '',
                'startTime': '',
                'numberOfGuests': '',
                'numberOfDays': '',
                'numberOfHours': '',
                'location': '',
                'grade': '',

            },
            boats: []
        }
    },
    mounted() {
        this.user = this.$store.state.userType;
        this.LoadBoats()

    },
    methods: {
        picture(id) {
            return devServer.proxy + '/images/boat' + id + '.jpg';
        },

        ShowOffers(value) {

            if (this.showingOffers == value) {
                this.showingOffers = 'None'

            } else {

                axios
                    .get(devServer.proxy + '/boatDiscountReservations', {
                        params: {
                            id: value
                        },
                        headers: {
                            'Authorization': this.$store.getters.tokenString
                        }
                    })
                    .then(response => {
                        this.reservations = response.data

                        console.log('Discount reservations' + this.reservations)
                    })




                this.showingOffers = value
            }

        },


        MakeReservation(res, boat) {

            axios
                .get(devServer.proxy + '/makeDiscountBoatReservation', {
                    params: {
                        id: res.id
                    },
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                   alert(response.data)
                    this.ShowOffers(boat.id)
                    console.log(response.data)
                })

        },
        format_date(value) {
            if (value) {
                return moment(String(value)).format('DD.MM.YYYY HH:mm')
            }
        },

        checkSubscription(mansion) {

           if(this.user != 'Client') return true;
            else{
            axios
                .post(devServer.proxy + '/subscriptions/checkBoatSubscription', mansion, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                   alert(response.data)
                    return response.data
                });
            }
        },


        SubscribeClient(mansion) {

            axios
                .post(devServer.proxy + '/subscriptions/checkBoatSubscription', mansion, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                   if (response.data === true){
                      alert('You are already subscribed to this boat')
                   }else{
                     
                     axios
                        .post(devServer.proxy + '/subscriptions/newBoatSubscription', mansion, {
                           headers: {
                                 'Authorization': this.$store.getters.tokenString,
                                 'Content-Type': 'application/json'
                           }
                        })
                        .then(response => {
                           alert('submited')
                           console.log(response.data)
                           this.LoadMansions()
                        });
                   }
                });
            
        },


        LoadBoats() {
            axios
                .get('/boats', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    this.boats = response.data;
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
                this.boats.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
            } else if (this.sort == 'Grade') {
                this.boats.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
            } else if (this.sort == 'Location') {
                this.boats.sort((b, a) => (a.address > b.address) ? 1 : ((b.address > a.address) ? -1 : 0));
            }
        },
        SearchByGrade() {
            this.search = 'Grade'
        },
        SearchByLocation() {
            this.search = 'Location'
        },
        SearchByName() {
            this.search = 'Name'
        },
        Search() {

            var searchParams = {
                'type': this.search,
                'value': this.searchValue
            }
            console.log(searchParams)

            axios
                .post(devServer.proxy + '/boats/search', searchParams, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {

                    console.log(response.data)
                    this.boats = response.data
                });
        },
        SearchForReservations() {

            if (!this.ReservationFormValid()) {
                alert('You need to fill the form properly')
                return
            } else {
                this.makingReservation = true;
                axios
                    .post(devServer.proxy + '/reservations/availableBoats', this.reservationForm, {
                        headers: {
                            'Authorization': this.$store.getters.tokenString,
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => {
                        console.log(response.data)
                        this.boats = response.data
                    });
            }
        },
        ReservationFormValid() {

            if (this.reservationForm.startDate == '' ||
                this.reservationForm.startTime == '' ||
                this.numberOfGuests == '' ||
                this.numberOfDays == '' ||
                this.numberOfHours == '') {
                return false
            }


            var date = moment(this.reservationForm.startDate).format("YYYY-MM-DD")
            var now = new Date()

            if (moment(now).isAfter(date)) {
                alert('You cannot chose date from the past')
                return false;
            }
            return true
        },
        SortResultByAvgGrade() {
            this.sortSearchResult = 'Average grade'
        },
        SortResultByPrice() {
            this.sortSearchResult = 'Price'
        },
        CancelMakingReservation() {
            this.makingReservation = false;
            this.LoadBoats()
        },
        MakeBoatReservation(b) {

            var boatReservation = {
                entityId: b.entityId,
                additionalServices: [],
                numberOfGuests: this.reservationForm.numberOfGuests,
                price: 500,
                startDate: this.reservationForm.startDate,
                startTime: this.reservationForm.startTime,
                numberOfDays: this.reservationForm.numberOfDays,
                numberOfHours: this.reservationForm.numberOfHours
            }

            
            for(let index of b.additinalServicesId){
               let i=index-1
              var name = document.getElementById(b.entityId+i).checked;
              if(name == true) {boatReservation.additionalServices.push(index)}
            }

            console.log(boatReservation)
            axios
                .post(devServer.proxy + '/reservations/createBoatReservation', boatReservation, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    alert(response.data)
                    this.makingReservation = false
                    this.LoadBoats()
                });


        }

    }
}

</script>

<style>

</style>