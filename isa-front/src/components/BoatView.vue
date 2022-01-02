<template>
<div class="boat-view">
  <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner top-slider">
      <div class="carousel-item active">
        <img src="../assets/yacht.jpg" class=" w-100 h-25" alt="../assets/yacht.jpg">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100 h-25  " alt="../assets/yacht.jpg">
      </div>
      <div class="carousel-item">
        <img src="../assets/yacht.jpg" class="d-block w-100 h-25" alt="../assets/yacht.jpg">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <p class="fs-5">
    {{boatToShow.name}}
  </p>

</div>

</template>

<script>
import axios from 'axios'
export default {
  name: "BoatView",
  data: function (){
    return{
      boatToShow: null
    }

  },
  mounted() {
    var path = window.location.href;
    var boatId = path.split('/boats/')[1].replaceAll('%20', ' ');
    axios
    .get('http://localhost:8080/boat', {
      params:
          {
            boatId : boatId
          }
    })
    .then(response =>{
      this.boatToShow = response.data
    })

  }
}


</script>

<style scoped>
.boat-view{
  horiz-align: center;
  width: 70%;
  margin-left: 15%;
  background-color: #bbbbbb;
  height: 100%;
}

.boat-view .top-slider{
  height: 350px;

}
</style>