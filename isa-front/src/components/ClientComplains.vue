<template>
   <section class=" bg-dark p-5 text-sm-start p-10">
      <section class = "container">
         <div class="input-group justify-content-end mr-30">
            <ul class="nav">
               <li class="nav-item">
                  <button class="btn btn-link" v-on:click=DisplayMansions>Mansions</button>
               </li>
               <li class="nav-item">
                  <button class="btn btn-link" v-on:click=DisplayBoats>Boats</button>
               </li>
               <li class="nav-item">
                  <button class="btn btn-link" v-on:click=DisplayAdventures>Adventures</button>
               </li>
            </ul>
         </div>
         <div  v-if="display == 'mansions'">
         <div v-if="mansions.length == 0" class="text-light"><br><br><br><h2 class="card-title text-center">No mansion complain candidates</h2><br><br><br></div>

            <div v-for="m in mansions" :key="m">
               <div class="card mb-3">
                  <img class="card-img-top" :src=picture(m.id) alt="Mansion name">
                  <div class="card-body">
                     <h5 class="card-title">{{m.name}}</h5>
                     <p class="card-text">{{m.promoDescription}}</p>
                     <p class="card-text"><small class="text-muted">{{m.address.address}},{{m.address.city}},{{m.address.country}}</small></p>
                     <button class = "btn btn-danger" v-on:click = "ShowOwnerComplaintBox(m)">Complain about the owner</button> 
                     <button class = "btn btn-danger" v-on:click = "ShowComplaintBox(m)">Complain about the mansion</button>            
                  </div>
                  <div v-if="givingMOwnerComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="ownerComplaintContent"></textarea>
                     <button class="btn btn-secondary" v-on:click = SubmitMOwnerComplain(m)>Sumbit</button>
                  </div>
                  <div v-if="givingMansionComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Mansion complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="complainContent"></textarea>
                     <button class="btn btn-secondary" v-on:click = SubmitMansionComplain(m)>Sumbit</button>
                  </div>
               </div>
            </div>
         </div>
         <div v-if="display == 'boats'">
         <div v-if="boats.length == 0" class="text-light"><br><br><br><h2 class="card-title text-center">No boat complain candidates</h2><br><br><br></div>

            <div v-for="m in boats" :key="m">
               <div class="card mb-3">
                  <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
                  <div class="card-body">
                     <h5 class="card-title">{{m.name}}</h5>
                     <p class="card-text">{{m.promoDescription}}</p>
                     <p class="card-text"><small class="text-muted">{{m.address.address}},{{m.address.city}},{{m.address.country}}</small></p>
                     <button class = "btn btn-danger" v-on:click = "ShowBOwnerComplainBox(m)">Complain about the owner</button> 
                     <button class = "btn btn-danger" v-on:click = "ShowBoatComplainBox(m)">Complain about the boat</button>            
                  </div>
                  <div v-if="givingBOwnerComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="ownerComplaintContent"></textarea>
                     <button class="btn btn-secondary" v-on:click = "SubmitBOwnerComplain(m)">Sumbit</button>
                  </div>
                  <div v-if="givingBoatComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Boat complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="complainContent"></textarea>
                     <button class="btn btn-secondary" v-on:click = "SubmitBoatComplain(m)">Sumbit</button>
                  </div>
               </div>
            </div>
         </div>
         <div v-if="display == 'adventures'">
         <div v-if="adventures.length == 0" class="text-light"><br><br><br><h2 class="card-title text-center">No complain candidates</h2><br><br><br></div>

            <div v-for="m in adventures" :key="m">
               <div class="card mb-3">
                  <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
                  <div class="card-body">
                     <h5 class="card-title">{{m.name}}</h5>
                     <p class="card-text">{{m.promoDescription}}</p>
                     <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
                     <button class = "btn btn-danger" v-on:click = "ShowInstructorComplainBox(m)">Complain about the instructor</button> 
                  </div>
                  <div v-if="givingInstructorComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="ownerComplaintContent"></textarea>
                     <button class="btn btn-secondary" v-on:click = "SubmitInstructorComplain(m)">Sumbit</button>
                  </div>
               </div>
            </div>
         </div>
         <br>
         <br>
      </section>
   </section>

</template>
<script>
   import axios from 'axios'
   axios.defaults.baseURL = process.env.VUE_APP_URL;

   export default {
   data(){
       return{
   
           complainContent: '',
           ownerComplaintContent: '',
   
           display: 'mansions',
           givingMOwnerComplain: 'None',
           givingMansionComplain: 'None',
           givingBOwnerComplain: 'None',
           givingBoatComplain: 'None',
           givingInstructorComplain: 'None',
   
           mansions: [],
           boats: [],
           adventures:[]
       }
   
   },
   mounted(){

            axios
                .get('/complaints/boats', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    console.log(response.data)
                    this.boats = response.data;
                });

            axios
                .get( '/complaints/mansions', {
                    headers: {
                        'Authorization': this.$store.getters.tokenString
                    }
                })
                .then(response => {
                    console.log(response.data)
                    this.mansions = response.data;
                });
   
   
   },
   methods:{  
       picture(id) {
         return '/images/mansion'+id+'.jpg'; },
       DisplayMansions(){  
           console.log('showing mansions')    
         this.display = 'mansions'
       },
       DisplayBoats(){ 
         console.log('showing boats')  
         this.display = 'boats'
       },
       DisplayAdventures(){
       console.log('showing adventures')
         this.display = 'adventures'
       },
       ShowOwnerComplaintBox(m){
         if (this.givingMOwnerComplain == m){ this.givingMOwnerComplain = 'None'; return}
         this.givingMOwnerComplain=m
       },
       ShowComplaintBox(m){
         if (this.givingMansionComplain == m){ this.givingMansionComplain = 'None'; return}
         this.givingMansionComplain=m
       },
       SubmitMOwnerComplain(m){
   
            var complaint ={
             content:this.ownerComplaintContent,
             entityId: m.id
          }

         console.log(complaint)
           axios
            .post('/complaints/addMansionOwnerComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
               this.Reset()
         });
   
       },
       SubmitMansionComplain(m){
   
            var complaint ={
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('/complaints/addMansionComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
               this.Reset()
         });
       },
       ShowBOwnerComplainBox(m){
         if (this.givingBOwnerComplain == m){ this.givingBOwnerComplain = 'None'; return}
         this.givingBOwnerComplain=m
       },
       ShowBoatComplainBox(m){
         if (this.givingBoatComplain == m){ this.givingBoatComplain = 'None'; return}
         this.givingBoatComplain=m
       },
       SubmitBOwnerComplain(m){

            var complaint ={
             content:this.ownerComplaintContent,
             entityId: m.id
          }
   
           axios
            .post('/complaints/addBoatOwnerComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
               this.Reset()
         });
   
       },
       SubmitBoatComplain(m){
           alert('doing it')
            var complaint ={
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('/complaints/addBoatComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
               this.Reset()
         });
   
   
   
   
   
   
       },
       SubmitInstructorComplain(m){
          console.log(m)
       },    
       ShowInstructorComplainBox(m){
         if (this.givingInstructorComplain == m){ this.givingInstructorComplain = 'None'; return}
         this.givingInstructorComplain=m
       },
       Reset(){
           this.complainContent =  ''
           this.ownerComplaintContent= ''
   
           this.givingMOwnerComplain = 'None'
           this.givingMansionComplain = 'None'
           this.givingBOwnerComplain = 'None'
           this.givingBoatComplain = 'None'
           this.givingInstructorComplain = 'None'

       }
   
   
   }
   
   
   }
   
</script>