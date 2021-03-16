import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import './styles.scss'
import vuetify from './plugins/vuetify';

Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  vuetify,
  render: h => h(App)
}).$mount('#app')
