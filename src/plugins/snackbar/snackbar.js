import SnackbarComponent from './snackbar.vue'   
export default {    // 导出一个对象，里面包含vue注册插件所调用的方法install
    install: function (Vue) {
        const SnackbarConstructor = Vue.extend(SnackbarComponent)    
        const instance = new SnackbarConstructor();    // 生成一个该子类的实例

        instance.$mount()    // 将这个实例挂载在新创建的div上，并加入到body中
        document.querySelector("#blogSncakbar").appendChild(instance.$el)
        const $snackbar = {
            success: ((mes) => {
                if (instance.visible) return;
                instance.message = mes
                instance.type = "success"
                instance.visible = true
            }), error: ((mes) => {
                if (instance.visible) return;
                instance.message = mes
                instance.type = "error"
                instance.visible = true
            }), info: ((mes) => {
                if (instance.visible) return;
                instance.message = mes
                instance.type = "info"
                instance.visible = true
            }), warning: ((mes) => {
                if (instance.visible) return;
                instance.message = mes
                instance.type = "warning"
                instance.visible = true
            })
        }
        Vue.prototype.$snackbar = $snackbar
    }
}