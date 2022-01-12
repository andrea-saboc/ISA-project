<template>

<section class="container">
    <div v-for="m in reservations" :key="m">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">Price: {{m.priceWithDiscount}}</h5>

                <p class="card-text"> Start date: {{m.startDate}}</p>
                <p class="card-text"> End date: {{m.endDate}}</p>
                <button class = "btn btn" v-on:click = "MakeReservation(m)">Unsubscribe</button> 
            </div>
        </div>
    </div>

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
            alert("boat id: ", boatId.toString())
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
            alert(this.boat.id)
            })

        },
        LoadReservations(){

            alert(this.boat.id)
            axios
                .post(devServer.proxy + '/boatDiscountReservations', this.boat.id, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {                   
                    alert(response.data)
                    this.reservations = response.data
                });


        },
        MakeReservation(){


        }

    }
}
</script>