<template>
  <div id="nav">
    <ul>
      <li><dl><router-link to="/">Home</router-link> </dl></li>
      <li><dl><router-link to="/about">About</router-link></dl></li>
      <li style="float:right"><dl><router-link to="/clientRegistration">Register</router-link></dl></li>
      <li style="float:right"><dl><router-link to="/login">Login</router-link></dl></li>
    </ul>
  </div>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  background-color: #dddddd;
}

#nav a.router-link-exact-active {
  color: #42b983;

}
li {
  display: inline;
  float: left;
  border: 1px solid #bbb;
  background-color: #dddddd;
}
ul {
  border: 1px solid #bbb;
  background-color: #dddddd;
}

</style>
