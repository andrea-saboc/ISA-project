<template>
  <div class="mansion-view" v-if="mansionToShow!=null">
    <br>
    <br>
    <br>
    <div class="row">
      <div class="colinfo">
        <br class="sm">
        <p style="text-transform: uppercase; font-size: 18px; color: gray">
          {{mansionToShow.type}}
        </p>
        <p class="fw-bold" style="font-size: 30px; font-weight: bolder; text-transform: uppercase">
          {{mansionToShow.name}} · {{mansionToShow.avgGrade}} <i class="fa fa-star" aria-hidden="true"></i>


        </p>
        <br class="sm">
        <p style="font-size: 18px">
          Offered by {{ mansionOwner.name }} {{ mansionOwner.surname }}
          <br>
          {{address.address}}, {{address.city}}, {{address.country}}
        </p>
        <br class="sm">
        <div v-if="clientSubscribed != 'true' && user!=null && user=='Client'">
          <button  class="btn btn-primary" v-on:click="SubscribeClient" ><i class="fa fa-star-o" aria-hidden="true"></i>
            Subscribe</button></div>
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
            <h5 style="margin-top: 5%; font-weight: bolder">{{mansionToShow.pricePerDay}}€ per day · {{mansionToShow.priceForSevenDays}}€ per week</h5>

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
            {{mansionToShow.promoDescription}}
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
            {{mansionToShow.cancellationPolicy}}
          </div>
          <br>
          <br>
        </div>

        <hr v-if="loggedUser!=null && loggedUser.advertiserType == null">
        <div class="info" v-if="loggedUser!=null && loggedUser.advertiserType == null">
          <h2><button class="btn btn-lg-link" v-on:click="ShowReservationOffers" v-if="loggedUser">Show reservationOffers</button></h2>
        </div>
        <hr v-if="loggedUser!=null && loggedUser.advertiserType == null">

        <hr v-if="loggedUser!=null && mansionToShow.mansionOwner.id==loggedUser.id">
        <div class="subscribers" v-if="loggedUser!=null && mansionToShow.mansionOwner.id==loggedUser.id">
          <p style="font-weight: bolder; font-size: 26px">
            Subscribers
          </p>
          <div v-if="mansionSubscribers.length==0">
            <p> No subscribers yet!</p>
          </div>
          <div v-else>
            <table class="table">
              <thead>
              <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Email</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(s, index) in mansionSubscribers" :key="s.id">
                <td>{{index+1}}</td>
                <td>{{s.name}}</td>
                <td>{{s.surname}}</td>
                <td>{{s.email}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <hr v-if="loggedUser!=null && mansionToShow.mansionOwner.id==loggedUser.id">

        <hr v-if="mansionToShow.rules!=null && mansionToShow.rules.length!=0">
        <div class="navigation-equipments" v-if="mansionToShow.rules!=null && mansionToShow.rules.length!=0">
          <p style="font-weight: bolder; font-size: 26px">
            Rules
          </p>
          <div v-if="mansionToShow.rules!=null" && mansionToShow.rules.length!=0>
            <div v-for="rule in mansionToShow.rules" :key="rule.ruleId">
              <p >
                {{rule.rule}}
              </p>
            </div>

          </div>

        </div>
        <hr>
        <p style="font-weight: bolder; font-size: 26px">
          Calendar
        </p>
        <button v-if="loggedUser!=null && mansionToShow.mansionOwner.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#exampleModal">Add availability period</button>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add availability for {{mansionToShow.name}}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><i class="fa fa-times" aria-hidden="true"></i></button>
              </div>
              <div class="modal-body">
                <v-date-picker mode="dateTime" is24hr v-model="startDateTime" style="width: 100%" :disabled-dates="availableDates">
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
                <button type="button" class="btn btn-primary" v-on:click="addAvailabilityPeriod" data-bs-dismiss="modal">Add</button>
              </div>
            </div>
          </div>
        </div>
        <br>
        <v-calendar :columns="$screens({ default: 1, lg: 2 })" :attributes='calendar_attributes'
                    :available-dates='availableDates'/>
        <hr>
        <p style="font-weight: bolder; font-size: 26px">
          Discounts
        </p>
        <button v-if="loggedUser!=null && mansionToShow.mansionOwner.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#addQuickResModal">Add new quick reservation</button>
        <hr>
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
        <div v-if="(mansionToShow.InteriorImages!=null && mansionToShow.InteriorImages.length!=0) || (mansionToShow.ExteriorImages!=null && mansionToShow.ExteriorImages.length>0)">
          <hr>
          <p style="font-weight: bolder; font-size: 26px">
            Images
          </p>
          <div class="gallery">
            <figure v-for="inImg in mansionToShow.InteriorImages" :key="inImg.id" class="gallery__item galleritem-1">
              <img v-bind:src="getImg(inImg)" class="gallery-img" alt="Image 1">
            </figure>
            <figure v-for="inImg in mansionToShow.ExteriorImages" :key="inImg.id" class="gallery__item galleritem-1">
              <img v-bind:src="getImg(inImg)" class="gallery-img" alt="Image 1">
            </figure>
          </div>
        </div>
        <hr>
      </div>
      <div class="coladd-reservation" v-if="loggedUser!=null && mansionToShow.mansionOwner!=null && loggedUser.id==mansionToShow.mansionOwner.id">
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
              <input type="number" class="form-control" min="1" max="{{mansionToShow.capacity}}" v-model="clientResNumberOfGuests"  aria-label="Username" aria-describedby="basic-addon1">
            </div>
            <br>
            <button type="button" class="btn btn-primary" style="width: 100%" v-on:click="makeReservationForClient()">Add reservation</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal Quick reservations -->
    <div class="modal fade" id="addQuickResModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Add new quick reservation</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"><i class="fa fa-times" aria-hidden="true"></i></button>
          </div>
          <div class="modal-body">
            <v-date-picker mode="dateTime" is24hr v-model="startDateTimeQuick" style="width: 100%" :disabled-dates="availableDates">
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
                <input type="number" class="form-control" placeholder="Days" aria-label="Username" v-model="numberOfDaysQuick" aria-describedby="basic-addon1">
              </div>

            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="basic-addon111">Number of guests</span>
              <input type="number" class="form-control" placeholder="Number of guests" aria-label="Username" v-model="numberOfGuestsQuick" aria-describedby="basic-addon1">
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" id="basic-addon11">Final price</span>
              <input type="number" class="form-control" placeholder="Price" aria-label="Username" v-model="priceQuick" aria-describedby="basic-addon1">
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
import axios from "axios";
import {devServer} from "../../../vue.config";
import {ref} from "vue";

export default {
  name: "MansionView",
  data: function () {
    return {
      mansionSubscribers: new Array(),
      startDateTimeQuick: '',
      numberOfGuestsQuick: '',
      numberOfHoursQuick: 0,
      numberOfDaysQuick: 0,
      priceQuick: '',
      clientResEmail: '',
      clientResEmailForm: true,
      clientResEmailExists: true,
      clientResAdditionalServices: new Array(),
      clientResStartDate: '',
      clientResEndDate: '',
      clientResNumberOfGuests: '',
      clientResNumberOfHours: '',
      clientResNumberOfDays: '',
      loggedUser: null,
      user: null,
      clientSubscribed: true,
      startDateTime: '',
      endDateTime: '',
      mansionToShow: [],
      address: [],
      mansionOwner: [],
      value: '',
      availablePeriods: [],
      availableDates: [],
      additionalServices: [],
      boatReservations: [],
      reservedDates: [],
      quickReservationsFree: new Array(),
      quickReservationReserved: new Array(),
      availableUntil: '',
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
    this.user = this.$store.state.userType

    console.log("User", this.$store.state.userType)

    var path = window.location.href;
    var mansionid = path.split('/mansion/')[1].replaceAll('%20', ' ');
    console.log("Mansion view --> masnion id: ", mansionid.toString())
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
              console.log("Logged user in mnsion view: ", response.data)
              this.loggedUser = response.data
            }
        ).catch(() =>{
      this.loggedUser = null
    })
    axios
        .get('http://localhost:8080/mansion', {
          params:
              {
                id: mansionid
              },
          headers: {
            'Authorization': this.$store.getters.tokenString
          }
        })
        .then(response => {

          this.mansionToShow = response.data

          //alert(this.user)
          if (this.user == 'Client'){
            this.CheckClientSubscription()}

          console.log("Mansion to show:", this.mansionToShow)
          console.log(this.mansionToShow.address)
          this.address = this.mansionToShow.address
          console.log(response.data)
          this.mansionOwner = this.mansionToShow.mansionOwner
          axios
              .get(devServer.proxy + "/getMansionsSubscribers", {
                params:
                    {
                      mansionId: this.mansionToShow.id
                    },
                headers: {
                  'Authorization': this.$store.getters.tokenString
                }
              })
          .then((resp1 =>{
            this.mansionSubscribers = resp1.data
            console.log("Mansion subscribers: ", this.mansionSubscribers)
          }))
              .catch(() => {
                alert("Error occured while trying to find mansion subscribers!")
              })
          this.calculateAvailableDaysForCalendar()
        })
  },
  methods:{
    ShowReservationOffers(){
      window.location.href = "/mansionReservationOffers/" + this.mansionToShow.id.toString();
    },
    CheckClientSubscription(){
      alert(this.user)
      axios
          .post(devServer.proxy + '/subscriptions/checkMansionSubscription', this.mansionToShow, {
            headers: {
              'Authorization': this.$store.getters.tokenString,
              'Content-Type': 'application/json'
            }
          })
          .then(response => {
            alert(response.data)
            this.clientSubscribed = response.data
          });
    },
    SubscribeClient(){
      //alert(this.boatToShow.id)
      axios
          .post(devServer.proxy + '/subscriptions/newMansionSubscription', this.mansionToShow, {
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
    calculateAvailableDaysForCalendar(){
      for(var tmp in this.availablePeriods) {
        var startDate = new Date(this.availablePeriods[tmp].startDate);
        var endDate = new Date(this.availablePeriods[tmp].endDate)
        this.availableDates.push({
          start: new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate()),
          end: new Date(endDate.getFullYear(), endDate.getMonth(), endDate.getDate())
        });
      }
      for(var reservation in this.boatReservations){
        var startDateRes = new Date(this.mansionReservations[reservation].startDate)
        var endDateRes = new Date(this.mansionReservations[reservation].endDate)
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
          .get(devServer.proxy + "/getMansionDiscountReservations", {
            headers: {
              'Authorization' : this.$store.getters.tokenString
            },
            params:{
              "boatId" : this.mansionToShow.id
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
      axios
          .post(devServer.proxy + "/addAvailablePeriodForMansion", {
            "boatId" : this.mansionToShow.id,
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
    },
    makeReservationForClient(){
      axios.post(devServer.proxy+ "/makeMansionReservationClient", {
        email : this.clientResEmail,
        additionalServiceSet : this.clientResAdditionalServices,
        startDate : this.clientResStartDate,
        days : this.clientResNumberOfDays,
        "boatId" : this.mansionToShow.id,
        numberOfGuests : this.clientResNumberOfGuests
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString,
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
    },
    addQuickReservation(){
      console.log("pokusavam da kreiram")
      axios.post(devServer.proxy + "/createDiscountMansionReservation", {
        "boatId" : this.mansionToShow.id,
        "startDate" : this.startDateTimeQuick,
        "days" : this.numberOfDaysQuick,
        "hours" : this.numberOfHoursQuick,
        "numberOfGuests" : this.numberOfGuestsQuick,
        "priceWithDiscount": this.priceQuick,
        "validUntil" : this.availableUntil
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString,
          'Content-Type': 'application/json'
        }
      })
          .then(response => {
            alert("response data",response.data)
          })
      .catch(() =>
      {
        alert("Error happened!")
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



.additional-desc{
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
}

.mansion-view .icons{
  width: 10%;
  margin: 2%;
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