<template>
<div class="boat-information-change">
  <div>
    <div v-if="boats!=null && boats.length>0">
      <select class="form-select form-select-lg mb-3 custom-select" style="width: 20%" aria-label=".form-select-lg example" v-model="selectedBoat" v-on:change="changeBoat">
        <option v-for="boat in boats" v-bind:value="boat" :key="boat.id">
          {{boat.name}}
        </option>
      </select>
      <button class=" btn btn-primary" style="margin-left: 1%; margin-bottom: 2%" v-on:click="deleteSelectedBoat()">Delete</button>
    </div>
    <div v-else>
      There is no boat yet!
    </div>
  <div>

    <form>
      <h3 class="mb-3">Change boat informations</h3>
      <div class="col-11">
        <label for="boat-name" class="form-label">Boat name</label>
        <input type="text" class="form-control" id="boat-name" v-model = "name" required>
      </div>
      <div class="double-field">
        <div class="col-5">
          <label for="boat-type" class="form-label">Boat type</label>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <label class="input-group-text" for="inputGroupSelect01">Type</label>
            </div>
            <select class="custom-select" id="inputGroupSelect01" v-model="type">
              <option selected value="">Choose...</option>
              <option value="Motorboat">Motorboat</option>
              <option value="Sailboat">Sailboat</option>
              <option value="RIB">RIB</option>
              <option value="Catamaran">Catamaran</option>
              <option value="Houseboat">Houseboat</option>
              <option value="Jet ski">Jet ski</option>
              <option value="Yacht">Yacht</option>
            </select>
          </div>
        </div>
        <div class="col-3">
          <label for="capacity" class="form-label" >Capacity</label>
          <input type="number" class="form-control" id="capacity" min="1" v-model="capacity" required>
        </div>
        <div class="col-3">
          <label for="lenght" class="form-label" >Lenght</label>
          <input type="number" class="form-control" id="lenght" min="1" v-model="length" required>
        </div>
      </div>
      <hr class="my-3">
      <h5>Engine information</h5>
      <hr class="my-3">
      <div class="double-field">
        <div class="col-3">
          <label for="engine-number" class="form-label">Number of engines</label>
          <input type="number" class="form-control" id="engine-number" min="1" v-model="engineNum" required>
        </div>
        <div class="col-3">
          <label for="engine-power" class="form-label">Engine power</label>
          <input type="number" class="form-control" id="engine-power" min="1" v-model="enginePower" required>
        </div>
        <div class="col-3">
          <label for="max-speed" class="form-label">Max speed</label>
          <input type="number" class="form-control" id="max-speed" min="1" v-model="maxSpeed" required>
        </div>


      </div>
      <hr class="my-3">
      <h5>Navigation equipment</h5>
      <hr class="my-3">
      <div class="double-field">
        <div class="form-check">
          <input type="checkbox" class="form-check-input" id="GPS"  v-model="GPS">
          <label class="form-check-label" for="GPS">GPS</label>
        </div>
        <div class="form-check">
          <input type="checkbox" class="form-check-input" id="radar"  v-model="radar">
          <label class="form-check-label" for="radar">radar</label>
        </div>
        <div class="form-check">
          <input type="checkbox" class="form-check-input" id="VHF"  v-model="VHF">
          <label class="form-check-label" for="VHF">VHF radio</label>
        </div>
        <div class="form-check">
          <input type="checkbox" class="form-check-input" id="fishfinder"  v-model="fishfinder">
          <label class="form-check-label" for="fishfinder">fishfinder</label>
        </div>
      </div>
      <hr class="my-3">
      <h5>Location</h5>

      <hr class="my-3">
      <div class="col-11">
        <label for="address" class="form-label">Address</label>
        <input type="text" class="form-control" id="address" placeholder="1234 Main St" v-model="address" required>
      </div>
      <div class="double-field">
        <div class="col-md-5">
          <label for="country" class="form-label">Country</label>
          <input type="text" class="form-control" id="country" placeholder="United States" v-model="country" required>
        </div>
        <div class="col-md-6">
          <label for="state" class="form-label">City</label>
          <input type="text" class="form-control" id="state" placeholder="1234 Main St" v-model="city" required>
        </div>
      </div>
      <div class="double-field">
        <div class="col-md-5">
          <label for="longitude" class="form-label">Longitude</label>
          <input type="text" class="form-control" id="longitude" placeholder="1223" v-model="longitude" required>
        </div>
        <div class="col-md-5">
          <label for="latitude" class="form-label">Latitude</label>
          <input type="text" class="form-control" id="latitude" v-model="latitude" placeholder="1223" required>
        </div>
      </div>
      <hr class="my-4">
      <div class="col-11">
        <label for="promodesc" class="form-label">Promotional description</label>
        <textarea class="form-control" placeholder="Write a description" id="promodesc" style="height: 100px" v-model="promoDescription"></textarea>
      </div>

      <div class="double-field">
        <div class="mb-3 col-md-5">
          <div >
            <label for="interiorImages" class="form-label">Interior images</label>
            <input class="form-control" type="file" id="interiorImages" multiple @change="onInteriorImagesSelected">
          </div>
          <br>
          <div>
            <div v-for="(image, index) in interiorImagesForFront" :key="(image, index)">
              <button v-on:click="removeImageI(image, index)" class="btn btn-danger rounded-circle removeImageBtn end-0 m-4">X</button>
              <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
            </div>
          </div>
        </div>
        <div class="mb-3 col-md-5">
          <div >
            <label for="exteriorImages" class="form-label">Exterior images</label>
            <input class="form-control" type="file" id="exteriorImages" multiple @change="onExteriorImagesSelected">
          </div>
          <br>
          <div>
            <div v-for="(image, index) in exteriorImagesForFront" :key="(image, index)">
              <button v-on:click="removeImageE(image, index)" class="btn btn-danger rounded-circle removeImageBtn end-0 m-4">X</button>
              <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
            </div>
          </div>
        </div>

      </div>
      <div class="double-field">
      </div>
      <hr class="my-4">
      <div class="col-11">
        <label for="cancellb" class="form-label">Cancellation conditions</label>
        <textarea class="form-control" placeholder="Define cancellation conditions" id="cancellb" style="height: 100px" v-model="cancellationPolicy"></textarea>
      </div>
      <hr class="my-4">
      <h5>Rules</h5>
      <div class="double-field">
        <div class="col-8">
          <input type="text" class="form-control" id="new-rule" placeholder="E.g. Smoking is not allowed" >
        </div>
        <div class="col-3">
          <button type="button" class="btn btn-secondary btn-sm" id="rr" v-on:click="addRule" >Add rule</button>
        </div>
      </div>
      <div class="my-11">
        <label v-for="(rule, index) in rules"
               :key="index">
          {{index+1}}. {{rule}}
        </label>
      </div>
      <hr class="my-4">
      <h5>Additional services</h5>
      <hr class="my-4">
      <div class="double-field">
        <div class="col-5">
          <label for="new-additional-service-name" class="form-label">New service's name</label>
          <input type="text" class="form-control" id="new-additional-service-name" placeholder="Service name" >
        </div>
        <div class="col-2">
          <label for="new-additional-service-hour" class="form-label">Price per hour</label>
          <input type="number" class="form-control" id="new-additional-service-hour">
        </div>
        <div class="col-2">
          <label for="new-additional-service-day" class="form-label">Price per day</label>
          <input type="number" class="form-control" id="new-additional-service-day">
        </div>
        <div class="col-3">
          <br>
          <button type="button" class="btn btn-secondary btn-sm" id="as" v-on:click="addAdditionalService">Add service</button>
        </div>
      </div>
      <hr class="my-4">
      <h5>Renting pricelist</h5>
      <hr class="my-4">
      <div class="double-field">
        <div class="col-3">
          <label for="boat-price-per-hour" class="form-label">Price per hour</label>
          <input type="number" class="form-control" id="boat-price-per-hour" v-model="pricePerHour">
        </div>
        <div class="col-3">
          <label for="boat-price-per-day" class="form-label">Price per day</label>
          <input type="number" class="form-control" id="boat-price-per-day" v-model="pricePerDay">
        </div>
        <div class="col-3">
          <label for="boat-price-per-seven" class="form-label">Price for seven days</label>
          <input type="number" class="form-control" id="boat-price-per-seven" v-model="priceForSevenDays">
        </div>
      </div>
      <hr>
      <button class="w-100 btn btn-primary btn-lg" type="submit" @click="saveChanges">Save changes</button>
    </form>
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
      token: null,
      name : '',
      type : '',
      length : '',
      engineNum : '',
      enginePower : '',
      maxSpeed : '',
      GPS : false,
      radar : false,
      VHF : false,
      fishfinder: false,
      address : '',
      city : '',
      country : '',
      longitude : '',
      latitude : '',
      capacity : '',
      promoDescription : '',
      cancellationPolicy : '',
      imgInter : new Array(),
      imgExter : new Array(),
      rules : new Array(),
      interiorImagesForFront: [],
      exteriorImagesForFront: [],
      selectedExteriorImages : new Array(),
      selectedInteriorImages : new Array(),
      additionalServices : new Array(),
      additionalServiceName : '',
      additionalServicePricePerHour : '',
      additionalServicePricePerDay : '',
      pricePerHour: '',
      pricePerDay: '',
      priceForSevenDays: '',
      selectedBoat: null,
      boats : new Array(),
      possibleToChange: true,
      user: null,

      boatName: ''
    }
  },
  mounted(){
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    this.user = this.$store.state.userType
   // if (this.user!=null && this.user =='BoatOwner'){
      axios.get(devServer.proxy+"/ownersBoats", {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
          .then(response1 => {
            console.log("brodovi vlasnika u change iboat infos", response1.data)
            this.boats = response1.data;
            if(this.boats.length>0){
              this.selectedBoat = this.boats[0];
              this.setFields()
            }

          })
    //}
  },
  methods:{
    saveChanges(){
      alert("Changing boat", this.selectedBoat)
      console.log("selectovani brod", this.selectedBoat, this.selectedBoat.id)
      axios
          .post('http://localhost:8080/changeBoat',
              {
                "id": this.selectedBoat.id,
                "name": this.name,
                "type": this.type,
                "length": this.length,
                "cancellationPolicy" : this.cancellationPolicy,
                "numberOfEngines": this.engineNum,
                "enginePower": this.enginePower,
                "maxSpeed": this.maxSpeed,
                "GPS": this.GPS,
                "radar": this.radar,
                "VHFradio": this.VHF,
                "fishfinder": this.fishfinder,
                "address": this.address,
                "city": this.city,
                "country": this.country,
                "longitude": this.longitude,
                "latitude": this.latitude,
                "promoDescription": this.promoDescription,
                // "InteriorImages": new Array(),
               // "ExteriorImages": this.imgExter,
                "capacity": this.capacity,
                //"rules": this.rules,
                "pricePerHour": this.pricePerHour,
                "pricePerDay": this.pricePerDay,
                "priceForSevenDays": this.priceForSevenDays,
                //"additionalServices": this.additionalServices

              }, {
                headers: {
                  'Authorization' : this.$store.getters.tokenString
                }
              })
          .then(response => {
            alert(response.data)
          })
          .catch(()=>{
            alert("The boat is not possible to change")
          });
    },
    setFields(){
      this.name = this.selectedBoat.name;
      this.type = this.selectedBoat.type;
      this.length = this.selectedBoat.length;
      this.maxSpeed = this.selectedBoat.maxSpeed;
      this.cancellationPolicy = this.selectedBoat.cancellationPolicy;
      this.capacity = this.selectedBoat.capacity;
      this.enginePower = this.selectedBoat.enginePower;
      this.fishfinder = this.selectedBoat.fishfinder;
      this.gps = this.selectedBoat.gps;
      this.engineNum = this.selectedBoat.numberOfEngines;
      this.priceForSevenDays = this.selectedBoat.priceForSevenDays;
      this.pricePerDay = this.selectedBoat.pricePerDay;
      this.pricePerHour = this.selectedBoat.pricePerHour;
      this.promoDescription=this.selectedBoat.promoDescription;
      this.radar = this.selectedBoat.radar;
      this.address = this.selectedBoat.address.address;
      this.city = this.selectedBoat.address.city;
      this.country = this.selectedBoat.address.country;
      this.longitude = this.selectedBoat.address.longitude;
      this.latitude = this.selectedBoat.address.latitude;
    },
    changeBoat(){
      console.log("selectovani brod", this.selectedBoat)
      alert(this.selectedBoat.id)
      this.boatName=this.selectedBoat.name;
      this.setFields();
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

.boat-registration{

  width: 60%;
  horiz-align: center;
  margin-left: 20%;
  margin-top: 2%;
  border-radius: 3%;
  padding: 3%;
  background-blend-mode: lighten;
}
.double-field{
  display: flex;
  flex-direction: row;
}

.double-field .form-check{
  margin-left: 0.5%;
}

.boat-registration .form-label{
}

</style>