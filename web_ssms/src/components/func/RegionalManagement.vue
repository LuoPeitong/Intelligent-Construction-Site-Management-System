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
      </el-form-item>
    </el-form>
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
      <el-form-item>
        <el-button type="primary" @click="querrySubmit">查找</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" fit stripe height="600px" style="width: 100%">
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="id" label="序号" width="150"></el-table-column>
      <el-table-column prop="classes" label="类型" width="120">
        <template slot-scope="{ row }">
          {{ row.classes == 1 ? '活动区域' : '禁止区域' }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="区域名称" width="120"></el-table-column>
      <el-table-column prop="createBy" label="操作人" width="120"></el-table-column>
      <el-table-column prop="createTime" label="操作时间" width="150"></el-table-column>
    </el-table>
  </div>
</template>

<script>
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
      staff: {}
    }
  },
  methods: {
    onSubmit () {
      console.log(JSON.stringify(this.editArea))
    },
    querrySubmit () {
      console.log(JSON.stringify(this.queryArea))
    },
    handleClick (row) {
      // 深拷贝，避免修改editArea时，row也变化
      this.editArea = JSON.parse(JSON.stringify(row))
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
        })
        .catch(failResponse => {
          this.PrintMessage(400, '网络错误')
        })
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
</style>
