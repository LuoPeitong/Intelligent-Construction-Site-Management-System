<template>
  <div>
    <el-descriptions class="margin-top" title="人员统计信息" :column="2" border>
      <el-descriptions-item>
        <template slot="label">总人数</template>
        {{ perStatistics.total }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">终端在线人数</template>
        {{ perStatistics.isOnline }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">离线人数</template>
        {{ perStatistics.notOnline }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">超时人数</template>
        {{ perStatistics.late }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">最早离场时间信息</template>
        {{ perStatistics.leaveEarly.leaveMoment }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">最晚进厂时间信息</template>
        {{ perStatistics.lateEnter.enterMoment }}
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions class="margin-top" title="最早进场人员信息" :column="2" border>
      <el-descriptions-item>
        <template slot="label">姓名</template>
        {{ perStatistics.original.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">工号</template>
        {{ perStatistics.original.jobNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">部门</template>
        {{ perStatistics.original.departmentName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">项目组</template>
        {{ perStatistics.original.projectName}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">进场时间</template>
        {{ perStatistics.original.enterMoment}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">离场时间</template>
        {{ perStatistics.original.leaveMoment }}
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions class="margin-top" title="最晚离场人员信息" :column="2" border>
      <el-descriptions-item>
        <template slot="label">姓名</template>
        {{ perStatistics.last.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">工号</template>
        {{ perStatistics.last.jobNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">部门</template>
        {{ perStatistics.last.departmentName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">项目组</template>
        {{ perStatistics.last.projectName}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">进场时间</template>
        {{ perStatistics.last.enterMoment}}
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">离场时间</template>
        {{ perStatistics.last.leaveMoment }}
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script>
export default {
  name: 'PerInfoStatistics',
  data () {
    return {
      // 用于根据日期查询 最早进场人员信息 最晚离场人员信息
      moment: '2022-12-12',
      perStatistics: []
    }
  },
  mounted () {
    this.findData()
  },
  methods: {
    findData () {
      this.$axios
        .post('attendance/findData', {
          moment: this.moment
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.perStatistics = successResponse.data.object
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>

</style>
