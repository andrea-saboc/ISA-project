<template>
  <div class="mansion-registration">
    <form>
      <h3 class="mb-3">Mansion registration</h3>
      <div class="col-11">
        <label for="mansion-name" class="form-label">Mansion name</label>
        <input type="text" class="form-control" id="mansion-name" placeholder="E.g. San Victoria Motel" v-model = "name" required>
      </div>
      <div class="double-field">
        <div class="col-4">
          <label for="number_rooms" class="form-label">Number of rooms</label>
          <input type="number" class="form-control" id="number_rooms"  v-model = "numberOfRooms" required>
        </div>
        <div class="col-4">
          <label for="bed_per_room" class="form-label">Beds per room</label>
          <input type="number" class="form-control" id="bed_per_room" v-model = "numberOfBeds" required>

        </div>
        <div class="col-3">
          <button type="button" class="btn btn-secondary btn-sm" id="room-adding-button" v-on:click="addRoom ">Add room</button>
        </div>
      </div>
      <div v-for="(key, value) in rooms" :key="value">
        {{ key[0]}} bed - {{ key[1] }} rooms
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
          <div>
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
          <div>
            <label for="exteriorImages" class="form-label">Exterior images</label>
            <input class="form-control" type="file" id="exteriorImages" multiple @change="onExteriorImagesSelected">
          </div>
          <br>
        </div>
        <div>
          <div v-for="(image, index) in exteriorImagesForFront" :key="(image, index)">
            <button v-on:click="removeImageE(image, index)" class="btn btn-danger rounded-circle removeImageBtn end-0 m-4">X</button>
            <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
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
          <label for="boat-price-per-day" class="form-label">Price per day</label>
          <input type="number" class="form-control" id="boat-price-per-day" v-model="pricePerDay">
        </div>
        <div class="col-3">
          <label for="boat-price-per-seven" class="form-label">Price for seven days</label>
          <input type="number" class="form-control" id="boat-price-per-seven" v-model="priceForSevenDays">
        </div>
      </div>
      <hr>
      <button class="w-100 btn btn-primary btn-lg" type="submit" @click="registerMansion">Register a mansion</button>
    </form>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MansionRegistration",
  data(){
    return {
      token: null,
      name: '',
      address : '',
      numberOfBeds: 0,
      numberOfRooms: 0,
      rooms: new Map(),
      roomsRequest: new Array(),
      city : '',
      country : '',
      longitude : '',
      latitude : '',
      promoDescription : '',
      cancellationPolicy : '',
      rules : new Array(),
      selectedExteriorImages : new Array(),
      selectedInteriorImages : new Array(),
      imgInter : new Array(),
      imgExter : new Array(),
      interiorImagesForFront: [],
      exteriorImagesForFront: [],
      additionalServices : new Array(),
      additionalServiceName : '',
      additionalServicePricePerDay : '',
      pricePerDay: '',
      priceForSevenDays: ''
    }
  },
  mounted() {
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);

  },
  methods:{
    addRoom(){
      if(this.rooms.has(this.numberOfBeds))
        this.rooms.set(this.numberOfBeds, this.rooms.get(this.numberOfBeds)+this.numberOfBeds)

      else
        this.rooms.set(this.numberOfBeds, this.numberOfRooms)

    },
    addRule(){
      var rule = document.getElementById('new-rule').value;
      this.rules.push(rule)
      document.getElementById('new-rule').value='';
    },
    addAdditionalService(){
      var name = document.getElementById('new-additional-service-name').value;
      var day = document.getElementById('new-additional-service-day').value;
      this.additionalServices.push({name: name, pricePerDay: day });
      document.getElementById('new-additional-service-name').value='';
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
    removeImageI: function(image, index){
      this.interiorImagesForFront.splice(index, 1)
      this.imgInter.splice(index, 1)
    },
    removeImageE: function(image, index){
      this.exteriorImagesForFront.splice(index, 1)
      this.imgExter.splice(index, 1)
    },
    registerMansion(){
      console.log("Registering a mansion "+this.name)
      var roomArray = new Array();
      for ( const [key, value] of this.rooms){
        for (let i = 0; i<value; i++){
          roomArray.push(key)
        }
      }
      alert(roomArray.length)
      axios
          .post('http://localhost:8080/registerMansion',
              {
                "name": this.name,
                "cancellationPolicy" : this.cancellationPolicy,
                "address": this.address,
                "city": this.city,
                "country": this.country,
                "longitude": this.longitude,
                "latitude": this.latitude,
                "promoDescription": this.promoDescription,
                "InteriorImages": this.imgInter,
                "ExteriorImages": this.imgExter,
                "pricePerDay": this.pricePerDay,
                "priceForSevenDays": this.priceForSevenDays,
                "additionalServices": this.additionalServices,
                "rooms": roomArray,
                "rules": this.rules

              }, {
                headers: {
                  'Authorization' : 'Bearer ' + this.token,
                }
              })
          .then(response => {
            alert(response.data)
            this.messege = response.data
          })
      .catch((error) => {
        alert("Error occured while trying to register!")
        console.log("Registering mansion error", error)
      });

    }
  }
}
</script>


<style scoped>

.mansion-registration{

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

.mansion-registration .form-label{
}

</style>