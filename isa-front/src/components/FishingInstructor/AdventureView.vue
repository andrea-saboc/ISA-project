<template>
<div class="mansion-view">
<br>
<br>
<br>
<br>
  <div class="row">
    <div class="colinfo">
  <br class="sm">
  <p style="text-transform: uppercase; font-size: 18px; color: gray">
    {{adventureToShow.type}}
  </p>
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
    <br>
    <br>
  </div>

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


</div>
</div>
</div>
</template>


<script>

import axios from 'axios'
export default {
  name: "AdventureView",
  data: function (){
    return{
      loggedUser: null,
      user: null,
      adventureToShow: [],
      address: [],
      fishingInstructor: [],
      token:null,
      imagesAll: []
    }

  },
   computed: {
  now: function () {
    return this.user
    }
  },
  mounted() {


      this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   console.log('toke je',this.token)
    var path = window.location.href;
     console.log(" Path je ", path)
    var adventureId = path.split('/adventure/')[1].replaceAll('%20', ' ');
    console.log("Bpat view --> adventureId id: ", adventureId.toString())

    axios.get("http://localhost:8080/userData", {
      headers: {
        'Authorization' :  'Bearer ' + this.token,
      }
    })
        .then(response => {
          this.loggedUser = response.data
          console.log("ULOGOVANI KORISNIK JE ", this.loggedUser)
          axios
         .get('http://localhost:8080/adventure', {
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
       console.log("Boat to show:", this.adventureToShow)
      this.address = this.adventureToShow.address
      console.log(response.data)
      this.fishingInstructor = this.adventureToShow.fishingInstructor
      for(var i=0;i<this.adventureToShow.images.length;i++){
          this.setImg(this.adventureToShow.images[i]);
      
    }
    console.log(this.imagesAll)
     
    })
    .catch(()=>{
      console.log("The is a trouble")
    })
   }
        ).catch(() =>{
          this.loggedUser = null
           console.log("POSTOJI PROBLEM")
    })
   
  },
  methods:
  {
    setImg: function(image){
      console.log('Image path je ',image.path)
      axios.get('http://localhost:8080/entityImage/'+image.path, {
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