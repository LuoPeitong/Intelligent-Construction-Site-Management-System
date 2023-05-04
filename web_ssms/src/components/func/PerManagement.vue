<template>
  <div>
    <div style="float: left;">
      <span style="margin-left: 5px;">项目：</span>
      <el-select v-model="search" placeholder="请选择">
        <el-option v-for="item in $store.state.projectList" :key="item.id" :label="item.name" :value="item.name"></el-option>
      </el-select>
    </div>
    <div style="float: right;">
      <el-button @click="addButton" type="primary">新增</el-button>
    </div>
    <el-table :data="staffList.filter(data => !search || data.projectName.toLowerCase().includes(search.toLowerCase()))" stripe
             max-height="565" style="width: 100%">
            <el-table-column label="工号" prop="jobNo"></el-table-column>
            <el-table-column label="姓名" prop="name"></el-table-column>
            <el-table-column label="部门" prop="departmentName"></el-table-column>
            <el-table-column label="工种" prop="profession"></el-table-column>
            <el-table-column label="项目组" prop="projectName"></el-table-column>
            <el-table-column label="状态" prop="isOnline"></el-table-column>
            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
              </template>
              <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--新增/编辑 的会话框-->

    <div>
      <el-dialog title="人员编辑" :visible.sync="dialogFormVisible">
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
            <el-select v-model="form.departmentId" placeholder="请选择部门">
              <el-option v-for="item in $store.state.departmentList" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="项目" :label-width="formLabelWidth">
            <el-select v-model="form.projectId" placeholder="请选择项目">
              <el-option v-for="item in $store.state.projectList" :key="item.id" :label="item.name" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogCancel">取 消</el-button>
          <el-button type="primary" @click="dialogConfirm">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: 'PerManagement',
  data () {
    return {
      // 表格内容
      staffList: [],
      // 搜索框的值 下拉列表选中的值
      search: '',
      // 下拉列表
      projectList: [],
      // 控制Dialog
      dialogFormVisible: false,
      // Dialog中的表单
      form: {
        id: '',
        name: '',
        jobNo: '',
        departmentId: '',
        projectId: '',
        profession: '',
        isOnline: ''
      },
      // Dialog中表单标签的宽
      formLabelWidth: '120px'
    }
  },
  mounted () {
    this.getStaffList()
    this.$refs.diaglog.rendered = true
  },
  methods: {
    // 行 编辑
    handleEdit (index, row) {
      // console.log(index, JSON.stringify(row))
      this.form = row
      this.dialogFormVisible = true
    },
    // 行 删除
    handleDelete (index, row) {
      // console.log(index, row)
      this.$confirm('此操作将永久删除该人员, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('staff/delete', {
            id: row.id
          })
          .then(successResponse => {
            this.staffList = successResponse.data.object
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          })
          .catch(failResponse => {
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    getStaffList () {
      this.$axios
        .post('staff/getAllStaff', {
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.staffList = successResponse.data.object
            // console.log('staffList=', JSON.stringify(this.staffList))
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
      // console.log(JSON.stringify(this.form))
      this.$axios
        .post('staff/edit', {
          id: this.form.id,
          jobNo: this.form.jobNo,
          name: this.form.name,
          departmentId: this.form.departmentId,
          profession: this.form.profession,
          isOnline: this.form.isOnline,
          projectId: this.form.projectId
        })
        .then(successResponse => {
          this.staffList = successResponse.data.object
          this.PrintMessage(successResponse.data.code, successResponse.data.message)
        })
        .catch(failResponse => {
        })
      this.form = {}
      this.dialogFormVisible = false
    },
    // 新增按钮
    addButton () {
      this.form = {}
      this.dialogFormVisible = true
    }
  }
}
</script>
<style scoped></style>
