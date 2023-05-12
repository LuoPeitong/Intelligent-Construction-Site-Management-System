<template>
  <div>
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-left: 5px;">部门：</span>
      <el-select v-model="search" placeholder="请选择">
        <el-option v-for="item in $store.state.departmentList" :key="item.id" :label="item.name" :value="item.name"></el-option>
      </el-select>
      <el-form-item>
        <el-button type="primary" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="AlarmData.filter(data => !search || data.departmentName.toLowerCase().includes(search.toLowerCase()))"
              fit stripe height="400px" style="width: 100%">
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="jobNo" label="工号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>x
      <el-table-column prop="departmentName" label="部门"></el-table-column>
      <el-table-column prop="projectName" label="项目组"></el-table-column>

      <el-table-column prop="handling" label="处理情况" :formatter="formatHandling"></el-table-column>
      <el-table-column prop="moment" label="时间"></el-table-column>
      <el-table-column prop="message" label="说明"></el-table-column>
    </el-table>
    <el-dialog title="告警处理" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="员工工号" :label-width="formLabelWidth">
          <el-input v-model="form.jobNo" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="处理情况" :label-width="formLabelWidth">
          <el-select v-model="form.handling" placeholder="请选择项目">
            <el-option v-for="item in handling" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="说明" :label-width="formLabelWidth">
          <el-input v-model="form.message" autocomplete="off" disabled></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogCancel">取 消</el-button>
        <el-button type="primary" @click="dialogConfirm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'AlarmManagement',
  data () {
    return {
      form: {},
      AlarmData: [],
      search: '',
      dialogFormVisible: false,
      formLabelWidth: '120px',
      handling: [{id: '0', name: '未处理'}, {id: '1', name: '已处理'}]
    }
  },
  mounted () {
    this.Init()
  },
  methods: {
    // 行 编辑
    handleClick (row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    reset () {
      this.search = ''
    },
    formatHandling (row) {
      switch (row.handling) {
        case '1':
          return '已处理'
        default:
          return '未处理'
      }
    },
    Init () {
      this.$axios
        .post('alarm/getAll', {})
        .then(res => {
          if (res.data.code === 200) {
            this.AlarmData = res.data.object
          } else {
            this.PrintMessage(res.data.code, res.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    // dialog 取消
    dialogCancel () {
      this.form = {}
      this.dialogFormVisible = false
    },
    // dialog 确认
    dialogConfirm () {
      this.$axios
        .post('alarm/editAlarm', {
          id: this.form.id,
          handling: this.form.handling
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.AlarmData = successResponse.data.object
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
      this.form = {}
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>

</style>
