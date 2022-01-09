<template>
<section class = "container">
<br>
<br>
<hr>
<h2>Mansions:</h2>
 <div v-for="m in mansions" :key="m">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">{{m.mansion.name}}</h5>
            <p class="card-text">{{m.mansion.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.mansion.address.address}},{{m.mansion.address.city}},{{m.boat.address.country}}</small></p>
            <button class = "btn btn-danger" v-on:click = "CancelMansionSubsciption(m)">Unsubscribe</button> 
        </div>
    </div>
</div>
<hr>
<br>
<hr>
<h2>Boats:</h2>
 <div v-for="m in boats" :key="m">
    <div class="card mb-3">
        <div class="card-body">
            <h5 class="card-title">{{m.boat.name}}</h5>
            <p class="card-text">{{m.boat.promoDescription}}</p>
            <p class="card-text"><small class="text-muted">{{m.boat.address.address}},{{m.boat.address.city}},{{m.boat.address.country}}</small></p>
            <button class = "btn btn-danger" v-on:click = "CancelBoatSubsciption(m)">Unsubscribe</button> 
        </div>
    </div>
</div>
<hr>
<br>
<hr>
<h2>Instructors:</h2>
 <div v-for="m in instructors" :key="m">
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
import {devServer} from "../../vue.config";
//import Popper from 'popper.js'
export default{
    name: 'boats',
    data: function(){
        return{
            mansions: [],
            boats : [],
            instructors:[]
            }
        },
    mounted(){

        this.LoadMansions()
        this.LoadBoats()

    },
    methods:{

        LoadMansions(){
            alert('Ucitavanje broda')
            axios
            .get(devServer.proxy + '/subscriptions/mansions', {
                headers: {
                    'Authorization': this.$store.getters.tokenString
                }
            })
            .then(response => {
                this.mansions = response.data;
            });

        },
        LoadBoats(){
            axios
                .get(devServer.proxy + '/subscriptions/boats', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    console.log(response.data)
                    this.boats = response.data;
                });
        },

       CancelBoatSubsciption(s){
          axios
         .post(devServer.proxy + '/subscriptions/cancelBoatSub',s,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            console.log(response.data)
            this.LoadBoats()
      });
       },
       CancelMansionSubsciption(s){
         axios
         .post(devServer.proxy + '/subscriptions/cancelMansionSub',s,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
      })
         .then(response => {
            console.log(response.data)
            this.LoadMansions()
      });
       }

    }
}

</script>