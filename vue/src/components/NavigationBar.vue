<script setup>
import { ref, watch } from 'vue';
import { House, Medal, CirclePlus, Sunny, Moon, Reading } from '@element-plus/icons-vue';
import { useRouter, useRoute } from 'vue-router';
import SearchBar from './SearchBar.vue';
import { useDark } from '@vueuse/core'
import storage from '../utils/LocalStorage';

const isDark = useDark()

const router = useRouter()

const activeIndex = ref('/')

const userID = ref('未登录')

function clickAvatar() {
    console.log(storage.get("userID"))
    if(!storage.get("userID")){
        router.push('/login')
    }else{
        router.push('/account')
    }
    ElNotification({
        title: '未登录时点击头像得提示登录，登陆了之后跳转到个人信息',
        message: '可是指针移到头像上方时为什么没有变成手型呢',
        type: 'error',
        showClose: false,
    })
}

function clickUpload(){
    router.push({ path: '/upload' }); 
}

const route = useRoute()

watch(route, (val, oldval) => {
    // console.log(elMenu.activeIndex)
    // if (oldval.path == "/" && val.path != "/") {
    //     elMenu.activeIndex = null
    // }
})

</script>

<template>
    <div class="navigation-container">
        <el-menu mode="horizontal" class="navigation-menu" :default-active="route.path" router :ellipsis="false">
            <el-menu-item style="display: var(--LOGO-display);">
                LOGO
            </el-menu-item>
            <!-- index属性放路径-ysh -->
            <el-menu-item index="/">
                <!-- 使用插槽放icon-ysh -->
                <template #title>
                    <el-icon>
                        <House />
                    </el-icon>
                    <span style="margin-right: 5px;">首页</span>
                </template>
            </el-menu-item>
            <el-menu-item index="/class">
                <template #title>
                    <el-icon>
                        <Reading />
                    </el-icon>
                    <span style="margin-right: 5px;">分类</span>
                </template>
            </el-menu-item>
            <el-menu-item index = "/rank">
                <template #title>
                    <el-icon>
                        <Medal />
                    </el-icon>
                    <span style="margin-right: 5px;">排行</span>
                </template>
            </el-menu-item>
        </el-menu>
        <div style="align-self: center; display: flex; flex-direction: row;">
            <SearchBar style="align-self: center; width: 18rem;" />
            <el-button :icon="CirclePlus" style="align-self: center;" type="primary" color="teal" size="large" round @click="clickUpload">上传
            </el-button>
            <el-switch size="large" v-model="isDark" style="align-self:center; --el-switch-on-color: teal; margin: 0 1rem;" inline-prompt
                :active-icon="Moon" :inactive-icon="Sunny" />
        </div>
        <div class="user-profile">
            <div style="align-self: center; margin-right: 2rem; display: var(--userID-display);">{{ userID }}</div>
            <!-- 这里应该放一个头像，然后点击会出现一些选项 -->
            <el-avatar style="align-self: center; cursor: pointer;" @click="clickAvatar"></el-avatar>
        </div>
    </div>
</template>

<style scoped>
.navigation-menu {
    border-bottom-width: 0;
    align-self: center;
    width: 40rem;
}

.navigation-container {
    z-index: 1;
    display: flex;
    flex-direction: row;
    width: 100%;
    padding: 0 2rem;
    justify-content: space-between;
    box-shadow: var(--el-box-shadow-light);
    height: 60px;
}

.user-profile {
    align-self: center;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    width: 10rem
}
</style>

<style>
:root {
    --el-menu-active-color: teal;
    --el-menu-hover-text-color: teal;
    --el-menu-hover-bg-color: var(--el-bg-color);
    --el-menu-bg-color: var(--el-bg-color);
}
</style>