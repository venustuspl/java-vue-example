<template>
  <div id="app">
    <Dashboard />
    <MyMap />
  </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Dashboard from './components/Dashboard.vue'
import MyMap from './MyMap.vue'

export default {
  name: 'App',
  components: {
    Dashboard,
    MyMap
  }
}
</script>

<style>
  @import './assets/styles/global.css';
</style>