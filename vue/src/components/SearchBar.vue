<script setup>

import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Search } from '@element-plus/icons-vue'

const content = ref('')
const router = useRouter()

function goToSearch() {
    if (content.value.length > 0) {
        router.push({ path: '/search', query: { content: content.value } })
    } else {
        router.push({path: '/'})
    }
}
</script>

<template>

    <div style="display: flex; flex-direction: row;">
        <el-input class="search-input" v-model="content" maxlength="15" placeholder="搜索课程" size="large">
        </el-input>
        <el-button :icon="Search" style="margin: 0 0 0 1rem; --el-button-hover-bg-color: var(--color-main-darker); --el-button-hover-border-color: var(--color-main-darker);" type="primary" color="var(--color-main)" size="large" @click="goToSearch"
            circle></el-button>
    </div>

</template>

<style>
.search-input {
    --el-input-border-radius: 50px;
    --el-input-focus-border-color: var(--color-main);
    width: var(--search-input-width);
    transition: all 0.5s;
}
</style>