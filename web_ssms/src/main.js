// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import store from './store/index'
import 'element-ui/lib/theme-chalk/index.css'
import publicFunc from './publicFunc'

// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
Vue.prototype.$axios = axios

// 设置反向代理，前端请求默认发送到 http://localhost:8443/SSMS_war_exploded/
// axios.defaults.baseURL = 'http://47.120.42.193:8443/SSMS/'
axios.defaults.baseURL = 'http://localhost:8443/SSMS_war_exploded/'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(publicFunc)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
