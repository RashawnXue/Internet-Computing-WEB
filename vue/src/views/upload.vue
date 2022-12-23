<template>
  <div style="z-index: 0; display: flex; flex-direction: column; align-items: center;">
    <el-card class="table-container">
      <div>
          <el-col :span="12">
            <div class="sub-title">资源名</div>
            <el-autocomplete
                class="inline-input"
                v-model="loadFileParams.name"
                :fetch-suggestions="querySearch"
                placeholder="请输入内容"
                :trigger-on-focus="false"
                @select="handleSelect"
            ></el-autocomplete>
          </el-col>
          <el-col :span="12">
            <div class="sub-title">课程名称</div>
            <el-autocomplete
                class="inline-input"
                v-model="loadFileParams.coursename"
                :fetch-suggestions="querySearch"
                placeholder="请输入内容"
                @select="handleSelect"
            ></el-autocomplete>
          </el-col>
          <el-col :span="12">
            <div class="sub-title">资源类型</div>
            <el-autocomplete
                class="inline-input"
                v-model="loadFileParams.coursetype"
                :fetch-suggestions="querySearch1"
                placeholder="请输入内容"
                @select="handleSelect"
            ></el-autocomplete>
          </el-col>
        <el-form-item  prop="child" v-if="loadFileParams.coursetype == '链接'">
          <div class="sub-title">请输入您要上传的链接</div>
          <el-input v-model="loadFileParams.interlinking" clearable></el-input>
        </el-form-item>

        <div class="sub-title">资源介绍</div>
        <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入内容"
            v-model="loadFileParams.briefIntro">
        </el-input>
      </div>
      <el-button  size="normal" type="success" @click="submitUpload">将信息上传到服务器</el-button>
      <el-upload
          class="upload-demo"
          drag
          :action="uploadFileURL"
          :data="loadFileParams"
          :before-upload="uploadBefore"
          :on-success="successResave"
          :headers="headers"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :file-list="fileList"
          multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">贡献你的资源吧</div>
      </el-upload>
    </el-card>


  </div>


</template>

<script>
// 后端课程信息数据获取
import {ref} from "vue";
import axios from "axios";

const courses = ref([])
import storage from '../utils/LocalStorage.js'
import {ElMessageBox} from "element-plus";
const userName = storage.get("userID")
// url为对应接口的映射
axios.get('http://localhost:9090/course/findAll').then(function (resp) {
  courses.value = resp.data
  console.log(courses.value)
})
export default {
  data() {
    return {
      uploadFileURL:
          'http://localhost:9090/resource/uploadfile', // 上传文件的地址!
      loadFileParams: { // 上传文件的参数！
        name : '',
        coursename : '',
        username: userName,
        coursetype:'',
        briefIntro:'',
        interlinking : '',
      },
      headers: {
        Authorization: window.sessionStorage.getItem("myToken")
      },
      coursesU: [],
      coursetype:[],
    }
  },
  methods: {
    submitUpload() {
      if(this.loadFileParams.name == ''){
        ElMessageBox.alert('请您检查资源名输入', {
          confirmButtonText: '确定',
        })
        return
      }
      if(this.loadFileParams.coursename == ''){
        ElMessageBox.alert('请您检查课程名称输入', {
          confirmButtonText: '确定',
        })
        return
      }
      if(this.loadFileParams.coursetype == ''){
        ElMessageBox.alert('请您检查资源类型输入', {
          confirmButtonText: '确定',
        })
        return
      }
      if(this.loadFileParams.coursetype == '链接'){
        if(this.loadFileParams.interlinking == ''){
          ElMessageBox.alert('请您检查链接输入', {
            confirmButtonText: '确定',
          })
          return
        }
      }
      if(this.loadFileParams.briefIntro == ''){
        ElMessageBox.alert('请您检查资源介绍输入', {
          confirmButtonText: '确定',
        })
        return
      }
      if(storage.get("userID")==null){
        ElMessageBox.alert('请您先登录', {
          confirmButtonText: '确定'
        })
      }else{
        axios.post(this.uploadFileURL, this.loadFileParams).then(function (res) {
          console.log(res)
        })
      }


    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file,this.loadFileParams);
    },
    uploadBefore(file) { // 这个函数的目的就是在上传文件之前获取到文件的名字然后保存到携带的参数变量里面！
      this.loadFileParams.fileNames = file.name
    },
    successResave(response, file, fileList){
      console.log(response)
      if(response.code==10001){
        alert("保存成功")
        this.proType="";
        this.mobanname="";
        this.value5="";
        this.$router.push("/moban")
      }
    },
    querySearch(queryString, cb) {
      var coursesU = this.coursesU;
      var results = queryString ? coursesU.filter(this.createFilter(queryString)) : coursesU;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    querySearch1(queryString, cb) {
      var coursetype = this.coursetype;
      var results = queryString ? coursetype.filter(this.createFilter(queryString)) : coursetype;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return (coursesU) => {
        return (coursesU.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    loadAll() {
      return [
        { "value": "数据结构与算法"},
        { "value": "互联网计算"},
        { "value": "C++高级程序设计"},
        { "value": "计算机组织与结构"}
      ];
    },
    loadAll1() {
      return [
        { "value": "文件"},
        { "value": "链接"},
      ];
    },
    handleSelect(item) {
      if(this.loadFileParams.coursetype == "链接")
          console.log(item);
    }
  },
  mounted() {
    this.coursesU = this.loadAll();
    this.coursetype = this.loadAll1();
  }


}
</script>

<style scoped>
.table-container {
  width: 50rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  background-color: var(--el-bg-color);
  box-sizing: border-box;
  box-shadow: 0 1px 3px hsl(0deg 0% 7% / 10%);
  padding: 1rem;
}

</style>
