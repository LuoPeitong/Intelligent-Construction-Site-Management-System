<template>
  <el-menu
    id="menu"
    default-active="2"
    unique-opened
    class="el-menu-vertical-demo"
    background-color="#545c64"
    text-color="#fff"
    text-align="left"
    active-text-color="#ffd04b">
    <el-menu-item @click="logout">退出登录</el-menu-item>
    <el-submenu :index="nav.id" :popper-append-to-body="false" v-for="(nav, key) in NavbarList" :key="key">
      <template slot="title">
        <i :class="nav.img"></i>
        <span>{{ nav.title }}</span>
      </template>
      <el-menu-item style="min-width: 100px" :index="s_nav.id" v-for="(s_nav, s_key) in nav.subMenu"
                    :key="s_key" @click="active(s_nav)">
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
      // console.log('nav=', JSON.stringify(this.$store.state.editableTabs))
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
