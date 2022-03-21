import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework'

Vue.use(Vuetify);
const opts = {}


export default new Vuetify({
    icons: {
        iconfont: 'mdi', // 默认值 - 仅用于展示目的
    }, opts
});
