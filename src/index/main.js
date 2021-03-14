import {
	createApp
} from 'vue'
import App from './App.vue'
import installaxios from'./plugins/axios.js'
import 'mdui/dist/js/mdui.min.js'
import 'mdui/dist/css/mdui.min.css'
const app = createApp(App)

installaxios(app)
app.mount('#app')



