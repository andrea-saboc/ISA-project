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
          <div>
            <div >
              <label for="interiorImages" class="form-label">Interior images</label>
              <input class="form-control" type="file" id="interiorImages" multiple @change="onInteriorImagesSelected">
            </div>
            <br>
            <div>
              <table>
                <tbody>
                <tr v-for="(image, index) in allInteriorImages" :key="(image, index)">
                    <td>
                      <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
                    </td>
                  <td>
                    <button v-on:click="removeImageI(image, index)" type="button">x</button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="mb-3 col-md-5">
          <div>
            <label for="exteriorImages" class="form-label">Exterior images</label>
            <input class="form-control" type="file" id="exteriorImages" multiple @change="onExteriorImagesSelected">
          </div>
          <br>
          <div>
            <table>
              <tbody>
              <tr v-for="(image, index) in allExteriorImages" :key="(image, index)">
                <td>
                  <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
                </td>
                <td>
                  <button v-on:click="removeImageE(image, index)" type="button">x</button>
                </td>
              </tr>
              </tbody>
            </table>
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
      <div v-if="rules.length>0">
        <table class="table table-striped">
          <thead>
          <tr>
            <th></th>
            <th>Rule</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(r, index) in rules" :key="(r, index)">
            <th>{{ index +1}}</th>
            <th>{{ r.rule }}</th>
            <th><button type="button" v-on:click="removeRule(r, index)" >x</button></th>
          </tr>
          </tbody>
        </table>
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
          <button type="button" class="btn btn-secondary btn-sm" id="as" v-on:click="addAdditionalService()">Add service</button>
        </div>
      </div>
      <div v-if="additionalServiceNew.length>0">
        <table class="table table-striped">
            <thead>
              <tr>
                  <th></th>
                <th>Name</th>
                <th> Price per day</th>
                <th>Price per hour</th>
                <th></th>
              </tr>
            </thead>
          <tbody>
            <tr v-for="(as, index) in additionalServiceNew" :key="(as, index)">
              <th>{{ index +1}}</th>
              <th>{{ as.name }}</th>
              <th>{{ as.pricePerDay }}</th>
              <th>{{ as.pricePerHour }}</th>
              <th><button type="button" v-on:click="removeAdditionalService(as, index)" >x</button></th>
            </tr>
          </tbody>
        </table>
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
      <button class="w-100 btn btn-primary btn-lg" type="button" @click="saveChanges">Save changes</button>
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
      rulesToDelete : new Array(),
      selectedExteriorImages : new Array(),
      selectedInteriorImages : new Array(),
      additionalServices : new Array(),
      additionalServiceNew : new Array(),
      additionalServicesDeleted : new Array(),
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
      allExteriorImages: new Array(),
      allInteriorImages: new Array(),
      interiorImagesOldNum: 0,
      exteriorImagesOldNum: 0,
      deleteOldExterior: new Array(),
      deleteOldInterior: new Array(),
      boatName: ''
    }
  },
  mounted(){
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    this.user = this.$store.state.userType
   // if (this.user!=null && this.user =='BoatOwner'){
      axios.get(devServer.proxy+"/ownersBoats", {
        headers: {
          'Authorization' : 'Bearer ' + this.token
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
                "InteriorImages": this.imgInter,
                "ExteriorImages": this.imgExter,
                "InteriorImagesToDelete": this.deleteOldInterior,
                "ExteriorImagesToDelete": this.deleteOldExterior,
                "capacity": this.capacity,
                "rules": this.rules,
                "rulesToDelete" : this.rulesToDelete,
                "pricePerHour": this.pricePerHour,
                "pricePerDay": this.pricePerDay,
                "priceForSevenDays": this.priceForSevenDays,
                "additionalServices": this.additionalServiceNew,
                "deleteAdditionalServices": this.additionalServicesDeleted

              }, {
                headers: {
                  'Authorization' : 'Bearer ' + this.token,
                }
              })
          .then(response => {
            alert(response.data)
          })
          .catch(()=>{
            alert("The boat is not possible to change")
          });
    },
    setImg(img){
      axios.get('http://localhost:8080/entityImage/'+img.path, {
        headers: {
          'Authorization': 'Bearer ' + this.token,
        },
        responseType: 'arraybuffer',
      })
          .then(response => {
            console.log('response sa backa', response)
            console.log("Id of the exterior image is ", img.id)
            let base64ImageString = Buffer.from(response.data, 'binary').toString('base64')
            let srcValue = "data:image/png;base64," + base64ImageString
            this.allExteriorImages.push({
              id: img.id,
              path: srcValue
            })

          })
          .catch(() => {
            console.log("There is a trouble")
          })
    },
    setImgI(img){
      axios.get('http://localhost:8080/entityImage/'+img.path, {
        headers: {
          'Authorization': 'Bearer ' + this.token,
        },
        responseType: 'arraybuffer',
      })
          .then(response => {
            console.log('response sa backa', response)
            console.log("Id of the exterior image is ", img.id)
            let base64ImageString = Buffer.from(response.data, 'binary').toString('base64')
            let srcValue = "data:image/png;base64," + base64ImageString
            this.allInteriorImages.push({
              id: img.id,
              path: srcValue
            })

          })
          .catch(() => {
            console.log("There is a trouble")
          })
    },
    setImages() {
      for (var eimg of this.selectedBoat.exteriorImages) {
        this.setImg(eimg);

      }
      console.log(this.selectedBoat.interiorImages.length)
      for(var iimg of this.selectedBoat.interiorImages){
        console.log('Image path je ', iimg.toString().replaceAll('\\','/'))
        console.log(devServer.proxy + "/entityImage/"+"/" + iimg.path)
        this.setImgI(iimg);

      }
      console.log("all exterior images", this.allExteriorImages);
      console.log("all interior images", this.allInteriorImages);

    },
    setAdditionalServices() {
      axios.get(devServer.proxy + "/additionalServices", {
        params:
            {
              id: this.selectedBoat.id
            },
        headers: {
          'Authorization': 'Bearer ' + this.token,
        }
      })
          .then(response1 => {
            console.log("Additional services", response1.data)
            this.additionalServices = response1.data;
            console.log("Setting additional services", this.additionalServices.length)
            for(var as of this.additionalServices) {
              this.additionalServiceNew.push({
                id : as.id,
                name : as.name,
                pricePerDay : as.pricePerDay,
                pricePerHour : as.pricePerHour
              })
            }

          })
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
      this.GPS = this.selectedBoat.gps;
      this.VHF = this.selectedBoat.vhfradio;
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
      this.additionalServices = new Array();
      this.additionalServiceNew = new Array();
      this.additionalServicesDeleted = new Array();
      this.rules =this.selectedBoat.rules;
      this.rulesToDelete = new Array();
      this.allExteriorImages = new Array();
      this.allInteriorImages= new Array();
      this.interiorImagesOldNum= 0;
      this.exteriorImagesOldNum= 0;
      this.deleteOldExterior=  new Array();
      this.deleteOldInterior= new Array();
      this.interiorImagesOldNum = this.selectedBoat.interiorImages.length;
      this.exteriorImagesOldNum = this.selectedBoat.exteriorImages.length;
      this.selectedExteriorImages = new Array();
      this.selectedInteriorImages = new Array();
      this.setAdditionalServices();
      this.setImages();

    },
    changeBoat(){
      console.log("selectovani brod", this.selectedBoat)
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
        alert(response.data)
        axios.get(devServer.proxy + "/ownersBoats", {
          headers: {
            'Authorization': this.$store.getters.tokenString
          }
        })
            .then(response1 => {
              console.log("brodovi vlasnika nakon brisanja", response1.data)
              this.boats = response1.data;
              if (this.boats.length > 0) {
                this.selectedBoat = this.boats[0];
                this.setFields()
              }

            })
      })
      .catch(()=>{
        alert("The boat is not possible to be deleted")
      })
    },
    removeAdditionalService(as, index){
      console.log("To delete", as.id)
      this.additionalServiceNew.splice(index, 1);
      if(as.id!= -1) {
        alert("in if")
        this.additionalServicesDeleted.push(as.id);
        console.log("To delete ", this.additionalServicesDeleted)
      }
      alert("refresh??")
    },
    addAdditionalService(){
      var name = document.getElementById('new-additional-service-name').value;
      var hour = document.getElementById('new-additional-service-hour').value;
      var day = document.getElementById('new-additional-service-day').value;
      this.additionalServiceNew.push({id: -1, name: name, pricePerHour: hour, pricePerDay: day });
      document.getElementById('new-additional-service-name').value='';
      document.getElementById('new-additional-service-hour').value='';
      document.getElementById('new-additional-service-day').value='';

    },
    removeRule(r, index){
      this.rules.splice(index,1);
      if(r.ruleId!=-1)
      this.rulesToDelete.push(r.ruleId);
      console.log("after deleting: ", this.rules);
    },
    addRule(){
      var ruleText = document.getElementById('new-rule').value;
      this.rules.push({
        ruleId : -1,
        rule : ruleText
      })
      document.getElementById('new-rule').value='';
    },
    onExteriorImagesSelected(event){
      for(var img of event.target.files){
        this.allExteriorImages.push(
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
        this.allInteriorImages.push(
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
      this.allInteriorImages.splice(index, 1)
          if (index<this.interiorImagesOldNum){
            this.interiorImagesOldNum--;
            console.log("image",image)
            this.alert("image id:",image.id)
            this.deleteOldInterior.push(image.id); //kako da dodjem do id slike?
          } else{
            let indexToDelete = index-this.interiorImagesOldNum;
            this.imgInter.splice(indexToDelete, 1);
          }
    },
    removeImageE: function(image, index){
      this.allExteriorImages.splice(index, 1)
      console.log("Exterior image index is ", index, ", and old images num", this.exteriorImagesOldNum, "and id: ", image.id)
          if (index<this.exteriorImagesOldNum){
            console.log("here 1")
            this.exteriorImagesOldNum--;
            console.log("here 2")
            console.log("image",image)
            this.deleteOldExterior.push(image.id);
            console.log("Deleting old ", this.deleteOldExterior)
          }else{
            let indexToDelete = index-this.exteriorImagesOldNum;
            console.log("Deleting a newly added image at index: ",indexToDelete, "there ara", this.imgExter.length )
            this.imgExter.splice(indexToDelete,1)
          }
    },
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