<script setup>

import { ref, onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router';
import IconCourse from '../components/icons/IconCourse.vue'
import IconNew from '../components/icons/IconNew.vue'

const router = useRouter()

// 这个数组目前是静态的，最终是要从数据库中获取
const briefIntros = ref([
    { courseName: "C++高级程序设计", courseIntro: "有一说一，以往教授的内容很难被称为高级，基本还是java语法的对应版。——jfygg" },
    { courseName: "数据结构与算法", courseIntro: "没有简介" },
    { courseName: "计算机组织结构", courseIntro: "也没有简介" },
    { courseName: "互联网计算", courseIntro: "还是没有简介" },
    { courseName: "软件工程与计算Ⅱ", courseIntro: "这是大二下的课" }]);

function goToCourse(item) {
    router.push({ path: '/detail/' + item.courseName })
    ElNotification({
        title: '跳转到' + item.courseName + "的详情页",
        message: 'nothing',
        type: 'warning',
        showClose: false,
    })
}

function selectCourses(index, indexPath, item) {
    console.log(index)
    console.log(indexPath)
    console.log(item)
    // 然后更新briefIntros数组
}

const activeIndex = ref('1')

</script>

<template>
    <div class="content-container">
        <div class="home-side-bar">
            <el-menu mode="vertical" :default-active="activeIndex"
                style="background-color: var(--color-background-soft); border-width: 0;" @select="selectCourses">
                <el-menu-item index="1">
                    <span>111</span>
                </el-menu-item>
                <el-menu-item index="2">
                    <span>222</span>
                </el-menu-item>
            </el-menu>
        </div>
        <el-card v-for="item in briefIntros" class="content-item">
            <template #header>
                <div class="content-item-header">
                    <IconCourse style="margin-right: 0.5rem; margin-top: 2px;" />
                    <div class="content-item-header-name" @click="goToCourse(item)">{{ item.courseName }}</div>
                    <IconNew style="margin-left: 0.5rem; margin-top: 2px;" />
                </div>
            </template>
            {{ "简介：" + item.courseIntro }}
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

.home-side-bar{
    position: fixed;
    width: 20rem;
    left: 2rem;
    display: var(--show-side-bar);
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
