<script setup>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
import axios from "axios";
const route = useRoute()
console.log(route)
const activeName = ref('first')

// download test: 仅作前后端对接测试用
function downLoad(){
    //通过window.location调起下载框
    window.location = "http://localhost:9090/resource/downloadfile?resourceId=" + 9;// url + 下载的resourceId
}
</script>

<template>
    
    <div class="content-container">
      <el-container>

        <el-main class="content-item">
          <el-row type="flex" justify="center" align="middle">
            <div class = "courseTitle">
              <h1>为您展示{{route.query.courses}}的课程详情</h1>
            </div>
          </el-row>
          <div class="briefIro">
            <h2>课程简介</h2>
              <h3>任课教师:{{route.query.teacher}}</h3>
              <h3>课程时间:{{route.query.during}}</h3>
              <h3>课程评价:{{route.query.itro}}</h3>


          </div>
            <div class="courseDetail">
                <h2>课程详情</h2>
                <h3>有待补充</h3>
                <!-- test -->
                <!--下载按钮-->
                <el-button type="warning" icon="el-icon-download" :circle="isCircle" @click="downLoad()"></el-button>
            </div>
        </el-main>

      </el-container>
    </div>

    <el-skeleton :rows="5" />

  </template>
  

<style scoped>
.courseTitle{
  color: #606266;
  text-align: center;
  font-size: larger;
  display: inline-block;
  vertical-align: center;
}
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    --el-bg-color:var(--el-bg-color-page);
}
.content-item {
  width: 60rem;
  border-radius: 16px;
  margin-bottom: 1rem;
  background-color: var(--color-background);
  box-sizing: border-box;
  box-shadow: 0 0 10px 10px rgba(128, 162, 167, 0.82);
}
.boxIntro{
  margin-right: 20px;
}
</style>