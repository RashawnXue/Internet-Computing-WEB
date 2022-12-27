<script setup>
import { useRoute } from 'vue-router';
import CourseCard from '../components/CourseCard.vue';
import ResourceCard from '../components/ResourceCard.vue';
import { ref, watch } from "vue";
import axios from "axios";
import URL from '../global/url';

const route = useRoute()

// 后端课程信息数据获取
const courses = ref([])
const resources = ref([])
axios.get(URL.findCourseByName + route.query.content).then(function (resp) {
    courses.value = resp.data
    console.log(courses.value)
})
axios.get(URL.findResourceByName + route.query.content).then(function (resp) {
    resources.value = resp.data
    console.log(resources.value)
})

watch(route, () => {
    axios.get(URL.findCourseByName + route.query.content).then(function (resp) {
        courses.value = resp.data
        console.log(courses.value)
    })
    axios.get(URL.findResourceByName + route.query.content).then(function (resp) {
        resources.value = resp.data
        console.log(resources.value)
    })
})

</script>

<template>
    <el-divider v-if="courses.length > 0" content-position="center"
        style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        为您展示“{{ route.query.content }}”的相关课程
    </el-divider>
    <el-divider v-else content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        未搜索到“{{ route.query.content }}”的相关课程
    </el-divider>
    <div class="course-cards-content-container">
        <CourseCard :courses="courses" />
    </div>
    <el-divider v-if="resources.length > 0" content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        为您展示“{{ route.query.content }}”的相关资源
    </el-divider>
    <el-divider v-else content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        未搜索到“{{ route.query.content }}”的相关资源
    </el-divider>
    <div class="resource-cards-content-container">
        <ResourceCard :resources="resources" showCourseName/>
    </div>
</template>

<style scoped>
.course-cards-content-container {
    margin: auto;
    z-index: 0;
    width: var(--home-content-container-width);
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-evenly;
    transition: all 0.5s;
}

.resource-cards-content-container {
    margin: auto;
    width: var(--search-resource-cards-content-container-width);
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: all 0.5s;
}
</style>