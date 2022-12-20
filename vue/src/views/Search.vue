<script setup>
import { useRoute } from 'vue-router';
import CourseCard from '../components/CourseCard.vue';
import { ref, watch } from "vue";
import axios from "axios";

const route = useRoute()

// 后端课程信息数据获取
const courses = ref([])

// url为对应接口的映射
axios.get('http://localhost:9090/course/findByName/' + route.query.content).then(function (resp) {
    courses.value = resp.data
    console.log(courses.value)
})

watch(route, () => {
    axios.get('http://localhost:9090/course/findByName/' + route.query.content).then(function (resp) {
        courses.value = resp.data
        console.log(courses.value)
    })
})


</script>

<template>
    <el-divider content-position="center" style="--el-bg-color: var(--el-bg-color-page); margin-top: 0.5rem;">
        为您展示“{{ route.query.content }}”的相关内容
    </el-divider>
    <div class="content-container">
        <CourseCard :courses="courses" />
    </div>
</template>

<style scoped>
.content-container {
    margin: auto;
    z-index: 0;
    overflow: hidden;
    width: 48rem;
}
</style>