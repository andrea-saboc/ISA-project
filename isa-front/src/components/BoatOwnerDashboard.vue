<template>
<div class="boatowner-dashborad">
  <h4>
    Dashboard
  </h4>
  <div class="horizontal">
    <CCol :md="6" class="mb-4" style="width: 50%">
      <CCard>
        <CCardHeader> Line Chart </CCardHeader>
        <CCardBody>
          <CChartLine :data="defaultData" />
        </CCardBody>
      </CCard>
    </CCol>
    <CCol :md="6" class="mb-4" style="width: 50%">
      <CCard>
        <CCardHeader>Bar Chart</CCardHeader>
        <CCardBody><CChartBar :data="defaultData" /></CCardBody>
      </CCard>
    </CCol>
  </div>
</div>
</template>

<script>
import { CChartBar } from '@coreui/vue-chartjs'
import { CChartLine} from  '@coreui/vue-chartjs'
export default {
  name: "BoatOwnerDashboard",
  components: { CChartBar, CChartLine },
  computed: {
    defaultData() {
      return {
        labels: [
          'January',
          'February',
          'March',
          'April',
          'May',
          'June',
          'July',
          'August',
          'September',
          'October',
          'November',
          'December',
        ],
        datasets: [
          {
            label: 'GitHub Commits',
            backgroundColor: '#5a75cb',
            data: [40, 20, 12, 39, 10, 40, 39, 80, 40, 20, 12, 12],
          },
        ],
      }
    },
  },
  data: function (){
    return{
      user: null
    }
  },
  mounted(){
    this.user = this.$store.state.userType;
    this.computed.defaultData().datasets.label = 'Incomings'
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
    this.computed.defaultData().datasets.data().push(12)
  }
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