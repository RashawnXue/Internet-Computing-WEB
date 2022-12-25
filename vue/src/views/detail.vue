<script setup>
import { useRoute } from 'vue-router'
import { ref } from 'vue'
import axios from "axios";
const route = useRoute()
import { DataBoard, Clock, Avatar, Download, School} from "@element-plus/icons-vue";

console.log(route)
const activeName = ref('first')
const picture = route.query.picture
const courseName = route.query.courses
const detailList = ref([])
const fill = ref(true)
axios.get('http://localhost:9090/resource/findByCoursename/' + courseName ).then(function (resp) {
  detailList.value = resp.data
  console.log(detailList.value)
})
// download test: 仅作前后端对接测试用
function downLoad(i){
    //通过window.location调起下载框
    window.location = "http://localhost:9090/resource/downloadfile?resourceId=" + i;// url + 下载的resourceId
}

</script>

<template>
  <div style="z-index: 0; display: flex; flex-direction: column; align-items: center;">
    <el-card class="table-container">
    <el-row >
      <el-col :span = "12">
        <img :src="'data:image/png;base64,'+ picture" style="width: 80%"/>
      </el-col>
      <el-col :span="12">
        <div class="briefIro">
          <h1 style="margin-bottom: 10px">{{route.query.courses}}</h1>
          <div style="font-size: larger; margin-bottom: 5px">
            <el-icon style="vertical-align: -0.2em;" size="large"><Avatar /></el-icon>
            教师:{{route.query.teacher}}
          </div>
          <div style="font-size: larger; margin-bottom: 20px">
            <el-icon style="vertical-align: -0.2em;" size="large"><Clock /></el-icon>
            课程时间:{{route.query.during}}
          </div>
        </div>
      </el-col>
    </el-row>
      <el-row>
        <el-col :span="12">
          <div style="font-size: larger">
            <el-icon style="vertical-align: -0.2em;" size="large">
              <DataBoard />
            </el-icon>课程介绍:
          </div>
          <div style="margin-right: 10px; font-size: larger; margin-bottom: 20px" >
            {{route.query.itro}}
          </div>
        </el-col>
      </el-row>

      <el-row>
        <el-col>
          <div style="font-size: larger">
            <el-icon style="vertical-align: -0.2em;" size="large">
              <School />
            </el-icon>课程资源:
          </div>
            <div>
              <div style="margin-bottom: 15px">切换显示: <el-switch v-model="fill" /></div>
              <el-space :fill="fill" wrap>
                <el-card v-for="item in detailList" :key="item" class="box-card">
                  <template #header>
                    <div class="card-header">
                      <span>资源</span>
                      <el-button class="button" text type="success" size="large" @click="downLoad(item.id)">下载资源</el-button>
                    </div>
                  </template>
                  <div  class="text item">
                    <div v-if = "item.type == null">
                      <div>链接名： {{item.name}}</div>
                      <div>链接： {{item.datapath}}</div>
                      <div>资源描述： {{item.intro}}</div>
                    </div>
                    <div v-if = "item.type == 'file'">
                      <div>文件名： {{item.name}}</div>
                      <div>文件描述： {{item.intro}}</div>
                    </div>

                  </div>
                </el-card>
              </el-space>
            </div>
        </el-col>
        <div style="margin-right: 10px; font-size: larger; margin-bottom: 20px" >

        </div>
      </el-row>

    </el-card>
  </div>
</template>




<style>
.el-row {
  margin-bottom: 20px;

}
.el-col {
  border-radius: 4px;
}
.table-container {
  width: var(--rank-table-width);
  border-radius: 8px;
  margin-bottom: 1rem;
  background-color: var(--el-bg-color);
  box-sizing: border-box;
  box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
  padding: 1rem;
}
</style>