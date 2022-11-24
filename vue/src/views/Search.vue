<script setup>
import { useRoute } from 'vue-router';
const route = useRoute()
console.log(route)


</script>

<template>
    你搜索的内容是: {{route.query.content}}
</template>