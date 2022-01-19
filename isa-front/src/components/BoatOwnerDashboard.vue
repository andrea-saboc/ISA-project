<template>
<div>
  Dashboard
</div>
</template>

<script>
export default {
  name: "BoatOwnerDashboard",
  data: function (){
    return{
      user: null
    }
  },
  mounted(){
    this.user = this.$store.state.userType;
  }
}

</script>

<style scoped>

</style>