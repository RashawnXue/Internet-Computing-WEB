<script setup>
import {useRoute} from 'vue-router'
import {ref} from 'vue'
import axios from "axios";
import {DataBoard, Clock, Avatar, View, Download, School, User} from "@element-plus/icons-vue";
import URL from '../global/url';
import ResourceCard from '../components/ResourceCard.vue';

const route = useRoute()
console.log(route)
const activeName = ref('first')
const picture = route.query.picture
const courseName = route.query.courses
const detailList = ref([])
const fill = ref(true)
axios.get(URL.findResourceByCourseName + courseName).then(function (resp) {
    detailList.value = resp.data
    console.log(detailList.value)
})

// download test: 仅作前后端对接测试用
function downLoad(i) {
    //通过window.location调起下载框
    window.location = URL.downloadFile + "?resourceId=" + i;// url + 下载的resourceId
}

</script>

<template>
    <div style="z-index: 0; display: flex; flex-direction: column; align-items: center;">
        <el-card class="table-container">
            <el-row>
                <el-col :span="12">
                    <img :src="'data:image/png;base64,' + picture" style="width: 80%"/>
                </el-col>
                <el-col :span="12">
                    <div class="briefIro">
                        <h1 style="margin-bottom: 10px; font-weight: bolder;">{{ route.query.courses }}</h1>
                        <div style="font-size: larger; margin-bottom: 8px">
                            <el-icon style="vertical-align: -0.2em;" size="large">
                                <User/>
                            </el-icon>
                            授课教师: {{ route.query.teacher }}
                        </div>
                        <div style="font-size: larger; margin-bottom: 8px">
                            <el-icon style="vertical-align: -0.2em;" size="large">
                                <Clock/>
                            </el-icon>
                            课程时间: {{ route.query.during }}
                        </div>
                        <div style="font-size: larger; margin-bottom: 8px">
                            <el-icon style="vertical-align: -0.2em;" size="large">
                                <View/>
                            </el-icon>
                            浏览次数: {{ route.query.viewtime }}
                        </div>
                    </div>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24">
                    <div style="font-size: larger">
                        <el-icon style="vertical-align: -0.2em; margin-right: 5px;" size="large">
                            <DataBoard/>
                        </el-icon>
                        课程介绍:
                    </div>
                    <p style="margin-right: 10px; margin-bottom: 20px; text-indent: 2em;">
                        {{ route.query.itro }}
                    </p>
                </el-col>
            </el-row>
            <div style="font-size: larger; margin-bottom: 20px">
                <el-icon style="vertical-align: -0.2em; margin-right: 5px;" size="large">
                    <School/>
                </el-icon>
                课程资源:
            </div>
            <div>
                <div style="display: flex; flex-direction: column; align-items: center; transition: all 0.5s;">
                    <ResourceCard :resources="detailList"></ResourceCard>
                </div>
            </div>
        </el-card>
    </div>
</template>

<style scoped>
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
    transition: all 0.5s;
}
</style>