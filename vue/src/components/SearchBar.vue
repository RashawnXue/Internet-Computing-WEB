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
            title: 'Success',
            message: '请输入内容',
            type: 'warning',
            showClose: false,
        })
    }
}
</script>

<template>
    
    <div style="display: flex; flex-direction: row;">
        <el-input v-model="content" style="--el-input-border-radius: 20px; --el-input-focus-border-color: teal" maxlength="20" placeholder="请输入内容"
            size="large">
        </el-input>
        <el-button :icon="Search" style="margin-left: 5px;" type="primary" color="teal" size="large" @click="goToSearch" circle></el-button>
    </div>

</template>