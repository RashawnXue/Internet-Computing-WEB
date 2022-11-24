<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const content = ref("")
const router = useRouter()
console.log(useRouter())

</script>

<template>
    <div>
        这是Home页
    </div>

    <!-- 两行代码极简搜索栏( -->
    <input type="text" v-model="content">
    <button @click="router.push({ path: '/search', query: { content: content } })">搜索</button>

    <div>
        要搜索的内容: {{ content }}
    </div>

</template>