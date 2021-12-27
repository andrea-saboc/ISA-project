<template>
<section class="p-5">
    <div class = "container">
    <div class="row text-center">

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

    </div>
    </div>
</section>

<div class="card text-white bg-dark ml-100 mb-100" style="max-width: 20rem; position: fixed;bottom: 10px; right: 10px">
  <div class="card-header">Search or sort adventures</div>
  <div class="card-body">
  <label>Chose search category:</label>


  <div class="dropdown mt-3">
    <button class="btn btn-secondary dropdown-toggle" type="button"  id="dropdown1" data-bs-toggle="dropdown">
        Name
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdown1">
        <li><button class="dropdown-item">Name</button></li>
        <li><button class="dropdown-item">Location</button></li>
        <li><button class="dropdown-item">Grade</button></li>
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
import Popper from 'popper.js'
export default{
    name: 'adventures',
    data(){
        return{
            sort: 'Name',
        adventures : [
           {
               'name': 'Ime prvo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenay'
           },
           {
               'name': 'Ime drugo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjenaaa'
           },
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
        searchCategoryClicked: true
        }
    },
    methods:{

        SortByName(){
            this.sort = 'Name';
        },
        SortByGrade(){
            this.sort = 'Grade';
        },
        SortByLocation(){
            this.sort = 'Location';
        },
        Sort(){

            if (this.sort == 'Name') {
				this.adventures.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sort == 'Grade') {
				this.adventures.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
			} else if (this.sort == 'Location') {
				this.adventures.sort((b, a) => (a.address > b.address) ? 1 : ((b.address > a.address) ? -1 : 0));
			}
            
        }

    }
}
</script>

<style>


</style>