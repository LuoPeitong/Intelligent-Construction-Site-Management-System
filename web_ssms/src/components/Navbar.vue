<template>
  <el-menu id="menu" class="el-menu-vertical-demo" default-active="2" background-color="#545c64" text-color="#fff" text-align="left" active-text-color="#ffd04b" unique-opened>
    <el-menu-item @click="logout">退出登录</el-menu-item>
    <el-submenu v-for="(nav, key) in NavbarList" :index="nav.id" :key="key" :popper-append-to-body="false">
      <template slot="title">
        <i :class="nav.img"></i>
        <span>{{ nav.title }}</span>
      </template>
      <el-menu-item v-for="(s_nav, s_key) in nav.subMenu" :index="s_nav.id" :key="s_key" @click="active(s_nav)" style="min-width: 100px">
        <span>{{ s_nav.title }}</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: 'Navbar',
  data () {
    return {
      NavbarList: []
    }
  },
  mounted () {
    this.getMenu()
  },
  methods: {
    logout () {
      this.$router.replace({path: '/'})
    },
    active (nav) {
      this.$store.commit('addEditableTabs', nav)
      this.$store.commit('saveEditableTabsValue', nav.id)
    },
    getMenu () {
      const staff = JSON.parse(sessionStorage.getItem('staff'))
      const jobNo = staff.jobNo
      this.$axios
        .post('menu/getMenu', {
          jobNo: jobNo
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.NavbarList = successResponse.data.object
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style>
.el-submenu .el-menu-item {
  min-width: 100px;
}
</style>
