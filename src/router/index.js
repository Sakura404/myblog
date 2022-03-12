import Vue from 'vue'
import VueRouter from 'vue-router'
import work from '../components/working.vue'
import adminpost from '../components/admin/admin-post.vue'
import dashboard from '../components/dashboard.vue'
import admin from '../components/admin/admin.vue'
import home from '../views/home.vue'
import editor from '../components/admin/editor.vue'
import article from '../components/post.vue'
import postlist from '../views/postlist.vue'
import login from '../views/login.vue'
import game from '../views/game.vue'
import adminComment from '../components/admin/admin-comment.vue'
import adminImage from '../components/admin/admin-image.vue'
Vue.use(VueRouter)

const routes = [
    {
        path: '/admin',
        component: admin,
        meta: {
            requireAuth: true
        },
        children: [{
            path: 'dashboard',
            name: 'dashboard',
            component: dashboard
        }, {
            path: 'image',
            name: 'adminImage',
            component: adminImage
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
            path: 'editor/:id',
            name: 'editorwithid',
            component: editor
        },
        {
            path: 'editor',
            name: 'editor',
            component: editor
        },
        {
            path: 'comment',
            name: 'adminComment',
            component: adminComment
        },
        {
            path: '/',
            redirect: 'post'
        }]
    }, {
        path: '/login',
        name: 'login',
        component: login
    }, {
        path: '/',
        component: home,
        children: [{
            path: '/',
            name: 'home',
            component: postlist,
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
    mode: 'history',
    base: process.env.BASE_URL,
    scrollBehavior() {
        return { x: 0, y: 0 }
    }
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(res => res.meta.requireAuth)) { // 验证是否需要登陆
        var id = window.sessionStorage.getItem('token');

        if (id) { // 查询本地存储信息是否已经登陆 
            console.log(id)
            next();
        } else {
            next({
                path: '/login', // 未登录则跳转至login页面
                query: {
                    redirect: (to.fullPath)// 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面
                }
            });
        }
    } else {
        next();
    }
})

export default router
