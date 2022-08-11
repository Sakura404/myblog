import Vue from 'vue'
import App from './App.vue'

import vuetify from './plugins/vuetify.js'
import router from './router/index.js'
import './styles.scss'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Prism from 'prismjs';
import randomImg from './plugins/randomImg.js'
import Moment from 'moment'
import 'moment/locale/zh-cn'

Vue.use(Prism)
Prism.highlightAll();
Vue.prototype.$randomImg = randomImg
Vue.prototype.$Moment = Moment
Vue.use(VueAxios, axios)
Vue.config.productionTip = false
Vue.use(vuetify);
new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app')
