<template>
<navbar>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Fishing Fish</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home</a>
      </li>
    </ul>
  </div>
  <form class="form-inline my-2 my-lg-0">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/clientRegistration">Register</a>
      </li>
    </ul>
  </form>
  <form class="form-inline my-2 my-lg-0">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/login">Login</a>
      </li>
    </ul>
  </form>

</nav>

</navbar>
  <router-view/>
</template>

<script>
import axios from 'axios'
export default{
  data(){
    return{
    user: {},
    LoggedIn: false
    }
  },
  
  mounted(){

    alert('entered')

    axios
      .get('http://localhost:8080/loginTest')
      .then(response => {
        if (!response.data === 'User is not logged in') {
          this.user = response.data;
          this.LoggedIn = true;
        }
      });
  }


}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}


</style>
