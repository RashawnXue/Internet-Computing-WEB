<script setup>
import {useRoute} from 'vue-router';
import {ref} from "vue";
import axios from "axios";

const route = useRoute()
console.log(route)

// 后端课程信息数据获取
const courses = ref([])

// url为对应接口的映射
axios.get('http://localhost:9090/course/findByName/' + route.query.content).then(function (resp) {
    courses.value = resp.data
    console.log(courses.value)
})


</script>

<template>
    <div class="home-side-bar">
        <el-menu
            style="border-right-width: 0; background-color: transparent; --el-menu-hover-bg-color: transparent; --el-menu-bg-color: transparent"
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
        <el-divider content-position="center">
            为您展示“{{ route.query.content }}”的相关内容
        </el-divider>
        <div class="content-container">
            <el-card v-for="item in courses" class="content-item">
                <template #header>
                    <div class="content-item-header">
                        <IconCourse style="margin-right: 0.5rem; margin-top: 2px;"/>
                        <div class="content-item-header-name" @click="goToCourse(item)">{{ item.coursename }}</div>
                        <IconNew style="margin-left: 0.5rem; margin-top: 2px;"/>
                    </div>
                </template>
                {{ "简介：" + item.introduction }}
            </el-card>
        </div>
    </div>
</template>

<style scoped>
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    --el-bg-color: var(--el-bg-color-page);
}

.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: var(--content-align-items);
    padding-left: var(--content-padding-left);
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

.home-side-bar {
    z-index: 1;
    position: fixed;
    width: 12rem;
    left: 0;
    display: var(--side-bar-display);
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