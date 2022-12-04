<script setup>

import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router';
import IconCourse from '../components/icons/IconCourse.vue'
import IconNew from '../components/icons/IconNew.vue'
import axios from "axios";
import { Reading } from '@element-plus/icons-vue';

const router = useRouter()

function goToCourse(item) {
    router.push({ path: '/detail/' + item.courseName })
    ElNotification({
        title: '跳转到' + item.courseName + "的详情页",
        message: 'nothing',
        type: 'warning',
        showClose: false,
    })
}

// 后端课程信息数据获取
const courses = ref([])
// url为对应接口的映射
axios.get('http://localhost:9090/courses/findAll').then(function (resp) {
    courses.value = resp.data
    console.log(courses.value)
})

const collapse = ref(false)

let rem = window.getComputedStyle(document.documentElement)["fontSize"]
window.onresize = function () {
    rem = window.getComputedStyle(document.documentElement)["fontSize"]
    let windowWidth = window.innerWidth
    console.log(rem)
    console.log(windowWidth)
    if (windowWidth < 68 * Number(rem.substring(0, rem.length - 2))) {
        collapse.value = true
    } else {
        collapse.value = false
    }
}

// 筛选相应课程
function filterCourses(index, indexPath, item) {
    console.log(index)
}

</script>

<template>
    <div class="home-side-bar">
        <el-menu
            style="border-right-width: 0; background-color: var(--el-bg-color-page); --el-menu-hover-bg-color: var(--el-bg-color-page); --el-menu-bg-color: var(--el-bg-color-page)"
            :collapse="collapse" @select="filterCourses">
            <el-sub-menu>
                <template #title>
                    <el-icon>
                        <Reading />
                    </el-icon>
                    <span>课程分类</span>
                </template>
                <el-menu-item index="软件学院">
                    软件学院
                </el-menu-item>
                <el-menu-item index="其他">
                    其他
                </el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>
    <div class="content-container">
        <el-card v-for="item in courses" class="content-item">
            <template #header>
                <div class="content-item-header">
                    <IconCourse style="margin-right: 0.5rem; margin-top: 2px;" />
                    <div class="content-item-header-name" @click="goToCourse(item)">{{ item.course_name }}</div>
                    <IconNew style="margin-left: 0.5rem; margin-top: 2px;" />
                </div>
            </template>
            {{ "简介：" + item.introduction }}
        </el-card>
    </div>
</template>

<style scoped>
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    padding-left: var(--content-padding-left);
    align-items: var(--content-align-items);
}

.home-side-bar {
    z-index: 1;
    position: fixed;
    width: 12rem;
    left: 0;
    display: var(--side-bar-display);
}

.content-item {
    width: 50rem;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 4px;
    margin-bottom: 1rem;
    box-sizing: border-box;
    padding: 1rem;
    --el-card-padding: 1rem;
}

.content-item-header {
    display: flex;
    flex-direction: row;
    align-items: center;
    font-size: large;
    font-weight: bolder;
    padding-top: 0;
}

.content-item-header-name {
    font-weight: bolder;
    cursor: pointer;
}

.content-item-header-name:hover {
    color: teal;
}
</style>

