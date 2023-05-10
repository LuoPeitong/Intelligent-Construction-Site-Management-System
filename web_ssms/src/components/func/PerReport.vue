<template>
<div>
  <div style="float: left;">
    <span style="margin-left: 5px;">项目：</span>
    <el-select v-model="search" placeholder="请选择">
      <el-option v-for="item in $store.state.projectList" :key="item.id" :label="item.name" :value="item.name"></el-option>
    </el-select>
  </div>
  <el-table :data="PerReport.filter(data => !search || data.projectName.toLowerCase().includes(search.toLowerCase()))"
            fit max-height="565" style="width: 100%" :row-class-name="tableRowClassName">
    <el-table-column label="工号" prop="jobNo"></el-table-column>
    <el-table-column label="姓名" prop="name"></el-table-column>
    <el-table-column label="部门" prop="departmentName"></el-table-column>
    <el-table-column label="工种" prop="profession"></el-table-column>
    <el-table-column label="项目组" prop="projectName"></el-table-column>
    <el-table-column label="状态" prop="isOnline"></el-table-column>
    <el-table-column label="进场时间" prop="enterMoment"></el-table-column>
    <el-table-column label="离场时间" prop="leaveMoment"></el-table-column>
  </el-table>
</div>
</template>

<script>
export default {
  name: 'PerReport',
  data () {
    return {
      PerReport: [],
      search: ''
    }
  },
  created () {
    this.Init()
  },
  methods: {
    Init () {
      this.$axios
        .post('staff/queryPerReport', {
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.PerReport = successResponse.data.object
            // console.log('staffList=', JSON.stringify(this.staffList))
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    tableRowClassName ({row}) {
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
