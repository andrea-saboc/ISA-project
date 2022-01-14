<template class="tem">
  <div class="boat-registration">

    <form>
      <h3 class="mb-3">Boat registration</h3>
      <div class="col-11">
        <label for="boat-name" class="form-label">Boat name</label>
        <input type="text" class="form-control" id="boat-name" placeholder="E.g. Titanic" v-model = "name" required>
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
          <label for="interiorImages" class="form-label">Interior images</label>
          <input class="form-control" type="file" id="interiorImages" multiple @change="onInteriorImagesSelected">
        </div>
        <div class="mb-3 col-md-5">
          <label for="exteriorImages" class="form-label">Exterior images</label>
          <input class="form-control" type="file" id="exteriorImages" multiple @change="onExteriorImagesSelected">
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
      <button class="w-100 btn btn-primary btn-lg" type="submit" @click="registerBoat">Register a boat</button>
    </form>

  </div>


</template>

<script>
import axios from 'axios'
export default {
  name: "BoatRegistration",
  data(){
    return {
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
      selectedExteriorImages : new Array(),
      selectedInteriorImages : new Array(),
      additionalServices : new Array(),
      additionalServiceName : '',
      additionalServicePricePerHour : '',
      additionalServicePricePerDay : '',
      pricePerHour: '',
      pricePerDay: '',
      priceForSevenDays: ''

    }
    },
  methods:{
    addRule(){
      var rule = document.getElementById('new-rule').value;
      this.rules.push(rule)
      document.getElementById('new-rule').value='';
    },
    addAdditionalService(){
      var name = document.getElementById('new-additional-service-name').value;
      var hour = document.getElementById('new-additional-service-hour').value;
      var day = document.getElementById('new-additional-service-day').value;
      this.additionalServices.push({name: name, pricePerHour: hour, pricePerDay: day });
      document.getElementById('new-additional-service-name').value='';
      document.getElementById('new-additional-service-hour').value='';
      document.getElementById('new-additional-service-day').value='';

    },
    onExteriorImagesSelected(event){
      this.imgExter = new Array()
      console.log(typeof(this.imgExter))
      console.log(event)
      this.selectedExteriorImages = event.target.files
      console.log(this.selectedExteriorImages)
      //const reader = new FileReader()
      console.log("selected exterior image length is", this.selectedExteriorImages)
      for (var img in this.selectedExteriorImages){
        console.log(isNaN(img))
          if (isNaN(img)) {
            return;
          }

          console.log("before pushig file index:", img)
          this.onUpload(this.selectedExteriorImages[img])
      }

    },
    onUpload: function(file){
      console.log(this.imgExter)
      console.log("In onUpload, file:", file)
      console.log("Type of the file is", file.type)
      const reader= new FileReader();
      reader.onload = (e) =>{
        console.log("e:",e.target.result)
        this.imgExter.push(e.target.result)
      }
      reader.readAsDataURL(file);
    },
    onInteriorImagesSelected(event){
      this.imgExter = new Array()
      console.log(typeof(this.imgInter))
      console.log(event)
      this.selectedInteriorImages = event.target.files
      console.log(this.selectedInteriorImages)
      //const reader = new FileReader()
      console.log("selected exterior image length is", this.selectedInteriorImages)
      for (var img in this.selectedInteriorImages){
        console.log(isNaN(img))
        if (isNaN(img)) {
          return;
        }

        console.log("before pushig file index:", img)
        this.onUploadI(this.selectedInteriorImages[img])
      }

    },
    onUploadI: function(file){
      console.log(this.imgInter)
      console.log("In onUpload, file:", file)
      console.log("Type of the file is", file.type)
      const reader= new FileReader();
      reader.onload = (e) =>{
        console.log("e:",e.target.result)
        this.imgInter.push(e.target.result)
      }
      reader.readAsDataURL(file);
    },
    registerBoat(){
      alert("Registering a boat")
      alert(this.name)
      alert(this.type)
      axios
      .post('http://localhost:8080/registerBoat',
          {
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
            "InteriorImages": this.imgInter,
            "ExteriorImages": this.imgExter,
            "capacity": this.capacity,
            "rules": this.rules,
            "pricePerHour": this.pricePerHour,
            "pricePerDay": this.pricePerDay,
            "priceForSevenDays": this.priceForSevenDays,
            "additionalServices": this.additionalServices

          }, {
            headers: {
              'Authorization' : this.$store.getters.tokenString
            }
          })
          .then(response => {
            alert(response.data)
            this.messege = response.data
          });

    }
  }

}
</script>

<style scoped>
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