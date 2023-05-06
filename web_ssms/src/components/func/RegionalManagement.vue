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
    <div id="Container"></div>
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
const AMap = window.AMap
export default {
  name: 'RegionalManagement',
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
    reset () {
      this.editArea = {name: '', region: '', classes: '', createBy: '', createTime: ''}
      this.queryArea = {name: '', classes: ''}
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
      const regExp = /^(\d{1,3}\.\d{1,6}),\s*(\d{1,2}\.\d{1,6});?$/
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
    },
    mapInit () {
      // 创建地图实例
      this.map = new AMap.Map('Container', {
        zoom: 13, // 初始缩放级别
        center: this.path[0] // 初始中心点坐标
      })

      var polygon = new AMap.Polygon({
        path: this.path
      })

      // 将多边形添加到地图上
      this.map.add([polygon])

      // 自适应显示多边形
      this.map.setFitView()

      // 创建多边形编辑器
      var polyEditor = new AMap.PolygonEditor(this.map)

      // 监听多边形修改事件
      polyEditor.on('adjust', () => {
        // 获取修改后的路径
        var newPath = polygon.getPath()

        // 将修改后的路径赋值给 this.path[]
        this.path = newPath
      })

      // 将多边形添加到吸附列表中
      polyEditor.addAdsorbPolygons([polygon])

      // 监听多边形添加事件
      polyEditor.on('add', function (data) {
        var polygon = data.target
        polyEditor.addAdsorbPolygons(polygon)

        // 双击多边形打开编辑器
        polygon.on('dblclick', () => {
          polyEditor.setTarget(polygon)
          polyEditor.open()
        })
      })

      // 双击多边形打开编辑器
      polygon.on('dblclick', () => {
        polyEditor.setTarget(polygon)
        polyEditor.open()
      })

      polyEditor.setTarget(polygon)
      polyEditor.open()

      // 监听地图的 click 事件
      this.map.on('click', () => {
        // 结束多边形编辑
        polyEditor.close()
      })

      polyEditor.on('end', (event) => {
        const path = event.target.getPath()
        console.log(path)
        this.path = path
        console.log('this path = ' + this.path)
        this.editArea.region = this.path.map(p => `${p.lng},${p.lat}`).join(';')
      })
    }
  },
  created () {
    this.getAreaTable()
    this.staff = JSON.parse(sessionStorage.getItem('staff'))
  },
  mounted () {
    if (this.isLegalCoordinate('118.194826,24.49446;118.195964,24.494536;118.196358,24.496041')) {
      this.mapInit()
    }
  }
}
</script>

<style>
.regionInput {
  width: 400px;
}
#Container {
  width: 100%;
  height:400px;
  margin-bottom:20px;
}
</style>
