<template>

<button class = "btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#res">Make a reservation
</button>

    <div class="modal fade" id="res" tabindex="-1" role="dialog" aria-labelledby="res" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="res">Moking reservation</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">


            <div class="input-group input-group-lg">
            <label class="form-control">Select start date:</label><input class="form-control" placeholder="Select date" type="date" id="example"><br>
            </div>
            <div class="input-group input-group-lg">
            <label class="form-control">Number of days:</label><input class="form-control" type="number"><br>
            </div>
            <div class="input-group input-group-lg">
            <label class="form-control">Number of guests:</label><input class="form-control" type="number"><br>
            </div>          
            <br>
            <label>Optional:</label><br>
            <div class="input-group input-group">
            
            <label class="form-control">Location:</label><input class="form-control" type="text"><br>
            </div>
            <div class="input-group input-group">
            <label class="form-control">Grade:</label><input class="form-control" type="number"><br>
            </div>




        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Done</button>
        </div>
        </div>
    </div>
    </div>















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