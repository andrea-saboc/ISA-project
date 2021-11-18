<template>

<div id="boats">

<div v-for="boat in boats">
  
			<div class="row" style="position:relative;">

		  			<label style="color:#2ECC71;font-size:26px;">{{boat.name}}</label><br><br>
                    <label style="color:#2ECC71;font-size:20px;">{{boat.promoDescription}}</label>
                        <div style="position:absolute;bottom:30px;left:30px;">
                            <label>{{boat.address}}</label>
                        </div>
				
			</div>
            <br>
            <br>
</div>

<div class="right-fixed"><label style="color:black;font-size:20px;">Pretraga po razlicitim krit:</label></div>
<div class="left-fixed"> <label style="color:black;font-size:20px;">Odabrani entitet pa combobox:</label></div>

</div>
</template>

<script>
import axios from 'axios'
export default{
data: function(){
    return{
        boats : []
        }
    },
mounted(){
      axios
         .get('http://localhost:8080/boats')
         .then(response => {
            this.boats = response.data;
            alert(response.data);        
      });   

},
methods:{


}

}

</script>

<style>

.row{   

    width: 1000px;
    height: 200px;
    background-color: white;
	padding: 30px;
	margin-top: 900px;
	border-radius: 10px;
	box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
}
.row:hover{
    background-color:#FCFCFC;
	box-shadow: inset 0 0 0 1px rgba(0, 0, 0, 0);
}

.right-fixed {
  background-color: #dddddd;
  opacity: 0.3;
  width: 250px;
  height: 350px;
  position: fixed;
  bottom: 10px;
  right: 10px;
	border-radius: 20px;
    padding: 60px;
}
.left-fixed {
  background-color: #dddddd;
  opacity: 0.3;
  width: 185px;
  height: 100px;
  position: fixed;
  bottom: 30px;
  left: 30px;
	border-radius: 10px;
    padding: 60px;
}

</style>