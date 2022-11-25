<script setup>
import NavigationBar from './components/NavigationBar.vue';
</script>

<template>
  <header style="position: fixed; top: 0; width: 100%;">
    <!--使用 router-link 组件进行导航 -->
    <!--通过传递 `to` 来指定链接 -->
    <!--`<router-link>` 将呈现一个带有正确 `href` 属性的 `<a>` 标签-->
    <!-- <router-link to="/">返回首页</router-link> -->
    <!-- 不知道NavBar该放哪儿，直接放根组件里了-ysh -->
    <NavigationBar />
  </header>
  <main>
    <div>
      <!-- 路由出口 -->
      <!-- 路由匹配到的组件将渲染在这里 -->
      <router-view></router-view>
    </div>
  </main>
</template>

<style scoped>

</style>
