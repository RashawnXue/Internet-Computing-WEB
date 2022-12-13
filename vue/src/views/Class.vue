<script setup>

import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router';
import CourseCard from '../components/CourseCard.vue';
import axios from "axios";
import { CollectionTag} from '@element-plus/icons-vue';


// 后端课程信息数据获取
const courses = ref([])

// url为对应接口的映射
axios.get('http://localhost:9090/course/findAll').then(function (resp) {
    courses.value = resp.data
    console.log(courses.value)
})

const collapse = ref(false)

let rem = window.getComputedStyle(document.documentElement)["fontSize"]
window.onresize = function () {
    rem = window.getComputedStyle(document.documentElement)["fontSize"]
    let windowWidth = window.innerWidth
    if (windowWidth < 68 * Number(rem.substring(0, rem.length - 2))) {
        collapse.value = true
    } else {
        collapse.value = false
    }
}

// 筛选相应课程
function filterCourses(index, indexPath, item) {
    console.log(index)//这里需要向后端请求新的数据, 这里的index是院系名称
}

</script>

<template>
    <div class="home-side-bar">
        <el-menu
            style="border-right-width: 0; background-color: transparent; --el-menu-hover-bg-color: transparent; --el-menu-bg-color: transparent"
            :collapse="collapse" @select="filterCourses" default-openeds="['1']" default-active="软件学院">
            <el-sub-menu index="1">
                <template #title>
                    <el-icon>
                        <CollectionTag />
                    </el-icon>
                    <span>课程分类</span>
                </template>
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
            </el-sub-menu>
        </el-menu>
    </div>
    <div class="content-container">
        <el-divider content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        目前仅收录南京大学院系相关课程
        </el-divider>
        <CourseCard :courses="courses"></CourseCard>
    </div>
</template>

<style scoped>
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: var(--content-align-items);
    padding-left: var(--content-padding-left);
}

.home-side-bar {
    z-index: 1;
    position: fixed;
    margin-top: 1rem;
    width: 12rem;
    left: 0;
    display: var(--side-bar-display);
}
</style>
