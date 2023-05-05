import App from './App'
import publicFunc from './publicFunc'
// #ifndef VUE3
import Vue from 'vue'
Vue.config.productionTip = false

// 服务器地址
//Vue.prototype.$baseUrl = "http://47.120.42.193:8443/SSMS/"
// 本地地址
Vue.prototype.$baseUrl = "http://localhost:8443/SSMS_war_exploded/"

Vue.use(publicFunc)

App.mpType = 'app'
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif