<template>
<section class="p-5">
    <div class = "container">
    <div class="row text-center">

 <div class="col-md">    
    <div v-for="(value, index) in adventures">
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
    <div v-for="(value, index) in adventures">
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

<div class="card text-white bg-dark ml-100 mb-100" style="max-width: 20rem; position: fixed;bottom: 10px; right: 10px">
  <div class="card-header">Search or sort adventures</div>
  <div class="card-body">
  <label>Chose search category:</label>


  <div class="dropdown mt-3">
    <button class="btn btn-secondary dropdown-toggle" type="button"  id="dropdown1" data-bs-toggle="dropdown">
        Adventure name
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdown1">
        <li><a class="dropdown-item">Action</a></li>
        <li><button class="dropdown-item">Another action</button></li>
        <li><button class="dropdown-item">Something else here</button></li>
    </ul>
  </div>

  

 <div class="input-group mt-2">
    <input type="text" class="form-control" aria-label="Search adventures...">
      <button class = "btn btn-primary btn-lg">Search</button>
    </div>
 </div>

 <div class="card-body">
  <label>Chose sort category:</label>
  
<br>
<div class="input-group">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdown2" data-bs-toggle="dropdown" aria-expanded="true">
            Adventure name
        </button>
        <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdown2">
            <li><a class="dropdown-item active" href="#">Action</a></li>
            <li><a class="dropdown-item" href="#">Another action</a></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Separated link</a></li>
        </ul>
    </div>
    <button class = "btn btn-primary">Sort</button>
 </div>
 </div>
</div>

</template>

<script>
import axios from 'axios'
import Popper from 'popper.js'
export default{
    name: 'adventures',
    data(){
        return{
        adventures : [
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
        searchCategoryClicked: true
        }
    },
    methods:{

    }
}
</script>

<style>


</style>