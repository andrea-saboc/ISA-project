<template>
   <section class="bg-dark text-light p-5 text-center text-sm-start">
      <div class = "container pt-100">
         <div class="btn-group">
            <button class="btn btn-primary" v-on:click=DisplayPastReservations>Reservation history</button>
            <button class="btn btn-primary" v-on:click=DisplayCurrentReservations>Active reservations</button>
         </div>
         <div v-if="display == 'current'">
            <div  v-for="res in currentReservations">
               <div class="card text-dark bg-light mt-3">
                  <div class="card-header h4">
                     {{}}
                  </div>
                  <div class="card-body">
                     <label>Start date: {{res.startDate}}.</label>
                     <label>Number of days: {{res.durationInDays}}.</label>
                     <label>Number of guests: {{res.numberOfClients}}.</label>
                     <div><button class="btn btn-danger" v-on:click="CancelReservation()">Cancel reservation</button></div>
                  </div>
               </div>
            </div>
         </div>
         <div v-if="display == 'past'">
            <div class="input-group justify-content-end mr-30">
               <ul class="nav">
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:click="DisplayMansions">Mansions</button>
                  </li>
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:clik="DisplayBoats">Boats</button>
                  </li>
                  <li class="nav-item">
                     <button class="btn btn-link" v-on:click="DisplayAdventures">Adventures</button>
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
               <button class = "btn btn-primary" v-on:click=sortReservations>Sort</button>
            </div>
            <div v-if="displayPastReservations == 'mansions'">
               <div v-for="res in pastMansionReservations">
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
                     <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                     <div class="input-group">
                      <div class="input-group-prepend">
                        <span class="input-group-text" id="" >Grade:</span>
                      </div>
                      <input type="number" class="form-control" v-model="feedbackGrade">
                    </div>
                  </div>
               </div>
            </div>
            <div v-if="displayPastReservations == 'boats'">
               <div v-for="res in pastBoatReservations">
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
            <div v-if="displayPastReservations == 'adventures'">
               <div v-for="res in pastAdventureReservations">
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
import axios from 'axios'
import Mansions from './Mansions.vue'
import Boats from './Boats.vue'
import Adventures from './Adventures.vue'

export default {
  name: 'clientReservations',
  components: { Mansions, Boats, Adventures
  },
  data: function(){
      return{
          display: 'current',
          displayPastReservations: 'mansions',
          givingFeedback: 'None',
          feedbackContent: '',
          sortReservations: 'Date',
          currentReservations: [],
        pastMansionReservations:[
           {
               'name': 'Ime prvo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Ime drugo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Ime trece, ime broda zamisli',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Cetvrto',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           }
        ],
        pastBoatReservations:[],
        pastAdventureReservations:[]
      }
  },
  mounted(){
      axios
         .get('http://localhost:8080/reservations')
         .then(response => {
            this.currentReservations = response.data
            console.log(response.data)
          });

  },
  methods:{
    DisplayMansions(){       
      this.displayPastReservations = 'mansions'
    },
    DisplayBoats(){  
      this.displayPastReservations = 'boats'
    },
    DisplayAdventures(){
      this.displayPastReservations = 'adventures'
    },
    DisplayPastReservations(){
        this.display = 'past'
    },
    DisplayCurrentReservations(){
      this.display = 'current'
    },
    ShowFeedbackBox(res){
      this.givingFeedback = res;
    },
    CancelGivingFeedback(res){
      this.givingFeedback = 'None';
    },
    SubmitFeedback(){

    },
    SortByDate(){
      this.sortReservations = 'Date'
    },
    SortByPrice(){
      this.sortReservations = 'Price'
    },
    SortByDuration(){
      this.sortReservations = 'Duration'
    },
    sortReservations(){

    }

  }
}

</script>