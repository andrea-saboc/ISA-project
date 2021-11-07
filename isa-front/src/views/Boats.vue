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


</div>

</template>

<script>
export default{
data: function(){
return{
boats : [
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
           }
        ]
       }
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

</style>