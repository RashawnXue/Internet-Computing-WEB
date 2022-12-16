<template>
  <div class="box">
    <el-main>
      <div class="head">
        <el-icon><Sunny /></el-icon>在这里上传你的资源吧
      </div>
      <el-upload class="upload-demo" drag action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
        multiple>
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
          Drop file here or <em>click to upload</em>
        </div>
        <template #tip>
          <div class="el-upload__tip">
            jpg/png files with a size less than 500kb
          </div>
        </template>
      </el-upload>
    </el-main>
  </div>

</template>

<script setup lang="ts">
import { UploadFilled } from '@element-plus/icons-vue'
</script>

  
<style scoped>
.head{
  text-align: center;
  font-family: "JetBrains Mono ExtraBold";
  font-weight: normal;
  color: lightslategray;
}
.box {
  box-sizing: border-box;
  width: 50%;
  margin: 70px auto;
  border-radius: 18px;
  box-shadow: 0 0 10px 10px lightslategray;
  display: flex;
  flex-direction: row;
}
.upload-demo{
  width: 80%;
  margin: 10px auto;
  text-align: center;
  flex-direction: row;

}

</style>
  