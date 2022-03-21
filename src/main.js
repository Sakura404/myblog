import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/index.js'
import tinymce from 'tinymce'
import VueTinymce from '@packy-tang/vue-tinymce'
import './styles.scss'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Prism from 'prismjs';
import randomImg from './plugins/randomImg.js'

Vue.use(Prism)
Prism.highlightAll();
Vue.prototype.$randomImg = randomImg
Vue.use(VueAxios, axios)


Vue.prototype.$tinymce = tinymce // 将全局tinymce对象指向给Vue作用域下
Vue.use(VueTinymce)              // 安装vue的tinymce组件

Vue.config.productionTip = false
new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app')
