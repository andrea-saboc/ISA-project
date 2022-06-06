<template>
<div class="mansion-information-change">
  <div>
    <div v-if="mansions!=null && mansions.length>0">
      <select class="form-select form-select-lg mb-3 custom-select" style="width: 20%" aria-label=".form-select-lg example" v-model="selectedMansion" v-on:change="changeMansion">
        <option v-for="mansion in mansions" v-bind:value="mansion" :key="mansion.id">
          {{mansion.name}}
        </option>
      </select>
      <button class=" btn btn-primary" style="margin-left: 1%; margin-bottom: 2%" v-on:click="deleteSelectedMansion()">Delete</button>
    </div>
    <div v-else>
      There is no mansion yet!
    </div>
  </div>
  <div>
    <form>
      <h3 class="mb-3">Change mansion</h3>
      <div class="col-11">
        <label for="mansion-name" class="form-label">Mansion name</label>
        <input type="text" class="form-control" id="mansion-name" v-model = "name" required>
      </div>
      <div class="double-field" style="margin-top: 2%; margin-bottom: 2%">
        <div class="col-4">
          <label for="number_rooms" class="form-label">Number of rooms</label>
          <input type="number" class="form-control" id="number_rooms"  v-model = "numberOfRooms" required>
        </div>
        <div class="col-4">
          <label for="bed_per_room" class="form-label">Beds per room</label>
          <input type="number" class="form-control" id="bed_per_room" v-model = "numberOfBeds" required>

        </div>
        <div class="col-3">
          <button type="button" style="margin-top: 15%" class="btn btn-secondary btn-sm" id="room-adding-button" v-on:click="addRoom ">Add room</button>
        </div>
      </div>
        <table class="table table-striped">
          <thead>
          <tr>
            <th>Beds</th>
            <th>Number of rooms</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(key, value) in rooms" :key="value">
            <th>{{ key[0]}} bedded</th>
            <th>{{ key[1] }} rooms</th>
            <th><button type="button" v-on:click="removeRoom(key[0])" >-</button></th>
          </tr>
          </tbody>
        </table>
      <hr class="my-3">
      <h5>Location</h5>

      <hr class="my-3">
      <div class="col-11">
        <label for="address" class="form-label">Address</label>
        <input type="text" class="form-control" id="address" v-model="address" required>
      </div>
      <div class="double-field">
        <div class="col-md-5">
          <label for="country" class="form-label">Country</label>
          <input type="text" class="form-control" id="country" v-model="country" required>
        </div>
        <div class="col-md-6">
          <label for="state" class="form-label">City</label>
          <input type="text" class="form-control" id="state" v-model="city" required>
        </div>
      </div>
      <div class="double-field">
        <div class="col-md-5">
          <label for="latitude" class="form-label">Latitude</label>
          <input type="text" class="form-control" id="latitude" v-model="latitude" required>
        </div>
        <div class="col-md-5">
          <label for="longitude" class="form-label">Longitude</label>
          <input type="text" class="form-control" id="longitude" v-model="longitude" required>
        </div>
      </div>
      <hr class="my-4">
      <div class="col-11">
        <label for="promodesc" class="form-label">Promotional description</label>
        <textarea class="form-control" id="promodesc" style="height: 100px" v-model="promoDescription"></textarea>
      </div>

      <div class="double-field">
        <div class="mb-3 col-md-5">
          <div>
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
        <textarea class="form-control" id="cancellb" style="height: 100px" v-model="cancellationPolicy"></textarea>
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
          <button type="button" class="btn btn-secondary btn-sm" id="as" v-on:click="addAdditionalService">Add service</button>
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
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  name: "ChangeMansionInformation",
  data: function (){
    return{
      token: null,
      name : '',
      address : '',
      city : '',
      country : '',
      longitude : '',
      latitude : '',
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
      selectedMansion: null,
      mansions : new Array(),
      possibleToChange: true,
      user: null,
      allExteriorImages: new Array(),
      allInteriorImages: new Array(),
      interiorImagesOldNum: 0,
      exteriorImagesOldNum: 0,
      deleteOldExterior: new Array(),
      deleteOldInterior: new Array(),
      mansionName: '',
      numberOfBeds: 0,
      numberOfRooms: 0,
      rooms: new Map(),
    }
  },
  mounted() {
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    axios.get("/ownersMansions", {
      headers: {
        'Authorization' : 'Bearer ' + this.token
      }
    })
        .then(response1 => {
          console.log("Vikendice vlasnika u change mansion infos", response1.data)
          this.mansions = response1.data;
          if(this.mansions.length>0){
            this.selectedMansion = this.mansions[0];
            this.setFields()
          }

        })
  },
  methods: {
    saveChanges(){
      var roomArray = new Array();
      for ( const [key, value] of this.rooms){
        for (let i = 0; i<value; i++){
          roomArray.push(key)
        }
      }
      axios
          .post('/changeMansion',
              {
                "id": this.selectedMansion.id,
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
                "InteriorImagesToDelete": this.deleteOldInterior,
                "ExteriorImagesToDelete": this.deleteOldExterior,
                "rules": this.rules,
                "rulesToDelete" : this.rulesToDelete,
                "pricePerDay": this.pricePerDay,
                "priceForSevenDays": this.priceForSevenDays,
                "additionalServices": this.additionalServiceNew,
                "rooms": roomArray,
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
            alert("The mansion is not possible to change")
          });
    },
    setImg(img){
      axios.get('/entityImage/'+img.path, {
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
      axios.get('/entityImage/'+img.path, {
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
    setImages(){
      for (var eimg of this.selectedMansion.exteriorImages) {
        this.setImg(eimg);

      }
      console.log(this.selectedMansion.interiorImages.length)
      for(var iimg of this.selectedMansion.interiorImages){
        this.setImgI(iimg);

      }
    },
    setAdditionalServices(){
      axios.get("/additionalServicesMansion", {
        params:
            {
              id: this.selectedMansion.id
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
      .catch((err)=>{
        alert("Error occured while trying to get additional services for mansion!")
        console.log("Additional services for mansion:", err)
      })
    },
    addRoom(){
      console.log("da li je rooms instanca mape ")
      if( this.rooms instanceof Map) console.log("Jeste")
      else console.log("nije")
      if(this.rooms.has(this.numberOfBeds))
        this.rooms.set(this.numberOfBeds, this.rooms.get(this.numberOfBeds)+this.numberOfBeds)
      else
        this.rooms.set(this.numberOfBeds, this.numberOfRooms)
    },
    removeRoom(numberOfBedsToBeDeleted){
      if(this.rooms.has(numberOfBedsToBeDeleted)){
        if(this.rooms.get(numberOfBedsToBeDeleted)==1){
          this.rooms.delete(numberOfBedsToBeDeleted)
        } else{
          this.rooms.set(numberOfBedsToBeDeleted, this.rooms.get(numberOfBedsToBeDeleted)-1)
        }
      }

    },
    setRooms(){
      console.log("Setting rooms function, there are :", this.selectedMansion.rooms.length, " rooms to set");
      for(var r of this.selectedMansion.rooms){
        if(this.rooms.has(r.numberOfBeds))
          this.rooms.set(r.numberOfBeds, this.rooms.get(r.numberOfBeds)+1)
        else this.rooms.set(r.numberOfBeds, 1)
      }
    },
    setFields(){
      this.name = this.selectedMansion.name;
      this.address = this.selectedMansion.address.address;
      this.city = this.selectedMansion.address.city;
      this.country = this.selectedMansion.address.country;
      this.longitude = this.selectedMansion.address.longitude;
      this.latitude = this.selectedMansion.address.latitude;
      this.promoDescription = this.selectedMansion.promoDescription;
      this.cancellationPolicy = this.selectedMansion.cancellationPolicy;
      this.pricePerDay = this.selectedMansion.pricePerDay;
      this.priceForSevenDays = this.selectedMansion.priceForSevenDays;
      this.allExteriorImages = new Array();
      this.allInteriorImages= new Array();
      this.interiorImagesOldNum= 0;
      this.exteriorImagesOldNum= 0;
      this.deleteOldExterior=  new Array();
      this.deleteOldInterior= new Array();
      this.interiorImagesOldNum = this.selectedMansion.interiorImages.length;
      this.exteriorImagesOldNum = this.selectedMansion.exteriorImages.length;
      this.selectedExteriorImages = new Array();
      this.selectedInteriorImages = new Array();
      this.additionalServices = new Array();
      this.additionalServiceNew = new Array();
      this.additionalServicesDeleted = new Array();
      this.rooms = new Map();
      this.setAdditionalServices();
      this.setImages();
      this.setRooms();

    },
    changeMansion(){
      console.log("selectovani brod", this.selectedMansion)
      alert(this.selectedMansion.id)
      this.mansionName=this.selectedMansion.name;
      this.setFields();
      },
    deleteSelectedMansion(){
      axios.post( "/deleteMansion",{
        "boatId" : this.selectedMansion.id
      }, {
        headers: {
          'Authorization' : 'Bearer ' + this.token
        }
      })
          .then(response => {
            alert("Mansion successfully deleted!", response.data)
            axios.get("/ownersMansions", {
              headers: {
                'Authorization': 'Bearer ' + this.token
              }
            })
                .then(response1 => {
                  console.log("vikendice vlasnika nakon brisanja", response1.data)
                  this.mansions = response1.data;
                  if (this.mansions.length > 0) {
                    this.selectedMansion = this.mansions[0];
                    this.setFields()
                  }

                })
          })
          .catch(()=>{
            alert("The mansion is not possible to be deleted")
          })
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
    addAdditionalService(){
      var name = document.getElementById('new-additional-service-name').value;
      var hour = document.getElementById('new-additional-service-hour').value;
      var day = document.getElementById('new-additional-service-day').value;
      this.additionalServiceNew.push({id: -1, name: name, pricePerHour: hour, pricePerDay: day });
      document.getElementById('new-additional-service-name').value='';
      document.getElementById('new-additional-service-hour').value='';
      document.getElementById('new-additional-service-day').value='';
    },
    removeAdditionalService(as, index){
      console.log("To delete", as.id)
      this.additionalServiceNew.splice(index, 1);
      if(as.id!= -1) {
        alert("in if")
        this.additionalServicesDeleted.push(as.id);
        console.log("To delete ", this.additionalServicesDeleted)
      }
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

  }
}
</script>

<style scoped>
.mansion-information-change{
  margin: 10%;
  margin-left: 20%;
}

.double-field{
  display: flex;
  flex-direction: row;
}

.double-field .form-check{
  margin-left: 0.5%;
}

</style>