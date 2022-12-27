<script setup>

import { ref, onMounted, reactive, onActivated } from 'vue'
import CourseCard from '../components/CourseCard.vue';
import axios from "axios";
import URL from '../global/url';

// 后端课程信息数据获取
const courses = ref([])

// url为对应接口的映射
axios.get(URL.findAll).then(function (resp) {
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

onActivated(() => {
    document.scrollingElement.scrollTop = 0 
})

</script>

<template>
    <div class="content-container">
        <CourseCard :courses="courses"></CourseCard>
    </div>
</template>

<style scoped>
.content-container {
    margin: auto;
    z-index: 0;
    overflow: hidden;
    width: var(--home-content-container-width);
    display: flex;
    justify-content: space-evenly;
    flex-direction: row;
    flex-wrap: wrap;
}
</style>
