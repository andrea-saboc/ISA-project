<template>

<div id="profileInfo">
<h4>Change your info :</h4>
<table>
   <tr>
      <td> <label>Name:</label></td>
      <td><input class="infoInput" type="text" v-model="name"></td>
      <td><button>Change</button></td>
   </tr>
   <tr>
      <td> <label>Surname:</label></td>
      <td><input type="text" v-model="surname"></td>
      <td><button>Change</button></td>
   </tr>
   <tr>
      <td> <label>Address:</label></td>
      <td><input type="text" v-model="address"></td>
      <td><button>Change</button></td>
   </tr>
   <tr>
      <td> <label>City:</label></td>
      <td><input type="text" v-model="city"></td>
      <td><button>Change</button></td>
   </tr>
   <tr>
      <td> <label>Country:</label></td>
      <td><input type="text" v-model="country"></td>
      <td><button>Change</button></td>
   </tr>
   <tr>
      <td> <label>Phone number:</label></td>
      <td><input type="text" v-model="phoneNumber"></td>
      <td><button>Change</button></td>
   </tr>
</table>
<h4>Change your password :</h4>
<table>
   <tr>
      <td> <label>Enter your old password:</label></td>
      <td><input type="password" v-model="password"></td>
   </tr>
   <tr>
      <td> <label>Enter your new password:</label></td>
      <td><input type="password" v-model="newPasswordRepeated"></td>
   </tr>
   <tr>
      <td> <label>Reenter your new password:</label></td>
      <td><input type="password" v-model="newPassword"></td>
   </tr>
   <tr>
      <td></td>
      <td><button>Change</button></td>
   </tr>
</table>
</div>
</template>

<script>
export default{



}

</script>

<style>

  #profileInfo{
      text-align:center;
  }

  #profileInfo input{
    width: 300px;
	padding:10px 10px 10px 5px;
  display:block;
  width:300px;
  border:none;
  border-bottom:1px solid #757575;
  }

  #profileInfo table{
    margin-left: auto;
    margin-right: auto;
  }

</style>