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
    <el-table :data="tableData" fit height="600px" style="width: 100%">
      <el-table-column label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="date" label="日期" width="150"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="province" label="省份" width="120"></el-table-column>
      <el-table-column prop="city" label="市区" width="120"></el-table-column>
      <el-table-column prop="address" label="地址" width="300"></el-table-column>
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
        classes: ''
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
      console.log(row)
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
          name: this.editArea.name,
          region: this.editArea.region,
          classes: this.editArea.classes,
          createBy: this.staff.name
        })
        .then(res => {
          if (res.data.code === 200) {
            const h = this.$createElement
            this.$notify({
              title: '成功',
              message: h('i',
                {style: 'color: teal'},
                res.data.message)
            })
          }
        })
        .catch(failResponse => {
          const h = this.$createElement
          this.$notify({
            title: '失败',
            message: h('i',
              {style: 'color: teal'},
              failResponse.data.message)
          })
        })
    }
  },
  created () {
    this.getAreaTable()
    this.staff = JSON.parse(sessionStorage.getItem('staff'))
    console.log(this.staff.name)
  }
}
</script>

<style>
.regionInput {
  width: 400px;
}
</style>
