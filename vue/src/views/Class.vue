<script setup>

import { ref } from 'vue'
import CourseCard from '../components/CourseCard.vue';
import { CollectionTag, Filter } from '@element-plus/icons-vue';
import storage from '../utils/LocalStorage';

// 课程信息数据获取
const showCourses = ref(storage.get("courses"))

// 控制左侧边栏折叠
const collapse = ref(false)
let rem = window.getComputedStyle(document.documentElement)["fontSize"]
let windowWidth = window.innerWidth
if (windowWidth < 66 * Number(rem.substring(0, rem.length - 2))) {
    collapse.value = true
} else {
    collapse.value = false
}

window.onresize = function () {
    rem = window.getComputedStyle(document.documentElement)["fontSize"]
    let windowWidth = window.innerWidth
    if (windowWidth < 66 * Number(rem.substring(0, rem.length - 2))) {
        collapse.value = true
    } else {
        collapse.value = false
    }
}

// 筛选课程
function filterCourses(index, indexPath, item) {
    console.log(index)
    let allCourses = storage.get("courses")
    showCourses.value = allCourses.filter((x) => ((x.department == index) | (x.studytime == index)))
    if (showCourses.value.length == 0) {
        ElMessage({
            message: '抱歉，暂未收录此类课程',
            type: 'warning'
        })
    }
}

</script>

<template>
    <div class="class-side-bar">
        <el-menu
            style="border-right-width: 0; background-color: transparent; --el-menu-hover-bg-color: transparent; --el-menu-bg-color: transparent;"
            :collapse="collapse" @select="filterCourses" unique-opened>
            <el-sub-menu index="1" style="max-height: 250px;">
                <template #title>
                    <el-icon>
                        <Filter />
                    </el-icon>
                    <span>开课院系</span>
                </template>
                <el-scrollbar style="height:200px;">
                    <el-menu-item index="软件学院">
                        软件学院
                    </el-menu-item>
                    <el-menu-item index="计算机科学与技术系">
                        计算机科学与技术系
                    </el-menu-item>
                    <el-menu-item index="人工智能学院">
                        人工智能学院
                    </el-menu-item>
                    <el-menu-item index="其他院系">
                        其他院系
                    </el-menu-item>
                </el-scrollbar>
            </el-sub-menu>
            <el-sub-menu index="2" style="max-height: 250px;">
                <template #title>
                    <el-icon>
                        <CollectionTag />
                    </el-icon>
                    <span>开课时间</span>
                </template>
                <el-scrollbar style="height:200px;">
                    <el-menu-item index="大一上">
                        大一上
                    </el-menu-item>
                    <el-menu-item index="大一下">
                        大一下
                    </el-menu-item>
                    <el-menu-item index="大二上">
                        大二上
                    </el-menu-item>
                    <el-menu-item index="大二下">
                        大二下
                    </el-menu-item>
                    <el-menu-item index="大三上">
                        大三上
                    </el-menu-item>
                    <el-menu-item index="大三下">
                        大三下
                    </el-menu-item>
                    <el-menu-item index="大四上">
                        大四上
                    </el-menu-item>
                    <el-menu-item index="大四下">
                        大四下
                    </el-menu-item>
                </el-scrollbar>
            </el-sub-menu>
        </el-menu>
    </div>
    <div class="content-container">
        <CourseCard :courses="showCourses"></CourseCard>
    </div>
</template>

<style scoped>
.content-container {
    z-index: 0;
    padding-left: var(--class-content-padding-left);
    width: var(--class-content-container-width);
    margin-left: 0;
    transition: all 0.5s;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    overflow: hidden;
}

.class-side-bar {
    z-index: 1;
    position: fixed;
    margin-top: 1rem;
    width: 12rem;
    left: 0;
    display: var(--side-bar-display);
}

:deep(.el-scrollbar__bar.is-horizontal) {
    height: 0 !important;
}
</style>
