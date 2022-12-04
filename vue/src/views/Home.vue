<script setup>

import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router';
import IconCourse from '../components/icons/IconCourse.vue'
import IconNew from '../components/icons/IconNew.vue'
import axios from "axios";

const router = useRouter()

function goToCourse(item) {
    router.push({ path: '/detail/'+ item.courseName})
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
axios.get('http://localhost:9090/courses/findAll').then(function (resp){
  courses.value = resp.data
  console.log(courses.value)
})

</script>

<template>
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
    align-items: center;
}

.content-item {
    width: 50rem;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 4px;
    margin-bottom: 1rem;
    background-color: var(--color-background);
    box-sizing: border-box;
    padding: 1rem;
    --el-card-border-color: var(--color-border);
    color: var(--color-text);
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
