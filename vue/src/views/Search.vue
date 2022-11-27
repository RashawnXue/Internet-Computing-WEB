<script setup>
import { useRoute } from 'vue-router';

const route = useRoute()
console.log(route)


</script>

<template>
    <div class="content-container">
        <el-divider content-position="center" style="--el-bg-color:var(--color-background-soft); --el-text-color-primary: var(--color-text); --el-border-color: var(--color-border)">
            为您展示“{{ route.query.content }}”的相关内容
        </el-divider>
    </div>
</template>

<style scoped>
.content-container {
    z-index: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>