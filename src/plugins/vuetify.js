import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework'
import '@mdi/font/css/materialdesignicons.css' // Ensure you are using css-loader

Vue.use(Vuetify);
const opts = {}


export default new Vuetify({
   icons: {
      iconfont: 'mdi', // 默认值 - 仅用于展示目的
   }, opts
});
