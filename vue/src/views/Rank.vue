<script lang="ts" setup>

import {ref} from "vue";
import axios from "axios";
import {User, Coin, Trophy, GoldMedal} from "@element-plus/icons-vue";
import URL from '../global/url';

const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']) // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
const tableData = ref([])
// url为对应接口的映射
axios.get(URL.rank).then(function (resp) {
    tableData.value = resp.data
    console.log(tableData.value)
})

function cellStyle(obj) {
    if (obj.rowIndex === 0)
        return {color: 'rgb(239, 185, 0)', textAlign: 'center'}
    else if (obj.columnIndex === 1)
        return {textAlign: 'center'}
    else return {textAlign: 'center'}
}

</script>

<template>
    <div style="z-index: 0; display: flex; flex-direction: column; align-items: center;">
        <el-card class="table-container">
            <el-table
                :data="tableData" stripe size="large"
                :default-sort="{ prop: 'contribution', order: 'descending' }"
                :header-cell-style="{ color: 'rgba(128, 162, 167, 0.82)', textAlign: 'center' }"
                :cell-style="cellStyle"
            >
                <el-table-column prop="username" label="用户名">
                    <template #header>
                        <el-icon style="vertical-align: -0.2em;" size="large">
                            <User/>
                        </el-icon>
                        <span style="margin-left: 5px;">用户名</span>
                    </template>
                    <template #default="scope">
                        <el-icon v-if="scope.row.rank==1" style="vertical-align: -0.2em;" size="large">
                            <GoldMedal/>
                        </el-icon>
                        {{ scope.row.username }}
                    </template>
                </el-table-column>
                <el-table-column prop="contribution" label="贡献值">
                    <template #header>
                        <el-icon style="vertical-align: -0.2em;" size="large">
                            <Coin/>
                        </el-icon>
                        <span style="margin-left: 5px;">贡献值</span>
                    </template>
                </el-table-column>
                <el-table-column type="index" label="排名" width="240">
                    <template #header>
                        <el-icon style="vertical-align: -0.2em;" size="large">
                            <Trophy/>
                        </el-icon>
                        <span style="margin-left: 5px;">排名</span>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>


<style scoped>
@keyframes PopUp {
    from {
        scale: 0.9;
    }

    to {
        scale: 1;
    }
}

.table-container {
    width: var(--rank-table-width);
    border-radius: 8px;
    margin-bottom: 1rem;
    background-color: var(--el-bg-color);
    box-sizing: border-box;
    box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
    padding: 1rem;
    animation-name: PopUp;
    animation-duration: 0.5s;
}
</style>
  