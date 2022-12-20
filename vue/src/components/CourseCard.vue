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
    router.push({ path: '/detail', query: { courses: item.coursename, itro: item.introduction, teacher: item.teachername, during: item.studytime } })
    ElNotification({
        title: '跳转到' + item.coursename + "的详情页",
        message: 'nothing',
        type: 'warning',
        showClose: false,
    })

}

</script>

<template>
    <el-card v-for="item in courses" class="content-item" style="--el-card-padding: 0" @click="goToCourse(item)">
        <template #header>
            <img src="..\assets\img\C++高级程序设计.png"/>
            <div class="content-item-header">
                <div class="content-item-header-name">{{ item.coursename }}</div>
                <div class="content-item-header-tags">
                    <CourseTag style="margin: 2px 0.5rem 0 0;" tag="课程" color="rgb(126, 125, 187)" />
                    <CourseTag style="margin: 2px 0.5rem 0 0;" tag="软件学院" color="rgb(119, 127, 79)" />
                    <CourseTag style="margin: 2px 0.5rem 0 0;" :tag="item.studytime" color="pink" />
                </div>
            </div>
        </template>
        <div style="padding: 1rem;">
            {{ "简介：" + item.introduction }}
        </div>
    </el-card>
</template>

<style scoped>
.content-item {
    position: relative;
    width: 14rem;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 8px;
    margin: 0 1rem 2rem 1rem;
    box-sizing: border-box;
    transition: all 0.5s;
    float: left;
}

.content-item:hover {
    box-shadow: var(--el-box-shadow);
}

.content-item-header {
    font-size: large;
    font-weight: bolder;
    padding: 0.5rem 1rem;
}

.content-item-header-name {
    font-weight: bolder;
    cursor: pointer;
}

.content-item-header-name:hover {
    color: var(--color-main);
}

.content-item-header-tags {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-top: 6px;
}

img {
    transition: all 0.5s;
    cursor: pointer;
    width: 100%;
}

img:hover {
    transform: scale(1.1);
}
</style>
