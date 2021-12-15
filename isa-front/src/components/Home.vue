<template>
<section class="bg-dark text-light p-5 text-center text-sm-start">
  <div class = "container pt-100">
    <div class = "d-sm-flex align-items-center justify-content-between">
      <div>
      <h1>Log in to your account</h1>
      <p class="lead my-4">
        This is the place where you car run away from your miserable lifes once in a while..
      </p>
      <button class = "btn btn-primary btn-lg">Login ili Register
      </button>
      </div>
      <div>
        <img class="img-fluid w-30 s-sm-block" src = "../assets/logo.png">
      </div>
    </div>
  </div>
</section>
<section>
  <div class ="container">
    <nav class="nav nav-tabs">
      <a class="nav-link active" aria-current="page" href="#">Active</a>
      <a class="nav-link" href="#">Link</a>
      <a class="nav-link" href="#">Link</a>
    </nav>
  </div>
</section>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Home',
  components: {
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