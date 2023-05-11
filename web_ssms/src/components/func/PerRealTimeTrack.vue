<template>
  <div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-left: 5px;">部门：</span>
      <el-select v-model="search" placeholder="请选择">
        <el-option v-for="item in $store.state.departmentList" :key="item.id" :label="item.name" :value="item.name"></el-option>
      </el-select>
      <el-form-item label="姓名">
        <el-input v-model="form.name" placeholder="姓名" disabled></el-input>
      </el-form-item>
      <el-form-item label="工号">
        <el-input v-model="form.jobNo" placeholder="工号" disabled></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
    <div id="mapContainer"></div>
    <el-table :data="staffData.filter(data => !search || data.departmentName.toLowerCase().includes(search.toLowerCase()))"
              fit stripe height="400px" style="width: 100%">
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="jobNo" label="工号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="profession" label="工种"></el-table-column>
      <el-table-column prop="departmentName" label="部门"></el-table-column>
      <el-table-column prop="projectName" label="项目组"></el-table-column>
    </el-table>
  </div>
</template>

<script>
const AMap = window.AMap
export default {
  name: 'PerRealTimeTrack',
  data () {
    return {
      staffData: [],
      form: {
        name: '',
        jobNo: '',
        profession: '',
        departmentName: '',
        projectName: '',
        locations: ''
      },
      trackArray: [[118.193825, 24.498463]],
      search: ''
    }
  },
  created () {
    this.Init()
  },
  mounted () {
    this.mapInit()
  },
  methods: {
    mapInit () {
      // 调用高德地图api，将点连成线
      const polyline = new AMap.Polyline({
        path: this.trackArray,
        strokeColor: '#3366FF',
        strokeOpacity: 1,
        strokeWeight: 5
      })

      // 创建地图实例
      this.map = new AMap.Map('mapContainer', {
        zoom: 13, // 初始缩放级别
        center: this.trackArray[0] // 初始中心点坐标
      })

      // 将线条添加到地图上
      this.map.add([polyline])

      // 自适应显示线条
      this.map.setFitView([polyline])
    },
    reset () {
      this.form = {}
      this.trackArray = [[118.193825, 24.498463]]
      this.search = ''
    },
    handleClick (row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.trackArray = this.form.locations.split(';').map(item => item.split(','))
      this.mapInit()
    },
    Init () {
      this.$axios
        .post('trajectory/realTimeTrack', {})
        .then(res => {
          if (res.data.code === 200) {
            this.staffData = res.data.object
          } else {
            this.PrintMessage(res.data.code, res.data.message)
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>
#mapContainer {
  width: 100%;
  height:400px;
}
</style>
