<template>
<div class="boat-view" v-if="boatToShow!=null">
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">>
    <div v-if="(boatToShow.InteriorImages==null || boatToShow.InteriorImages.length==0) && (boatToShow.ExteriorImages==null || boatToShow.ExteriorImages.length==0)">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="../assets/yacht.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100" alt="...">
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
        <div v-for="(inImg, index) in boatToShow.InteriorImages" :key="index"  class="carousel-item">
          <img v-bind:src="getImg(inImg)" class="d-block w-100">
        </div>
        <div v-for="(inImg, index) in boatToShow.ExteriorImages" :key="index"  class="carousel-item">
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
    {{boatToShow.type}}
  </p>
  <p class="fw-bold" style="font-size: 30px; font-weight: bolder; text-transform: uppercase">
    {{boatToShow.name}} · {{boatToShow.avgGrade}} <i class="fa fa-star" aria-hidden="true"></i>


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
      <div v-if="clientSubscribed != 'true'">
        <button  class="btn btn-primary" v-on:click="SubscribeClient" v-if="loggedUser.role!=null && loggedUser.role=='ROLE_CLIENT'"><i class="fa fa-star-o" aria-hidden="true"></i>
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
          <h5 style="margin-top: 5%; font-weight: bolder">{{boatToShow.pricePerHour}}€ per hour · {{boatToShow.pricePerDay}}€ per day</h5>

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
    <br>
    <br>
  </div>
  <hr>
    <div class="info">
      <h2><button class="btn btn-lg-link" v-on:click="ShowReservationOffer">Show reservationOffers</button></h2>
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
          <div class="einfo" style="align-content: center">
            <div class="navigation-icon" style="align-content: center">
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-speedometer" viewBox="0 0 16 16">
                <path d="M8 2a.5.5 0 0 1 .5.5V4a.5.5 0 0 1-1 0V2.5A.5.5 0 0 1 8 2zM3.732 3.732a.5.5 0 0 1 .707 0l.915.914a.5.5 0 1 1-.708.708l-.914-.915a.5.5 0 0 1 0-.707zM2 8a.5.5 0 0 1 .5-.5h1.586a.5.5 0 0 1 0 1H2.5A.5.5 0 0 1 2 8zm9.5 0a.5.5 0 0 1 .5-.5h1.5a.5.5 0 0 1 0 1H12a.5.5 0 0 1-.5-.5zm.754-4.246a.389.389 0 0 0-.527-.02L7.547 7.31A.91.91 0 1 0 8.85 8.569l3.434-4.297a.389.389 0 0 0-.029-.518z"/>
                <path fill-rule="evenodd" d="M6.664 15.889A8 8 0 1 1 9.336.11a8 8 0 0 1-2.672 15.78zm-4.665-4.283A11.945 11.945 0 0 1 8 10c2.186 0 4.236.585 6.001 1.606a7 7 0 1 0-12.002 0z"/>
              </svg>
            </div>
            <div class="navigation-name" style="align-content: center">
              <p style="font-weight: bold; align-self: center">
                Speed
              </p>
              <p style="">
                {{boatToShow.maxSpeed}} km/h
              </p>
            </div>
          </div>
          <div class="einfo" style="align-content: center">
            <div class="navigation-icon" style="align-content: center">
              <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
              </svg>
            </div>
            <div class="navigation-name" style="align-content: center">
              <p style="font-weight: bold; align-self: center">
                Number of engines
              </p>
              <p style="align-self: center;" >
                {{boatToShow.numberOfEngines}}
              </p>
            </div>
          </div>

        </div>

      </div>
      <hr v-if="boatToShow.rules!=null && boatToShow.rules.length!=0">
      <div class="navigation-equipments" v-if="boatToShow.rules!=null && boatToShow.rules.length!=0">
        <p style="font-weight: bolder; font-size: 26px">
          Rules
        </p>
        <div v-if="boatToShow.rules!=null">
          <div v-for="rule in boatToShow.rules" :key="rule.ruleId">
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
      <button v-if="loggedUser!=null && boatToShow.boatOwner.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#exampleModal">Add availability period</button>
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Add availability for {{boatToShow.name}}</h5>
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
      <button v-if="loggedUser!=null && boatToShow.boatOwner.id==loggedUser.id" type="button" class="btn btn-primary" data-bs-toggle="modal" style="margin: 0.5%" data-bs-target="#addQuickResModal">Add new quick reservation</button>
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
      <div v-if="(boatToShow.InteriorImages!=null && boatToShow.InteriorImages.length!=0) || (boatToShow.ExteriorImages!=null && boatToShow.ExteriorImages.length>0)">
      <hr>
      <p style="font-weight: bolder; font-size: 26px">
        Images
      </p>
        <div class="gallery">
          <figure v-for="inImg in boatToShow.InteriorImages" :key="inImg.id" class="gallery__item galleritem-1">
          <img v-bind:src="getImg(inImg)" class="gallery-img" alt="Image 1">
          </figure>
          <figure v-for="inImg in boatToShow.ExteriorImages" :key="inImg.id" class="gallery__item galleritem-1">
            <img v-bind:src="getImg(inImg)" class="gallery-img" alt="Image 1">
          </figure>
        </div>
      </div>
<hr>
    </div>
    <div class="coladd-reservation" v-if="loggedUser!=null && boatToShow.boatOwner!=null && loggedUser.id==boatToShow.boatOwner.id">
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
            <input type="number" class="form-control" min="1" max="{{boatToShow.capacity}}" v-model="clientResNumberOfGuests"  aria-label="Username" aria-describedby="basic-addon1">
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
import {ref} from "vue";
import axios from 'axios'
import {devServer} from "../../vue.config";
export default {
  name: "BoatView",
  data: function (){
    return{
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
      clientSubscribed: '',
      startDateTime: '',
      endDateTime: '',
      boatToShow: [],
      address: [],
      boatOwner: [],
      value: '',
      availablePeriods: [],
      availableDates: [],
      additionalServices : [],
      boatReservations : [],
      reservedDates : [],
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
    var path = window.location.href;
    var boatId = path.split('/boat/')[1].replaceAll('%20', ' ');
    console.log("Bpat view --> boat id: ", boatId.toString())
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : this.$store.getters.tokenString
      }
    })
        .then(response => {
          console.log("Logged user in boat view: ", response.data)
          this.loggedUser = response.data
        }
        ).catch(() =>{
          this.loggedUser = null
    })
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
    .then(response => {
      this.boatToShow = response.data
      this.CheckClientSubscription(this.boatToShow)
      console.log("Boat to show:", this.boatToShow)
      console.log(this.boatToShow.address)
      this.address = this.boatToShow.address
      console.log(response.data)
      this.boatOwner = this.boatToShow.boatOwner
      axios
          .post(devServer.proxy + "/getBoatAvailability", {
            "boatId": this.boatToShow.id
          }, {
            headers: {
              'Authorization': this.$store.getters.tokenString
            }
          })
          .then(response => {
            this.availablePeriods = response.data
            console.log("Available periods for boat: ", this.availablePeriods)
            axios.get(devServer.proxy + "/additionalServices", {
              params:
                  {
                    id: boatId
                  },
              headers: {
                'Authorization': this.$store.getters.tokenString
              }
            }).then(resp => {
              this.additionalServices = resp.data
              axios.get(devServer.proxy + "/getReservedDatesForBoat", {
                params:
                    {
                      boatId: boatId
                    },
                headers: {
                  'Authorization': this.$store.getters.tokenString
                }
              }) .then((resp1 => {
                this.boatReservations = resp1.data
                console.log('Boat reservations:', this.boatReservations)
                this.calculateAvailableDaysForCalendar()
              }))
            })
          }
          )
    }
    )
    .catch(()=>{
      console.log("The is a trouble")
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
      for(var reservation in this.boatReservations){
        var startDateRes = new Date(this.boatReservations[reservation].startDate)
        var endDateRes = new Date(this.boatReservations[reservation].endDate)
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
      .get(devServer.proxy + "/getBoatDiscountReservations", {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        },
        params:{
          "boatId" : this.boatToShow.id
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
              end: { fillMode: 'solid', color: 'teal' },

            },
            dates: { start: startDate, end: endDate },
          })
        }
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
        console.log(this.calendar_attributes)
      })

    },
    addAvailabilityPeriod(){
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

    },
    getImg(image) {
      return devServer.proxy + "\\" + image.img;
    },
    ShowReservationOffer(){
      window.location.href = "/boatReservationOffers/" + this.boatToShow.id.toString();
    },
    checkEmail(){
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
    },
    makeReservationForClient(){
      axios.post(devServer.proxy+ "/makeBoatReservationClient", {
          email : this.clientResEmail,
          additionalServiceSet : this.clientResAdditionalServices,
          startDate : this.clientResStartDate,
          days : this.clientResNumberOfDays,
          hours : this.clientResNumberOfHours,
          "boatId" : this.boatToShow.id,
          numberOfGuests : this.clientResNumberOfGuests
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString,
          'Content-Type': 'application/json'
        }
      })
      .then(response =>
      alert(response.data))
    },
    addQuickReservation(){
      axios.post(devServer.proxy + "/createDiscountBoatReservation", {
        "boatId" : this.boatToShow.id,
        "startDate" : this.startDateTimeQuick,
        "days" : this.numberOfDaysQuick,
        "hours" : this.numberOfHoursQuick,
        "numberOfGuests" : this.numberOfGuestsQuick,
        "priceWithDiscount": this.priceQuick
      }, {
        headers: {
          'Authorization': this.$store.getters.tokenString,
          'Content-Type': 'application/json'
        }
      })
      this.calculateAvailableDaysForCalendar()
    }

  },

}


</script>

<style scoped>
.boat-view{
  horiz-align: center;
  width: 80%;
  margin-left: 15%;
  height: 100%;
  padding-bottom: 5%;
}

.boat-view .top-slider{
  height: 550px;
}

.additional-desc{
  display: flex;
  flex-direction: row;
  margin-bottom: 2%;
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