<script setup>
import { useRoute } from 'vue-router';
import SearchBar from '../components/SearchBar.vue';

const route = useRoute()
console.log(route)

</script>

<template>
    为您展示“{{ route.query.content }}”的相关资源
</template>