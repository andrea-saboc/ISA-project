<template>
  <div class="boat-reservations" v-if="loggedUser!=null && loggedUser.advertiserType!=null && loggedUser.advertiserType=='boat'">
    <h3 v-if="typeOfReservation==='regular'">All regular reservations</h3>
    <h3 v-if="typeOfReservation==='discount'">All discount reservations</h3>
    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin: 1%">
      <div class="btn-group me-2" role="group" aria-label="First group">
        <button type="button" class="btn btn-primary" v-on:click="ChangeTypeOfReservationToShow('discount')">Discount</button>
        <button type="button" class="btn btn-primary" v-on:click="ChangeTypeOfReservationToShow('regular')">Regular</button>
      </div>
    </div>
    <nav class="navbar navbar-dark bg-dark" style=" height: 10%;">
      <!-- Navbar content -->
      <v-date-picker mode="date" is24hr v-model="startDateTime">
        <template v-slot="{ inputValue, inputEvents }">
          <input
              class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
              :value="inputValue"
              v-on="inputEvents"
              placeholder="From date"
          />
        </template>
      </v-date-picker>
      <v-date-picker  mode="date" is24hr v-model="endDateTime">
        <template v-slot="{ inputValue, inputEvents }">
          <input
              class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
              :value="inputValue"
              v-on="inputEvents"
              placeholder="To date"
          />
        </template>
      </v-date-picker>
      <select class="custom-select" style="width: 200px" v-model="filterReservationStatus">
        <option selected value="all">All reservations</option>
        <option value="past">Past reservations</option>
        <option value="now">In progress</option>
        <option value="future">Future reservations</option>
      </select>
      <input class="form-control me-2" type="search" placeholder="Search boat" v-model="filterBoatName" aria-label="Search" style="width: 200px">

        <button class="btn btn-outline-primary" v-on:click="reservationFilter()">Search</button>
    </nav>
    <div v-if="typeOfReservation=='regular'">
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
          <th scope="col" v-if="filterReservationStatus=='past' && past==true">Create report</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(reservation, index) in filteredReservations" :key="reservation.id" style="align-content: center"
            v-on:click="selectReservation(reservation, 'REGULAR_BOAT')" data-toggle="modal" data-target="#exampleModal">
          <th scope="row" style="align-content: center" >{{index+1}}</th>
          <td>{{reservation.boat.name}}</td>
          <td v-bind:id="reservation.id+'resid'" style="align-content: center">{{formatDate(reservation.startDate)}}</td>
          <td style="align-content: center">{{formatDate(reservation.endDate)}}</td>
          <td style="align-content: center" v-if="reservation.user!=null">{{reservation.user.name}} {{reservation.user.surname}}</td>
          <td style="align-content: center" v-else>Not reserved</td>
          <td style="align-content: center">{{reservation.numberOfGuests}}</td>
          <td>{{reservation.totalPrice}} €</td>
          <td v-if="reservation.cancelled == true">Cancelled</td>
          <td v-else>Not cancelled</td>
          <td v-if="filterReservationStatus=='past' && reservation.cancelled == false && past==true"><button v-on:click="createReport(reservation)">Create report</button></td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-if="typeOfReservation==='discount'">
      <table  class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col"></th>
          <th scope="col">Boat</th>
          <th scope="col">From</th>
          <th scope="col">To</th>
          <th scope="col">Client</th>
          <th scope="col">Number of guests</th>
          <th scope="col">Pricing</th>
          <th scope="col">Status</th>
          <th scope="col">Available until</th>
          <th scope="col" v-if="filterReservationStatus=='past' && past==true">Create report</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(reservation, index) in filteredDiscountReservations" :key="reservation.id" style="align-content: center"
            v-on:click="selectReservation(reservation, 'DISCOUNT_BOAT')" data-toggle="modal" data-target="#exampleModal">
          <th scope="row" style="align-content: center" >{{index+1}}</th>
          <td>{{reservation.boat.name}}</td>
          <td v-bind:id="reservation.id+'resid'" style="align-content: center">{{formatDate(reservation.startDate)}}</td>
          <td style="align-content: center">{{formatDate(reservation.endDate)}}</td>
          <td style="align-content: center" v-if="reservation.user!=null">{{reservation.user.name}} {{reservation.user.surname}}</td>
          <td style="align-content: center" v-else>Not reserved</td>
          <td style="align-content: center">{{reservation.numberOfGuests}}</td>
          <td><span style="text-decoration-line: line-through;">{{reservation.priceWithDiscount}} €</span> {{reservation.priceWithoutDiscount}} € • <span style="font-weight: bolder">{{reservation.percentageOfDiscount}}% off</span></td>
          <td style="align-content: center">{{reservation.status}}</td>
          <td style="align-content: center">{{formatDate(reservation.validUntil)}}</td>
          <td v-if="filterReservationStatus=='past' && reservation.status=='RESERVED' && past==true">
            <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            Create report
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal create report -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Create report for {{selectedReservation.user.name}} {{selectedReservation.user.surname}}</h5>
          </div>
          <div class="modal-body">
            <div class="form-floating">
              <label for="floatingTextarea2">Comments</label>
              <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" v-model="reportText">
              </textarea>
            </div>
            <div class="horizontal">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1" v-model="clientShowedUp">
                <label class="form-check-label" for="flexCheckDefault1">
                  Client has shown up
                </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault2" v-model="requestSanctions">
                <label class="form-check-label" for="flexCheckDefault2">
                  Request sanctions
                </label>
              </div>
            </div>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" v-on:click="createReport">Create report</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal user information-->
    <div class="modal fade"  v-if="selectedReservation!=null" id="exampleModal" role="dialog" tabindex="-1" aria-labelledby="reservationModalLabel" aria-hidden="true" >
      <div class="modal-dialog">
        <div class="modal-content" style="padding: 5%" v-if="selectedReservation!=null">
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
          <h5 class="modal-title" id="reservationModalLabel1">More about the reservation</h5>
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
              <td v-if="this.selectedReservation.status == 'CANCELLED'">yes</td>
              <td v-else>no</td>
            </tr>
            <tr>
              <td class="row">Number of guests</td>
              <td>{{this.selectedReservation.numberOfGuests}}</td>
            </tr>
            </tbody>
          </table>
          <table v-if="this.selectedReservation.additionalServices!=null && this.selectedReservation.additionalServices.length!=0" class = "table table-borderless">
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
        <div v-else>
          This discount reservation is not reserved yet!
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
      typeOfReservation: 'regular',
      loggedUser : null,
      boatReservations : [],
      startDateTime : '',
      endDateTime : '',
      filterBoatName : '',
      filterReservationStatus: 'all',
      past: false,
      filteredReservations: new Array(),
      selectedReservation: null,
      selectedReservationType: 'REGULAR_BOAT',
      selectedUserSubscribed: new Array(),
      discountReservations : new Array(),
      filteredDiscountReservations : new Array(),
      reportText:'',
      clientShowedUp: true,
      requestSanctions: false

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
                  this.boatReservations = response1.data.boatReservations;
                  this.filteredReservations = this.boatReservations
                  this.selectedReservation = this.boatReservations[0]
                  this.discountReservations = response1.data.boatDiscountReservations;
                  this.filteredDiscountReservations = this.discountReservations
                  console.log("Filtered discount after mounted ", this.filteredDiscountReservations)
                  console.log("Filtered regular after mounted ", this.filteredReservations)
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
      //console.log("Date in form:",moment(String(date1)).format('MM.DD.YYYY. hh:mm'))
      return moment(String(date1)).format('DD.MM.YYYY. hh:mm')
    },
    selectReservation(reservation, type){
      this.selectedReservationType = type
      console.log("In select reservation function")
      this.selectedReservation = reservation
      console.log("Another reservation is selected", this.selectedReservation)
      if(reservation.user != null){
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
              console.log("Subscriber boats are unavailable")
            })
      } else{
        this.selectedReservation = null
      }
    },
    reservationFilter(){
      if(this.startDateTime == null) this.startDateTime = ''
      if(this.endDateTime == null) this.endDateTime = ''
      console.log("filter reservation params:", this.filterBoatName, "start", this.startDateTime,"end", this.endDateTime, "status", this.filterReservationStatus)

      if(this.typeOfReservation === 'regular'){
        this.filteredReservations = new Array()
        for (var reservation of this.boatReservations){
          console.log("prvi uslov:", reservation.boat.name.includes(this.filterBoatName),
          "drugi uslov", (new Date(reservation.startDate).getTime() >=(new Date(this.startDateTime).getTime())) || this.startDateTime=='',
          "treci uslov",(new Date(reservation.endDate).getTime() <= (new Date(this.endDateTime).getTime()))  || this.endDateTime=='',
        "cetvrti uslov", (new Date(reservation.endDate).getTime() <= (new Date(this.endDateTime).getTime()))  || this.endDateTime=='')
          if(reservation.boat.name.includes(this.filterBoatName)
              && ((new Date(reservation.startDate).getTime() >=(new Date(this.startDateTime).getTime())) || this.startDateTime=='')
              && ((new Date(reservation.endDate).getTime() <= (new Date(this.endDateTime).getTime()))  || this.endDateTime=='')
              && this.reservationFilterStatus(reservation)){
            this.filteredReservations.push(reservation)
          }
        }
        console.log("Filered reservations", this.filteredReservations, "past is ", this.past)
      } else{
        this.filteredDiscountReservations = new Array()
        for (var dreservation of this.discountReservations){
          if(dreservation.boat.name.includes(this.filterBoatName)
              && ((new Date(dreservation.startDate).getTime()>= (new Date(this.startDateTime).getTime() ))|| this.startDateTime=='')
              && ((new Date(dreservation.endDate).getTime() <= (new Date(this.endDateTime).getTime())) || this.endDateTime=='')
              && this.reservationFilterStatus(dreservation)){
            this.filteredDiscountReservations.push(dreservation)
          }
        }
        console.log("Filered reservations", this.filteredDiscountReservations, "past is ", this.past)
      }console.log("past is ", this.past)

      this.endDateTime==''
      this.startDateTime==''
      return this.filteredReservations
    },
    reservationFilterStatus(reservation){
      this.past= false;
      console.log("past is ", this.past)
      if(this.filterReservationStatus == 'all'){
        return true;
      }
      if( this.filterReservationStatus == 'now'){
        if(new Date(reservation.endDate).getTime()>= new Date().getTime()
        && new Date(reservation.startDate).getTime()<= new Date().getTime()) {
          return true;
        }else{
          return false
        }
      }
      if (this.filterReservationStatus == 'future'){
        if(new Date(reservation.endDate).getTime()>= new Date().getTime()
            && new Date(reservation.startDate).getTime()>= new Date().getTime()){
          return true;
        } else{
          return false
        }
      }
      if(this.filterReservationStatus == 'past'){
        this.ChangePast(true);
        if(new Date(reservation.endDate).getTime()<= new Date().getTime()
            && new Date(reservation.startDate).getTime()<= new Date().getTime()){
          console.log("past is ", this.past)
          return true;
        } else{
          return false
        }
      } else{
        return true
      }
    },
    ChangeTypeOfReservationToShow(type){
      this.typeOfReservation = type;
      console.log('Type of reservation is changed:', this.typeOfReservation)
    },
    ChangePast(value){
      this.past = value;
      console.log("past is changed to ", this.past)
    },
    createReport(){
      axios
      .post(devServer.proxy + "/createReport", {
        "reportText" : this.reportText,
        "requestedToSanction" : this.requestSanctions,
        "approved" : false,
        "clientShowedUp" : this.clientShowedUp,
        "id" : this.selectedReservation.id,
        "reservationType" : this.selectedReservationType,
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString
        }
      }
      )
      .then(response =>
      alert("Successfully created!", response))
      .catch(() =>
      {
        alert("Server error")
      })
    }
  }



}
</script>

<style scoped>
.boat-reservations{
  margin-bottom: 20%;
  margin-left: 15%;
  margin-right: 10%;
  margin-top: 10%;
}

</style>