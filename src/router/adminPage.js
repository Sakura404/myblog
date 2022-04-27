// const work = () => import(/* webpackChunkName: "admin" */ '../components/working.vue')
const adminPost = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-post.vue')
// const dashboard = () => import(/* webpackChunkName: "admin" */ '../components/dashboard.vue')
const admin = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin.vue')
const editor = () => import(/* webpackChunkName: "admin" */ '../components/admin/editor.vue')
const adminComment = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-comment.vue')
const adminImage = () => import(/* webpackChunkName: "admin" */ '../components/admin/admin-image.vue')
const adminPhrase = () => import(/* webpackChunkName: "admin"*/'../components/admin/admin-phrase.vue')
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
         name: 'adminPost',
         component: adminPost
      },
      {
         path: 'phrase',
         name: 'adminPhrase',
         component: adminPhrase
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