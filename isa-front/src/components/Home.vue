
<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
  <div class = "container pt-100">
    <div class = "d-sm-flex align-items-center justify-content-between">
      <div>
      <h1>Log in to your account</h1>
      <p class="lead my-4">
        This is some kind of description or whatever.. Ovdje bi mozda mogla i slika?
      </p>
      <button class = "btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#login">Login
      </button>
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginn">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
      </div>
      <div>
        <img class="img-fluid w-30 s-sm-block" src = "">
      </div>
    </div>
  </div>
</section>
<section class="p-10">
  <div class ="container" py-30>
   <listing></listing>
  </div>
</section>
</template>

<script>
import axios from 'axios'
import Listing from './Listing.vue'

export default {
  name: 'Home',
  components: { Listing
  },
  methods:{
    RedirectBoats(){
      this.$router.push({name: 'boats'});
    },
    RedirectMansions(){
      this.$router.push({name: 'mansions'});
    },
    RedirectAdventures(){
      this.$router.push({name: 'adventures'});
    }

  }
}
</script>
<style>

#home{   
  margin: 0 auto;
  text-align: center;
}

#home button{
	padding:10px;
	border:none;
	background-color:#2ECC71;
	color:#fff;
	font-weight:600;
	border-radius:20px;
	width:600px;
  height:50px;

}

#home button:hover{
	background: #333;
  }

</style>