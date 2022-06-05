<template class="tem">
  <div class="adventure-registration">
    <form>
      <h3 class="mb-3">Adventure registration</h3>
      <div class="col-11">
        <label for="adventure-name" class="form-label">Adventure name</label>
        <input type="text" class="form-control" id="adventure-name" placeholder="E.g. Titanic" v-model = "name" required>
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
          <input type="text" class="form-control" id="latitude"  placeholder="1223"  v-model="latitude" required>
        </div>

      </div>
      <hr class="my-4">
      <div class="col-11">
        <label for="promodesc" class="form-label">Promotional description</label>
        <input class="form-control" placeholder="Write a description" id="promodesc" style="height: 100px" v-model="promoDescription">
      </div>
      <br>
      <div class="col-11">
        <label for="promodesc" class="form-label">Maximum number of people</label>
        <input type="number" min="1" max="30" v-model="capacity" class="form-control" placeholder="Enter the maximum number of people...">
      </div> 
      <br>
      <div class="col-11">
        <label for="promodesc" class="form-label">Equipments</label>
        <textarea class="form-control" placeholder="Write equipments" id="promodesc" style="height: 100px" v-model="equipment"></textarea>
      </div>
      <br>
      <div class="col-11">
        <label for="promodesc" class="form-label">Short biography of the instructor</label>
        <textarea class="form-control" placeholder="Write a description" id="promodesc" style="height: 100px" v-model="biography"></textarea>
      </div>
        <br>
    
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
      
      <div class="double-field">
        <div class="mb-3 col-md-5">
          <label class="form-label">Interior images</label>
          <input class="form-control" type="file" @change="onFileChange">
        </div>
        <br>
        <div v-for="(image, index) in imagesForFront" :key="(image, index)">
                    <button v-on:click="removeImage(image, index)" class="btn btn-danger rounded-circle removeImageBtn end-0 m-4">X</button>
                    <img class="ms-3 me-3 mt-3 newImage img-fluid" v-bind:src="image.path" >
        </div>

      </div>
       
      
<hr>
      <button class="w-100 btn btn-primary btn-lg" type="submit" @click="registerAdventure">Register an adventure </button>
    </form>

  </div>


</template>

<script>
import axios from 'axios'


export default {
  name: "AdventureRegistration",
  data: function(){
    return {
      name: '',
      address : '',
      city : '',
      country : '',
      longitude : '',
      latitude : '',
      capacity : '',
      promoDescription : '',
      equipment: '',
      biography: '',
      cancellationPolicy : '',
      rules : new Array(),
      additionalServices : new Array(),
      additionalServiceName : '',
      additionalServicePricePerHour : '',
      additionalServicePricePerDay : '',
      pricePerHour: '',
      pricePerDay: '',
      priceForSevenDays: '',
      imagesForFront: [],
      imagesForBackend: new Array(),
      imageCount: 0
    }
    },
    mounted()
    {
         let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   console.log('toke je',token)
    },
  methods:{
     onFileChange: function(e){
        console.log('fajl select');
        const file = e.target.files[0];
        this.createBase64Image(file);
        this.imageCount++;
        this.imagesForFront.push(
          {
            id: this.imageCount, 
            name: ""+this.imageCount,
            path: URL.createObjectURL(file)
           }
           );
        console.log('Nice');
        console.log(this.imagesForFront);
    },
    createBase64Image(file){
        const reader= new FileReader();
        reader.onload = (e) =>{
            this.imagesForBackend.push(e.target.result);
        }
        reader.readAsDataURL(file);
        console.log('Images for beckend');
        console.log(this.imagesForBackend);
    },
    removeImage: function(image, index){
        this.imageCount--;
        this.imagesForFront.splice(index,1);
        this.imagesForBackend.splice(index,1);
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
    registerAdventure(){
      alert("Registering an adventure")
      alert(this.name)
      alert(this.latitude)
      
      axios.post('http://localhost:8080/registerAdventure',{
            "name": this.name,
            "address": this.address,
            "city": this.city,
            "country": this.country,
            "longitude": this.longitude,
            "latitude": this.latitude,
            "promoDescription": this.promoDescription,
            "cancellationPolicy" : this.cancellationPolicy,
            "equipment":this.equipment,
            "biography":this.biography,
            "capacity": this.capacity,
            "rules": this.rules,
            "pricePerHour": this.pricePerHour,
            "pricePerDay": this.pricePerDay,
            "priceForSevenDays": this.priceForSevenDays,
            "additionalServices": this.additionalServices,
            "imagesForBackend": this.imagesForBackend
            }, {
            headers: {
              'Authorization' : this.$store.getters.tokenString
            }
          })
          .then(response => {
            alert(response.data)
            this.messege = response.data
            let router = this.$router;
             router.push("/fishingInstructorHomePage")
             return;

          })

    }
  }

}
</script>

<style scoped>
.adventure-registration{

  width: 60%;
  horiz-align: center;
  margin-left: 20%;
  margin-top: 2%;
  border-radius: 3%;
  padding: 3%;
  background-blend-mode: lighten;
}
.removeImageBtn{
    position: absolute;
}
.double-field{
  display: flex;
  flex-direction: row;
}

.double-field .form-check{
  margin-left: 0.5%;
}

.adventure-registration .form-label{
}



</style>