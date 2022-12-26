<script setup>
import { useRouter } from 'vue-router';
import CourseTag from './CourseTag.vue';

const props = defineProps({
    courses: {
        type: Array,
        default: [],
    },
})

const router = useRouter()

function goToCourse(item) {
    router.push({ path: '/detail', query: { courses: item.coursename, itro: item.introduction, teacher: item.teachername, during: item.studytime, picture: item.picture, id: item.id } })
}

</script>

<template>
    <el-card v-for="item in courses" class="content-item" style="--el-card-padding: 0">
        <template #header>
            <img :src="'data:image/png;base64,' + item.picture" @click="goToCourse(item)" />
            <div class="content-item-header">
                <div class="content-item-header-name" @click="goToCourse(item)">{{ item.coursename }}</div>
                <div class="content-item-header-tags">
                    <CourseTag style="margin: 2px 0.5rem 0 0;" tag="课程" color="rgb(126, 125, 187)" />
                    <CourseTag style="margin: 2px 0.5rem 0 0;" tag="软件学院" color="rgb(119, 127, 79)" />
                    <CourseTag style="margin: 2px 0.5rem 0 0;" :tag="item.studytime" color="rgb(79, 49, 45)" />
                </div>
            </div>
        </template>
        <p class="content-item-intro">
            {{ item.introduction}}
        </p>
    </el-card>
</template>

<style scoped>
@keyframes PopUp {
    from {
        scale: 0.6;
    }

    to {
        scale: 1;
    }
}

.content-item {
    width: 14rem;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 8px;
    margin: 0 1rem 2rem 1rem;
    box-sizing: border-box;
    transition: all 0.3s;
    animation-name: PopUp;
        animation-duration: 0.5s;
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
    transition: all 0.5s;
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

.content-item-intro {
    padding: 0.5rem 1rem 1rem 1rem;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
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
