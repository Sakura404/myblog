import 'mdui/dist/js/mdui.min.js'
import 'mdui/dist/css/mdui.min.css'
import App from './App.vue'
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
new Vue({
  render: h => h(App)
}).$mount('#app')
