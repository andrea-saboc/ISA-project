<template>
   <section class="bg-dark text-light p-5 text-center text-sm-start">
      <div class = "container pt-100">
         <div class="btn-group">
            <button class="btn btn-primary" v-on:click=DisplayPastReservations>Reservation history</button>
            <button class="btn btn-primary" v-on:click=DisplayCurrentReservations>Active reservations</button>
         </div>
         <div v-if="display == 'current'">
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
               <div v-for="res in pastBoatReservations" :key="res">
                  <div class="text-dark bg-light mt-3">
                     <div class="card-header h4">
                        {{res.mansion.name}}
                     </div>
                     <div class="card-body">
                        <label>Start date: {{res.startDate}}</label><br>
                        <label>Duration in days: {{res.durationInDays}}</label><br>
                        <label>Number of guests : {{res.numberOfClients}}</label><br>
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
               <div v-for="res in pastBoatReservations" :key="res">
                  <div class="text-dark bg-light mt-3">
                     <div class="card-header h4">
                        {{res.boat.name}}
                     </div>
                     <div class="card-body">
                        <label>Start date: {{res.startDate}}</label><br>
                        <label>Duration in days: {{res.durationInDays}}</label><br>
                        <label>Number of guests : {{res.numberOfClients}}</label><br>
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
import axios from 'axios'

export default {
  name: 'clientReservations',
  data: function(){
      return{

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
         pastMansionReservations:[],
         pastBoatReservations:[],
         pastAdventureReservations:[]
      }
  },
  mounted(){
     this.LoadReservations()
     this.LoadMansionReservationHistory()
     this.LoadBoatReservationHistory()
  },
  methods:{

   LoadReservations(){
         axios
         .get('http://localhost:8080/reservations',{
            headers: {
               'Authorization' : this.$store.getters.tokenString
            }
         })
         .then(response => {
            this.currentReservations = response.data       
      });

   },
   LoadMansionReservationHistory(){
        axios
         .get('http://localhost:8080/reservations/mansions',{
            headers: {
               'Authorization' : this.$store.getters.tokenString
            }
         })
         .then(response => {
            this.pastMansionReservations = response.data
            console.log(response.data)
          });
   },
   LoadBoatReservationHistory(){
         axios
         .get('http://localhost:8080/reservations/boats',{
            headers: {
               'Authorization' : this.$store.getters.tokenString
            }
         })
         .then(response => {
            this.pastBoatReservations = response.data
            console.log(response.data)
          });

   },
    DisplayMansions(){  
      console.log('showing mansions')     
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

    CancelGivingFeedback(){
      this.givingFeedback = 'None';
    },
    SubmitFeedback(){
       console.log('submit')
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
    SortReservations(){
       console.log('sort res..')
    },    


    ShowOwnerFeedbackBox(m){
      if (this.givingOwnerFeedback == m){ this.givingOwnerFeedback = 'None'; return}
      this.givingOwnerFeedback=m
    },
    ShowFeedbackBox(m){
      if (this.givingFeedback == m){ this.givingFeedback = 'None'; return}
      this.givingFeedback=m
    },
    SubmitBOwnerFeedback(m){

       var feedback ={
          content:this.ownerFeedbackContent,
          grade: this.ownerFeedbackGrade,
          reservation: m.id

       }
      
      var url=''
      if(this.displayPastReservations == 'mansions')
      { url ='http://localhost:8080/feedbacks/addMansionOwnerFeedback'}
      else if (this.displayPastReservations == 'boats')
      { url ='http://localhost:8080/feedbacks/addBoatOwnerFeedback'}
      else{
         console.log('adventures..')
      }
       
         console.log(feedback)
          axios
         .post(url,feedback,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            console.log(response.data)
            this.ownerFeedbackGrade='';
            this.ownerFeedbackContent='';
            this.$router.go(0);
      });
    },
    SubmitBoatFeedback(m){
       var feedback ={
          content:this.feedbackContent,
          grade: this.feedbackGrade,
          reservation: m.id

       }
      var url=''
      if(this.displayPastReservations == 'mansions'){ url ='http://localhost:8080/feedbacks/addMansionFeedback'}
      if(this.displayPastReservations == 'boats'){ url ='http://localhost:8080/feedbacks/addBoatFeedback'}
      else{          console.log('adventures..')}
       
         console.log(feedback)
          axios
         .post(url,feedback,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            console.log
            alert(response.data)
            this.feedbackContent='';
            this.feedbackGrade='';
            this.$router.go(0);
      });


    },
    CancelReservation(res){

         var url=''
         if(res.reservationType == 'BOAT'){url ='http://localhost:8080/reservations/cancelBoatReservation' }
         if(res.reservationType == 'MANSION'){url ='http://localhost:8080/reservations/cancelMansionReservation' }
         axios
         .post(url,res.reservationId,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            this.LoadReservations()
            console.log(response.data)
      });

    }

  }
}

</script>