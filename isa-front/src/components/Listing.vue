<template>

<div class="btn-group">
  <button href="#" class="btn btn-primary" v-on:click=DisplayMansions>Mansions</button>
  <button href="#" class="btn btn-primary" v-on:click=DisplayBoats>Boats</button>
  <button href="#" class="btn btn-primary" v-on:click=DisplayAdventures>Adventures</button>
</div>
<br>
<br>
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

</template>

<script>
import axios from 'axios'
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
      this.display = 'boats'
      console.log(this.display)
    },
    DisplayAdventures(){
      this.display = 'adventures'
    }

  }
}

</script>

<style>


</style>