<script setup>
import { useRouter } from 'vue-router';
import CourseTag from './CourseTag.vue';

const props = defineProps({
    courses: {
        type: Array,
        default: [],
    },
    new: {
        type: Boolean,
        default: false,
    }
})

const router = useRouter()

function goToCourse(item) {
    router.push({ path: '/detail', query: { courses: item.course_name } })
    ElNotification({
        title: '跳转到' + item.course_name + "的详情页",
        message: 'nothing',
        type: 'warning',
        showClose: false,
    })

}

</script>

<template>
    <el-card v-for="item in courses" class="content-item">
        <template #header>
            <div class="content-item-header">
                <CourseTag tag="课程" color="rgb(126, 125, 187)" style="margin: 2px 0.5rem 0 0;"></CourseTag>
                <div class="content-item-header-name" @click="goToCourse(item)">{{ item.coursename }}</div>
                <CourseTag tag="软件学院" color="rgb(119, 127, 79)" style="margin: 2px 0.5rem 0 0.5rem;"></CourseTag>
                <CourseTag tag="最近更新" color="rgb(63, 107, 142)" style="margin: 2px 0.5rem 0 0;" v-if="new" ></CourseTag>
            </div>
        </template>
        {{ "简介：" + item.introduction }}
    </el-card>
</template>

<style scoped>
.content-item {
    width: 50rem;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 4px;
    margin-bottom: 1rem;
    box-sizing: border-box;
    padding: 1rem;
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
