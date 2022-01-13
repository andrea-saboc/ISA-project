<template>
  <div class="boat-reservations" v-if="loggedUser!=null && loggedUser.advertiserType!=null && loggedUser.advertiserType=='boat'">
    <h3>All reservations</h3>
    <nav class="navbar navbar-dark bg-dark" style=" height: 10%;">
      <!-- Navbar content -->
      <v-date-picker mode="dateTime" is24hr v-model="startDateTime">
        <template v-slot="{ inputValue, inputEvents }">
          <input
              class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
              :value="inputValue"
              v-on="inputEvents"
              placeholder="From date"
          />
        </template>
      </v-date-picker>
      <v-date-picker  mode="dateTime" is24hr v-model="endDateTime">
        <template v-slot="{ inputValue, inputEvents }">
          <input
              class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
              :value="inputValue"
              v-on="inputEvents"
              placeholder="To date"
          />
        </template>
      </v-date-picker>
      <select class="custom-select" style="width: 200px">
        <option selected value="all">All reservations</option>
        <option value="past">Past reservations</option>
        <option value="now">In progress</option>
        <option value="future">Future reservations</option>
      </select>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search boat" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </nav>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col"></th>
        <th scope="col">Boat</th>
        <th scope="col">From</th>
        <th scope="col">To</th>
        <th scope="col">Client</th>
        <th scope="col">Number of guests</th>
        <th scope="col">Total price</th>
        <th scope="col">Status</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(reservation, index) in boatReservations" :key="reservation.id" style="align-content: center"
          v-on:click=selectReservation(reservation) data-toggle="modal" data-target="#exampleModal">
        <th scope="row" style="align-content: center" >{{index+1}}</th>
        <td>{{reservation.boat.name}}</td>
        <td v-bind:id="reservation.id+'resid'" style="align-content: center">{{formatDate(reservation.startDate)}}</td>
        <td style="align-content: center">{{formatDate(reservation.endDate)}}</td>
        <td style="align-content: center">{{reservation.user.name}} {{reservation.user.surname}}</td>
        <td style="align-content: center">{{reservation.numberOfGuests}}</td>
        <td>{{reservation.totalPrice}}</td>
        <td v-if="reservation.cancelled == true">Cancelled</td>
        <td v-else>*</td>
      </tr>


      </tbody>
    </table>
    <!-- Modal -->
    <div class="modal fade"  v-if="selectedReservation!=null" id="exampleModal" role="dialog" tabindex="-1" aria-labelledby="reservationModalLabel" aria-hidden="true" >
      <div class="modal-dialog">
        <div class="modal-content" style="padding: 5%">
          <div class="modal-header">
            <h5 class="modal-title" id="reservationModalLabel">Client profile</h5>
          </div>
          <center>
            <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
              <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
              <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
            </svg>
            <h3 class="media-heading">{{selectedReservation.user.name}} {{selectedReservation.user.surname}}
              <br></h3>
            <p>
              {{selectedReservation.user.country}}
            </p>
            <table class="table table-borderless">
              <tbody>
              <tr>
                <td class="row" >Address</td>
                <td> {{selectedReservation.user.address}}, {{selectedReservation.user.city}}, {{selectedReservation.user.country}}</td>
              </tr>
              <tr>
                <td class="row"> Contact number</td>
                <td> {{selectedReservation.user.phoneNumber}}</td>
              </tr>
              <tr>
                <td class="row"> Email</td>
                <td> {{selectedReservation.user.email}}</td>
              </tr>
              <tr>
                <td class="row">
                  Subscribed
                </td>
                <td>
                  <div v-for="subsrciption in selectedUserSubscribed" :key="subsrciption.id" >
                    <span v-if="subsrciption.boat.boatOwner.id == loggedUser.id" class="badge bg-success" style="color: #eeeeee">{{subsrciption.boat.name}}</span>
                    <span v-else class="badge bg-dark" style="color: #eeeeee">{{subsrciption.boat.name}}</span>
                  </div>
                </td>
              </tr>
              </tbody>

            </table >




          </center>
          <hr>
          <h5 class="modal-title" id="reservationModalLabel">More about the reservation</h5>
          <table class = "table table-borderless">
            <tbody>
            <tr>
              <td class="row">Boat name</td>
              <td>{{this.selectedReservation.boat.name}}</td>
            </tr>
            <tr>
              <td class="row">Start time</td>
              <td>
                {{formatDate(this.selectedReservation.startDate)}}
              </td>
            </tr>
            <tr>
              <td class="row">End time</td>
              <td>{{formatDate(this.selectedReservation.endDate)}}</td>
            </tr>
            <tr>
              <td class="row">Cancelled</td>
              <td v-if="this.selectedReservation.cancelled">yes</td>
              <td v-else>no</td>
            </tr>
            <tr>
              <td class="row">Number of guests</td>
              <td>{{this.selectedReservation.numberOfGuests}}</td>
            </tr>
            </tbody>
          </table>
          <table v-if="this.selectedReservation.additionalServices.length!=0" class = "table table-borderless">
            <thead>
            <th>
              Additional services
            </th>
            </thead>
            <tbody>
            <div v-for="as in selectedReservation.additionalServices" :key="as.id">
              <tr>
                <td class="row">
                  Name
                </td>
                <td>
                  {{as.name}}
                </td>
              </tr>
            </div>
            </tbody>
          </table>
          <p style="font-size: 20px; horiz-align: right;">Final price: {{selectedReservation.totalPrice}} €</p>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
  <div class="container">


</div>
  </div>


</template>

<script>
import axios from "axios";
import {devServer} from "../../vue.config";
import moment from "moment";

export default {
  name: "BoatReservations",
  data: function (){
    return{
      loggedUser : null,
      boatReservations : [],
      startDateTime : '',
      endDateTime : '',
      filterBoatName : '',
      selectedReservation: null,
      selectedUserSubscribed: new Array(),
    }
  },
  mounted() {
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          this.loggedUser =response.data
          console.log("Ovaj user je ulogovan:", this.loggedUser)
          if(this.loggedUser.advertiserType == 'boat'){
            axios.get(devServer.proxy+"/getBoatOwnerReservations", {
              headers: {
                'Authorization' : this.$store.getters.tokenString
              }
            })
                .then(response1 => {
                  console.log("Rezervacija brodova vlasnika", response1.data)
                  this.boatReservations = response1.data;
                  this.selectedReservation = this.boatReservations[0]
                })}
        }
        )
        .catch(() => {
          console.log('Login user is unavailable')
          return;
        });

  },
  methods:{
    formatDate(date){
      var date1 = new Date(date)
      console.log("Date in form:",moment(String(date1)).format('MM.DD.YYYY. hh:mm'))
      //var element = document.getElementById(reservation.id+'resid');
      //element.innerHTML = date1.toString()
      return moment(String(date1)).format('DD.MM.YYYY. hh:mm')
    },
    selectReservation(reservation){
      console.log("In select reservation function")
      this.selectedReservation = reservation
      console.log("Another reservation is selected", this.selectedReservation)
      axios.get(devServer.proxy + "/subscriptions/boatsByBoatOwner", {
        headers:{
          'Authorization' : this.$store.getters.tokenString
        },
        params:{
          id : this.selectedReservation.user.id
        } })
        .then(respone => {
          console.log("Subscriptions got: ", respone.data)
          this.selectedUserSubscribed = respone.data
          console.log("Selected user is subscribed at boats: ", this.selectedUserSubscribed)
        })
        .catch(() =>{
          console.log("Subscribet boats are unavailable")
      })
    },
    reservationFilter(reservation){
        console.log(reservation.name)
    }
  }



}
</script>

<style scoped>
.boat-reservations{
  margin-bottom: 20%;
  margin-left: 15%;
  margin-right: 10%;
}

</style>