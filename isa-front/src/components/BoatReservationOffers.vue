<template>
<br>
<br>
<section class="container">
<br>
<br>
<br>
<h1 class="card-title">Discount offers for {{boat.name}}</h1>

<div v-if="reservations.length == 0"><br><br><br><h2 class="card-title text-center">No offers at the moment</h2><br><br><br></div>
    <div v-for="m in reservations" :key="m">
        <div class="card mb-3">
            <div class="card-body">
                <h4 class="card-title">Discount: {{m.percentageOfDiscount}}% !</h4>
                <h4 class="card-title">Price with discount: {{m.priceWithDiscount}}</h4>

                <p class="card-text"> Before discount: {{m.priceWithoutDiscount }}</p>
                <p class="card-text"> Start date: {{format_date(new Date(m.startDate))}}</p>
                <p class="card-text"> End date: {{  format_date(new Date(m.endDate)) }}</p>
                <p class="card-text"> Number of guests: {{  m.numberOfGuests}}</p>
                <button class = "btn btn" v-on:click = "MakeReservation(m)">Make a reservation</button>              
            </div>
        </div>
    </div>

</section>
</template>

<script>
import axios from 'axios'
import {devServer} from "../../vue.config";
import moment from 'moment';
//import Popper from 'popper.js'
export default{
    name: 'boats',
    data: function(){
        return{
            boat: '',
            reservations: []
            }
        },
    mounted(){

        this.LoadBoat()
        this.LoadReservations()
    },
    methods:{

        LoadBoat(){
            var path = window.location.href;
            var boatId = path.split('/boatReservationOffers/')[1].replaceAll('%20', ' ');

            axios
            .get(devServer.proxy + '/boat', {
            params:
                {
                    id : boatId
                },
            headers: {
                'Authorization' : this.$store.getters.tokenString
            }
            })
            .then(response =>{
            this.boat = response.data
            })

        },
        LoadReservations(){

            var path = window.location.href;
            var boatId = path.split('/boatReservationOffers/')[1].replaceAll('%20', ' ');

            axios
            .get(devServer.proxy + '/boatDiscountReservations', {
            params:
                {
                    id : boatId
                },
            headers: {
                'Authorization' : this.$store.getters.tokenString
            }
            })
            .then(response =>{
            this.reservations = response.data

            console.log('Discount reservations'+this.reservations)
            })


        },
        MakeReservation(res){

            axios
            .get(devServer.proxy + '/makeDiscountBoatReservation', {
            params:
                {
                    id : res.id
                },
            headers: {
                'Authorization' : this.$store.getters.tokenString
            }
            })
            .then(response =>{
            this.LoadReservations()
            console.log(response.data)
            })

        },
        format_date(value){
         if (value) {
           return moment(String(value)).format('DD.MM.YYYY HH:mm')
          }
        }

    }
}
</script>