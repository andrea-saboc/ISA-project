<template>
  <div class="mansion-view" v-if="mansionToShow!=null">
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
      <div v-if="(mansionToShow.InteriorImages==null || mansionToShow.InteriorImages.length==0) && (mansionToShow.ExteriorImages==null || mansionToShow.ExteriorImages.length==0)">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="../assets/mansion.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="../assets/mansion.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="../assets/mansion.jpg" class="d-block w-100" alt="...">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
      <div v-else>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="../assets/port.jpg" class="d-block w-100" alt="...">
          </div>
          <div v-for="(inImg, index) in mansionToShow.InteriorImages" :key="index"  class="carousel-item">
            <img v-bind:src="getImg(inImg)" class="d-block w-100">
          </div>
          <div v-for="(inImg, index) in mansionToShow.ExteriorImages" :key="index"  class="carousel-item">
            <img v-bind:src="getImg(inImg)" class="d-block w-100" style="max-height: 400px">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
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
        <p style="font-size: 18px; font-weight: bold">
          {{mansionToShow.capacity}} people · {{mansionToShow.enginePower}} horsepower · {{mansionToShow.length}} metres length
        </p>
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
        <hr>
        <div class="info">
          <h2><button class="btn btn-lg-link" v-on:click="ShowReservationOffers">Show reservationOffers</button></h2>
        </div>

        <hr>

        <hr v-if="mansionToShow.rules!=null && mansionToShow.rules.length!=0">
        <div class="navigation-equipments" v-if="mansionToShow.rules!=null && mansionToShow.rules.length!=0">
          <p style="font-weight: bolder; font-size: 26px">
            Rules
          </p>
          <div v-if="mansionToShow.rules!=null">
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
    <!-- Modal -->
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

              <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon7">Days</span>
                <input type="number" class="form-control" placeholder="Hours" aria-label="Username" v-model="numberOfHoursQuick" aria-describedby="basic-addon1">
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
import {devServer} from "../../vue.config";
import {ref} from "vue";

export default {
  name: "MansionView",
  data: function () {
    return {
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
              console.log("Logged user in mansion view: ", response.data)
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
          if (this.user == 'Client')
            this.mansionToShow = response.data
          this.CheckClientSubscription()
          console.log("Mansion to show:", this.mansionToShow)
          console.log(this.mansionToShow.address)
          this.address = this.mansionToShow.address
          console.log(response.data)
          this.mansionOwner = this.mansionToShow.mansionOwner
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