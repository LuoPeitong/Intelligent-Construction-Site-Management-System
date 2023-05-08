<template>
  <div>
    <div ref="chart" style="width: 100%; height: 350px;"></div>
    <div>
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
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'
import 'echarts/charts'
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
        .then(response => {
          if (response.data.code === 200) {
            this.perInfo = response.data.object.perInfo

            // 计算各工种人数
            const professions = {}
            response.data.object.staffList.forEach((staff) => {
              if (staff.profession in professions) {
                professions[staff.profession]++
              } else {
                professions[staff.profession] = 1
              }
            })

            // 计算在线、离线人数
            let onlineCount = 0
            let offlineCount = 0
            response.data.object.staffList.forEach((staff) => {
              if (staff.isOnline === '在线') {
                onlineCount++
              } else {
                offlineCount++
              }
            })

            // 创建ECharts实例
            const chart = echarts.init(this.$refs.chart)
            // 配置饼图
            const options = {
              title: [{
                text: '各工种人数',
                x: '22%',
                y: 'top'
              }, {
                text: '在线、离线人数',
                x: '72%',
                y: 'top'
              }],
              series: [{
                name: '各工种人数',
                type: 'pie',
                center: ['25%', '50%'],
                radius: ['20%', '40%'],
                label: {
                  formatter: '{b}：{c} ({d}%)'
                },
                data: Object.entries(professions).map(([profession, count]) => ({ name: profession, value: count }))
              }, {
                name: '在线、离线人数',
                type: 'pie',
                center: ['75%', '50%'],
                radius: ['20%', '40%'],
                label: {
                  formatter: '{b}：{c} ({d}%)'
                },
                data: [
                  { name: '在线', value: onlineCount },
                  { name: '离线', value: offlineCount }
                ]
              }]
            }

            // 渲染饼图
            chart.setOption(options)
          } else {
            this.PrintMessage(response.data.message)
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
