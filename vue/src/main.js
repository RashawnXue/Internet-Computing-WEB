import { createApp, VueElement } from 'vue'
import App from './App.vue'
import Router from './router/index.js'

import 'element-plus/theme-chalk/el-loading.css';
import 'element-plus/theme-chalk/el-message-box.css';
import 'element-plus/theme-chalk/el-message.css';

import 'element-plus/theme-chalk/dark/css-vars.css'

import storage from './utils/LocalStorage.js'

import './assets/main.css'
import axios from "axios";

VueElement.prototype.$storage=storage;

const app = createApp(App)
app.use(Router)
app.mount('#app')
