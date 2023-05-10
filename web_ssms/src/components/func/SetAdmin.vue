<template>
  <div>
    <el-select v-model="search" placeholder="请选择">
      <el-option v-for="item in permissions" :key="item.id" :label="item.name" :value="item.id"></el-option>
    </el-select>
    <el-table :data="staffList.filter(data => !search || data.permissions.toLowerCase().includes(search.toLowerCase()))"
              stripe fit max-height="565" style="width: 100%">
      <el-table-column label="工号" prop="jobNo"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      <el-table-column label="部门" prop="departmentName"></el-table-column>
      <el-table-column label="工种" prop="profession"></el-table-column>
      <el-table-column label="级别" prop="permissions" :formatter="formatPermissions"></el-table-column>
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          </template>
      </el-table-column>
    </el-table>
    <el-dialog title="管理员设置" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="员工姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工号" :label-width="formLabelWidth">
          <el-input v-model="form.jobNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="工种" :label-width="formLabelWidth">
          <el-input v-model="form.profession" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="部门" :label-width="formLabelWidth">
          <el-input v-model="form.departmentName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="级别" :label-width="formLabelWidth">
          <el-select v-model="form.permissions" placeholder="请选择项目">
            <el-option v-for="item in permissions" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
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
  name: 'SetAdmin',
  data () {
    return {
      staffList: [],
      search: '0',
      permissions: [{id: '0', name: '普通员工'}, {id: '1', name: '管理员'}, {id: '2', name: '超级管理员'}],
      form: {},
      dialogFormVisible: false,
      // Dialog中表单标签的宽
      formLabelWidth: '120px'
    }
  },
  created () {
    this.Init()
  },
  methods: {
    // 行 编辑
    handleEdit (index, row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    Init () {
      this.$axios
        .post('user/setAdminQuery', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.staffList = successResponse.data.object
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    formatPermissions (row) {
      switch (row.permissions) {
        case '1':
          return '管理员'
        case '2':
          return '超级管理员'
        default:
          return '普通员工'
      }
    },
    // dialog 取消
    dialogCancel () {
      this.form = {}
      this.dialogFormVisible = false
    },
    // dialog 确认
    dialogConfirm () {
      this.$axios
        .post('user/editPermissions', {
          jobNo: this.form.jobNo,
          permissions: this.form.permissions
        })
        .then(successResponse => {
          this.staffList = successResponse.data.object
          this.PrintMessage(successResponse.data.code, successResponse.data.message)
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
