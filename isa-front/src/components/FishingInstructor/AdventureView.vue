<template>
<div class="mansion-view" v-if="adventureToShow!=null">
<br>
<br>
<br>
<br>
  <div class="row">
    <div class="colinfo">
  <br class="sm">
  <p class="fw-bold" style="font-size: 30px; font-weight: bolder; text-transform: uppercase">
    {{adventureToShow.name}}  <i class="fa fa-star" aria-hidden="true"></i>
  </p>

     <div v-for="(image, index) in imagesAll" :key="(image, index)">
                    <img class="ms-3 me-3 mt-3 newImage img-fluid" id="image123"  v-bind:src="image">
     </div>
  <br class="sm">
  <p style="font-size: 18px">
    Offered by {{fishingInstructor.name}} {{fishingInstructor.surname}}
    <br>
    {{address.address}}, {{address.city}}, {{address.country}}
  </p>
  <hr>
      <div class="additional-desc">
        <div class="icons">
          <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-cash-coin" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M11 15a4 4 0 1 0 0-8 4 4 0 0 0 0 8zm5-4a5 5 0 1 1-10 0 5 5 0 0 1 10 0z"/>
            <path d="M9.438 11.944c.047.596.518 1.06 1.363 1.116v.44h.375v-.443c.875-.061 1.386-.529 1.386-1.207 0-.618-.39-.936-1.09-1.1l-.296-.07v-1.2c.376.043.614.248.671.532h.658c-.047-.575-.54-1.024-1.329-1.073V8.5h-.375v.45c-.747.073-1.255.522-1.255 1.158 0 .562.378.92 1.007 1.066l.248.061v1.272c-.384-.058-.639-.27-.696-.563h-.668zm1.36-1.354c-.369-.085-.569-.26-.569-.522 0-.294.216-.514.572-.578v1.1h-.003zm.432.746c.449.104.655.272.655.569 0 .339-.257.571-.709.614v-1.195l.054.012z"/>
            <path d="M1 0a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.083c.058-.344.145-.678.258-1H3a2 2 0 0 0-2-2V3a2 2 0 0 0 2-2h10a2 2 0 0 0 2 2v3.528c.38.34.717.728 1 1.154V1a1 1 0 0 0-1-1H1z"/>
            <path d="M9.998 5.083 10 5a2 2 0 1 0-3.132 1.65 5.982 5.982 0 0 1 3.13-1.567z"/>
          </svg>
        </div>
        <div class="info">
          <h5 style="margin-top: 5%; font-weight: bolder">{{adventureToShow.pricePerHour}}€ per hour · {{adventureToShow.pricePerDay}}€ per day</h5>

        </div>
      </div>
  <div class="additional-desc">
    <div class="icons">
      <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-star" viewBox="0 0 16 16">
        <path d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z"/>
      </svg>
    </div>

    <div class="info">
      <h4>Description</h4>
      {{adventureToShow.promoDescription}}
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
      {{adventureToShow.cancellationPolicy}}
    </div>
    <br>
    <br>
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
      <h4>Biography FishingInstructor</h4>
      {{adventureToShow.biography}}
    </div>
    <br>
    <br>
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
      <h4>Equipment</h4>
      {{adventureToShow.equipment}}
    </div>
  </div>

<!--RULES -->
 <hr v-if="adventureToShow.rules!=null && adventureToShow.rules.length!=0">
      <div class="navigation-equipments" v-if="adventureToShow.rules!=null && adventureToShow.rules.length!=0">
        <p style="font-weight: bolder; font-size: 26px">
          Rules
        </p>
        <div v-if="adventureToShow.rules!=null">
          <div v-for="rule in adventureToShow.rules" :key="rule.ruleId">
            <p >
              {{rule.rule}}
            </p>
          </div>

        </div>

      </div>

      <!-- Additional services -->
      <hr>
      <div v-if="additionalServices!=null && additionalServices.length!=0">
      <p style="font-weight: bolder; font-size: 26px">
        Additional services
      </p>
      <div class="additional-services">
        <table class="table table-striped">
          <thead>
          <tr>
            <th>
              Service
            </th>
            <th>
              Price/h
            </th>
            <th>
              Price/day
            </th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="ar in additionalServices" :key="ar.id">
            <td>{{ar.name}}</td>
            <td>{{ar.pricePerHour}}</td>
            <td>{{ar.pricePerDay}}</td>
          </tr>
          </tbody>
        </table>

      </div>
      </div>
 <!-- KALENDAR -->

 <p style="font-weight: bolder; font-size: 26px">
        Calendar OVAJ DEO IDE NA POCETAK STRANICE INSTRUKTORA
      </p>
      <button v-if="loggedUser!=null && fishingInstructor.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#exampleModal">Add availability period</button>
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Add availability for {{fishingInstructor.name}}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><i class="fa fa-times" aria-hidden="true"></i></button>
            </div>
            <div class="modal-body">
              <v-date-picker mode="dateTime" is24hr v-model="startDateTime" style="width: 100%" :disabled-dates="availableDates" @dayclick="dayClicked">
                <template v-slot="{ inputValue, inputEvents }">
                  <input
                      class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                      :value="inputValue"
                      v-on="inputEvents"
                      style="overflow: visible"
                      placeholder="From time"
                  />
                </template>
              </v-date-picker>
              <v-date-picker  mode="dateTime" is24hr v-model="endDateTime" style="width: 100%" :disabled-dates="availableDates">
                <template v-slot="{ inputValue, inputEvents }">
                  <input
                      class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                      :value="inputValue"
                      v-on="inputEvents"
                      placeholder="To time"
                  />
                </template>
              </v-date-picker>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary"  data-bs-dismiss="modal"  v-on:click="addAvailabilityPeriod">Add</button>
            </div>
          </div>
        </div>
      </div>
      <br>
      <v-calendar :columns="$screens({ default: 1, lg: 2 })" :attributes='calendar_attributes'
                  :available-dates='availableDates'/>
      <hr>

<!-- Brza rezervacija -->
      <p style="font-weight: bolder; font-size: 26px">
        Discounts
      </p>
      <button v-if="loggedUser!=null && fishingInstructor.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#addQuickResModal">Add new quick reservation</button>
      <hr>


      <!-- MAPA -->
      <p style="font-weight: bolder; font-size: 26px">
        Location
      </p>
      <p style="font-size: 18px;">
        {{address.address}}, {{address.city}}, {{address.country}}
        <br>
        {{address.latitude}}, {{address.longitude}}
      </p>
      <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px">

        <ol-view ref="view" :center="[address.longitude,address.latitude]" :rotation="rotation" :zoom="zoom" :projection="projection" />

        <ol-tile-layer>
          <ol-source-osm />
        </ol-tile-layer>

        <ol-vector-layer>
          <ol-source-vector>
            <ol-feature>
              <ol-geom-point :coordinates="[address.longitude,address.latitude]"></ol-geom-point>
              <ol-style>
                <ol-style-circle radius="5">
                  <ol-style-fill color="white"></ol-style-fill>
                  <ol-style-stroke color="red" :width="10" ></ol-style-stroke>
                </ol-style-circle>
              </ol-style>
            </ol-feature>

          </ol-source-vector>

        </ol-vector-layer>

      </ol-map>

</div>
      <div class="coladd-reservation" v-if="loggedUser!=null && loggedUser.id==fishingInstructor.id">
      <div class="input-reservations">
      <h5>
        Add reservation
      </h5>
        <div class="mb-3">
            <div class="input-group mb-3">
              <span class="input-group-text" id="basic-addon3">Email</span>
              <input type="text" class="form-control" v-model="clientResEmail" v-on="{keydown: checkEmail}" aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <p v-if="!clientResEmailForm" style="font-size: small; font-style: italic">Invalid email.</p>
            <p v-if="!clientResEmailExists" style="font-size: small; font-style: italic">User not registered.</p>
          <label for="reservationStart" class="form-label">Select start time</label>
          <v-date-picker mode="dateTime" is24hr v-model="clientResStartDate" id="reservationStart" :available-dates='availableDates'>
            <template v-slot="{ inputValue, inputEvents }">
              <input
                  class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                  :value="inputValue"
                  v-on="inputEvents"
                  style="overflow: visible"
                  placeholder="From time"
              />
            </template>
          </v-date-picker>
          <div class="horizontal">
            <div class="input-group mb-3">
              <span class="input-group-text" id="basic-addon4">Days</span>
              <input type="number" class="form-control" v-model="clientResNumberOfDays"  aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <div class="input-group mb-3" style="margin-left: 2%">
              <span class="input-group-text" id="basic-addon1">Hours</span>
              <input type="number" class="form-control" v-model="clientResNumberOfHours"  aria-label="Username" aria-describedby="basic-addon1">
            </div>
          </div>
          <hr>
          <div v-if="additionalServices!=null && additionalServices.length!=0">
            Addition services:
            <table>
              <tbody>
              <tr v-for="as in additionalServices" :key="as.id">
                <td><div class="form-check">
                  <input class="form-check-input" type="checkbox" value=""  v-bind:id="as.id+'ascr'"  v-on:click="addAditionalServiceToRes(as)">
                  <label class="form-check-label" for="as.id+'ascr'">
                    {{as.name}}
                  </label>
                </div></td>
              </tr>
              </tbody>
            </table>
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Number of guests</span>
            <input type="number" class="form-control" min="1" max="{{adventureToShow.capacity}}" v-model="clientResNumberOfGuests"  aria-label="Username" aria-describedby="basic-addon1">
          </div>
         
      <br>
          <button type="button" class="btn btn-primary" style="width: 100%" v-on:click="makeReservationForClient()">Add reservation</button>
        </div>
      </div>
      </div>
    </div>
  <!-- Modal -->
  <div class="modal fade" id="addQuickResModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">Add new quick reservation</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><i class="fa fa-times" aria-hidden="true"></i></button>
        </div>
        <div class="modal-body">
          <v-date-picker mode="dateTime" is24hr v-model="startDateTimeQuick" style="width: 100%" :available-dates='availableDates'>
            <template v-slot="{ inputValue, inputEvents }">
              <input
                  class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                  :value="inputValue"
                  v-on="inputEvents"
                  style="overflow: visible"
                  placeholder="From time"
              />
            </template>
          </v-date-picker>
          <div class="horizontal">
            <div class="input-group mb-3" style="margin-right: 5%">
              <span class="input-group-text" id="basic-addon6">Days</span>
              <input type="number" class="form-control" min=0 placeholder="Days" aria-label="Username" v-model="numberOfDaysQuick" aria-describedby="basic-addon1">
            </div>

            <div class="input-group mb-3">
              <span class="input-group-text" id="basic-addon7">Hours</span>
              <input type="number" class="form-control" min=0 placeholder="Hours" aria-label="Username" v-model="numberOfHoursQuick" aria-describedby="basic-addon1">
            </div>

          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon111">Number of guests</span>
            <input type="number" class="form-control" min=0 placeholder="Number of guests" aria-label="Username" v-model="numberOfGuestsQuick" aria-describedby="basic-addon1">
          </div>
          <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon11">Final price</span>
            <input type="number" class="form-control" min=0 placeholder="Price" aria-label="Username" v-model="priceQuick" aria-describedby="basic-addon1">
          </div>
          <v-date-picker mode="dateTime" is24hr v-model="availableUntil" style="width: 100%">
            <template v-slot="{ inputValue, inputEvents }">
              <input
                  class="px-2 py-1 border rounded focus:outline-none focus:border-blue-300"
                  :value="inputValue"
                  v-on="inputEvents"
                  style="overflow: visible"
                  placeholder="Valid until"
              />
            </template>
          </v-date-picker>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" v-on:click="addQuickReservation">Add</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       
      </div>
    </div>

</div>
</div>
</div>
</template>


<script>
import {ref} from "vue";
import axios from 'axios'
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  name: "AdventureView",
  data: function (){
    return{
      adventureToShow: [],
      loggedUser: null,
      user: null,
      fishingInstructor: [],
      token:null,
      imagesAll: [],
      startDateTimeQuick: '',
      numberOfGuestsQuick: '',
      numberOfHoursQuick: 0,
      numberOfDaysQuick: 0,
      priceQuick: '',
       clientResEmail: '',
      clientResEmailForm: true,
      clientResEmailExists: true,
      clientResAdditionalServices: [],
      clientResStartDate: '',
      clientResEndDate: '',
      clientResNumberOfGuests: '',
      clientResNumberOfHours: '',
      clientResNumberOfDays: '',
       startDateTime: '',
      endDateTime: '',
      place:'',
       address: [],
        value: '',
      availablePeriods: [],
      availableDates: [],
      disavailableDates: [],
      additionalServices : [],
      validateDates:[],
      fishingReservations : [],
      reservedDates : [],
      availableUntil: '',
      quickReservationsFree: new Array(),
      quickReservationReserved: new Array(),
      calendar_attributes: [
        {
          key: 'today',
          highlight: 'red',
          dates: [new Date()]
        },
      ]


    }

  },
  setup() {
    const projection = ref('EPSG:4326')
    const zoom = ref(8)
    const rotation = ref(0)
    return {
      projection,
      zoom,
      rotation
    }
  },
  
  mounted() {
      this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
      var path = window.location.href;
      var adventureId = path.split('/adventure/')[1].replaceAll('%20', ' ');
   
    axios.get("/userData", {
      headers: {
        'Authorization' :  'Bearer ' + this.token,
      }
    })
        .then(response => {
          this.loggedUser = response.data
          console.log("ULOGOVANI KORISNIK JE ", this.loggedUser)
        }).catch(() =>{
          this.loggedUser = null
    })
    axios
         .get('/adventure', {
      params:
          {
            id : adventureId
          },
      headers: {
        'Authorization' :'Bearer ' + this.token,
      }
    })
    .then(response => {
      this.adventureToShow = response.data
       console.log("Adventure to show:", this.adventureToShow.rules)
      this.address = this.adventureToShow.address
      console.log(response.data)
      this.fishingInstructor = this.adventureToShow.fishingInstructor
      axios
          .post("/getFishingAvailability", {
            "fishingId": this.adventureToShow.fishingInstructor.id
          }, {
            headers: {
              'Authorization': 'Bearer ' + this.token,
            }
          })
          .then(response => {
            this.availablePeriods = response.data
            console.log("Available periods for fishing: ", this.availablePeriods)
            axios.get("/additionalServicesAdventure", {
              params:
                  {
                    id: adventureId
                  },
              headers: {
                'Authorization': this.$store.getters.tokenString
              }
            })
            .then(resp => {
              this.additionalServices = resp.data
              axios.get("/getAllReservedAdventureDatesForFishing", {
                headers: {
                  'Authorization': 'Bearer ' + this.token,
                
              }
            })

            .then((resp1 => {
               this.fishingReservations = resp1.data
               console.log('Fishing instructor reservations:', this.fishingReservations)
                this.calculateAvailableDaysForCalendar()

            }))
            })
          })
      for(var i=0;i<this.adventureToShow.images.length;i++){
          this.setImg(this.adventureToShow.images[i])
    }
    }).catch(() =>{
         console.log("There is some problem")
    })
  },
  methods:
  {
    setImg: function(image)
    {
      console.log('Image path je ',image.path.toString().replaceAll('\\','/'))
      console.log('/entityImage/'+image.path)
      axios.get('/entityImage/'+image.path, {
      headers: {
        'Authorization' : 'Bearer ' + this.token,
      },
        responseType: 'arraybuffer',
         }) 
         .then(response => {
           console.log('response sa backa', response)

           let base64ImageString = Buffer.from(response.data, 'binary').toString('base64')
      let srcValue = "data:image/png;base64,"+base64ImageString
      this.imagesAll.push(srcValue)
          
    })
    .catch(()=>{
      console.log("The is a trouble")
    })
    
  },

  calculateAvailableDaysForCalendar(){
    console.log("DUZINA JE", this.availablePeriods.length)
    if(this.availablePeriods.length==0)
    {
       this.availableDates.push({
          start: new Date(Date.now()),
          end: new Date(Date.now())
       });
    }
      for(var tmp in this.availablePeriods) {
        var startDate = new Date(this.availablePeriods[tmp].startDate);
        var endDate = new Date(this.availablePeriods[tmp].endDate)
        if(startDate<Date.now() && endDate>=Date.now())
        { 
          this.availableDates.push({
          start: new Date(Date.now()),
          end: new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate())
        });
        }
        if(startDate>=Date.now()){
        this.availableDates.push({
          start: new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()),
          end: new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate())
        });
      }
      }

      for(var reservation in this.fishingReservations){
        var startDateRes = new Date(this.fishingReservations[reservation].startDate)
        var endDateRes = new Date(this.fishingReservations[reservation].endDate)
        this.reservedDates.push({
          start: new Date(startDateRes.getFullYear(), startDateRes.getMonth(), startDateRes.getDate(), startDateRes.getHours(), startDateRes.getMinutes()),
          end: new Date(endDateRes.getFullYear(), endDateRes.getMonth(), endDateRes.getDate(), endDateRes.getHours(), endDateRes.getMinutes())
        });
      }
      for (var id in this.reservedDates){
        this.calendar_attributes.push({
          highlight: {
            start: { fillMode: 'outline' },
            base: { fillMode: 'outline' },
            end: { fillMode: 'outline' },
          },
          dates: { start: this.reservedDates[id].start, end: this.reservedDates[id].end },
        })
      }
      console.log("Calculated available days:", this.availableDates)
      console.log("Calculated reserved days:", this.reservedDates)
      console.log('Calenar attributes before:',this.calendar_attributes)
      this.calculateDiscountReservations()
      console.log('Calendar attributes after:',this.calendar_attributes)
    },
    calculateDiscountReservations(){
      axios
      .get("/getAdventureDiscountReservations", {
        headers: {
          'Authorization' : 'Bearer ' + this.token,
        },
        params:{
          "adventureId": this.adventureToShow.id
        }
      })
      .then(response =>
      {
        console.log('Got discount reservation:', response.data)
        this.quickReservationsFree = response.data.freeReservations;
        this.quickReservationReserved = response.data.reservedReservations;
        console.log("Free quick reservations:", this.quickReservationsFree)
        console.log("Reserved quick reservations:", this.quickReservationReserved)

        for(var i in this.quickReservationsFree){
          var startDate = new Date(this.quickReservationsFree[i].startDate)
          var endDate = new Date(this.quickReservationsFree[i].endDate)
          this.disavailableDates.push({
          start: new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()),
          end: new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate())
        });
          console.log(i, "Start day for free is:", startDate)
          console.log(i, "End day for free is:", endDate)
          this.calendar_attributes.push({
            highlight: {
              start: { fillMode: 'solid', color: 'teal' },
              base: { fillMode: 'solid', color: 'teal' },
              end: { fillMode: 'solid', color: 'teal' }
            },
            dates: { start: startDate, end: endDate },
          })
        }
        console.log("Free discount reservations are added")
        for(var j in this.quickReservationReserved){
          var startDate1 = new Date(this.quickReservationReserved[j].startDate)
          var endDate1 = new Date(this.quickReservationReserved[j].endDate)
          this.disavailableDates.push({
          start: new Date(startDate1.getFullYear(), startDate1.getMonth(), startDate1.getDate()),
          end: new Date(endDate1.getFullYear(), endDate1.getMonth(), endDate1.getDate())
        });
          this.calendar_attributes.push({
            highlight: {
              start: { fillMode: 'solid' },
              base: { fillMode: 'solid' },
              end: { fillMode: 'solid' },
              color: 'pink'
            },
            dates: { start: startDate1, end: endDate1 },
          })
        }
        console.log("Reserved discount reservations are added")
        console.log(this.calendar_attributes)
      })
    },
    addAvailabilityPeriod(){

      if(this.endDateTime<this.startDateTime)
      {
        alert("End Date is before Start Date")
        return;
      }
      if(this.endDateTime=== '' ||  this.startDateTime=== '')
      {
        alert("Enter all fields")
        return;
      }
      else{
      axios
      .post("/addAvailablePeriodForFishing", {
        "fishingId" : this.adventureToShow.fishingInstructor.id,
        "startTime" : this.startDateTime,
        "endTime" : this.endDateTime
      }, {
        headers: {
          'Authorization' : 'Bearer ' + this.token,
        }
      })
      .then(response => {
        this.availablePeriods = response.data
        this.calculateAvailableDaysForCalendar()
        console.log("New available periods: ", this.availablePeriods )
        this.startDateTime="";
        this.endDateTime="";
        this.calculateAvailableDaysForCalendar()
      })
      }

    },
    addQuickReservation(){
      if(this.startDateTimeQuick==='' || this.numberOfDaysQuick===''||  this.numberOfHoursQuick==='' || this.numberOfGuestsQuick===''
      || this.priceQuick===''|| this.availableUntil==='')
       {
        alert("Enter all fields")
        return;
      }
      var d = new Date();
       d.setDate(d.getDate());
  
      if(this.availableUntil>=this.startDateTimeQuick || this.availableUntil<d)
      {
        alert("Valide until must be before starte date and date now");
        return;
      }
      
      else{
      axios.post( "/createDiscountAdventureReservation", {
                                     
        "boatId" : this.adventureToShow.id,
        "startDate" : this.startDateTimeQuick,
        "days" : this.numberOfDaysQuick,
        "hours" : this.numberOfHoursQuick,
        "numberOfGuests" : this.numberOfGuestsQuick,
        "priceWithDiscount": this.priceQuick,
        "validUntil" : this.availableUntil
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.token,
          'Content-Type': 'application/json'
        }
      })

      .then(response =>{
            alert(response.data)
            this.calculateAvailableDaysForCalendar()
            this.startDateTimeQuick="";
      this.numberOfDaysQuick="";
      this.numberOfHoursQuick="";
      this.numberOfGuestsQuick="";
      this.priceQuick="";
      this.availableUntil="";
      })
      .catch( function (err){
        alert(err)
      })
      
      
    }
    },checkEmail(){
        if (!this.validEmail(this.clientResEmail)){
          this.clientResEmailForm = false
        } else{
          this.clientResEmailForm = true
        }/*else {
          this.clientResEmailForm = true
          axios.post(devServer.proxy + '/emailExistsClient', {
            email: this.clientResEmail
          }, {
            headers: {
              'Authorization': this.$store.getters.tokenString,
              'Content-Type': 'application/json'
            }
          }).then(response =>{
            this.clientResEmailExists = response.data
            if(!this.clientResEmailExists){
              alert("User with that email is not registered yet!")
            }
          })
        }*/
    },
    validEmail: function (email) {
      var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    addAditionalServiceToRes(additionalService){
      let checkbox = document.getElementById(additionalService.id + 'ascr').checked
      console.log("additional service is selected:", checkbox)
      if(checkbox){
        this.clientResAdditionalServices.push(additionalService)
      } else{
        for( var i = 0; i < this.clientResAdditionalServices.length; i++){

          if ( this.clientResAdditionalServices[i] === additionalService) {
            this.clientResAdditionalServices.splice(i, 1);
          }

        }
      }
      console.log("additional service is :", this.clientResAdditionalServices)
    }, 
    makeReservationForClient(){
      axios.post("/makeAdventureReservationClient", {
          email : this.clientResEmail,
          additionalServiceSet : this.clientResAdditionalServices,
          startDate : this.clientResStartDate,
          days : this.clientResNumberOfDays,
          hours : this.clientResNumberOfHours,
          "boatId" : this.adventureToShow.id,
          numberOfGuests : this.clientResNumberOfGuests
      }, {
        headers: {
          'Authorization': 'Bearer ' + this.token,
          'Content-Type': 'application/json'
        }
      })
      .then(response =>{
            alert(response.data)
        this.clientResEmail = '';
            this.clientResAdditionalServices = new Array();
            this.clientResStartDate ='';
            this.clientResNumberOfDays=''
            this.clientResNumberOfHours=''
            this.clientResNumberOfGuests=''
      }
      )
      .catch( function (err){
        alert(err)
      })
      this.calculateAvailableDaysForCalendar()
    }
 }
}
</script>
<style scoped>
.mansion-view{
  horiz-align: center;
  width: 80%;
  margin-left: 15%;
  height: 100%;
  padding-bottom: 5%;
}

.mansion-view .top-slider{
  height: 550px;
}

.additional-desc{
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
}

.mansion-view .icons{
  width: 10%;
  margin: 2%;
}

.mansion-view .navigation-equipments-list, .mansion-view .engine-information-list{
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

.mansion-view .row .colinfo {
  width: 60%;
}

.coladd-reservation{
  background-color: #e0e0e0;
  height: 30%;
  width: 30%;
  margin-top: 10%;
  margin-left: 5%;
  border: solid 0.1px black;
  border-radius: 11px;
}

.input-reservations{
  padding: 6%;
}

.horizontal{
  display: flex;
  flex-direction: row;
  margin-top: 5%;
}

.gallery {
  display: grid;
  grid-gap: 10px;
}

.gallery-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}




</style>