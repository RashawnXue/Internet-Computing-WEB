<script setup>

import { ref } from 'vue';
import { House } from '@element-plus/icons-vue';
import SearchBar from './SearchBar.vue';

const activeIndex = ref('/')

</script>

<template>
    <div
        style="display: flex; flex-direction: row; width: 100%; border-bottom: 1px solid #dcdfe6; padding: 0 2rem; justify-content: space-between;">
        <div style="align-self: center; width:40%;">
            <el-menu mode="horizontal"
                style="border-right-width: 0px;--el-menu-active-color: teal; --el-menu-hover-text-color: teal; --el-menu-item-hover-fill: white; --el-menu-hover-bg-color: white; --el-menu-border-color: white;"
                :default-active="activeIndex" router>
                <!-- index属性放路径-ysh -->
                <el-menu-item index="/">
                    <!-- 使用插槽放icon-ysh -->
                    <template #title>
                        <el-icon>
                            <House />
                        </el-icon>
                        <span style="margin-right: 10px;">首页</span>
                    </template>
                </el-menu-item>
                <!-- 其实搜索界面是不用加到navBar里的，这里只是占个位演示页面切换-ysh -->
                <el-menu-item index="/search">搜索</el-menu-item>
            </el-menu>
        </div>
        <SearchBar style="align-self: center;" />
        <!-- 这里应该放一个头像，然后点击会出现一些选项 -->
        <el-avatar style="align-self: center;"></el-avatar>
    </div>

</template>