<template>
    <div>
        <div class="loginBox">

            <div style="flex:1" padding="20px">
                <img class="loginPic" src="@/assets/img/login_pic.png" style="width:100%">
            </div>

            <div style="flex:1" class="inputSection">
                <div>

                    <div class="loginButtons" style="display:flex;flex-direction: row;">
                        <el-button class="loginButton" @click="changeLoginType(true)">用户登录</el-button>
                        <el-button class="loginButton" @click="changeLoginType(false)">账号注册</el-button>
                    </div>

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
                                <el-button type="primary"
                                    @click="submitForm(formRef)">{{ pageRef.typeIsLogin ? "立即登录" : "立即注册" }}</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>

import { reactive, ref } from 'vue';
import type { FormInstance } from 'element-plus';

const pageRef = ref({
    typeIsLogin: true,
    lastUrl: '',

})


const formRef = ref<FormInstance>()

const loginForm = reactive({
    username: '',
    password: '',
    passwordCheck: ''
});

function changeLoginType(value: any) {
    pageRef.value.typeIsLogin = value;
};


const validateUsrname = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入用户名.'))
    } else if (value === '114514') {
        callback(new Error('哼 哼 哈啊啊啊啊啊啊啊啊啊啊啊啊啊啊'))
    } else {
        callback()
    }
}

const validatePass = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请输入密码.'))
    } else {
        if (loginForm.passwordCheck !== '') {
            if (!formRef.value) return
            formRef.value.validateField('checkPass', () => null)
        }
        callback()
    }
}

const validatePassCheck = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请再次输入密码.'))
    } else if (value !== loginForm.password) {
        callback(new Error("两次密码输入不一致!"))
    } else {
        callback()
    }
}

const submitForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.validate((valid) => {
        if (valid) {
            //这里将来要与后端对接
            console.log(loginForm)
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

<style>
body {
    margin: 0;
    padding: 0;
}

.loginBox {
    box-sizing: border-box;
    width: 50%;
    margin: 70px auto;
    border-radius: 18px;
    box-shadow: 0 0 10px 10px rgba(25, 128, 139, 0.82);
    display: flex;
    flex-direction: row;
}

.inputSection {
    box-sizing: border-box;
    width: 90%;
    height: 90%;
    margin: 20px;
    display: flex;
    flex-direction: column;
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
    background-color: rgb(227, 231, 234);

}

.customLoginForm {
    margin-top: 15%;
    height: 100%;
    width: "auto";
    margin-left: 0px;
    margin-right: 10%;
}
</style>