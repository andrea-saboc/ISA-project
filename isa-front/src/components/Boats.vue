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
    name: 'boats',
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
        });   

    },
    methods:{


    }
}

</script>

<style>

</style>