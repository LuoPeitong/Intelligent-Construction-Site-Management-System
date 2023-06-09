import Vue from 'vue'
import Router from 'vue-router'

// 导入自己写的组件
import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    // 下面都是固定的写法
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: AppIndex
    }
  ]
})
