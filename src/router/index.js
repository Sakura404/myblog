import Vue from 'vue'
import VueRouter from 'vue-router'
import work from '../components/working.vue'
import post from '../components/post.vue'
import dashboard from '../components/dashboard.vue'
import navm from '../components/navm.vue'
import home from '../components/home.vue'
Vue.use(VueRouter)

const routes = [{
    path: '/admin',
    name: 'admin',
    component: navm,
    children: [{
        path: 'dashboard',
        name: 'dashboard',
        component: dashboard
    },
    {
        path: 'work',
        name: 'work',
        component: work
    },
    {
        path: 'post',
        name: 'post',
        component: post
    }
        ,
    {
        path: '/',
        redirect: 'dashboard'
    }]
}, {
    path: '/home',
    name: 'home',
    component: home
}, {
    path: '/',
    redirect: '/home'
}
]

const router = new VueRouter({
    routes
})

export default router