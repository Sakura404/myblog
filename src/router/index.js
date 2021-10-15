import Vue from 'vue'
import VueRouter from 'vue-router'
import work from '../components/working.vue'
import adminpost from '../components/admin-post.vue'
import dashboard from '../components/dashboard.vue'
import navm from '../components/navm.vue'
import home from '../views/home.vue'
import edior from '../components/edior.vue'
import article from '../components/post.vue'
import homepage from '../views/postpage.vue'
import game from '../views/game.vue'
Vue.use(VueRouter)

const routes = [{
    path: '/admin',
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
        component: adminpost
    },
    {
        path: 'edior',
        name: 'edior',
        component: edior
    },
    {
        path: '/',
        redirect: 'dashboard'
    }]
}, {
    path: '/',
    component: home,
    children: [{
        path: '/',
        name: 'home',
        component: homepage,
    }, {
        path: '/post/:id',
        name: 'post',
        component: article
    }, {
        path: '/game',
        name: 'game',
        component: game
    }, {
        path: '/*',
        redirect: '/'
    }]
}, {
    path: '/*',
    redirect: '/'
}
]

const router = new VueRouter({
    routes,
    base: process.env.BASE_URL,
    scrollBehavior() {
        return { x: 0, y: 0 }
    }
})

export default router
