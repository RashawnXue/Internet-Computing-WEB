<script setup>
import NavigationBar from './components/NavigationBar.vue';

</script>

<template>
  <el-container>
    <el-header style="z-index: 1; position: fixed; top: 0; width: 100%; --el-header-padding: 0; background-color: var(--color-background);">
      <!--使用 router-link 组件进行导航 -->
      <!--通过传递 `to` 来指定链接 -->
      <!--`<router-link>` 将呈现一个带有正确 `href` 属性的 `<a>` 标签-->
      <!-- <router-link to="/">返回首页</router-link> -->
      <NavigationBar />
    </el-header>
    <el-main style="top: 60px; padding: 1rem;">
      <!-- 路由出口 -->
      <!-- 路由匹配到的组件将渲染在这里 -->
      <router-view></router-view>
    </el-main>
    <el-footer style="position: relative; top: 60px; width: 100%; bottom: 0px; text-align: center;">
      ——2022-2023第一学期软件学院互联网计算课程大作业——
    </el-footer>
  </el-container>
</template>

<style scoped>

</style>
