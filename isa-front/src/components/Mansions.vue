<template>
<p>helou</p>

</template>

<script>
import axios from 'axios'
export default{
    name: 'mansions',
    data(){
        return{
        sort: 'Name',
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
        SortByName(){
            this.sort = 'Name';
        },
        SortByGrade(){
            this.sort = 'Grade';
        },
        SortByLocation(){
            this.sort = 'Location';
        },
        Sort(){

            if (this.sort == 'Name') {
				this.mansions.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
			} else if (this.sort == 'Grade') {
				this.mansions.sort((a, b) => (a.avgGrade > b.avgGrade) ? 1 : ((b.avgGrade > a.avgGrade) ? -1 : 0));
			} else if (this.sort == 'Location') {
				this.mansions.sort((b, a) => (a.address > b.address) ? 1 : ((b.address > a.address) ? -1 : 0));
			}
            
        }


    }

}
</script>

<style>


</style>