import moment from 'moment'
const momentVue = {
    install(Vue) {
        Vue.myGlobalMethod = function () {  // 1. 添加全局方法或属性，如: vue-custom-element
            moment()   // 逻辑...
          }  
    }
}
export default momentVue