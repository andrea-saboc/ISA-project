<script>
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import axios from "axios";
import {devServer} from "../../../vue.config";
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)


export default {
  components: {
   FullCalendar, // make the <FullCalendar> tag available
    Bar
  },
  data() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        weekends: true,
        editable: true,
        selectable: true,
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,dayGridWeek,dayGridDay'
        },

        events: [],
        eventClick: (arg) => {
          alert(arg.event.extendedProps.description)
        },
      },
        selectedEntity: '',
        allEntities: [],
        allData: '',
        yearlyIncomingsSelected: 0,
        monthlyIncomingsSelected: 0,
        weeklyIncomingsSelected: 0,
       yearlySelected: '',
      monthlySelected: '',
      weeklySelected: '',
      chartDataYearSelected: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for year all adventures',
            backgroundColor: '#5350D5',
            data: []
          }
        ]
      },
      chartDataMonthSelected: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for current month all adventures',
            backgroundColor: '#D56850',
            data: []
          }
        ]
      },
      chartDataWeekSelected: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for current week all adventures',
            backgroundColor: '#48bf91',
            data: []
          }
        ]
      },        token : null,
        today : new Date(),
        datase: [],
        yearlyIncomings: 0,
        monthlyIncomings: 0,
        weeklyIncomings: 0,
        chartDataYear: {
          labels: [ 'January', 'February', 'March'],
          datasets: [
            {
              label: 'Reservations for year all adventures',
              backgroundColor: '#5350D5',
              data: []
            }
          ]
        },
        chartDataMonth: {
          labels: [ 'January', 'February', 'March'],
          datasets: [
            {
              label: 'Reservations for current month all adventures',
              backgroundColor: '#D56850',
              data: []
            }
          ]
        },
        chartDataWeek: {
          labels: [ 'January', 'February', 'March'],
          datasets: [
            {
              label: 'Reservations for current week all adventures',
              backgroundColor: '#48bf91',
              data: []
            }
          ]
        },

    }
  },
  mounted(){
    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    this.today = new Date();
    this.chartDataYear.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonth.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeek.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
    this.chartDataYearSelected.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonthSelected.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeekSelected.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
   console.log('toke je',this.token)

    axios.get(devServer.proxy + "/userData", {
      headers: {
        'Authorization' : 'Bearer ' + this.token,
      }
    })
    .then(response => {
      this.loggedUser =response.data
      console.log("Ovaj user je ulogovan:", this.loggedUser)
    
        axios.get(devServer.proxy+"/allReservationAdventures", {
          headers: {
            'Authorization' :'Bearer ' + this.token,
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

    axios.get(devServer.proxy+'/graphicDataFishingInstructor', {
      headers: {
        'Authorization': 'Bearer ' + this.token,
      }
    })
        .then(response => {
          console.log(response.data);
          this.allData = response.data;
          var allYearly = response.data.yearly.allData;
          console.log(allYearly);
          this.chartDataYear.datasets[0].data.push(allYearly.january);
          this.chartDataYear.datasets[0].data.push(allYearly.february);
          this.chartDataYear.datasets[0].data.push(allYearly.march);
          this.chartDataYear.datasets[0].data.push(allYearly.april);
          this.chartDataYear.datasets[0].data.push(allYearly.may);
          this.chartDataYear.datasets[0].data.push(allYearly.june);
          this.chartDataYear.datasets[0].data.push(allYearly.july);
          this.chartDataYear.datasets[0].data.push(allYearly.august);
          this.chartDataYear.datasets[0].data.push(allYearly.september);
          this.chartDataYear.datasets[0].data.push(allYearly.october);
          this.chartDataYear.datasets[0].data.push(allYearly.november);
          this.chartDataYear.datasets[0].data.push(allYearly.december);
          this.yearlyIncomings = allYearly.incomings;
          var allMonthly = response.data.monthly.allData;
          console.log("month all", allMonthly)
          this.chartDataMonth.datasets[0].data.push(allMonthly.one);
          this.chartDataMonth.datasets[0].data.push(allMonthly.two);
          this.chartDataMonth.datasets[0].data.push(allMonthly.three);
          this.chartDataMonth.datasets[0].data.push(allMonthly.four);
          this.chartDataMonth.datasets[0].data.push(allMonthly.five);
          this.chartDataMonth.datasets[0].data.push(allMonthly.six);
          this.chartDataMonth.datasets[0].data.push(allMonthly.seven);
          this.chartDataMonth.datasets[0].data.push(allMonthly.eight);
          this.chartDataMonth.datasets[0].data.push(allMonthly.nine);
          this.chartDataMonth.datasets[0].data.push(allMonthly.ten);
          this.chartDataMonth.datasets[0].data.push(allMonthly.eleven);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twelve);
          this.chartDataMonth.datasets[0].data.push(allMonthly.thirteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.fourteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.fifteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.sixteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.seventeen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.eighteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.nineteen);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twenty);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyOne);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyTwo);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyThree);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyFour);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyFive);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentySix);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentySeven);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyEight);
          this.chartDataMonth.datasets[0].data.push(allMonthly.twentyNine);
          this.chartDataMonth.datasets[0].data.push(allMonthly.thirty);
          this.chartDataMonth.datasets[0].data.push(allMonthly.thirtyOne);
          this.monthlyIncomings = allMonthly.incomings;
          var allWeekly = response.data.weekly.allData;
          console.log("week all", allWeekly);
          this.chartDataWeek.datasets[0].data.push((allWeekly.monday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.tuesday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.wednesday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.thursday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.friday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.saturday));
          this.chartDataWeek.datasets[0].data.push((allWeekly.sunday));
          this.weeklyIncomings = allWeekly.incomings;
          axios.get(devServer.proxy+"/ownersAdventures", {
            headers: {
              'Authorization' :'Bearer ' + this.token,
            }
          })
              .then(response1 => {
                console.log("avanture vlasnika", response1.data)
                this.allEntities= response1.data;
                if(this.allEntities!=null && this.allEntities.length>0){
                  this.selectedEntity = this.allEntities[0];
                  this.changeEntity();
                }
              })
        })
    },
  methods:{
    setFields(){
      this.chartDataYearSelected.datasets[0].data = [];
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.january);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.february);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.march);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.april);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.may);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.june);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.july);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.august);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.september);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.october);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.november);
      this.chartDataYearSelected.datasets[0].data.push(this.yearlySelected.december);
      this.yearlyIncomingsSelected = this.yearlySelected.incomings;
      this.chartDataMonthSelected.datasets[0].data = [];
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.one);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.two);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.three);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.four);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.five);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.six);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.seven);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.eight);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.nine);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.ten);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.eleven);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twelve);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.thirteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.fourteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.fifteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.sixteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.seventeen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.eighteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.nineteen);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twenty);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyOne);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyTwo);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyThree);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyFour);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyFive);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentySix);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentySeven);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyEight);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.twentyNine);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.thirty);
      this.chartDataMonthSelected.datasets[0].data.push(this.monthlySelected.thirtyOne);
      this.monthlyIncomingsSelected = this.monthlySelected.incomings;
      this.chartDataWeekSelected.datasets[0].data=[];
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.monday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.tuesday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.wednesday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.thursday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.friday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.saturday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.sunday));
      this.weeklyIncomingsSelected = this.weeklySelected.incomings;

    },
    changeEntity(){
      for (var a of this.allData.yearly.entityData){
        console.log("a", a)
        if (a.id == this.selectedEntity.id){
          this.yearlySelected = a.entityData;
          break;
        }
      }
      for (var b of this.allData.monthly.entityData){
        if (b.id == this.selectedEntity.id){
          this.monthlySelected = b.entityData;
          break
        }
      }
      for (var c of this.allData.weekly.entityData){
        if (c.id == this.selectedEntity.id){
          this.weeklySelected = c.entityData;
          break;
        }
      }
      this.setFields()
    }
  }
 }
  

</script>
<template>
<div class="boatowner-dashborad">
  <h4>
    Dashboard
  </h4>
  <div class="horizontal" style="border-radius: 20px; border-width: 1pt; border-color: #2c3e50; border-style: solid">
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Yearly review {{today.getFullYear()}}</h4>
      <h5 style="color: #0D184F">Incomings {{this.yearlyIncomings}}€</h5>
      <Bar :chart-data="chartDataYear"/>
    </div>
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Monthly review {{today.getMonth()+1}}.{{today.getFullYear()}}.</h4>
      <h5 style="color: #0D184F">Incomings {{this.monthlyIncomings}}€</h5>
      <Bar :chart-data="chartDataMonth" />
    </div>
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Weekly review</h4>
      <h5 style="color: #0D184F">Incomings in this week {{this.weeklyIncomings}}€</h5>
      <Bar :chart-data="chartDataWeek" />
    </div>
  </div>
  <div v-if="allEntities!=null && allEntities.length>0" style="margin: 2%; font-weight: normal; font-size: 20px">
    <select class="form-select form-select-lg mb-3 custom-select" style="width: 20%" aria-label=".form-select-lg example" v-model="selectedEntity" v-on:change="changeEntity">
      <option v-for="e in allEntities" v-bind:value="e" :key="e.id">
        {{e.name}}
      </option>
    </select>
  </div>
  <div v-else style="margin: 2%; font-weight: normal; font-size: 20px">
    There is no entities!
  </div>
  <div class="horizontal" style="border-radius: 20px; border-width: 1pt; border-color: #2c3e50; border-style: solid">
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Yearly review {{today.getFullYear()}}  for {{selectedEntity.name}}</h4>
      <h5 style="color: #0D184F">Incomings {{this.yearlyIncomingsSelected}}€</h5>
      <Bar :chart-data="chartDataYearSelected"/>
    </div>
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Monthly review {{today.getMonth()+1}}.{{today.getFullYear()}}.  for {{selectedEntity.name}}</h4>
      <h5 style="color: #0D184F">Incomings {{this.monthlyIncomingsSelected}}€</h5>
      <Bar :chart-data="chartDataMonthSelected" />
    </div>
    <div class="column" style="margin: 2%; width: 40%">
      <h4 style="color: #0D184F">Weekly review  for {{selectedEntity.name}}</h4>
      <h5 style="color: #0D184F">Incomings {{this.weeklyIncomingsSelected}}€</h5>
      <Bar :chart-data="chartDataWeekSelected" />
    </div>
  </div>

  <h4>
    Calendar
  </h4>
 
  <FullCalendar :options="calendarOptions" />
</div>

</template>


<style scoped>
.boatowner-dashborad{
  margin: 7%;
  margin-left: 20%;
  margin-right: 5%;
}

.horizontal{
  display: flex;
  flex-direction: row;
}

</style>