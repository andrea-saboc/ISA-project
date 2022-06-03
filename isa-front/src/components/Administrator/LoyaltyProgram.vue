<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
  <div class = "container pt-100">


<div class="w-50">
   <br>
   <br>
   <h3>Change loyalty program :</h3>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">Client reservation score</span>
      </div>
      <input type="number" min=0  class="form-control" v-model="client_reservation_score">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeClientScore">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">owner reservation score</span>
      </div>
      <input type="number" min=0  class="form-control" v-model="owner_reservation_score">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeOwnerScore">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">system percentage</span>
      </div>
      <input type="number"  min=0 class="form-control" v-model="system_percentage">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangePercentage">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">silver_points_min</span>
      </div>
      <input type="number" min=0 class="form-control" v-model="silver_points_min">
      <button class="btn btn-outline-secondary" type="button"  v-on:click="ChangeSilverMin">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="" >client discount silver</span>
      </div>
      <input type="number" min=0 class="form-control"  v-model="client_discount_silver">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeDiscountClientSilver">Change</button>
   </div>

   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">gold points min</span>
      </div>
      <input type="number"  min=0  class="form-control" v-model="gold_points_min">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeGoldMin">Change</button>
   </div>
   
   <div class="input-group">
      <div class="input-group-prepend">
         <span class="input-group-text" id="">client discount gold</span>
      </div>
      <input type="number" min=0 class="form-control" v-model="client_discount_gold">
      <button class="btn btn-outline-secondary" type="button" v-on:click="ChangeDiscountClientGold">Change</button>
   </div>
</div>
      </div>

</section>
</template>


<script>
import axios from "axios";
import {devServer} from "../../../vue.config";
export default{
  name: "loyaltyProgram",
    data: function(){
        return{
        
            loggedUser:null,
            client_reservation_score: Number,
            owner_reservation_score: Number,
            system_percentage: Number,
            silver_points_min: Number,
            client_discount_silver: Number,
            gold_points_min: Number,
            client_discount_gold: Number,
            loyaltyProgram:null,
            token: null
        }
    },
    mounted(){
        this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
        this.LoadUser();
    },
    methods:
    {
        LoadUser(){
         axios
         .get(devServer.proxy +'/userData',{
            headers: {
               'Authorization' :  'Bearer ' + this.token,
            }
         })
         .then(response => {
            this.loggedUser=response.data
             axios
         .get(devServer.proxy +'/loyaltyProgram',{
            headers: {
               'Authorization' :  'Bearer ' + this.token,
            }
         })
         .then(response1 => {
            this.loyaltyProgram=response1.data
            this.client_reservation_score=this.loyaltyProgram.client_reservation_score;
            this.owner_reservation_score=this.loyaltyProgram.owner_reservation_score
            this.system_percentage=this.loyaltyProgram.system_percentage
            this.silver_points_min=this.loyaltyProgram.silver_points_min
            this.client_discount_silver=this.loyaltyProgram.client_discount_silver
            this.gold_points_min=this.loyaltyProgram.gold_points_min
            this.client_discount_gold=this.loyaltyProgram.client_discount_gold

      });
    })
        }, ChangeClientScore(){
         this.loyaltyProgram.client_reservation_score= this.client_reservation_score;
         this.UpdateUser();
      },
      ChangeOwnerScore(){
        this.loyaltyProgram.system_percentage = this.system_percentage;
         this.UpdateUser();
      },
      ChangePercentage(){
         this.user.address = this.address;
         this.UpdateUser();
      },
      ChangeSilverMin(){
       this.loyaltyProgram.silver_points_min=this.silver_points_min
         this.UpdateUser();
      },
      ChangeDiscountClientSilver(){
         this.loyaltyProgram.client_discount_silver=this.client_discount_silver
         this.UpdateUser();
      },
      ChangeGoldMin(){
       this.loyaltyProgram.gold_points_min= this.gold_points_min;
         this.UpdateUser();
      },
      ChangeDiscountClientGold(){
       this.loyaltyProgram.client_discount_gold= this.client_discount_gold;
         this.UpdateUser();
      },
      UpdateUser(){
         console.log(this.user)
         axios
         .post(devServer.proxy + '/updateLoyaltyProgram',this.loyaltyProgram,{
         headers: {
         'Authorization' : this.$store.getters.tokenString,
         'Content-Type': 'application/json'
         }
         })
         .then(response => {
            console.log(response.data)
            alert('Loyalty program is updated.')
            this.LoadUser()
         });
      }
    }
}
</script>



<style>

</style>