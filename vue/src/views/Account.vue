<script setup lang="ts">
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox, FormInstance } from 'element-plus';
import axios from 'axios'
import {useRouter} from 'vue-router'
import storage from '../utils/LocalStorage';
import URL from '../global/url'

const router=useRouter()
const formRef = ref<FormInstance>()
const hasLogin=ref(storage.get("userID")!==null).value
const username=ref(hasLogin?storage.get("userID"):'').value

const updateForm = reactive({
    oldpassword: '',
    newpassword: '',
    passwordCheck: ''
});
var formSender = ({
    username: '',
    password: ''
})

window.onload = checkNotLogin
function checkNotLogin() {
    if (!storage.get("userID")) {
        router.push('/login')
    }
}
/**
 * 检查原密码表单项是否合法
 */
const validateOldpassword = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入原密码.'))
    } else {
        callback()
    }
}
/**
 * 检查新密码表单项是否合法
 */
const validatePass = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入新密码.'))
    } else if(value===updateForm.oldpassword){
        callback(new Error('与原密码相同！'))
    }else if(value.length<6){
        callback(new Error('请至少输入6位密码'))
    }else{
        if (updateForm.passwordCheck !== '') {
            if (!formRef.value) return
            formRef.value.validateField('passwordCheck', () => null)
        }
        callback()
    }
}
/**
 * 检查新密码检验表单项是否合法
 */
const validatePassCheck = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请再次输入新密码.'))
    } else if (value !== updateForm.newpassword) {
        callback(new Error("两次密码输入不一致!"))
    } else {
        callback()
    }
}
const rules = reactive({
    oldpassword: [{ validator: validateOldpassword, trigger: 'blur' }],
    newpassword: [{ validator: validatePass, trigger: 'blur' }],
    passwordCheck: [{ validator: validatePassCheck, trigger: 'blur' }]
})
/**
 * 提交表单项到后端
 */
const submitForm=(formEl: FormInstance | undefined)=>{
    checkNotLogin()
    if(!formEl)return
    formEl.validate((valid)=>{
        if(valid){
            formSender=({
                username:username,
                password:updateForm.oldpassword,
            })
            axios.post(URL.checkModify,formSender).then(function (res){
                if(res.data==true){
                    formSender.password=updateForm.newpassword
                    axios.post(URL.modifyPasswd,formSender).then(function (resp){
                        if(resp.data=="success"){
                            ElMessageBox.confirm('请重新登陆账号.','修改密码成功！',{
                                confirmButtonText:'确定',
                                showCancelButton:false,
                            }).then(()=>{
                                storage.remove("userID")
                                router.go(0)
                            })
                        }else if(resp.data=="fail"){
                            ElMessageBox.alert('请重试.','修改密码失败', {
                                confirmButtonText: '确定'
                            })
                        }else if(resp.data=="not exist"){
                            ElMessageBox.alert('请重新登录.','用户不存在！' , {
                                confirmButtonText: '确定'
                            })
                            storage.remove("userID")
                            router.go(0)
                        }
                    })
                }else if(res.data==false){
                    ElMessageBox.alert('请重新输入原密码.','原密码输入不正确！' , {
                        confirmButtonText: '确定'
                    })
                }else{
                    ElMessageBox.alert('请重新登录.', '用户不存在！', {
                        confirmButtonText: '确定'
                    })
                    storage.remove("userID")
                    router.go(0)
                }
            })
        }else{
            return false
        }
    })
}
</script>

<template>
    <div class="inputBox">
        <div style="padding-top:1rem;padding-bottom: 0rem;margin-top:1rem;margin-bottom:0rem; text-align: center;font-size: larger;">当前账户：{{username}}</div>
        <div class="customInputForm">
            <el-divider style="margin-bottom:1rem;"/>
            <el-form ref="formRef" :model="updateForm" status-icon :rules="rules" label-width="5rem">
                <el-form-item label="原密码" prop="oldpassword">
                    <el-input v-model="updateForm.oldpassword" type="password" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item label="新密码" prop="newpassword">
                    <el-input v-model="updateForm.newpassword" type="password" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item label="确认密码" prop="passwordCheck">
                    <el-input v-model="updateForm.passwordCheck" type="password" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <el-button
                        style="margin-top: 10%;margin-left: auto;height: 70%;width: 30%;min-width: fit-content;"
                        type="primary"
                        @click="submitForm(formRef)" color="rgb(1, 132, 127)">修改密码</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style>
.inputBox {
    box-sizing: border-box;
    width: 25%;
    margin: 70px auto;
    padding-bottom: 1%;
    border-radius: 16px;
    box-shadow: var(--el-box-shadow-light);
    flex-direction: column;
    background-color: var(--el-bg-color);
    transition: all 0.5s;
    animation-name: PopUp;
    animation-duration: 0.5s;
}
.customInputForm {
    margin-top: 1rem;
    height: 100%;
    width: auto;
    margin-left: 15%;
    margin-right: 15%;
    margin-bottom: 5%;
    transition: all 0.5s;
}
</style>