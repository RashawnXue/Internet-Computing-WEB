<template>
    <div class="content-container">
      <el-main class="content-item">
        <el-upload
        class="upload-demo"
        drag
        action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
        multiple
        >
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
  