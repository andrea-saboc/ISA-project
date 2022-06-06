<template>
<div class="boat-information-change">
  <div>
    <div v-if="adventures!=null && adventures.length>0">
      <select class="form-select form-select-lg mb-3 custom-select" style="width: 20%" aria-label=".form-select-lg example" v-model="selectedAdventure" v-on:change="changeAdventure">
        <option v-for=" adventure in adventures" v-bind:value="adventure" :key="adventure.id">
          {{adventure.name}}
        </option>
      </select>
      <button class=" btn btn-primary" style="margin-left: 1%; margin-bottom: 2%" v-on:click="deleteSelectedAdventure()">Delete</button>
    </div>
    <div v-else>
      There is no adventure yet!
    </div>
  <div>

    <form>
      <h3 class="mb-3">Change adventure informations</h3>
      <div class="col-11">
        <label for="boat-name" class="form-label">Adventure name</label>
        <input type="text" class="form-control" id="boat-name" v-model = "name" required>
      </div>
       <div class="col-11">
        <label for="boat-name" class="form-label">Biography fishing instructor</label>
        <input type="text" class="form-control" id="boat-name" v-model = "biography" required>
      </div>
      <hr class="my-4">
      <div class="col-11">
        <label for="promodesc" class="form-label">Equipment</label>
        <textarea class="form-control" placeholder="Write a equipment" id="promodesc" style="height: 100px" v-model="equipment"></textarea>
      </div>
      <div class="col-3">
          <label for="capacity" class="form-label" >Capacity</label>
          <input type="number" class="form-control" id="capacity" min="1" v-model="capacity" required>
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
              <label for="interiorImages" class="form-label">Images</label>
              <input class="form-control" type="file" id="interiorImages" multiple @change="onImagesSelected">
            </div>
            <br>
            <div>
              <table>
                <tbody>
                <tr v-for="(image, index) in allImages" :key="(image, index)">
                    <td>
                      <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
                    </td>
                  <td>
                    <button v-on:click="removeImage(image, index)" type="button">x</button>
                  </td>
                </tr>
                </tbody>
              </table>
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
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  name: "ChangeAdventureInformation",
  data: function (){
    return{
      token: null,
      name : '',
      biography: '',
      equipment:'',
      address : '',
      city : '',
      country : '',
      longitude : '',
      latitude : '',
      capacity : '',
      promoDescription : '',
      cancellationPolicy : '',
      img : new Array(),
      rules : new Array(),
      rulesToDelete : new Array(),
      selectedImages : new Array(),
      additionalServices : new Array(),
      additionalServiceNew : new Array(),
      additionalServicesDeleted : new Array(),
      additionalServiceName : '',
      additionalServicePricePerHour : '',
      additionalServicePricePerDay : '',
      pricePerHour: '',
      pricePerDay: '',
      priceForSevenDays: '',
      selectedAdventure: null,
      adventures : new Array(),
      possibleToChange: true,
      user: null,
      allImages: new Array(),
      imagesOldNum: 0,
      deleteOldImages: new Array(),
      adventureName: ''
    }
  },
  mounted(){
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    this.user = this.$store.state.userType
      axios.get("/ownersAdventures", {
        headers: {
          'Authorization' : 'Bearer ' + this.token
        }
      })
          .then(response1 => {
            
            this.adventures = response1.data;
            if(this.adventures.length>0){
              this.selectedAdventure = this.adventures[0];
            this.setFields()
            }

          })
    //}
  },
  methods:{
    saveChanges(){
        console.log("OVDEEEEEEEEEEEE SAAAAAAM",this.img);
      alert("Changing Adventure", this.selectedAdventure)
      axios
          .post('/changeAdventure',
              {
                "id": this.selectedAdventure.id,
                "name": this.name,
                "biography": this.biography,
                "equipment": this.equipment,
                "cancellationPolicy" : this.cancellationPolicy,
                "address": this.address,
                "city": this.city,
                "country": this.country,
                "longitude": this.longitude,
                "latitude": this.latitude,
                "promoDescription": this.promoDescription,
                "images": this.img,
                "imagesToDelete": this.deleteOldImages,
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
            alert("The adventure is not possible to change")
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
            console.log("Id of the interior image is ", img.id)
            let base64ImageString = Buffer.from(response.data, 'binary').toString('base64')
            let srcValue = "data:image/png;base64," + base64ImageString
            this.allImages.push({
              id: img.id,
              path: srcValue
            })

          })
          .catch(() => {
            console.log("There is a trouble")
          })
    },
    setImages() {
     
      for(var iimg of this.selectedAdventure.images){
        this.setImg(iimg);
      }

    },
    setAdditionalServices() {
      axios.get( "/additionalServicesAdventure", {
        params:
            {
              id: this.selectedAdventure.id
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
      this.name = this.selectedAdventure.name;
      this.cancellationPolicy = this.selectedAdventure.cancellationPolicy;
      this.capacity = this.selectedAdventure.capacity;
      this.priceForSevenDays = this.selectedAdventure.priceForSevenDays;
      this.pricePerDay = this.selectedAdventure.pricePerDay;
      this.pricePerHour = this.selectedAdventure.pricePerHour;
      this.promoDescription=this.selectedAdventure.promoDescription;
      this.address = this.selectedAdventure.address.address;
      this.city = this.selectedAdventure.address.city;
      this.country = this.selectedAdventure.address.country;
      this.longitude = this.selectedAdventure.address.longitude;
      this.latitude = this.selectedAdventure.address.latitude;
      this.additionalServices = new Array();
      this.additionalServiceNew = new Array();
      this.additionalServicesDeleted = new Array();
      this.rules =this.selectedAdventure.rules;
      this.rulesToDelete = new Array();
      this.allImages= new Array();
      this.imagesOldNum= 0;
      this.biography=this.selectedAdventure.biography
      this.equipment=this.selectedAdventure.equipment
      this.deleteOldImages= new Array();
      this.imagesOldNum = this.selectedAdventure.images.length;
      this.selectedImages = new Array();
      this.setAdditionalServices();
      this.setImages();

    },
    changeAdventure(){
      console.log("selectovana avantura ", this.selectedAdventure)
      this.adventureName=this.selectedAdventure.name;
      this.setFields();
    },
    deleteSelectedAdventure(){
      axios.post("/deleteAdventure",{
        "adventureId" : this.selectedAdventure.id
      }, {
        headers: {
          'Authorization' : this.$store.getters.tokenString
        }
      })
      .then(response => {
        alert(response.data)
        axios.get( "/ownersAdventures", {
          headers: {
            'Authorization': this.$store.getters.tokenString
          }
        })
            .then(response1 => {
              this.adventures = response1.data;
              if (this.adventures.length > 0) {
                this.selectedAdventure = this.adventures[0];
                this.setFields()
              }

            })
      })
      .catch(()=>{
        alert("The adventure is not possible to be deleted")
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
    onImagesSelected(event){
      this.selectedImages = event.target.files
      for (var img of event.target.files) {
        console.log("tu sam", img)
        this.allImages.push(
            {
              path: URL.createObjectURL(img)
            }
        );
      }
      //const reader = new FileReader()
      console.log("selected exterior image length is", this.selectedImages)
      for (var imgg in this.selectedImages){
        console.log(isNaN(imgg))
        if (isNaN(imgg)) {
          return;
        }
        console.log("before pushig file index:", imgg)
        this.onUploadI(this.selectedImages[imgg])
      }

    },
    onUploadI: function(file){ 
      const reader= new FileReader();
      reader.onload = (e) =>{
        console.log("e:",e.target.result)
        this.img.push(e.target.result)
      }
      reader.readAsDataURL(file);
    },
   
    removeImage: function(image, index){
      this.allImages.splice(index, 1)
          if (index<this.imagesOldNum){
            this.imagesOldNum--;
            console.log("image",image)
            this.deleteOldImages.push(image.id); //kako da dodjem do id slike?
          } else{
            let indexToDelete = index-this.imagesOldNum;
            this.img.splice(indexToDelete, 1);
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