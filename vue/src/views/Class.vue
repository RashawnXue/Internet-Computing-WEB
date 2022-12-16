<script setup>

import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router';
import CourseCard from '../components/CourseCard.vue';
import axios from "axios";
import { CollectionTag, Filter } from '@element-plus/icons-vue';


// 后端课程信息数据获取
const allCourses = ref([])
const showCourses = ref([])

// url为对应接口的映射
axios.get('http://localhost:9090/course/findAll').then(function (resp) {
    allCourses.value = resp.data
    console.log(allCourses.value)
    showCourses.value = allCourses.value
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
    console.log(index)
    showCourses.value = allCourses.value.filter((x) => ((x.school == index)|(x.studytime==index)))
        //课程数据库需要添加一个新的字段(例如school)表示课程所属院系以便前端筛选
    if (showCourses.value.length == 0) {
        
    }
}

</script>

<template>
    <div class="home-side-bar">
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
    <el-divider content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        目前仅收录南京大学院系相关课程
    </el-divider>
    <div class="content-container">
        <CourseCard :courses="showCourses"></CourseCard>
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
:deep(.el-scrollbar__bar.is-horizontal) {
    height: 0 !important;
}
</style>
