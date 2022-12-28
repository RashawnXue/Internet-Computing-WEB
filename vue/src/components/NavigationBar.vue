<script setup>
import { ref, watch, computed, getCurrentInstance } from 'vue';
import { House, Medal, CirclePlus, Sunny, Reading, UserFilled, MostlyCloudy, Plus } from '@element-plus/icons-vue';
import { useRouter, useRoute } from 'vue-router';
import SearchBar from './SearchBar.vue';
import { useDark } from '@vueuse/core'
import storage from '../utils/LocalStorage';
import axios from "axios"
import URL from '../global/url'

const isDark = useDark()

const router = useRouter()

const activeIndex = ref('/')

var hasLogin=ref(storage.get("userID")!==null).value

const userID = ref(!hasLogin?' 未登录':storage.get("userID"))

var userContribution=-1

const userContrib=()=>{
    if(!hasLogin){
        return userContribution
    }else{
        axios.get(URL.getContrib+userID.value).then(res=>{
            userContribution=res.data
        })
        return userContribution
    }
}
/**
 * 点击头像事件
 * 未登录：跳转至登录页面
 * 已登录：彩蛋
 */
function clickAvatar() {
    if(!storage.get("userID")){
        router.push('/login')
    } else {
        ElNotification({
            title: 'while\(true\)',
            message: '\{ learn\(coding\); \}',
            showClose: false,
            position:'bottom-right',
            duration:'1000'
        })
    }
}
/**
 * 点击修改密码事件：跳转至/account页面
 */
function clickChangePassword(){
    router.push('/account')
}
/**
 * 点击登出事件:移除登录状态并刷新页面
 */
function clickLogout() {
    if (storage.get("userID") !== null) {
        storage.remove("userID")
    }
    router.go(0)
}
function clickUpload() {
    router.push({ path: '/upload' });
}

const route = useRoute()

</script>

<template>
    <div class="navigation-container" id="nav-container">
        <el-menu id="menu" mode="horizontal" class="navigation-menu" :default-active="route.path" router
            :ellipsis="false" style="--el-menu-bg-color: transparent; --el-menu-hover-bg-color: transparent;">
            <el-menu-item style="display: var(--LOGO-display);">
                WE SHARE
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
            <el-menu-item index="/rank">
                <template #title>
                    <el-icon>
                        <Medal />
                    </el-icon>
                    <span style="margin-right: 5px;">排行</span>
                </template>
            </el-menu-item>
        </el-menu>
        <div style="display: var(--nav-other-display); overflow: hidden;">
            <div style="align-self: center; display: flex; flex-direction: row; overflow: hidden;">
                <SearchBar style="align-self: center;" />
                <el-button :icon="Plus"
                    style="align-self: center; --el-button-hover-bg-color: var(--color-main-darker); --el-button-hover-border-color: var(--color-main-darker); display: var(--nav-upload-display); margin-left: 1rem;"
                    type="primary" color="var(--color-main)" size="large" round @click="clickUpload">上传
                </el-button>
                <el-switch size="large" v-model="isDark"
                    style="align-self: center; --el-switch-on-color: var(--color-main); margin: 0 1rem;"
                    inline-prompt :active-icon="MostlyCloudy" :inactive-icon="Sunny" />
            </div>
            <div class="user-profile">
                <div style="align-self: center; margin-right: 1rem; display: var(--userID-display); overflow: hidden; white-space: nowrap;">{{ userID }}</div>
                <el-popover width="15rem"
                    popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 1rem; border-radius: 8px;">
                    <template #reference>
                        <el-avatar style="align-self: center; cursor: pointer;" @click="clickAvatar">{{ userID === null
                                ? ''
                                : userID.charAt(0)
                        }}</el-avatar>
                    </template>
                    <template #default>
                        <div class="account" style="display: flex; gap: 16px; flex-direction: column">
                            <div v-if="hasLogin">
                                <p class="greetings_main" style="font-size: large; margin: 1px; font-weight: 500;">
                                    欢迎您，{{ userID }}！
                                </p>
                                <p class="greetings_sub" style="font-size:small; margin-top: 7px; font-weight: 300;">
                                    今天要学点什么呢？
                                </p>
                                <el-divider />
                                <p class="contribution" style="margin-top: 2px;">
                                    当前贡献值：{{ userContrib() }}
                                </p>
                                <div class="avatar-function-buttons">
                                    <el-button @click="clickChangePassword" class="popover-button"
                                        style="margin: 1rem 0 0 0;">
                                        修改密码
                                    </el-button>
                                    <el-button @click="clickLogout" class="popover-button"
                                        style="margin: 1rem 0 0 1rem;">
                                        退出登录
                                    </el-button>
                                </div>
                            </div>
                            <div v-else>
                                <p class="greetings_main" style="font-size: large; margin: 1px; font-weight: 500;">
                                    当前尚未登录
                                </p>
                                <el-button @click="clickAvatar" class="popover-button" style="margin: 1rem 0 0 0;">
                                    立即登录
                                </el-button>
                            </div>
                        </div>
                    </template>
                </el-popover>
            </div>
        </div>
    </div>
</template>

<style scoped>
.navigation-menu {
    border-bottom-width: 0;
    align-self: center;
    width: 100%;
    height: 60px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    transition: all 0.5s;
    padding: 0 30%;
}

.sticky {
    padding: 0;
    width: auto;
    justify-content: flex-start;
}

.navigation-container {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 0 1rem;
    box-shadow: var(--el-box-shadow-light);
    box-sizing: border-box;
    /* border-bottom: solid 1px var(--color-border); */
    border-top: solid 1px var(--color-border);
    overflow: hidden;
    transition: all 0.5s;
}
.user-profile {
    align-self: center;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    overflow: hidden;
}

.popover-button {
    --el-color-primary: var(--color-main);
    --el-button-hover-bg-color: var(--el-bg-color-page);
    --el-button-hover-border-color: var(--el-border-color-darker);
    transition: all 0.3s;
    border-radius: 4px;
}

.avatar-function-buttons {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
</style>

<style>
:root {
    --el-menu-active-color: var(--color-main);
    --el-menu-hover-text-color: var(--color-main);
}
</style>