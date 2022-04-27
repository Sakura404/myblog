// const work = () => import(/* webpackChunkName: "admin" */ '../components/working.vue')
const adminpost = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-post.vue')
// const dashboard = () => import(/* webpackChunkName: "admin" */ '../components/dashboard.vue')
const admin = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin.vue')
const editor = () => import(/* webpackChunkName: "admin" */ '../components/admin/editor.vue')
const adminComment = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-comment.vue')
const adminImage = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-image.vue')

const adminPage = {
    path: '/admin',
    component: admin,
    meta: {
        requireAuth: true
    },
    children: [
        {
            path: 'image',
            name: 'adminImage',
            component: adminImage
        },
        //   {
        //     path: 'dashboard',
        //     name: 'dashboard',
        //     component: dashboard
        // }, 
        // {
        //     path: 'work',
        //     name: 'work',
        //     component: work
        // },
        {
            path: 'post',
            name: 'adminpost',
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
}
export default adminPage