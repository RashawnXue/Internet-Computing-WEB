<script setup>

import { ref, onActivated } from 'vue'
import CourseCard from '../components/CourseCard.vue';
import storage from '../utils/LocalStorage';
import axios from 'axios';
import URL from '../global/url';

// 课程信息数据获取
const courses = ref(storage.get("courses"))
axios.get(URL.findAll).then(function (resp) {
    courses.value = resp.data
    storage.set("courses", resp.data, 6000000)
})


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
    flex-direction: row;
    flex-wrap: wrap;
}

</style>
