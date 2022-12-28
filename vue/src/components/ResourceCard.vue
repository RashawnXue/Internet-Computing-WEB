<script setup>
import CourseTag from './CourseTag.vue';

const props = defineProps({
    // 展示资源信息列表
    resources: {
        type: Array,
        default: [],
    },
    // 是否显示课程名
    showCourseName: {
        type: Boolean,
        default: false,
    }
})

// 下载功能
function downLoad(id) {
    //通过window.location调起下载框
    window.location = "http://124.221.152.134:9090/resource/downloadfile?resourceId=" + id;// url + 下载的resourceId
}

</script>

<template>
    <el-card v-for="item in resources" :key="item" class="resource-card" style="--el-card-padding: 0">
        <template #header>
            <div class="card-header">
                <div style="font-weight: bolder; display: flex; align-items: center; flex-direction: row;">
                    <CourseTag v-if="item.type=='link'" style="margin: 0 0.5rem 0 0; display: inline;" tag="链接" color="rgb(0, 47, 167)" />
                    <CourseTag v-if="item.type=='文件'" style="margin: 0 0.5rem 0 0; display: inline;" tag="文件" color="rgb(242, 134, 6)" />
                    {{ item.name}}
                    <span v-if="showCourseName" style="font-size: smaller; margin-left: 0.5rem; margin-top: 2px;">{{ item.coursename }}</span>
                </div>
                <el-button :disabled="item.type == 'link'" class="button" text size="large" @click="downLoad(item.id)" style="--el-button-text-color: var(--color-main)">下载</el-button>
            </div>
        </template>
        <div class="card-intro">
            <div v-if="item.type == 'link'">
            链接:
            <a :href="item.datapath">{{ item.datapath }}</a>
            </div>
            <div>描述: {{ item.intro }}</div>
        </div>
    </el-card>
</template>

<style scoped>
@keyframes PopUp {
    from{
        scale: 0.6;
    }to{
        scale: 1;
    }
}

.resource-card{
    box-sizing: border-box;
    margin-bottom: 1rem;
    width: 100%;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    border-radius: 8px;
    margin: 0 0 2rem 0;
    box-sizing: border-box;
    transition: all 0.3s;
    animation-name: PopUp;
    animation-duration: 0.5s;
}

.resource-card:hover {
    box-shadow: var(--el-box-shadow);
}

.card-header{
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    padding: 0.5rem 1rem;
}

.card-intro{
    padding: 0.5rem 1rem 1rem 1rem;
}
</style>