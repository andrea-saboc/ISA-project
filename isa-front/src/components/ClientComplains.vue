<template>
<section class=" bg-dark p-5 text-sm-start p-10">

<section class = "container">
<div class="input-group justify-content-end mr-30">
    <ul class="nav">
        <li class="nav-item">
            <button class="btn btn-link" v-on:click=DisplayMansions>Mansions</button>
        </li>
        <li class="nav-item">
            <button class="btn btn-link" v-on:clik=DisplayBoats>Boats</button>
        </li>
        <li class="nav-item">
            <button class="btn btn-link" v-on:click=DisplayAdventures>Adventures</button>
        </li>
    </ul>
</div>

<div  v-if="display == 'mansions'">
    <div v-for="m in mansions">
    <div class="card mb-3">
        <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "ShowMOwnerComplainBox(m)">Complain about the owner</button> 
            <button class = "btn btn-danger" v-on:click = "ShowMansionComplainBox(m)">Complain about the mansion</button>            
        </div>
        <div v-if="givingMOwnerComplain == m" class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text">Complain content:</span>
                    </div>
                    <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                    <button class="btn btn-secondary" v-on:click = "SubmitMOwnerComplain(m)">Sumbit</button>
        </div>
                <div v-if="givingMansionComplain == m" class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text">Mansion complain content:</span>
                    </div>
                    <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                    <button class="btn btn-secondary" v-on:click = "SubmitMansionComplain(m)">Sumbit</button>
        </div>
    </div>
    </div>
</div>


<div v-if="display == 'boats'">
    <div v-for="m in boats">
    <div class="card mb-3">
        <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "ShowBOwnerComplainBox(m)">Complain about the owner</button> 
            <button class = "btn btn-danger" v-on:click = "ShowBoatComplainBox(m)">Complain about the boat</button>            
        </div>
        <div v-if="givingBOwnerComplain == m" class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text">Complain content:</span>
                    </div>
                    <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                    <button class="btn btn-secondary" v-on:click = "SubmitBOwnerComplain(m)">Sumbit</button>
        </div>
                <div v-if="givingBoatComplain == m" class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text">Boat complain content:</span>
                    </div>
                    <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                    <button class="btn btn-secondary" v-on:click = "SubmitBoatComplain(m)">Sumbit</button>
        </div>
        
    </div>
    </div>
</div>

<div v-if="display == 'adventures'">
    <div v-for="m in adventures">
    <div class="card mb-3">
        <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "ShowInstructorComplainBox(m)">Complain about the instructor</button> 
        </div>
        <div v-if="givingInstructorComplain == m" class="input-group">
                    <div class="input-group-prepend">
                    <span class="input-group-text">Complain content:</span>
                    </div>
                    <textarea class="form-control" aria-label="With textarea" v-model="feedbackContent"></textarea>
                    <button class="btn btn-secondary" v-on:click = "SubmitInstructorComplain(m)">Sumbit</button>
        
        </div>
    </div>
    </div>
</div>
<br>
<br>


</section>

</section>
<section class=" bg-light container">
<br>
<h2>Your complains so far:</h2>
</section>
</template>
<script>
import axios from 'axios'
import Mansions from './Mansions.vue'
import Boats from './Boats.vue'
import Adventures from './Adventures.vue'

export default {
data(){
    return{
        display: 'mansions',
        givingMOwnerComplain: 'None',
        givingMansionComplain: 'None',
        givingBOwnerComplain: 'None',
        givingBoatComplain: 'None',
        givingInstructorComplain: 'None',
        mansions: [
                       {
               'name': 'Ime trece, ime broda zamisli',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenab'
           },
           {
               'name': 'Cetvrto',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenaa'
           }
        ],
        boats: [],
        adventures:[
           {
               'name': 'Ime trece, ime broda zamisli',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenab'
           },
           {
               'name': 'Cetvrto',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenaa'
           }


        ]
    }

},
mounted(){

},
methods:{
    DisplayMansions(){  
        console.log('showing mansions')    
      this.display = 'mansions'
    },
    DisplayBoats(){ 
      console.log('showing boats')  
      this.display = 'boats'
    },
    DisplayAdventures(){
    console.log('showing adventures')
      this.display = 'adventures'
    },
    ShowMOwnerComplainBox(m){
      if (this.givingMOwnerComplain == m){ this.givingMOwnerComplain = 'None'; return}
      this.givingMOwnerComplain=m
    },
    ShowMansionComplainBox(m){
      if (this.givingMansionComplain == m){ this.givingMansionComplain = 'None'; return}
      this.givingMansionComplain=m
    },
    SubmitMOwnerComplain(m){

    },
    SubmitMansionComplain(m){

    },
    ShowBOwnerComplainBox(m){
      if (this.givingBOwnerComplain == m){ this.givingBOwnerComplain = 'None'; return}
      this.givingBOwnerComplain=m
    },
    ShowBoatComplainBox(m){
      if (this.givingBoatComplain == m){ this.givingBoatComplain = 'None'; return}
      this.givingBoatComplain=m
    },
    SubmitBOwnerComplain(m){

    },
    SubmitBoatComplain(m){

    },
    SubmitInstructorComplain(m){

    },    
    ShowInstructorComplainBox(m){
      if (this.givingInstructorComplain == m){ this.givingInstructorComplain = 'None'; return}
      this.givingInstructorComplain=m
    },


}


}

</script>