<template>
  <el-tabs v-model="$store.state.editableTabsValue" @edit="handleTabsEdit" type="card" closable>
    <el-tab-pane v-for="(item) in $store.state.editableTabs" :key="item.id" :label="item.title" :name="item.id">
      <keep-alive>
        <component :is='item.content'></component>
      </keep-alive>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import PerManagement from '@/components/func/PerManagement'
import PerReport from '@/components/func/PerReport'
import PerRealTimeTrack from '@/components/func/PerRealTimeTrack'
import PerInfoStatistics from '@/components/func/PerInfoStatistics'
import RegionalManagement from '@/components/func/RegionalManagement'
import PerInfoMonitoring from '@/components/func/PerInfoMonitoring'
import AttManagement from '@/components/func/AttManagement'
import AlarmManagement from '@/components/func/AlarmManagement'
import OrganManagement from '@/components/func/OrganManagement'
import SetAdmin from '@/components/func/SetAdmin'
export default {
  name: 'Tabs',
  components: {
    PerManagement,
    PerReport,
    PerInfoMonitoring,
    PerRealTimeTrack,
    PerInfoStatistics,
    AttManagement,
    RegionalManagement,
    AlarmManagement,
    OrganManagement,
    SetAdmin
  },
  data () {
    return {
      tabIndex: 2
    }
  },
  methods: {
    handleTabsEdit (targetName, action) {
      if (action === 'remove') {
        // 拷贝tabs列表
        let tabs = this.$store.state.editableTabs
        let activeName = this.$store.state.editableTabsValue
        if (activeName === targetName) {
          tabs.forEach((tab, index) => {
            if (tab.id === targetName) {
              let nextTab = tabs[index + 1] || tabs[index - 1]
              if (nextTab) {
                activeName = nextTab.id
              }
            }
          })
        }
        this.$store.commit('saveEditableTabsValue', activeName)
        let editableTabs = tabs.filter(tab => tab.id !== targetName)
        this.$store.commit('saveEditableTabs', editableTabs)
      }
    }
  }
}
</script>

<style scoped>

</style>
