<script lang="ts" setup>
import { ref } from "vue";
import axios from "axios";
const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']) // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
const tableData = ref([])
// url为对应接口的映射
axios.get('http://localhost:9090/user/rank').then(function (resp) {
    tableData.value = resp.data
    console.log(tableData.value)
})



</script>

<template>
  <div class="layout-container-demo" style="height: 500px">
    <el-container class="content-container">
      <el-main class="content-item">
        <el-table 
          :data="tableData"  border height = 400px stripe 
          :default-sort="{ prop: 'contribution', order: 'descending' }"
          :header-cell-style = "{ color: 'rgba(128, 162, 167, 0.82)', textAlign: 'center' }"
          :cell-style="{ textAlign: 'center'}"
          >
          <el-table-column prop="username" label="Name" width="250" />
          <el-table-column prop="contribution" label="Contribution" width="250" />
          <el-table-column prop="rank" label="Rank" />
        </el-table>
      </el-main>

    </el-container>
  </div>
</template>
  
  

<style scoped>
.content-container {
  z-index: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.content-item {
  width: 50rem;
  border-radius: 16px;
  margin-bottom: 1rem;
  background-color: var(--color-background);
  box-sizing: border-box;

  box-shadow: 0 0 10px 10px rgba(128, 162, 167, 0.82);
  padding: 1rem;

}

</style>
  