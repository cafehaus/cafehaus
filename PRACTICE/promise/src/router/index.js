import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import('@/views/home.vue'),
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/About.vue'),
  },
  {
    path: '/img-load',
    name: 'img-load',
    component: () => import('@/views/img-load.vue'),
  },
  {
    path: '/gift',
    name: 'gift',
    component: () => import('@/views/gift.vue'),
  },
  {
    path: '/traffic-light',
    name: 'traffic-light',
    component: () => import('@/views/traffic-light.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
