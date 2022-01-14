<template>
<section  class ="container">
<br>
  <hr>
<div class="input-group mb-3">
  <button class="btn btn-outline-secondary" v-on:click=DisplayMansions>Mansins</button>
  <button class="btn btn-outline-secondary" v-on:click=DisplayBoats>Boats</button>
  <button class="btn btn-outline-secondary" v-on:click=DisplayAdventures>Adventures</button>

</div>
  <hr>
<div py-50>
    <div v-if="display == 'mansions'">
        <mansions></mansions>
    </div>
    <div v-if="display == 'boats'">
        <boats></boats>
    </div>
    <div v-if="display == 'adventures'">
        <adventures></adventures>
    </div>
</div>
</section>
</template>

<script>
//import axios from 'axios'
import Mansions from './Mansions.vue'
import Boats from './Boats.vue'
import Adventures from './Adventures.vue'

export default {
  name: 'Listing',
  components: { Mansions, Boats, Adventures
  },
  data: function(){
      return{
          display: 'mansions'
      }
  },
  methods:{
    DisplayMansions(){   
        if(this.display == 'mansions')
            this.display = 'None'
        else
            this.display = 'mansions'
    },

    DisplayBoats(){
        if(this.display == 'boats')
            this.display = 'None'
        else
            this.display = 'boats'
    },
    DisplayAdventures(){
      this.display = 'adventures'
    }

  }
}

</script>

<style>


</style>