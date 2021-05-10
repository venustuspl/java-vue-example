import Vue from 'vue'
import App from './App.vue'
import { BootstrapVue } from 'bootstrap-vue'
import Vuelidate from "vuelidate";
import MyMap from './MyMap'

Vue.config.productionTip = true
Vue.component('my-map', MyMap)
Vue.use(Vuelidate, BootstrapVue);



new Vue({
  el: '#mapapp',
  render: h => h(App),
}).$mount('#app')




