<template>
   <section class="bg-dark text-light p-5 text-center text-sm-start">

      <div class = "container pt-100">
         <div class="btn-group">
            <button class="btn btn-primary" v-on:click=DisplayPastReservations>Reservation history</button>
            <button class="btn btn-primary" v-on:click=DisplayCurrentReservations>Active reservations</button>
         </div>
         <div v-if="display == 'current'">
         <div v-if="currentReservations.length == 0" class="text-light"><br><br><br><br><h2 class="card-title text-center">No reservations</h2><br><br><br><br></div>

            <div  v-for="res in currentReservations"  :key="res">
               <div class="card text-dark bg-light mt-3">
                  <div class="card-header h4">
                     {{res.reservationType}},{{res.entityName}},{{res.entityAddress}}
                  </div>
                  <div class="card-body">
                     <label>Start date: {{res.startDate}}.</label><br>
                     <label>End date: {{res.endDate}}.</label><br>
                     <label>Number of guests: {{res.numberOfGuests}}.</label><br>
                     <label>Total price: {{res.totalPrice}}.</label>
                     <div v-if="res.allowedCancelation"><button class="btn btn-danger" v-on:click="CancelReservation(res)">Cancel reservation</button></div>
                  </div>
               </div>
            </div>
         </div>
         <div v-if="display == 'past'">

            <div class="input-group justify-content-end mr-30">
               <ul class="nav">
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:click=DisplayMansions>Mansions</button>
                  </li>
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:click=DisplayBoats>Boats</button>
                  </li>
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:click=DisplayAdventures>Adventures</button>
                  </li>
               </ul>
               <div class="dropdown ">
                  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown2" data-bs-toggle="dropdown" aria-expanded="true">
                  {{sortReservations}}
                  </button>
                  <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdown2">
                     <li><button class="dropdown-item" v-on:click=SortByDate>Date</button></li>
                     <li><button class="dropdown-item" v-on:click=SortByPrice>Price</button></li>
                     <li><button class="dropdown-item" v-on:click=SortByDuration>Duration</button></li>
                  </ul>
               </div>
               <button class = "btn btn-primary" v-on:click=SortReservations>Sort</button>
            </div>
            <div v-if="displayPastReservations == 'mansions'">
            <div v-if="pastMansionReservations.length == 0" class="text-light"><br><br><br><h2 class="card-title text-center">No mansion reservations</h2><br><br><br></div>

               <div v-for="res in pastMansionReservations" :key="res">

                  <div class="text-dark bg-light mt-3">
                     <div class="card-header h4">
                        {{res.mansion.name}},{{res.mansion.address.address}},{{res.mansion.address.city}}
                     </div>
                     <div class="card-body">
                        <label>Price : {{res.totalPrice}}</label><br>
                        <label>Start date: {{  format_date(new Date(res.startDate))}}</label><br>
                        <label>End date: {{  format_date(new Date(res.endDate))}}</label><br>
                        <label>Number of guests : {{res.numberOfGuests}}</label><br>
                        <blockquote class="blockquote mb-0">
                           <button v-if="res.mansionOwnerFeedback==null" class = "btn btn-light" v-on:click = "ShowOwnerFeedbackBox(res)">Give feedback about the owner</button> 
                           <button v-if="res.mansionFeedback==null" class = "btn btn-light" v-on:click = "ShowFeedbackBox(res)">Give mansion feedback</button>            
                        </blockquote>
                     </div>
                     <div v-if="givingOwnerFeedback == res" class="input-group">
                        <div class="input-group-prepend">
                           <span class="input-group-text">Complain content:</span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea" v-model="ownerFeedbackContent"></textarea>
                        <span class="input-group-text">Grade:</span>
                        <input type="number" class="form-control" v-model="ownerFeedbackGrade"/>
                        <button class="btn btn-secondary" v-on:click = "SubmitBOwnerFeedback(res)">Sumbit</button>
                     </div>
                     <div v-if="givingFeedback == res" class="input-group">
                        <div class="input-group-prepend">
                           <span class="input-group-text">Boat complain content:</span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                        <span class="input-group-text">Grade:</span>
                        <input type="number" class="form-control" v-model="feedbackGrade"/>
                        <button class="btn btn-secondary" v-on:click = "SubmitBoatFeedback(res)">Sumbit</button>
                     </div>
                  </div>
               </div>
            </div>
            <div v-if="displayPastReservations == 'boats'">
               <div v-if="pastBoatReservations.length == 0" class="text-light"><br><br><br><h2 class="card-title text-center">No boat reservations</h2><br><br><br></div>

               <div v-for="res in pastBoatReservations" :key="res">

                  <div class="text-dark bg-light mt-3">
                     <div class="card-header h4">
                        {{res.boat.name}},{{res.boat.address.address}},{{res.boat.address.city}}
                     </div>
                     <div class="card-body">
                        <label>Start date: {{  format_date(new Date(res.startDate))}}</label><br>
                        <label>End date: {{  format_date(new Date(res.endDate))}}</label><br>
                        <label>Number of guests : {{res.numberOfGuests}}</label><br>
                        <blockquote class="blockquote mb-0">
                           <button v-if="res.boatOwnerFeedback==null" class = "btn btn-light" v-on:click = "ShowOwnerFeedbackBox(res)">Give feedback about the owner</button> 
                           <button v-if="res.boatFeedback==null" class = "btn btn-light" v-on:click = "ShowFeedbackBox(res)">Give boat feedback</button>            
                        </blockquote>
                     </div>
                     <div v-if="givingOwnerFeedback == res" class="input-group">
                        <div class="input-group-prepend">
                           <span class="input-group-text">Complain content:</span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea" v-model="ownerFeedbackContent"></textarea>
                        <span class="input-group-text">Grade:</span>
                        <input type="number" class="form-control" v-model="ownerFeedbackGrade"/>
                        <button class="btn btn-secondary" v-on:click = "SubmitBOwnerFeedback(res)">Sumbit</button>
                     </div>
                     <div v-if="givingFeedback == res" class="input-group">
                        <div class="input-group-prepend">
                           <span class="input-group-text">Boat complain content:</span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                        <span class="input-group-text">Grade:</span>
                        <input type="number" class="form-control" v-model="feedbackGrade"/>
                        <button class="btn btn-secondary" v-on:click = "SubmitBoatFeedback(res)">Sumbit</button>
                     </div>
                  </div>
               </div>
            </div>
            <div v-if="displayPastReservations == 'adventures'">
               <div v-for="res in pastAdventureReservations" :key="res">
                  <div class="text-dark bg-light mt-3">
                     <div class="card-header h4">
                        Naziv vikendice
                     </div>
                     <div class="card-body">
                        <blockquote class="blockquote mb-0">
                           <p>A well-known quote, contained in a blockquote element.</p>
                           <button v-if="givingFeedback == 'None'" class = "btn btn-secondary" v-on:click = "ShowFeedbackBox(res)">Give feedback</button> 
                           <button v-if="givingFeedback == res" class = "btn btn-secondary" v-on:click="SubmitFeedback()">Submit</button>             
                           <button v-if="givingFeedback == res" class = "btn btn-secondary" v-on:click="CancelGivingFeedback()">Cancel</button> 
                        </blockquote>
                     </div>
                  </div>
                  <div v-if="givingFeedback == res" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Feedback content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea"></textarea>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </section>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL = process.env.VUE_APP_URL;

import moment from 'moment';
export default {
    name: 'clientReservations',
    data: function() {
        return {

            givingOwnerFeedback: 'None',
            givingFeedback: 'None',


            display: 'current',
            displayPastReservations: 'mansions',

            feedbackContent: '',
            feedbackGrade: '',

            ownerFeedbackContent: '',
            ownerFeedbackGrade: '',

            sortReservations: 'Date',

            currentReservations: [],
            pastMansionReservations: [],
            pastBoatReservations: [],
            pastAdventureReservations: []
        }
    },
    mounted() {
        this.LoadReservations()
        this.LoadMansionReservationHistory()
        this.LoadBoatReservationHistory()
    },
    methods: {

        LoadReservations() {
            axios
                .get('/reservations', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    this.currentReservations = response.data
                });

        },
        LoadMansionReservationHistory() {
            axios
                .get('/reservations/mansions', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    this.pastMansionReservations = response.data
                    console.log(response.data)
                });
        },
        LoadBoatReservationHistory() {
            axios
                .get('/reservations/boats', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    this.pastBoatReservations = response.data
                    console.log(response.data)
                });

        },
        DisplayMansions() {
            console.log('showing mansions')
            console.log(this.pastMansionReservations)
            this.displayPastReservations = 'mansions'
        },
        DisplayBoats() {
            console.log(this.pastBoatReservations)
            this.displayPastReservations = 'boats'
        },
        DisplayAdventures() {
            this.displayPastReservations = 'adventures'
        },
        DisplayPastReservations() {

            this.display = 'past'
        },
        DisplayCurrentReservations() {

            this.display = 'current'
        },

        CancelGivingFeedback() {
            this.givingFeedback = 'None';
        },
        SubmitFeedback() {
            console.log('submit')
        },
        SortByDate() {
            this.sortReservations = 'Date'
        },
        SortByPrice() {
            this.sortReservations = 'Price'
        },
        SortByDuration() {
            this.sortReservations = 'Duration'
        },
        SortReservations() {

            if(this.sortReservations == 'Date'){
                this.pastBoatReservations.sort((b, a) => (a.endDate > b.endDate) ? 1 : ((b.endDate > a.endDate) ? -1 : 0));
                this.pastMansionReservations.sort((b, a) => (a.endDate > b.endDate) ? 1 : ((b.endDate > a.endDate) ? -1 : 0));          
            }else if(this.sortReservations == 'Price'){
                this.pastBoatReservations.sort((b, a) => (a.totalPrice > b.totalPrice) ? 1 : ((b.totalPrice > a.totalPrice) ? -1 : 0));         
                this.pastMansionReservations.sort((b, a) => (a.totalPrice > b.totalPrice) ? 1 : ((b.totalPrice > a.totalPrice) ? -1 : 0));
            }else{
                this.pastBoatReservations.sort((b, a) => ((a.endDate - a.startDate) > (b.endDate - b.startDate)) ? 1 : (((b.endDate - b.startDate) > (a.endDate - a.startDate)) ? -1 : 0));         
                this.pastMansionReservations.sort((b, a) => ((a.endDate - a.startDate) > (b.endDate - b.startDate)) ? 1 : (((b.endDate - b.startDate) > (a.endDate - a.startDate)) ? -1 : 0));
           
            }
        },


        ShowOwnerFeedbackBox(m) {
            if (this.givingOwnerFeedback == m) {
                this.givingOwnerFeedback = 'None';
                return
            }
            this.givingOwnerFeedback = m
        },
        ShowFeedbackBox(m) {
            if (this.givingFeedback == m) {
                this.givingFeedback = 'None';
                return
            }
            this.givingFeedback = m
        },
        SubmitBOwnerFeedback(m) {

            var feedback = {
                content: this.ownerFeedbackContent,
                grade: this.ownerFeedbackGrade,
                reservation: m.id

            }

            var url = ''
            if (this.displayPastReservations == 'mansions') {
                url = '/feedbacks/addMansionOwnerFeedback'
            } else if (this.displayPastReservations == 'boats') {
                url = '/feedbacks/addBoatOwnerFeedback'
            } else {
                console.log('adventures..')
            }

            console.log(feedback)
            axios
                .post(url, feedback, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    console.log(response.data)
                    this.ownerFeedbackGrade = '';
                    this.ownerFeedbackContent = '';
                    this.$router.go(0);
                });
        },
        SubmitBoatFeedback(m) {
            var feedback = {
                content: this.feedbackContent,
                grade: this.feedbackGrade,
                reservation: m.id

            }
            var url = ''
            if (this.displayPastReservations == 'mansions') {
                url = '/feedbacks/addMansionFeedback'
            }
            if (this.displayPastReservations == 'boats') {
                url = '/feedbacks/addBoatFeedback'
            } else {
                console.log('adventures..')
            }

            console.log(feedback)
            axios
                .post( url, feedback, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    console.log
                    alert(response.data)
                    this.feedbackContent = '';
                    this.feedbackGrade = '';
                    this.$router.go(0);
                });


        },
        CancelReservation(res) {

            var url = ''
            if (res.reservationType == 'BOAT') {
                url = '/reservations/cancelBoatReservation'
            }
            else if (res.reservationType == 'MANSION') {
                url = '/reservations/cancelMansionReservation'
            }
            else if (res.reservationType == 'MANSION DISCOUNT'){
                url = '/cancelDiscountMansionReservation'
            }
            else{
                url = '/cancelDiscountBoatReservation'
            }

            axios
                .post( url, res.reservationId, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    this.LoadReservations()
                    console.log(response.data)
                });

        },
        format_date(value){
         if (value) {
           return moment(String(value)).format('DD.MM.YYYY HH:mm')
          }
        }
      }

    
}
</script>