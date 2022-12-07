import { createApp, VueElement } from 'vue'
import App from './App.vue'
import Router from './router/index.js'
import 'element-plus/theme-chalk/dark/css-vars.css'
import 'element-plus/theme-chalk/index.css'

import storage from './utils/LocalStorage.js'

import './assets/main.css'
import axios from "axios";

VueElement.prototype.$storage=storage;

const app = createApp(App)
app.use(Router)
app.mount('#app')
