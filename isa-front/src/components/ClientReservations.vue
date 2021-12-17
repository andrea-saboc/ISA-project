<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
  <div class = "container pt-100">

<div class="btn-group">
  <button href="#" class="btn btn-primary" v-on:click=DisplayPastReservations>Reservation history</button>
  <button href="#" class="btn btn-primary" v-on:click=DisplayCurrentReservations>Active reservations</button>
</div>

<div v-if="display == 'current'">
  <div v-for="res in currentReservations">
      <div class="card text-dark bg-light mt-3">
        <div class="card-header">
          Quote
        </div>
        <div class="card-body">
          <blockquote class="blockquote mb-0">
            <p>A well-known quote, contained in a blockquote element.</p>
            <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
          </blockquote>
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
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown2" data-bs-toggle="dropdown2">
            Date
        </button>
        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdown2">
            <li><a class="dropdown-item active" href="#">Date</a></li>
            <li><a class="dropdown-item" href="#">Price</a></li>
            <li><a class="dropdown-item" href="#">Duration</a></li>
        </ul>
    </div>
    <button class = "btn btn-primary">Sort</button>
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
              <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
            </blockquote>
          </div>
        </div>
    </div>
  </div>
    <div v-if="displayPastReservations == 'boats'">
    <div v-for="res in pastBoatReservations">
        <div class="card text-white bg-secondary mt-3">
          <div class="card-header h4">
            Naziv vikendice
          </div>
          <div class="card-body">
            <blockquote class="blockquote mb-0">
              <p>A well-known quote, contained in a blockquote element.</p>
              <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
            </blockquote>
          </div>
        </div>
    </div>
  </div>
    <div v-if="displayPastReservations == 'adventures'">
    <div v-for="res in pastAdventureReservations">
        <div class="card text-white bg-secondary mt-3">
          <div class="card-header h4">
            Naziv vikendice
          </div>
          <div class="card-body">
            <blockquote class="blockquote mb-0">
              <p>A well-known quote, contained in a blockquote element.</p>
              <footer class="blockquote-footer">Someone famous in <cite title="Source Title">Source Title</cite></footer>
            </blockquote>
          </div>
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
          currentReservations:[
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
    }

  }
}

</script>