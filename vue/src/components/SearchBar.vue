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
        ElNotification({
            title: '注意',
            message: '请输入内容',
            type: 'warning',
            showClose: false,
        })
    }
}
</script>

<template>

    <div style="display: flex; flex-direction: row;">
        <el-input class="search-input" v-model="content" maxlength="15" placeholder="请输入内容" size="large">
        </el-input>
        <el-button :icon="Search" style="margin: 0 1rem;" type="primary" color="teal" size="large" @click="goToSearch"
            circle></el-button>
    </div>

</template>

<style>
.search-input {
    --el-input-border-radius: 50px;
    --el-input-focus-border-color: teal;
}
</style>