<template>
  <div class="boatowner-dashborad">
    <h4>
      Dashboard
    </h4>
    <div style="border-radius: 20px; border-width: 1pt; border-color: #2c3e50; border-style: solid">
      <div class="horizontal" >
        <div class="column" style="margin: 2%; width: 40%">
          <h4 style="color: #0D184F">Yearly review {{today.getFullYear()}}</h4>
          <h5 style="color: #0D184F">Incomings in this year {{this.yearlyIncomings}}€</h5>
          <Bar :chart-data="chartDataYear" />
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <h4 style="color: #0D184F">Monthly review {{today.getMonth()+1}}.{{today.getFullYear()}}.</h4>
          <h5 style="color: #0D184F">Incomings in this month {{this.monthlyIncomings}}€</h5>
          <Bar :chart-data="chartDataMonth" />
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <h4 style="color: #0D184F">Weekly review</h4>
          <h5 style="color: #0D184F">Incomings in this week {{this.weeklyIncomings}}€</h5>
          <Bar :chart-data="chartDataWeek" />
        </div>
      </div>
      <div class="horizontal" >
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataYeari" />
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataMonthi" />
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataWeeki" />
        </div>
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
    <div  style="border-radius: 20px; border-width: 1pt; border-color: #2c3e50; border-style: solid">
      <div class="horizontal">
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
      <div class="horizontal">
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataYearSelectedi"/>
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataMonthSelectedi" />
        </div>
        <div class="column" style="margin: 2%; width: 40%">
          <Line :chart-data="chartDataWeekSelectedi" />
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {devServer} from "../../../vue.config";
import { Bar, Line } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement,LineElement,PointElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement,PointElement,LineElement, CategoryScale, LinearScale)

export default {
  name: "MansionOwnerDashboard",
  components: {Bar, Line},
  data: function (){
    return {
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
      },
      chartDataYearSelectedi: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for year selected',
            backgroundColor: '#5350D5',
            data: []
          }
        ]
      },
      chartDataMonthSelectedi: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for current month selected',
            backgroundColor: '#D56850',
            data: []
          }
        ]
      },
      chartDataWeekSelectedi: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for current week selected',
            backgroundColor: '#48bf91',
            data: []
          }
        ]
      },
      token : null,
      today : new Date(),
      datase: [],
      yearlyIncomings: 0,
      monthlyIncomings: 0,
      weeklyIncomings: 0,
      chartDataYear: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for year all mansions',
            backgroundColor: '#5350D5',
            data: []
          }
        ]
      },
      chartDataMonth: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for current month all mansions',
            backgroundColor: '#D56850',
            data: []
          }
        ]
      },
      chartDataWeek: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Reservations for current week all mansions',
            backgroundColor: '#48bf91',
            data: []
          }
        ]
      },
      chartDataYeari: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for year',
            backgroundColor: '#5350D5',
            data: []
          }
        ]
      },
      chartDataMonthi: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for current month',
            backgroundColor: '#D56850',
            data: []
          }
        ]
      },
      chartDataWeeki: {
        labels: [ 'January', 'February', 'March'],
        datasets: [
          {
            label: 'Incomings for current week',
            backgroundColor: '#48bf91',
            data: []
          }
        ]
      },
    }
  },
  mounted(){
    this.today = new Date();
    this.chartDataYear.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonth.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeek.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
    this.chartDataYeari.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonthi.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeeki.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
    this.chartDataYearSelected.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonthSelected.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeekSelected.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]
    this.chartDataYearSelectedi.labels = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    this.chartDataMonthSelectedi.labels= [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
    this.chartDataWeekSelectedi.labels = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" ]

    this.token = localStorage.getItem('token').substring(1, localStorage.getItem('token').length-1);
    axios.get(devServer.proxy+'/graphicDataMansionOwner', {
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
          this.chartDataYeari.datasets[0].data.push(allYearly.januaryi);
          this.chartDataYeari.datasets[0].data.push(allYearly.februaryi);
          this.chartDataYeari.datasets[0].data.push(allYearly.marchi);
          this.chartDataYeari.datasets[0].data.push(allYearly.aprili);
          this.chartDataYeari.datasets[0].data.push(allYearly.mayi);
          this.chartDataYeari.datasets[0].data.push(allYearly.junei);
          this.chartDataYeari.datasets[0].data.push(allYearly.julyi);
          this.chartDataYeari.datasets[0].data.push(allYearly.augusti);
          this.chartDataYeari.datasets[0].data.push(allYearly.septemberi);
          this.chartDataYeari.datasets[0].data.push(allYearly.octoberi);
          this.chartDataYeari.datasets[0].data.push(allYearly.novemberi);
          this.chartDataYeari.datasets[0].data.push(allYearly.decemberi);
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
          this.chartDataMonthi.datasets[0].data.push(allMonthly.onei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twoi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.threei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.fouri);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.fivei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.sixi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.seveni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.eighti);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.ninei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.teni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.eleveni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twelvei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.thirteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.fourteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.fifteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.sixteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.seventeeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.eighteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.nineteeni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyOnei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyTwoi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyThreei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyFouri);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyFivei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentySixi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentySeveni);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyEighti);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.twentyNinei);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.thirtyi);
          this.chartDataMonthi.datasets[0].data.push(allMonthly.thirtyOnei);
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
          this.chartDataWeeki.datasets[0].data.push((allWeekly.mondayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.tuesdayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.wednesdayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.thursdayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.fridayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.saturdayi));
          this.chartDataWeeki.datasets[0].data.push((allWeekly.sundayi));
          this.weeklyIncomings = allWeekly.incomings;
          axios.get(devServer.proxy+"/ownersMansions", {
            headers: {
              'Authorization' :'Bearer ' + this.token,
            }
          })
              .then(response1 => {
                console.log("vikendice vlasnika", response1.data)
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
      this.chartDataYearSelectedi.datasets[0].data = [];

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
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.januaryi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.februaryi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.marchi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.aprili);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.mayi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.junei);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.julyi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.augusti);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.septemberi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.octoberi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.novemberi);
      this.chartDataYearSelectedi.datasets[0].data.push(this.yearlySelected.decemberi);
      this.yearlyIncomingsSelected = this.yearlySelected.incomings;
      this.chartDataMonthSelected.datasets[0].data = [];
      this.chartDataMonthSelectedi.datasets[0].data = [];
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
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.onei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twoi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.threei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.fouri);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.fivei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.sixi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.seveni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.eighti);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.ninei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.teni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.eleveni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twelvei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.thirteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.fourteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.fifteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.sixteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.seventeeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.eighteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.nineteeni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyOnei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyTwoi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyThreei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyFouri);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyFivei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentySixi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentySeveni);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyEighti);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.twentyNinei);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.thirtyi);
      this.chartDataMonthSelectedi.datasets[0].data.push(this.monthlySelected.thirtyOnei);
      this.monthlyIncomingsSelected = this.monthlySelected.incomings;
      this.chartDataWeekSelected.datasets[0].data=[];
      this.chartDataWeekSelectedi.datasets[0].data=[];
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.monday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.tuesday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.wednesday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.thursday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.friday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.saturday));
      this.chartDataWeekSelected.datasets[0].data.push((this.weeklySelected.sunday));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.mondayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.tuesdayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.wednesdayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.thursdayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.fridayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.saturdayi));
      this.chartDataWeekSelectedi.datasets[0].data.push((this.weeklySelected.sundayi));
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
  },

}
</script>

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