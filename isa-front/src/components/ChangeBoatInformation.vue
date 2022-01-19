<template>
<div class="boat-information-change">
  <div v-if="user!=null && user=='BoatOwner'">
    <div v-if="boats!=null && boats.length>0">
      <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" v-model="selectedBoat" v-on:change="changeBoat">
        <option v-for="boat in boats" v-bind:value="boat" :key="boat.id">
          {{boat.name}}
        </option>
      </select>
      {{selectedBoat.name}}
      {{boatName}}
      <button v-on:click="deleteSelectedBoat()">Delete</button>
    </div>
    <div v-else>
      There is no boat yet!
    </div>


  </div>
</div>
</template>

<script>
import axios from "axios";
import {devServer} from "../../vue.config";
export default {
  name: "ChangeBoatInformation",
  data: function (){
    return{
      selectedBoat: null,
      boats : new Array(),
      possibleToChange: true,
      user: null,

      boatName: ''
    }
  },
  mounted(){
    this.user = this.$store.state.userType
    if (this.user!=null && this.user =='BoatOwner'){
      axios.get(devServer.proxy+"/ownersBoats", {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
          .then(response1 => {
            console.log("brodovi vlasnika", response1.data)
            this.boats = response1.data;
            if(this.boats.length>0){
              this.selectedBoat = this.boats[0];
            }

          })
    }
  },
  methods:{
    changeBoat(){
      this.boatName=this.selectedBoat.name
    },
    deleteSelectedBoat(){
      axios.post(devServer.proxy + "/deleteBoat",{
        "boatId" : this.selectedBoat.id
      }, {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
      .then(response => {
        alert("Boat successfully deleted!", response.data)
        axios.get(devServer.proxy + "/ownersBoats", {
          headers: {
            'Authorization': this.$store.getters.tokenString
          }
        })
            .then(response1 => {
              console.log("brodovi vlasnika", response1.data)
              this.boats = response1.data;
              if (this.boats.length > 0) {
                this.selectedBoat = this.boats[0];
              }

            })
      })
      .catch(()=>{
        alert("The boat is not possible to be deleted")
      })
    }
  }

}
</script>

<style scoped>
.boat-information-change{
  margin: 10%;
  margin-left: 20%;
}

</style>