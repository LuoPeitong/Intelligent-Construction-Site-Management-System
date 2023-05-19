import Vue from 'vue'
import Vuex from 'vuex'
// import {id} from 'html-webpack-plugin/lib/chunksorter'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    editableTabs: [],
    editableTabsValue: '',
    projectList: [],
    departmentList: []
  },
  mutations: {
    // 保存当前菜单栏的路径
    addEditableTabs (state, editableTabs) {
      state.editableTabs.push(editableTabs)
      const res = new Map()
      let arr = state.editableTabs
      let val = 'id'
      state.editableTabs = arr.filter((item) => !res.has(item[val]) && res.set(item[val], 1))
    },
    saveEditableTabs (state, editableTabs) {
      state.editableTabs = editableTabs
    },
    saveEditableTabsValue (state, editableTabsValue) {
      state.editableTabsValue = editableTabsValue
    },
    saveProjectList (state, projectList) {
      state.projectList = projectList
    },
    saveDepartmentList (state, departmentList) {
      state.departmentList = departmentList
    }
  }
})
