<script setup lang="ts">
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox, FormInstance } from 'element-plus';
import axios from 'axios'
import {useRouter} from 'vue-router'
import storage from '../utils/LocalStorage';

const dbUrl='http://localhost:9090'
const router=useRouter()
const formRef = ref<FormInstance>()
const hasLogin=ref(storage.get("userID")!==null).value
const username=ref(hasLogin?storage.get("userID"):'').value

const updateForm = reactive({
    oldpassword: '',
    newpassword: '',
    passwordCheck: ''
});
let formSender = ({
    username: '',
    oldpassword: '',
    newpassword: ''
})
const validateOldpassword = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入原密码.'))
    } else {
        callback()
    }
}
window.onload = checkNotLogin
function checkNotLogin() {
    if (!storage.get("userID")) {
        router.push('/login')
    }
}
const validatePass = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入新密码.'))
    } else if(value===updateForm.oldpassword){
        callback(new Error('与原密码相同！'))
    }else{
        if (updateForm.passwordCheck !== '') {
            if (!formRef.value) return
            formRef.value.validateField('passwordCheck', () => null)
        }
        callback()
    }
}
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
const submitForm=(formEl: FormInstance | undefined)=>{
    checkNotLogin()
    if(!formEl)return
    formEl.validate((valid)=>{
        if(valid){
            formSender=({
                username:username,
                oldpassword:updateForm.oldpassword,
                newpassword:updateForm.newpassword
            })
            axios.post(dbUrl+'/user/updatePassword',formSender).then(function (res){
                if(res.data=="success"){
                    ElMessage({
                        message: '修改密码成功！请重新登录账号.',
                        type: 'success'
                    })
                    storage.remove("userID")
                    router.go(0)
                }else{
                    ElMessageBox.alert('原密码输入不正确！', '请重新输入原密码.', {
                        confirmButtonText: '确定'
                    })
                }
            })
        }else{
            console.log('error submit!')
            return false
        }
    })
}
</script>

<template>
    <div class="inputBox" >
        <div style="padding-top:1rem;margin-bottom:0rem; text-align: center;font-size: larger;">当前账户：{{username}}</div>
        <div class="customInputForm">
            <el-form ref="formRef" :model="updateForm" status-icon :rules="rules" label-width="5rem">
                <el-form-item label="原密码" prop="oldpassword">
                    <el-input v-model="updateForm.oldpassword" autocomplete="off" />
                </el-form-item>
                <el-form-item label="新密码" prop="newpassword">
                    <el-input v-model="updateForm.newpassword" type="password" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item label="确认密码" prop="passwordCheck">
                    <el-input v-model="updateForm.passwordCheck" type="password" clearable
                        autocomplete="off" />
                </el-form-item>
                <el-form-item>
                    <el-button
                        style="margin-top: 10%;margin-left: auto;height: 70%;width: 30%;min-width: fit-content"
                        type="primary"
                        @click="submitForm(formRef)">修改密码</el-button>
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
    border-radius: 18px;
    box-shadow: 0 0 10px 10px rgba(25, 128, 139, 0.82);
    flex-direction: column;
}

.customInputForm {
    margin-top: 10%;
    height: 100%;
    width: auto;
    margin-left: 15%;
    margin-right: 15%;
    margin-bottom: 5%;
}
</style>