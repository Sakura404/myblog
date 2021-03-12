import {
	createApp
} from 'vue'
import App from './App.vue'
import './plugins/element.js'
import installElementPlus from './plugins/element'
import installaxios from'./plugins/axios.js'
import 'mdui/dist/js/mdui.min.js'
import 'mdui/dist/css/mdui.min.css'
const app = createApp(App)
installElementPlus(app)
installaxios(app)
app.mount('#app')


