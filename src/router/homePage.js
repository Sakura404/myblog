const home = () => import(/* webpackChunkName: "home" */ '../views/home');
const article = () => import(/* webpackChunkName: "home" */ '../components/post.vue')
const postlist = () => import(/* webpackChunkName: "home" */ '../views/postlist.vue')
const timelines = () => import(/* webpackChunkName: "home" */  '../views/timelines.vue')
const archive = () => import(/* webpackChunkName: "home" */  '../views/archive.vue')
const about = () => import(/* webpackChunkName: "home" */ '../views/about.vue')
const game = () => import(/* webpackChunkName: "home" */ '../views/game.vue')

const homePage = {
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
        path: '/timelines',
        name: 'timelines',
        component: timelines
    }, {
        path: '/archive',
        name: 'archive',
        component: archive
    }, {
        path: '/about',
        name: 'about',
        component: about
    }, {
        path: '/*',
        redirect: '/'
    },]
}
export default homePage