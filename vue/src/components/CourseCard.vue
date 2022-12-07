<script setup>
import { useRouter } from 'vue-router';
import IconNew from './icons/IconNew.vue';
import IconCourse from './icons/IconCourse.vue';
import IconSchool from './icons/IconSchool.vue';

const props = defineProps(['courses'])

const router = useRouter()

function goToCourse(item) {
    console.log(item.coursename)
    router.push({ path: '/detail/' + item.coursename })
    ElNotification({
        title: '跳转到' + item.coursename + "的详情页",
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
                <IconCourse style="margin: 2px 0.5rem 0 0;" />
                <div class="content-item-header-name" @click="goToCourse(item)">{{ item.coursename }}</div>
                <IconSchool style="margin: 2px 0.5rem 0 0.5rem;" tag="软件学院" />
                <IconNew style="margin: 2px 0.5rem 0 0;" />
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
