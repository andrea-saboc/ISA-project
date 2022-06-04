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
      <div>
        <hr class="my-3">
        <h5>Fishing equipment</h5>
        <hr class="my-3">
        <div class="double-field">
          <div class="col-5">
            <label for="new-fe-name" class="form-label">New fishing equipment</label>
            <input type="text" class="form-control" id="new-fe-name" placeholder="Equipment name" >
          </div>
          <div class="col-3">
            <br>
            <button type="button" class="btn btn-secondary btn-sm" id="fe" v-on:click="addFishingEquipment()">Add equipment</button>
          </div>
        </div>
        <div v-if="fishingEquipment.length>0">
          <table class="table table-striped">
            <thead>
            <tr>
              <th></th>
              <th>Equipment</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(as, index) in fishingEquipment" :key="(as, index)">
              <th>{{ index +1}}</th>
              <th>{{ as.equipment }}</th>
              <th><button type="button" v-on:click="removeEquipment(index)" >x</button></th>
            </tr>
            </tbody>
          </table>
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
          <label for="latitude" class="form-label">Latitude</label>
          <input type="text" class="form-control" id="latitude" v-model="latitude" placeholder="1223" required>
        </div>
        <div class="col-md-5">
          <label for="longitude" class="form-label">Longitude</label>
          <input type="text" class="form-control" id="longitude" placeholder="1223" v-model="longitude" required>
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
      fishingEquipment: new Array(),
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
      priceForSevenDays: ''

    }
    },
  methods:{
    addFishingEquipment(){
      var equipment = document.getElementById('new-fe-name').value;
      this.fishingEquipment.push({equipment: equipment});
      document.getElementById('new-fe-name').value='';
    },
    removeEquipment( index){
      this.fishingEquipment.splice(index, 1)

    },
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
      for(var img of event.target.files){
        this.exteriorImagesForFront.push(
            {
              path: URL.createObjectURL(img)
            }
        );
      }
      this.selectedExteriorImages = event.target.files
      console.log("selected exterior image length is", this.selectedExteriorImages)
      for (var imgg in this.selectedExteriorImages){
        console.log(isNaN(imgg))
        if (isNaN(imgg)) {
          return;
        }
        console.log("before pushig file index:", imgg)
        this.onUpload(this.selectedExteriorImages[imgg])
      }
    },
    onInteriorImagesSelected(event){
      this.selectedInteriorImages = event.target.files
      for (var img of event.target.files) {
        console.log("tu sam", img)
        this.interiorImagesForFront.push(
            {
              path: URL.createObjectURL(img)
            }
        );
      }
      console.log(this.selectedInteriorImages)
      //const reader = new FileReader()
      console.log("selected exterior image length is", this.selectedInteriorImages)
      for (var imgg in this.selectedInteriorImages){
        console.log(isNaN(imgg))
        if (isNaN(imgg)) {
          return;
        }
        console.log("before pushig file index:", imgg)
        this.onUploadI(this.selectedInteriorImages[imgg])
      }

    },
    onUploadI: function(file){ //images for backend create base64 Image
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
    removeImageI: function(image, index){
      this.interiorImagesForFront.splice(index, 1)
      this.imgInter.splice(index, 1)
    },
    removeImageE: function(image, index){
      this.exteriorImagesForFront.splice(index, 1)
      this.imgExter.splice(index, 1)
    },
    registerBoat(){
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
            "additionalServices": this.additionalServices,
            "fishingEquipments": this.fishingEquipment

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