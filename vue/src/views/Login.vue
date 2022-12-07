<template>
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <div onload="load();">
        <div class="loginBox">

         <div style="flex:1" padding="20px">
            <img class="loginPic" src="../assets/img/login_pic.png" style="width:100%">
         </div>

         <div style="flex:1" class="inputSection">
            <div>

                <div class="loginButtons" style="display:flex;flex-direction: row;">
                    <el-button class="loginButton" @click="(changeLoginType(true),resetForm(formRef))">用户登录</el-button>
                    <el-button class="loginButton" @click="(changeLoginType(false),resetForm(formRef))">账号注册</el-button>
                </div>
                
                <div class="customLoginForm">
                    <el-form ref="formRef" :model="loginForm" status-icon :rules="rules" label-width="5rem" >
                        <el-form-item label="用户名" prop="username" >
                            <el-input v-model="loginForm.username" autocomplete="off"/>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                            <el-input v-model="loginForm.password" type="password" clearable autocomplete="off" />
                        </el-form-item>
                        <el-form-item v-if="!pageRef.typeIsLogin" label="确认密码" prop="passwordCheck">
                            <el-input v-model="loginForm.passwordCheck" type="password" clearable autocomplete="off" />
                        </el-form-item>
                        <el-form-item>
                        <el-button style="margin-top: 10%;margin-left: 12%;height: 70%;width: 30%;min-width: fit-content" type="primary" @click="submitForm(formRef)">{{pageRef.typeIsLogin?"立即登录":"立即注册"}}</el-button>
                        </el-form-item>
                    </el-form>
                </div>
                <!--<button @click="test()">testbutton</button>-->
            </div>
         </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import axios from "axios";
import {reactive,ref} from 'vue';
import {ElMessage, ElMessageBox,FormInstance} from 'element-plus';
import { useRouter } from 'vue-router';
import storage from "../utils/LocalStorage";

const dbUrl="http://localhost:9090"
const loginUrl="/user/login"
const registerUrl="/user/register"
const router=useRouter()

const pageRef=ref({
    typeIsLogin:true,
    homeUrl:'/',
})

const formRef=ref<FormInstance>()

const loginForm = reactive({
    username:'',
    password:'',
    passwordCheck:''
});
let formSender=({
    username:'',
    password:''
})

function tryRedirectToAccount(){
    
    if(storage.get("userID")){
        ElMessage({
                message:'您已登录过，请勿重复登录！',
            })
        router.push('/account/'+storage.get("userID"))
    }
}
window.onload=tryRedirectToAccount

function resetForm (formEl:FormInstance|undefined){
    if(!formEl)return
    formEl.resetFields()
}
function changeLoginType(value:any){
    tryRedirectToAccount()
    pageRef.value.typeIsLogin=value;
};

const validateUsrname =(rule:any,value:any,callback:any)=>{
    if(value===''){
        callback(new Error('请输入用户名.'))
    }else{
        callback()
    }
}

const validatePass = (rule:any, value:any,callback:any)=>{
    if(value === ''){
        callback(new Error('请输入密码.'))
    }else{
        if(loginForm.passwordCheck!== ''){
            if(!formRef.value) return
            formRef.value.validateField('passwordCheck',()=>null)
        }
        callback()
    }
}

const validatePassCheck=(rule:any,value:any,callback:any)=>{
    if (value === '') {
        callback(new Error('请再次输入密码.'))
    } else if (value !== loginForm.password){
        callback(new Error("两次密码输入不一致!"))
    } else {
        callback()
    }
}

/*const test=(formEl: FormInstance | undefined) => {
    storage.set("userID",loginForm.username,600000)
            router.push({ path: pageRef.value.lastUrl })
            ElMessage({
                message:'登录成功！',
                type:'success'
            })
}*/

const submitForm = (formEl: FormInstance | undefined) => {
    tryRedirectToAccount()
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      console.log(loginForm)
      formSender=({
        username:loginForm.username,
        password:loginForm.password
      })
      
      if(pageRef.value.typeIsLogin){//登录代码
        axios.post(dbUrl+loginUrl,{
            params:{
                user_data_receive:formSender
            }
        }).then(function(res){
            console.log(res)
            if(res.data=="not exist"){
                
                ElMessageBox.alert('请尝试检查用户名是否输入正确，或选择账户注册。','该用户名不存在！',{
                    confirmButtonText:'确定'
                })
                
            }else{
                if(res.data=="fail"){
                        ElMessageBox.alert('请输入正确的密码。','密码输入错误！',{
                        confirmButtonText:'确定'
                    })
                }else if(res.data=="success"){
                    storage.set("userID",formSender.username,600000)//默认过期时间：10分钟
                    router.push({ path: pageRef.value.homeUrl })
                    ElMessage({
                        message:'登录成功！',
                        type:'success'
                    })
                    
                }
            }
            
        })
      }else{//注册代码
        axios.post(dbUrl+registerUrl,{
            params:{
                user_data1:formSender
            }
        }).then(function (res){
            console.log(res)
            if(res.data=="exist"){
                
                ElMessageBox.alert('请尝试使用该用户名登录。','该用户名已存在！',{
                    confirmButtonText:'确定'
                })
                pageRef.value.typeIsLogin=true;
            }else if(res.data=="success"){
                storage.set("userID",formSender.username,600000)//默认过期时间：10分钟
                router.push({ path: pageRef.value.homeUrl })
                ElMessage({
                    message:'注册成功！已自动登录。',
                    type:'success'
                })
            }
        })
      }
    } else {
      console.log('error submit!')
      return false
    }
  })
}

const rules=reactive({
    username:[{validator:validateUsrname,trigger:'blur'}],
    password: [{validator:validatePass,trigger:'blur'}],
    passwordCheck:[{validator:validatePassCheck,trigger:'blur'}] 
})


</script>

<style>
body{
    margin:0;
    padding:0;
}

.loginBox{
    box-sizing: border-box;
    width:50%;
    margin:70px auto;
    border-radius: 18px;
    box-shadow: 0 0 10px 10px rgba(25, 128, 139, 0.82);
    display:flex;
    flex-direction:row; 
}
.inputSection{
    box-sizing: border-box;
    width: 90%;
    height: 90%;
    margin: 20px;
    display: flex;
    flex-direction: column;
}
.loginButtons{
    margin: 1rem;
    margin-bottom: 0px;
    margin-top: 1rem;
}
.loginButton{
        margin-top: auto;
        width: 75%;
        margin-left: 1rem;
        margin-right:auto;
        font-size: auto;
        max-width: 10rem;
        background-color: rgb(227, 231, 234);

    }
.customLoginForm{
    margin-top: 15%;
    height: 100%;
    width: "auto";
    margin-left:0px;
    margin-right: 10%;
}

</style>