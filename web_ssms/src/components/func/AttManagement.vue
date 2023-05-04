<template>
  <div  class="container">
    <div class="block">
      <span class="demonstration">时间选择：</span>
      <el-date-picker
        v-model="moment"
        type="daterange"
        value-format="yyyy-MM-dd"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions">
      </el-date-picker>
      <div style="float: right;">
        <el-button @click="exportButton" type="primary">导出</el-button>
      </div>
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
      moment: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      // 搜索框的值
      search: '',
      attendanceData: []
    }
  },
  created () {
    this.getAttendanceData()
  },
  watch: {
    moment: function (newValue, oldValue) {
      if (newValue !== oldValue) {
        this.getAttendanceData()
      }
    }
  },
  methods: {
    exportButton () {
      this.$axios
        .post('attendance/export', {
          beginDate: this.moment[0],
          endDate: this.moment[1]
        })
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
        .post('attendance/getAttendData', {
          beginDate: this.moment[0],
          endDate: this.moment[1]
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.attendanceData = successResponse.data.object
          } else {
            this.attendanceData = []
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
.block{
  margin-bottom: 20px;
}
.container{
  margin-left:20px;
}
</style>
