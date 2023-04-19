<template>
  <el-table :data="perInfo.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
            style="width: 100%" max-height="565" :row-class-name="tableRowClassName">
    <el-table-column prop="name" label="姓名"></el-table-column>
    <el-table-column prop="jobNo" label="工号"></el-table-column>
    <el-table-column prop="departmentName" width="180" label="部门"></el-table-column>
    <el-table-column prop="projectName" width="180" label="项目组"></el-table-column>
    <el-table-column prop="isOnline" label="连接终端状态" width="180"></el-table-column>
    <el-table-column prop="location" label="个人地理定位信息" width="180"></el-table-column>
    <el-table-column align="right">
      <template slot="header" slot-scope="scope">
        <el-input v-model="search" size="mini" placeholder="输入姓名搜索"/>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: 'PerInfoMonitoring',
  data () {
    return {
      perInfo: [],
      search: ''
    }
  },
  mounted () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      this.$axios
        .post('trajectory/getInfo', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.perInfo = successResponse.data.object
          }
        })
        .catch(failResponse => {
        })
    },
    tableRowClassName ({row}) {
      // console.log('row=', JSON.stringify(row))
      if (row.isOnline === '离线') {
        return 'warning-row'
      }
      return ''
    }
  }
}
</script>

<style scoped>
.el-table >>> .warning-row {
  background: oldlace;
}
</style>
