<template>
  <el-container>
    <el-header style="height: 100px;">头</el-header>
    <el-container>
      <el-aside style="width: 250px;">
        <el-menu :router="true" :default-active="this.$router.path">
          <el-submenu v-for="menu in menus" :index="menu.url" :key="menu.id">
            <span slot="title">{{ menu.text }}</span>
            <template v-if="menu.children">
              <el-menu-item v-for="submenu in menu.children" :index="submenu.url" :key="submenu.id">
                {{ submenu.text }}
              </el-menu-item>
            </template>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
import request from '../utils/request'

export default {
  name: 'Welcome',
  data () {
    return {
      isCollapse: true,
      menus: []
    }
  },
  methods: {
    getMenus: function () {
      request({
        url: '/application/menus',
        method: 'POST',
        data: JSON.stringify('')
      }).then(json => {
        console.log(JSON.stringify(json))
        this.menus = json
        console.log(this.menus)
      }).catch(error => {
        this.$message.error(error)
      })
    }
  },
  created: function () {
    this.getMenus()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
