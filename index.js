import Vue from 'vue'
import VueRouter from 'vue-router'
//vuetify提供的定位功能，回到上一个路由的时候可以定位到之前的位置
import goTo from 'vuetify/es5/services/goto'
import Index from '../views/Index.vue'
import Layout from '../views/Layout.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    name: 'Index',
    component: Index
  },
  {
    path: '/',
    component: Layout,
    redirect: '/tag',
    children: [
      {
        path: 'news',
        name: 'News',
        component: () => import('../views/News.vue')
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../views/Message.vue')
      },
      {
        path: 'category',
        name: 'Category',
        component: () => import('../views/Category.vue')
      },
      {
        path: 'achieve',
        name: 'Achieve',
        component: () => import('../views/Achieve.vue')
      },
      {
        path: 'tag',
        name: 'Tag',
        component: () => import('../views/Tag.vue')
      },
      {
        path: 'friend',
        name: 'Friend',
        component: () => import('../views/Friend.vue')
      },
      {
        path: 'my',
        name: 'My',
        component: () => import('../views/My.vue'),
        redirect: '/my/userinfo',
        children: [
          {
            path: 'userinfo',
            name: 'UserInfo',
            component: () => import('../views/UserInfo.vue')
          },
          {
            path: 'usersafe',
            name: 'UserSafe',
            component: () => import('../views/UserSafe.vue')
          },
          {
            path: 'feedback',
            name: 'FeedBack',
            component: () => import('../views/FeedBack.vue')
          },
          {
            path: 'about',
            name: 'About',
            component: () => import('../views/About.vue')
          }
        ]
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/404',
    name: 'Page404',
    component: () => import('../views/Page404.vue')
  }
]

const router = new VueRouter({
  scrollBehavior: (to, from, savedPosition) => {
    let scrollTo = 0;

    if(to.hash) {
      scrollTo = to.hash
    } else if (savedPosition) {
      scrollTo = savedPosition.y
    }
    return goTo(scrollTo)
  },
  routes
})

export default router
