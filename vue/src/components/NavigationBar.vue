<script setup>
import { ref, watch } from 'vue';
import { House, Medal, CirclePlus, Sunny, Moon, Reading } from '@element-plus/icons-vue';
import { useRouter, useRoute } from 'vue-router';
import SearchBar from './SearchBar.vue';
import { useDark } from '@vueuse/core'
import storage from '../utils/LocalStorage';
import axios from "axios"

const isDark = useDark()

const router = useRouter()

const activeIndex = ref('/')

const hasLogin=ref(storage.get("userID")!==null)

const userID = ref(hasLogin?'未登录':storage.get("userID"))
const userContrib = !hasLogin?0:axios.get("/user/getContrib",userID)

/*测试使用：
const userID="Mas"
const hasLogin=true
const userContrib=0*/

function clickAvatar() {
    console.log(storage.get("userID"))
    if(!storage.get("userID")){
    //if(!hasLogin){测试使用
        router.push('/login')
    }else{
        ElNotification({
        title: 'while\(true\)',
        message: '\{ drink\(coffee\); \}',
        showClose: false,
    })
    }
    /*ElNotification({
        title: '未登录时点击头像得提示登录，登陆了之后跳转到个人信息',
        message: '可是指针移到头像上方时为什么没有变成手型呢',
        type: 'error',
        showClose: false,
    })*/
}
function clickChangePassword(){
    //TODO
}
function clickLogout(){
    if(storage.get("userID")!==null){
        storage.remove("userID")
    }
    router.push('/login')
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
            <el-popover
                :width="210"
                popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
            >
                <template #reference>
                    <el-avatar style="align-self: center; cursor: pointer;" @click="clickAvatar">{{userID===null?'':userID.charAt(0)}}</el-avatar>
                </template>
                <template #default>
                    <div
                        class="account"
                        style="display: flex; gap: 16px; flex-direction: column"
                    >
                        <div v-if="hasLogin">
                            <p class="greetings_main" style="font-size: large; margin: 1px; font-weight: 500;">
                                欢迎您，{{userID}}！
                            </p>
                            <p class="greetings_sub" style="font-size:small; margin-top: 5px; font-weight: 300;">
                                今天要学点什么呢？
                            </p>
                            <el-divider style="margin: 7px;"/>
                            <p class="contribution" style="margin-top: 2px;">
                                当前贡献值：{{userContrib}}
                            </p>
                            <div class="avatarFunctionButtons">
                                <el-button @click="clickChangePassword">
                                    修改密码
                                </el-button>
                                <el-button @click="clickLogout">
                                    退出登录
                                </el-button>
                            </div>
                        </div>
                        <div v-else>
                            <p class="greetings_main" style="font-size: large; margin: 1px; font-weight: 500;">
                                当前尚未登录
                            </p>
                            <el-button @click="clickAvatar">
                                立即登录
                            </el-button>
                        </div>
                    </div>
                </template>
            </el-popover>
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