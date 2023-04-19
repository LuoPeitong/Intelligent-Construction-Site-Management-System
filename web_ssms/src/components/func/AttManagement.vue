<template>
  <div>
    <div style="float: right;">
      <el-button @click="exportButton" type="primary">导出</el-button>
    </div>
    <el-table :data="attendanceData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              stripe
              max-height="565" style="width: 100%">
      <el-table-column label="工号" prop="jobNo"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      <el-table-column label="日期" prop="moment"></el-table-column>
      <el-table-column label="进场时间" prop="enterMoment"></el-table-column>
      <el-table-column label="离场时间" prop="leaveMoment"></el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" placeholder="输入姓名搜索"/>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'AttManagement',
  data () {
    return {
      // 搜索框的值
      search: '',
      attendanceData: []
    }
  },
  mounted () {
    this.getAttendanceData()
  },
  methods: {
    exportButton () {
      this.$axios
        .post('attendance/export', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            const h = this.$createElement
            this.$notify({
              title: '成功',
              message: h('i', {style: 'color: teal'}, successResponse.data.message)
            })
          }
        })
        .catch(failResponse => {
        })
    },
    getAttendanceData () {
      this.$axios
        .post('attendance/findAll', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.attendanceData = successResponse.data.object
            // console.log('attendanceData=', JSON.stringify(this.attendanceData))
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>

</style>
