<template>
<div class="boat-view">
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner top-slider">
      <div class="carousel-item active">
        <img src="../assets/yacht.jpg" class=" w-100 h-25" alt="../assets/yacht.jpg">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100 h-25  " alt="../assets/yacht.jpg">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100 h-25" alt="../assets/yacht.jpg">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <div class="row">
    <div class="colinfo">
  <br class="sm">
  <p style="text-transform: uppercase; font-size: 18px; color: gray">
    {{boatToShow.type}}
  </p>
  <p class="fw-bold" style="font-size: 30px; font-weight: bolder; text-transform: uppercase">
    {{boatToShow.name}}

  </p>
  <br class="sm">
  <p style="font-size: 18px">
    Offered by {{boatOwner.name}} {{boatOwner.surname}}
    <br>
    {{address.address}}, {{address.city}}, {{address.country}}
  </p>
  <br class="sm">
  <p style="font-size: 18px; font-weight: bold">
   {{boatToShow.capacity}} people · {{boatToShow.enginePower}} horsepower · {{boatToShow.length}} metres length
  </p>
  <hr>
  <div class="additional-desc">
    <div class="icons">
      <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
      </svg>
    </div>
    <div class="info">
    <div v-if="clientSubscribed != 'true'">
      <button  class="btn btn-primary" v-on:click="SubscribeClient">Subscribe</button></div>
      <h4>Description</h4>
      {{boatToShow.promoDescription}}
    </div>
  </div>
  <div class="additional-desc">
    <div class="icons">
      <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-journal-x" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M6.146 6.146a.5.5 0 0 1 .708 0L8 7.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 8l1.147 1.146a.5.5 0 0 1-.708.708L8 8.707 6.854 9.854a.5.5 0 0 1-.708-.708L7.293 8 6.146 6.854a.5.5 0 0 1 0-.708z"/>
        <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
        <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
      </svg>
    </div>
    <div class="info">
      <h4>Cancellation policy</h4>
      {{boatToShow.cancellationPolicy}}
    </div>
  </div>
    <hr>
    <div class="navigation-equipments">
      <p style="font-weight: bolder; font-size: 26px">
        Navigation equipment
      </p>
      <div class="navigation-equipments-list">
      <div class="navigation-equipment" v-if="boatToShow.GPS">
        <div class="navigation-icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
            <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
            <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
          </svg>
        </div>
        <div class="navigation-name">
          GPS
        </div>
      </div>
        <div class="navigation-equipment" v-if="boatToShow.Radar">
          <div class="navigation-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-broadcast" viewBox="0 0 16 16">
            <path d="M3.05 3.05a7 7 0 0 0 0 9.9.5.5 0 0 1-.707.707 8 8 0 0 1 0-11.314.5.5 0 0 1 .707.707zm2.122 2.122a4 4 0 0 0 0 5.656.5.5 0 1 1-.708.708 5 5 0 0 1 0-7.072.5.5 0 0 1 .708.708zm5.656-.708a.5.5 0 0 1 .708 0 5 5 0 0 1 0 7.072.5.5 0 1 1-.708-.708 4 4 0 0 0 0-5.656.5.5 0 0 1 0-.708zm2.122-2.12a.5.5 0 0 1 .707 0 8 8 0 0 1 0 11.313.5.5 0 0 1-.707-.707 7 7 0 0 0 0-9.9.5.5 0 0 1 0-.707zM10 8a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"/>
            </svg>
          </div>
          <div class="navigation-name">
            Radar
          </div>
        </div>
        <div class="navigation-equipment" v-if="boatToShow.VHFradio">
          <div class="navigation-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-headset" viewBox="0 0 16 16">
              <path d="M8 1a5 5 0 0 0-5 5v1h1a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V6a6 6 0 1 1 12 0v6a2.5 2.5 0 0 1-2.5 2.5H9.366a1 1 0 0 1-.866.5h-1a1 1 0 1 1 0-2h1a1 1 0 0 1 .866.5H11.5A1.5 1.5 0 0 0 13 12h-1a1 1 0 0 1-1-1V8a1 1 0 0 1 1-1h1V6a5 5 0 0 0-5-5z"/>
            </svg>
          </div>
          <div class="navigation-name">
            VHF radio
          </div>
        </div>
        <div class="navigation-equipment" v-if="boatToShow.fishfinder">
          <div class="navigation-icon">
            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-tablet" viewBox="0 0 16 16">
              <path d="M12 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h8zM4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4z"/>
              <path d="M8 14a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
            </svg>
          </div>
          <div class="navigation-name">
            Fishfinder
          </div>
        </div>
        <div v-if="!boatToShow.fishfinder && !boatToShow.VHFradio && !boatToShow.radar && !boatToShow.GPS">
          <p style="font-size: 18px">
            There is no navigation equipment
          </p>
        </div>
        </div>
      </div>
      <hr>
      <div class="engine-information">

        <div class="engine-information-list">
          <div class="einfo">
            <div class="navigation-icon">
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-speedometer" viewBox="0 0 16 16">
                <path d="M8 2a.5.5 0 0 1 .5.5V4a.5.5 0 0 1-1 0V2.5A.5.5 0 0 1 8 2zM3.732 3.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 8a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 8zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 7.31A.91.91 0 1 0 8.85 8.569l3.434-4.297a.389.389 0 0 0-.029-.518z"/>
                <path fill-rule="evenodd" d="M6.664 15.889A8 8 0 1 1 9.336.11a8 8 0 0 1-2.672 15.78zm-4.665-4.283A11.945 11.945 0 0 1 8 10c2.186 0 4.236.585 6.001 1.606a7 7 0 1 0-12.002 0z"/>
              </svg>
            </div>
            <div class="navigation-name">
              <p style="font-weight: bold; align-self: center">
                Speed
              </p>
              <p style="">
                {{boatToShow.maxSpeed}} km/h
              </p>

            </div>
          </div>
          <div class="einfo">
            <div class="navigation-icon">
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
              </svg>
            </div>
            <div class="navigation-name">
              <p style="font-weight: bold; align-self: center">
                Number of engines
              </p>
              <p style="align-self: center; padding-left: 30%;" >
                {{boatToShow.numberOfEngines}}
              </p>

            </div>
          </div>


        </div>

      </div>
      <hr>
      <div class="navigation-equipments">
        <p style="font-weight: bolder; font-size: 26px">
          Rules
        </p>
        <p v-for="rule in boatToShow.rules"
           :key="rule.id">
          {{rule.rule}}
        </p>

      </div>
      <hr>
      <p style="font-weight: bolder; font-size: 26px">
        Calendar
      </p>
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Add availability period</button>
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Add availability for {{boatToShow.name}}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
              </svg></button>
            </div>
            <div class="modal-body">
              <v-date-picker mode="dateTime" is24hr v-model="startDateTime">
                <template v-slot="{ inputValue, inputEvents }">
                  <input
                      class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                      :value="inputValue"
                      v-on="inputEvents"
                  />
                </template>
              </v-date-picker>
              <v-date-picker  mode="dateTime" is24hr v-model="endDateTime">
                <template v-slot="{ inputValue, inputEvents }">
                  <input
                      class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                      :value="inputValue"
                      v-on="inputEvents"
                  />
                </template>
              </v-date-picker>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" v-on:click="addAvailabilityPeriod" data-bs-dismiss="modal">Add</button>
            </div>
          </div>
        </div>
      </div>
      <br>
      <v-calendar :columns="$screens({ default: 1, lg: 2 })" style="width: 100%; height: 30%" :attributes='calendar_attributes'
                  :available-dates='availableDates'/>
<hr>



    </div>


    </div>
  </div>


</template>
<script>
import axios from 'axios'
import {devServer} from "../../vue.config";
export default {
  name: "BoatView",
  setup() {
    return {
      coords: [54, 39],
    }
  },
  data: function (){
    return{
      clientSubscribed: '',
      startDateTime: '',
      endDateTime: '',
      boatToShow: [],
      address: [],
      boatOwner: [],
      value: '',
      availablePeriods: [],
      availableDates: [],
      calendar_attributes: [
        {
          key: 'today',
          highlight: 'red',
          dates: new Date(),
        },
      ]
    }

  },
  mounted() {
    var path = window.location.href;
    var boatId = path.split('/boat/')[1].replaceAll('%20', ' ');
    alert("boat id: ", boatId.toString())
    axios
    .get('http://localhost:8080/boat', {
      params:
          {
            id : boatId
          },
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
    .then(response =>{
      this.boatToShow = response.data
      this.CheckClientSubscription(this.boatToShow)
      console.log(this.boatToShow.address)
      this.address = this.boatToShow.address
      console.log(response.data)
      this.boatOwner = this.boatToShow.boatOwner
      axios
          .post(devServer.proxy + "/getBoatAvailability", {
            "boatId" : this.boatToShow.id
          }, { headers: {
              'Authorization' : this.$store.getters.tokenString
            }
          })
          .then(response =>{
            this.availablePeriods = response.data
            console.log("Available periods for boat: ", this.availablePeriods)
            this.calculateAvailableDaysForCalendar()
          })
    })


  },
  methods:{
    calculateAvailableDaysForCalendar(){
      for(var tmp in this.availablePeriods) {
        var startDate = new Date(this.availablePeriods[tmp].startDate);
        var endDate = new Date(this.availablePeriods[tmp].endDate)
        this.availableDates.push({
          start: new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()),
          end: new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate())
        });
      }
    },
    addAvailabilityPeriod(){
      alert(this.startDateTime, "and", this.endDateTime)
      axios
      .post(devServer.proxy + "/addAvailablePeriodForBoat", {
        "boatId" : this.boatToShow.id,
        "startTime" : this.startDateTime,
        "endTime" : this.endDateTime
      }, {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
      .then(response => {
        this.availablePeriods = response.data
        console.log("New available periods: ", this.availablePeriods )
        this.calculateAvailableDaysForCalendar()
      })

    },
    SubscribeClient(){
      alert(this.boatToShow.id)
            axios
                .post(devServer.proxy + '/subscriptions/newBoatSubscription', this.boatToShow, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    alert('submited')
                    console.log(response.data)
                });

    },
    CheckClientSubscription(boat){
      alert(boat)
            axios
                .post(devServer.proxy + '/subscriptions/checkBoatSubscription', boat, {
                    headers: {
                        'Authorization': this.$store.getters.tokenString,
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {                   
                    alert(response.data)
                    this.clientSubscribed = response.data
                });

    }

  }
}


</script>

<style scoped>
.boat-view{
  horiz-align: center;
  width: 80%;
  margin-left: 15%;
  height: 100%;
}

.boat-view .top-slider{
  height: 550px;
}

.additional-desc{
  display: flex;
  flex-direction: row;
}

.boat-view .icons{
  width: 10%;
  margin: 2%;
}

.boat-view .navigation-equipments-list, .boat-view .engine-information-list{
  display: flex;
  flex-direction: row;
}

.navigation-equipments .navigation-equipment{
  display: flex;
  flex-direction: column;
  margin: 2%;
}

.navigation-equipment .navigation-icon{
  height: 70%;
  align-self: center;
}

.navigation-equipment .navigation-name{
  height: 30%;
  font-size: 18px;
  align-self: center;
}

.einfo .navigation-icon{
  align-self: center;
  margin: 0.2%;
  padding-left: 30%;

}

.einfo .navigation-name{
  align-self: center;
  font-size: 18px;
  height: 30%;
  padding-left: 30%;
}

.einfo{
  min-width: 10%;
  margin: 0.5%;
}

.einfo p{

}

.boat-view .row .colinfo {
  width: 60%;
}




</style>