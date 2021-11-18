<template>
<div id="mansions">

<div v-for="mansion in mansions">
  
			<div class="row" style="position:relative;">

		  			<label style="color:#2ECC71;font-size:26px;">{{mansion.name}}</label><br><br>
                    <label style="color:#2ECC71;font-size:20px;">{{mansion.promoDescription}}</label>
                        <div style="position:absolute;bottom:30px;left:30px;">
                            <label>{{mansion.address}}</label>
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
    data(){
        return{
        mansions : []
        }
    },
    mounted(){
      axios
         .get('http://localhost:8080/mansions')
         .then(response => {
            this.mansions = response.data;       
      });   

    },
    methods:{


    }

}
</script>

<style>


</style>