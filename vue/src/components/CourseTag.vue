<script setup>
const props = defineProps(['tag', 'color'])
</script>

<template>
    <div class="course-tag" :style="{color: color}">
        {{ tag }}
    </div>
</template>

<style scoped>
.course-tag {
    box-sizing: border-box;
    border-radius: 4px;
    padding: 0 4px;
    height: 18px;
    font-size: 10px;
    /* color: rgb(119, 127, 79); */
    font-weight: bolder;
    text-align: center;
    vertical-align: middle;
    line-height: 1.5;
    background-color: var(--el-bg-color-page);
    transition: all 0.5s;
}
</style>