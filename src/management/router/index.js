import Vue from 'vue'
import VueRouter from 'vue-router'
import work from '../components/working.vue'
import post from '../components/post.vue'
import home from '../components/home.vue'

Vue.use(VueRouter)

const routes = [{
		path: '/home',
		name: 'Home',
		component: home
	},
	{
		path: '/work',
		name: 'work',
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: work
	},
	{
		path:'/post',
		name:'post',
		component: post
	}
	,
	{
		path: '/',
		redirect: '/home'
	}
]

const router = new VueRouter({
	routes
})

export default router
