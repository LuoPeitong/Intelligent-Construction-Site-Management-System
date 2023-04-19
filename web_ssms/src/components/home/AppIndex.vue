<template>
  <div>
    <Navbar id="menu"/>
    <Tabs id="tabs"/>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import Tabs from '@/components/Tabs'
export default {
  name: 'AppIndex',
  components: {
    Navbar,
    Tabs
  }
}
</script>

<style scoped>
  #menu{
    width:15vw;
    height:100vh;
    float:left;
  }
  #tabs{
    width:84%;
    float:left;
  }
</style>
