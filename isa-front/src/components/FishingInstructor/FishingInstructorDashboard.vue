<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import axios from "axios";
import {devServer} from "../../../vue.config";


export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data() {
    return {
      calendarOptions: {
        plugins: [ dayGridPlugin, interactionPlugin ],
        initialView: 'dayGridMonth',
        weekends: true,
        editable:true,
        selectable:true,
        headerToolbar:{
            left:'prev,next today',
            center:'title',
            right:'dayGridMonth,dayGridWeek,dayGridDay'
        },
       
        events: [],
        eventClick: (arg) =>
        {
          alert(arg.event.extendedProps.description)
        }
      }
    }
  },
  mounted(){
    let token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
   console.log('toke je',token)
    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : 'Bearer ' + token,
      }
    })
    .then(response => {
      this.loggedUser =response.data
      console.log("Ovaj user je ulogovan:", this.loggedUser)
    
        axios.get(devServer.proxy+"/allReservationAdventures", {
          headers: {
            'Authorization' :'Bearer ' + token,
          }
        })
            .then(response1 => {
                console.log("Podaci za kalendar: ",response1.data)
              this.calendarOptions.events=response1.data
            })
    })
        .catch(() => {
          console.log('Login user is unavailable')
          return;
        });
    }
 }
  

</script>
<template>
<div class="boatowner-dashborad">
  <h4>
    Calendar
  </h4>
 
  <FullCalendar :options="calendarOptions" />
</div>

</template>


<style scoped>
.boatowner-dashborad{
  margin: 10%;
  margin-left: 20%;
  margin-right: 5%;
}

.horizontal{
  display: flex;
  flex-direction: row;
}

</style>