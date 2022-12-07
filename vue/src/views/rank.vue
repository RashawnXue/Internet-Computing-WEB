
<template>
  <div class="layout-container-demo" style="height: 500px">
    <el-container class="content-container">
      <el-main class="content-item">
        <el-table :data="tableData" :header-cell-style="{ color: 'rgba(255,255,255,1)', textAlign: 'center' }"
          :cell-style="{ textAlign: 'center' }">
          <el-table-column prop="name" label="Name" width="250" />
          <el-table-column prop="contribution" label="Contribution" width="250" />
          <el-table-column prop="rank" label="rank" />
        </el-table>
      </el-main>

    </el-container>
  </div>
</template>
  
  
<script lang="ts" setup>
import { ref } from "vue";

const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']) // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
const tableData = [

  {
    name: '姓名',
    contribution: '贡献度',
    rank: '排行',
  },
  {
    name: 'xrc',
    contribution: 100,
    rank: 1,
  },
  {
    name: 'wms',
    contribution: 99,
    rank: 2,
  },
  {
    name: 'ysh',
    contribution: 98,
    rank: 3,
  }, {
    name: 'lds',
    contribution: 97,
    rank: 4,
  }, {
    name: 'wqw',
    contribution: 96,
    rank: 5,
  },
]
</script>
<style scoped>
.content-container {
  z-index: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content-item {
  width: 50rem;
  box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
  border-radius: 4px;
  margin-bottom: 1rem;
  background-color: var(--color-background);
  box-sizing: border-box;
  padding: 1rem;
  color: var(--color-text);

}
</style>
  