<template>
  <div>
    <el-form :inline="true" :model="editArea" class="demo-form-inline">
      <el-form-item label="区域名称">
        <el-input v-model="editArea.name" placeholder="区域名称"></el-input>
      </el-form-item>
      <el-form-item label="区域类型">
        <el-select v-model="editArea.classes" placeholder="区域类型">
          <el-option label="活动区域" value="1"></el-option>
          <el-option label="禁入区域" value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="区域坐标">
        <el-input v-model="editArea.region" placeholder="区域坐标" class="regionInput"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addArea">保存</el-button>
        <el-button type="primary" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
    <div id="mapContainer" v-if="path.length"></div>
    <mapPage id="mapPage" v-else/>
    <el-form :inline="true" :model="queryArea" class="demo-form-inline">
      <el-form-item label="区域名称">
        <el-input v-model="queryArea.name" placeholder="区域名称"></el-input>
      </el-form-item>
      <el-form-item label="区域类型">
        <el-select v-model="queryArea.classes" placeholder="区域类型">
          <el-option label="活动区域" value="1"></el-option>
          <el-option label="禁入区域" value="0"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-table :data="tableData.filter(data => (!queryArea.name || data.name.toLowerCase().includes(queryArea.name.toLowerCase()))&&(!queryArea.classes || data.classes.toLowerCase().includes(queryArea.classes.toLowerCase())))"
              fit stripe height="600px" style="width: 100%">
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button @click="handleDelete(scope.row)" type="danger" size="mini">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="序号"></el-table-column>
      <el-table-column prop="classes" label="类型">
        <template slot-scope="{ row }">
          {{ row.classes == 1 ? '活动区域' : '禁止区域' }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="区域名称"></el-table-column>
      <el-table-column prop="createBy" label="操作人"></el-table-column>
      <el-table-column prop="createTime" label="操作时间"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import mapPage from '@/components/mapPage'
const AMap = window.AMap
export default {
  name: 'RegionalManagement',
  components: { mapPage },
  data () {
    return {
      editArea: {
        name: '',
        region: '',
        classes: '',
        createBy: '',
        createTime: ''
      },
      queryArea: {
        name: '',
        classes: ''
      },
      tableData: [{}],
      staff: {},
      path: [],
      map: null,
      markers: [],
      polygon: null
    }
  },
  methods: {
    onSubmit () {
      console.log(JSON.stringify(this.editArea))
    },
    reset () {
      this.editArea = {name: '', region: '', classes: '', createBy: '', createTime: ''}
      this.queryArea = {name: '', classes: ''}
      this.path = []
    },
    handleClick (row) {
      // 深拷贝，避免修改editArea时，row也变化
      this.editArea = JSON.parse(JSON.stringify(row))
      if (this.isLegalCoordinate(this.editArea.region)) {
        this.mapInit()
      }
    },
    handleDelete (row) {
      this.$axios
        .post('area/delArea', {
          id: row.id
        })
        .then(res => {
          this.PrintMessage(res.data.code, res.data.message)
          const index = this.tableData.findIndex(item => item.id === row.id)
          if (index !== -1) {
            this.tableData.splice(index, 1)
          }
        })
        .catch(failResponse => {
        })
    },
    getAreaTable () {
      this.$axios
        .post('area/getAllArea', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.tableData = successResponse.data.object
          }
        })
        .catch(failResponse => {
        })
    },
    addArea () {
      if (this.isLegalCoordinate(this.editArea.region)) {
        this.$axios
          .post('area/addArea', {
            id: this.editArea.id,
            name: this.editArea.name,
            region: this.editArea.region,
            classes: this.editArea.classes,
            createBy: this.staff.name
          })
          .then(res => {
            this.PrintMessage(res.data.code, res.data.message)
            this.tableData = res.data.object
            this.reset()
          })
          .catch(failResponse => {
            this.PrintMessage(400, '网络错误')
          })
      } else {
        this.PrintMessage(203, '格式错误')
      }
    },
    isLegalCoordinate (str) {
      const regExp = /^(\d{1,3}\.\d{1,14}),\s*(\d{1,2}\.\d{1,14});?$/
      const arr = str.split(';')
      const result = []

      for (let i = 0; i < arr.length; i++) {
        const matchResult = arr[i].match(regExp)
        if (!matchResult) {
          console.error('错误：第', i + 1, '个点格式不正确')
          return false
        }
        const longitude = Number(matchResult[1])
        const latitude = Number(matchResult[2])
        if (longitude < 73.66 || longitude > 135.05 || latitude < 3.86 || latitude > 53.55) {
          console.error('错误：第', i + 1, '个点经纬度不在规定范围内')
          return
        }
        result.push([longitude, latitude])
      }
      this.path = result
      return true
      // return result
    },
    mapInit () {
      // 创建地图实例
      this.map = new AMap.Map('mapContainer', {
        zoom: 13, // 初始缩放级别
        center: this.path[0] // 初始中心点坐标
      })

      // 添加标记点
      for (let i = 0; i < this.path.length; i++) {
        const marker = new AMap.Marker({
          position: this.path[i],
          map: this.map
        })
        this.markers.push(marker)
      }

      // 添加连线
      const lineArr = this.path
      const polyline = new AMap.Polyline({
        path: lineArr,
        strokeColor: '#FF0000',
        strokeWeight: 3,
        strokeOpacity: 1,
        strokeStyle: 'solid',
        strokeDasharray: [10, 5]
      })
      this.map.add(polyline)

      // 创建多边形
      this.polygon = new AMap.Polygon({
        path: lineArr,
        fillColor: '#00BFFF', // 填充颜色
        fillOpacity: 0.35, // 填充透明度
        strokeColor: '#00BFFF', // 边线颜色
        strokeWeight: 3, // 边线宽度
        strokeOpacity: 1, // 边线透明度
        strokeStyle: 'solid', // 边线样式
        strokeDasharray: [10, 5] // 边线虚线样式
      })
      this.map.add(this.polygon)

      // 自适应显示多边形
      this.map.setFitView([this.polygon])
    }
  },
  created () {
    this.getAreaTable()
    this.staff = JSON.parse(sessionStorage.getItem('staff'))
  }
}
</script>

<style>
.regionInput {
  width: 400px;
}
#mapContainer {
  width: 100%;
  height:400px;
  margin-bottom:20px;
}
#mapPage{
  width: 100%;
  height:400px;
  margin-bottom:20px;
}
</style>
