<template>
<section class="p-5">
    <div class = "container">
    <div class="row text-center">

 <div class="col-md">    
    <div v-for="(value, index) in boats">
      <div v-if="index % 2 != 0">


            <div class="card mb-3">
                <img src="../assets/yacht.jpg" class="card-img-top img-fluid w-30">
                <div class="card-body">
                    <h5 class="card-title">{{value.name}}</h5>
                    <p class="card-text">{{value.promoDescription}}</p>
                    <p class="card-text"><small class="text-muted">{{value.address}}</small></p>
                </div>
            </div>
        </div>

      </div>
    </div>

<div class="col-md">
    <div v-for="(value, index) in boats">
      <div v-if="index % 2 == 0">

        
            <div class="card mb-3">
                <img src="../assets/yacht.jpg" class="card-img-top img-fluid w-30">
                <div class="card-body">
                    <h5 class="card-title">{{value.name}}</h5>
                    <p class="card-text">{{value.promoDescription}}</p>
                    <p class="card-text"><small class="text-muted">{{value.address}}</small></p>
                </div>
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
import Popper from 'popper.js'
export default{
    name: 'boats',
    data: function(){
        return{
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
            ]
            }
        },
    mounted(){
        axios
            .get('http://localhost:8080/boats')
            .then(response => {
                this.boats = response.data;       
        });   

    },
    methods:{


    }
}

</script>

<style>

</style>