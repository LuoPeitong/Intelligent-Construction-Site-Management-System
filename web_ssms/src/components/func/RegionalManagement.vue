<template>
  <div id="container">
    <el-form :inline="true" :model="formArea" class="demo-form-inline">
      <el-form-item label="区域名称">
        <el-input v-model="formArea.name" placeholder="区域名称"></el-input>
      </el-form-item>
      <el-form-item label="区域类型">
        <el-select v-model="formArea.class" placeholder="区域类型">
          <el-option label="活动区域" value="shanghai"></el-option>
          <el-option label="禁入区域" value="beijing"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="区域坐标">
        <el-input v-model="formArea.region" placeholder="区域坐标" class="regionInput"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'RegionalManagement',
  data() {
    return {
      formArea: {
        name: '',
        region: '',
        class: ''
      }
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!')
    }
  }
}
</script>

<style>
  #container {
    width: 100% !important;
    height: 100vh !important;
    margin-right: 10px !important;
  }
  .regionInput {
    width: 400px;
  }
</style>
