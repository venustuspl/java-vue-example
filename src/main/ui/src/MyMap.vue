<template>
  <div>
    <h1>Map</h1>
    <map-loader 
      :map-config="mapConfig"
      apiKey="AIzaSyDa-AkHL-hoDu1O-hkDyYp027RErzli3MA">

    </map-loader>
  </div>
</template>
<script>
import MapLoader from "./components/MapLoader.vue"


export default {
  props: {
    markers: Array
  },
  data(){
    return {
      mapConfig: {
      center: {lat: 52.237049, lng: 21.017532},
      mapTypeId: 'hybrid',
     zoom: 12
      }
    }
  },
  components: {
    MapLoader
  }
}
</script>

