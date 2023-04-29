<template>
  <div id="mapPage"></div>
</template>

<script>
const AMap = window.AMap
export default {
  name: 'mapPage',
  data () {
    return {
      map: null,
      coordinates: [118.10047077962123, 24.482787680423996]
    }
  },
  mounted () {
    // 创建地图实例
    this.map = new AMap.Map('mapPage', {
      zoom: 13, // 初始缩放级别
      center: this.coordinates // 初始中心点坐标
    })
    // 添加鼠标事件监听器
    AMap.event.addListener(this.map, 'click', (e) => {
      console.log(e.lnglat) // 输出鼠标点击的经纬度坐标
    })
  }
}
</script>
