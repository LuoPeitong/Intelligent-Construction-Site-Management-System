<template>
  <div>
    <el-collapse accordion v-model="activeName">
      <el-collapse-item title="部门管理" name="department">
        <div style="float: right;">
          <el-button @click="addButton" type="primary">新增</el-button>
        </div>
        <el-table :data="departmentList" stripe fit max-height="400" style="width: 100%">
          <el-table-column label="部门代号" prop="symbol"></el-table-column>
          <el-table-column label="部门" prop="name"></el-table-column>
          <el-table-column label="部门负责人" prop="head"></el-table-column>
          <el-table-column label="联系电话" prop="tel"></el-table-column>
          <el-table-column align="right">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="部门管理" :visible.sync="dialogFormVisible">
          <el-form :model="form">
            <el-form-item label="部门代号" :label-width="formLabelWidth">
              <el-input v-model="form.symbol" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="部门" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="负责人" :label-width="formLabelWidth">
              <el-input v-model="form.head" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" :label-width="formLabelWidth">
              <el-input v-model="form.tel" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogCancel">取 消</el-button>
            <el-button type="primary" @click="dialogConfirm">确 定</el-button>
          </div>
        </el-dialog>
      </el-collapse-item>

      <el-collapse-item title="项目管理">
        <div style="float: right;">
          <el-button @click="addProjectButton" type="primary">新增</el-button>
        </div>
        <el-table :data="projectList" stripe fit max-height="400" style="width: 100%">
          <el-table-column label="项目名称" prop="name"></el-table-column>
          <el-table-column label="工作区域" prop="workArea" :formatter="formatWorkArea"></el-table-column>
          <el-table-column label="禁入区域" prop="limitedArea" :formatter="formatLimitedArea"></el-table-column>
          <el-table-column label="上班时间" prop="startTime"></el-table-column>
          <el-table-column label="下班时间" prop="closingTime"></el-table-column>
          <el-table-column align="right">
            <template slot-scope="scope">
              <el-button size="mini" @click="projectEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="projectDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="项目管理" :visible.sync="projectFormVisible">
          <el-form :model="projectForm">
            <el-form-item label="项目名称" :label-width="formLabelWidth">
              <el-input v-model="projectForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="工作区域" :label-width="formLabelWidth">
              <el-select v-model="projectForm.workArea" placeholder="请选择" multiple>
                <el-option v-for="item in areaList" v-if="item.classes==='1'" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="禁入区域" :label-width="formLabelWidth">
              <el-select v-model="projectForm.limitedArea" placeholder="请选择" multiple>
                <el-option v-for="item in areaList"  v-if="item.classes==='0'" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="上班时间" :label-width="formLabelWidth">
              <el-input v-model="projectForm.startTime" autocomplete="off" placeholder="例如:08:00:00"></el-input>
            </el-form-item>
            <el-form-item label="下班时间" :label-width="formLabelWidth">
              <el-input v-model="projectForm.closingTime" autocomplete="off" placeholder="例如:18:00:00"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogProjectCancel">取 消</el-button>
            <el-button type="primary" @click="dialogProjectConfirm">确 定</el-button>
          </div>
        </el-dialog>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
export default {
  name: 'OrganManagement',
  data () {
    return {
      departmentList: [],
      department: {
        id: '',
        symbol: '',
        name: '',
        head: '',
        tel: ''
      },
      form: {
        id: '',
        symbol: '',
        name: '',
        head: '',
        tel: ''
      },
      projectForm: {},
      projectList: [],
      // 控制Dialog
      dialogFormVisible: false,
      projectFormVisible: false,
      // Dialog中表单标签的宽
      formLabelWidth: '120px',
      activeName: 'department',
      areaList: []
    }
  },
  created () {
    this.departmentInit()
    this.projectInit()
    this.getAllArea()
  },
  methods: {
    formatWorkArea (row) {
      const workAreaIds = row.workArea.split(',')
      const workAreas = workAreaIds.map(id => {
        const area = this.areaList.find(item => item.id === parseInt(id))
        return area ? area.name : ''
      })
      return workAreas.join(',')
    },
    formatLimitedArea (row) {
      const LimitedAreaIds = row.limitedArea.split(',')
      const LimitedAreas = LimitedAreaIds.map(id => {
        const area = this.areaList.find(item => item.id === parseInt(id))
        return area ? area.name : ''
      })
      return LimitedAreas.join(',')
    },
    getAllArea () {
      this.$axios
        .post('area/getAllArea', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.areaList = successResponse.data.object
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    departmentInit () {
      this.$axios
        .post('department/getList', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.departmentList = successResponse.data.object
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    projectInit () {
      this.$axios
        .post('project/getList', {})
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.projectList = successResponse.data.object
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
    },
    // 行 编辑
    handleEdit (index, row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    // 行 删除
    handleDelete (index, row) {
      // console.log(index, row)
      this.$confirm('确认删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('department/delDepartment', {
            id: row.id
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.departmentList = successResponse.data.object
              this.PrintMessage(successResponse.data.code, successResponse.data.message)
            } else {
              this.PrintMessage(successResponse.data.code, successResponse.data.message)
            }
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
    // 行 编辑
    projectEdit (index, row) {
      this.projectForm = JSON.parse(JSON.stringify(row))
      this.projectForm.workArea = row.workArea.split(',').map(Number)
      this.projectForm.limitedArea = row.limitedArea.split(',').map(Number)
      this.projectFormVisible = true
    },
    // 行 删除
    projectDelete (index, row) {
      this.$confirm('确认删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('project/delProject', {
            id: row.id
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.projectList = successResponse.data.object
              this.PrintMessage(successResponse.data.code, successResponse.data.message)
            } else {
              this.PrintMessage(successResponse.data.code, successResponse.data.message)
            }
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
    // dialog 取消
    dialogCancel () {
      this.form = {}
      this.dialogFormVisible = false
    },
    // dialog 确认
    dialogConfirm () {
      this.$axios
        .post('department/editDepartment', {
          id: this.form.id,
          symbol: this.form.symbol,
          name: this.form.name,
          head: this.form.head,
          tel: this.form.tel
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$store.commit('saveDepartmentList', successResponse.data.object)
            this.departmentList = successResponse.data.object
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
      this.form = {}
      this.dialogFormVisible = false
    },
    // dialog 取消
    dialogProjectCancel () {
      this.projectForm = {}
      this.projectFormVisible = false
    },
    // dialog 确认
    dialogProjectConfirm () {
      this.$axios
        .post('project/editProject', {
          id: this.projectForm.id,
          name: this.projectForm.name,
          workArea: this.projectForm.workArea.join(','),
          limitedArea: this.projectForm.limitedArea.join(','),
          startTime: this.projectForm.startTime,
          closingTime: this.projectForm.closingTime
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$store.commit('saveProjectList', successResponse.data.object)
            this.projectList = successResponse.data.object
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          } else {
            this.PrintMessage(successResponse.data.code, successResponse.data.message)
          }
        })
        .catch(failResponse => {
        })
      this.projectForm = {}
      this.projectFormVisible = false
    },
    // 新增按钮
    addButton () {
      this.form = {}
      this.dialogFormVisible = true
    },
    addProjectButton () {
      this.projectForm = {}
      this.projectFormVisible = true
    }
  }
}
</script>

<style scoped>

</style>
