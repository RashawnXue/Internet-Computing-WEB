<script lang="ts" setup>
import axios from "axios";
import { reactive, ref } from 'vue';
import { ElMessage, ElMessageBox, FormInstance } from 'element-plus';
import { useRouter } from 'vue-router';
import storage from "../utils/LocalStorage";
import URL from '../global/url.js'

const router = useRouter()

const pageRef = ref({
    typeIsLogin: true,
    homeUrl: '/',
})

const formRef = ref<FormInstance>()

const loginForm = reactive({
    username: '',
    password: '',
    passwordCheck: ''
});
var formSender = ({
    username: '',
    password: ''
})
window.onload = checkHasLogin
/**
 * 检查是否已登录:已登录则跳转至首页
 */
function checkHasLogin() {
    if (storage.get("userID")) {
        router.push('/')
    }
}

/**
 * 清空表单数据
 * @param formEl 
 */
function resetForm(formEl: FormInstance | undefined) {
    if (!formEl) return
    formEl.resetFields()
}
/**
 * 更改登录模式/注册模式
 * @param value true:登录模式 false:注册模式
 */
function changeLoginType(value: any) {
    checkHasLogin()
    pageRef.value.typeIsLogin = value;
};
/**
 * 检查用户名表单项是否合法
 */
const validateUsrname = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入用户名.'))
    } else {
        callback()
    }
}
/**
 * 检查密码表单项是否合法
 */
const validatePass = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入密码.'))
    } else if(value.length<6){
        callback(new Error('请至少输入6位密码'))
    }else {
        if (loginForm.passwordCheck !== '') {
            if (!formRef.value) return
            formRef.value.validateField('passwordCheck', () => null)
        }
        callback()
    }
}
/**
 * 检查密码检验表单项是否合法
 */
const validatePassCheck = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请再次输入密码.'))
    } else if (value !== loginForm.password) {
        callback(new Error("两次密码输入不一致!"))
    } else {
        callback()
    }
}
/**
 * 提交表单至后端
 */
const submitForm = (formEl: FormInstance | undefined) => {
    //防止二次登录
    checkHasLogin()
    if (!formEl) return
    formEl.validate((valid) => {
        if (valid) {
            formSender = ({
                username: loginForm.username,
                password: loginForm.password
            })
            if (pageRef.value.typeIsLogin) {
                //登录代码
                axios.post(URL.login, formSender).then(function (res) {
                    if (res.data == "not exist") {
                        ElMessageBox.alert('请尝试检查用户名是否输入正确，或选择账户注册。', '该用户名不存在！', {
                            confirmButtonText: '确定'
                        })
                    } else {
                        if (res.data == "fail") {
                            ElMessageBox.alert('请输入正确的密码。', '密码输入错误！', {
                                confirmButtonText: '确定'
                            })
                        } else if (res.data == "success") {
                            storage.set("userID", formSender.username, 6000000)//默认过期时间：100分钟
                            router.go(0)
                            ElMessage({
                                message: '登录成功！',
                                type: 'success'
                            })

                        }
                    }

                })
            } else {
                //注册代码
                axios.post(URL.register, formSender).then(function (res) {
                    console.log(res)
                    if (res.data == "exist") {
                        ElMessageBox.alert('请尝试使用该用户名登录。', '该用户名已存在！', {
                            confirmButtonText: '确定'
                        })
                        pageRef.value.typeIsLogin = true;
                    } else if (res.data == "success") {
                        storage.set("userID", formSender.username, 6000000)//默认过期时间：100分钟
                        router.go(0)
                        ElMessage({
                            message: '注册成功！已自动登录。',
                            type: 'success'
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

const rules = reactive({
    username: [{ validator: validateUsrname, trigger: 'blur' }],
    password: [{ validator: validatePass, trigger: 'blur' }],
    passwordCheck: [{ validator: validatePassCheck, trigger: 'blur' }]
})
</script>


<template>
    <div onload="load();">
        <div class="loginBox">
            <div style="flex:1" padding="20px">
                <img class="loginPic" src="../assets/img/login_pic.png" style="width:100%">
            </div>
            <div style="flex:1" class="inputSection">
                <div>
                    <!--登录/注册切换按钮-->
                    <div class="loginButtons" style="display:flex;flex-direction: row;">
                        <el-button class="loginButton"
                            @click="(changeLoginType(true), resetForm(formRef))">用户登录</el-button>
                        <el-button class="loginButton"
                            @click="(changeLoginType(false), resetForm(formRef))">账号注册</el-button>
                    </div>
                    <!--登录表单框-->
                    <div class="customLoginForm">
                        <el-form ref="formRef" :model="loginForm" status-icon :rules="rules" label-width="5rem">
                            <el-form-item label="用户名" prop="username">
                                <el-input v-model="loginForm.username" autocomplete="off" />
                            </el-form-item>
                            <el-form-item label="密码" prop="password">
                                <el-input v-model="loginForm.password" type="password" clearable autocomplete="off" />
                            </el-form-item>
                            <el-form-item v-if="!pageRef.typeIsLogin" label="确认密码" prop="passwordCheck">
                                <el-input v-model="loginForm.passwordCheck" type="password" clearable
                                    autocomplete="off" />
                            </el-form-item>
                            <el-form-item>
                                <el-button
                                    style="margin-top: 10%;margin-left: auto;height: 70%;width: 30%;min-width: fit-content;"
                                    type="primary" color="rgb(1, 132, 127)"
                                    @click="submitForm(formRef)">{{ pageRef.typeIsLogin ? "立即登录" : "立即注册" }}</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>



<style>
@keyframes PopUp {
    from {
        scale: 0.9;
    }

    to {
        scale: 1;
    }
}

body {
    margin: 0;
    padding: 0;
}

.loginBox {
    box-sizing: border-box;
    width: 50%;
    margin: 3rem auto;
    border-radius: 16px;
    border: 1px solid var(--el-border-color);
    box-shadow: var(--el-box-shadow-light);
    display: flex;
    flex-direction: row;
    background-color: var(--el-bg-color);
    transition: all 0.5s;
    animation-name: PopUp;
    animation-duration: 0.5s;
}

.inputSection {
    box-sizing: border-box;
    width: 90%;
    height: 90%;
    margin: 20px;
    display: flex;
    flex-direction: column;
    transition: all 0.5s;
}

.loginButtons {
    margin: 1rem;
    margin-bottom: 0px;
    margin-top: 1rem;
}

.loginButton {
    margin-top: auto;
    width: 75%;
    margin-left: 1rem;
    margin-right: auto;
    font-size: auto;
    max-width: 10rem;
    --el-color-primary: var(--color-main);
    --el-button-hover-bg-color: var(--el-bg-color-page);
    --el-button-hover-border-color: var(--el-border-color-darker);
}

.customLoginForm {
    margin-top: 15%;
    height: 100%;
    width: "auto";
    margin-left: 0px;
    margin-right: 10%;
    transition: all 0.5s;
}
</style>