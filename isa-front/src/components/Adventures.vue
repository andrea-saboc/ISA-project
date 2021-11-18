<template>
<div id="adventures">

<div v-for="adventure in adventures">
  
			<div class="row" style="position:relative;">

		  			<label style="color:#2ECC71;font-size:26px;">{{adventure.name}}</label><br><br>
                    <label style="color:#2ECC71;font-size:20px;">{{adventure.promoDescription}}</label>
                        <div style="position:absolute;bottom:30px;left:30px;">
                            <label>{{adventure.address}}</label>
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
        adventures : [
           {
               'name': 'Ime prvo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Ime drugo',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Ime trece, ime broda zamisli',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           },
           {
               'name': 'Cetvrto',
               'address': 'Adresa od vise dijelova',
               'promoDescription' : 'promo opis',
               'avgGrade' : 'ocjena'
           }
        ]
        }
    },
    methods:{

    }
}
</script>

<style>


</style>