<template>
  <el-menu
    id="menu"
    default-active="2"
    unique-opened="true"
    class="el-menu-vertical-demo"
    background-color="#545c64"
    text-color="#fff"
    text-align="left"
    active-text-color="#ffd04b">

    <el-submenu :index="nav.id" :popper-append-to-body="false" v-for="(nav, key) in NavbarList" :key="key">
      <template slot="title">
        <i :class="nav.img"></i>
        <span>{{ nav.title }}</span>
      </template>
      <el-menu-item style="min-width: 100px" :index="s_nav.id" v-for="(s_nav, s_key) in nav.subMenu" :key="s_key" @click="active(s_nav)">
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
    active (nav) {
      this.$store.commit('addEditableTabs', nav)
      // console.log('nav=', JSON.stringify(this.$store.state.editableTabs))
      this.$store.commit('saveEditableTabsValue', nav.id)
    },
    getMenu () {
      this.$axios
        .post('menu/getMenu', {})
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
  min-width: 100px ;
}
</style>
