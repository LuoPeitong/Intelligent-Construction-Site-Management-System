<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
    }
  },
  mounted () {
    this.getProjectList()
    this.getDepartmentList()
  },
  methods: {
    getDepartmentList () {
      this.$axios
        .post('department/getList', {
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$store.commit('saveDepartmentList', successResponse.data.object)
          }
        })
        .catch(failResponse => {
        })
    },
    getProjectList () {
      this.$axios
        .post('project/getList', {
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$store.commit('saveProjectList', successResponse.data.object)
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.el-menu--collapse .el-menu . el-submenu >>> .el-menu--popup {
  min-width:100px;
}
</style>
