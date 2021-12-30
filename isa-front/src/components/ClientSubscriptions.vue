<template>
<section class = "container">
<br>
<br>
<hr>
<h2>Mansions:</h2>
 <div v-for="m in mansions">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "CancelSubsciption(m)">Unsubscribe</button> 
        </div>
    </div>
</div>
<hr>
<br>
<hr>
<h2>Boats:</h2>
 <div v-for="m in boats">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "CancelSubsciption(m)">Unsubscribe</button> 
        </div>
    </div>
</div>
<hr>
<br>
<hr>
<h2>Instructors:</h2>
 <div v-for="m in instructors">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">{{m.name}}</h5>
            <p class="card-text">{{m.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
            <button class = "btn btn-danger" v-on:click = "CancelSubsciption(m)">Unsubscribe</button> 
        </div>
    </div>
</div>
<hr>
</section>
</template>
<script>
import axios from 'axios'
import Popper from 'popper.js'
export default{
    name: 'boats',
    data: function(){
        return{
            mansions: [],
            boats : [
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
           }
            ],
            instructors:[]
            }
        },
    mounted(){

    },
    methods:{
       CancelSubsciption(s){

       }

    }
}

</script>