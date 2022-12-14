<script setup>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
const route = useRoute()
console.log(route)

const activeName = ref('first')

</script>

<template>
    
    <div class="content-container">
        <el-container>
          为您展示{{route.query.courses}}的课程详情
          课程介绍
          资源展示
        <!-- <el-main>
            <el-tabs v-model="activeName" class="content-item">
            <el-tab-pane label="课程介绍" name="courseIntroduction">有关{{route.query.courses}}的详情页</el-tab-pane>
            <el-tab-pane label="资源展示" name="coursePresentation">资源展示</el-tab-pane>
            </el-tabs>
        </el-main> -->
      </el-container>
    </div>

    <el-skeleton :rows="5" />

  </template>
  

<style scoped>
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    --el-bg-color:var(--el-bg-color-page);
}
.content-item {
  width: 50rem;
  box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
  border-radius: 4px;
  margin-bottom: 1rem;
  background-color: var(--color-background);
  box-sizing: border-box;
  padding: 1rem;
  color: var(--color-text);

}


</style>