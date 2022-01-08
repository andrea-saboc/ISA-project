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
            <div v-for="m in mansions" :key="m">
               <div class="card mb-3">
                  <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
                  <div class="card-body">
                     <h5 class="card-title">{{m.name}}</h5>
                     <p class="card-text">{{m.promoDescription}}</p>
                     <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
                     <button class = "btn btn-danger" v-on:click = "ShowOwnerComplaintBox(m)">Complain about the owner</button> 
                     <button class = "btn btn-danger" v-on:click = "ShowComplaintBox(m)">Complain about the mansion</button>            
                  </div>
                  <div v-if="givingMOwnerComplain == m" class="input-group">
                     <div class="input-group-prepend">
                        <span class="input-group-text">Complain content:</span>
                     </div>
                     <textarea class="form-control" aria-label="With textarea" v-model="ownerComplainContent"></textarea>
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
            <div v-for="m in boats" :key="m">
               <div class="card mb-3">
                  <img class="card-img-top" src="../assets/yacht.jpg" alt="Mansion name">
                  <div class="card-body">
                     <h5 class="card-title">{{m.name}}</h5>
                     <p class="card-text">{{m.promoDescription}}</p>
                     <p class="card-text"><small class="text-muted">{{m.address}}</small></p>
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
   <section class=" bg-light container">
      <br>
      <h2>Your complains so far:</h2>
   </section>
</template>
<script>
   import axios from 'axios'
   
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
            .get('http://localhost:8080/complaints/boats',{
               headers: {
                  'Authorization' : this.$store.getters.tokenString
               }
            })
            .then(response => {
               this.boats = response.data;
               console.log(this.boats)      
         });
             axios
            .get('http://localhost:8080/complaints/mansions',{
               headers: {
                  'Authorization' : this.$store.getters.tokenString
               }
            })
            .then(response => {
               this.boats = response.data;
               console.log(this.boats)      
         });
   
   
   },
   methods:{
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
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('http://localhost:8080/complaints/addMansionOwnerComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
         });
   
       },
       SubmitMansionComplain(m){
   
            var complaint ={
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('http://localhost:8080/complaints/addMansionComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
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
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('http://localhost:8080/complaints/addBoatOwnerComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
         });
   
       },
       SubmitBoatComplain(m){
           alert('doing it')
            var complaint ={
             content:this.complainContent,
             entityId: m.id
          }
   
           axios
            .post('http://localhost:8080/complaints/addBoatComplain',complaint,{
            headers: {
            'Authorization' : this.$store.getters.tokenString,
            'Content-Type': 'application/json'
            }
         })
            .then(response => {
               console.log(response.data)
               alert('submited')
         });
   
   
   
   
   
   
       },
       SubmitInstructorComplain(m){
          console.log(m)
       },    
       ShowInstructorComplainBox(m){
         if (this.givingInstructorComplain == m){ this.givingInstructorComplain = 'None'; return}
         this.givingInstructorComplain=m
       },
   
   
   }
   
   
   }
   
</script>