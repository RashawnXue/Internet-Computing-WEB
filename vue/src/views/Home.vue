<script setup>

import { ref, onActivated } from 'vue'
import CourseCard from '../components/CourseCard.vue';
import storage from '../utils/LocalStorage';

// 课程信息数据获取
const courses = ref(storage.get("courses"))

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
