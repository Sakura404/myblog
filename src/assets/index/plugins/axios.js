import axios from 'axios'
import VueAxios from 'vue-axios'
export default (app) => {
	app.use(VueAxios, axios)
}
