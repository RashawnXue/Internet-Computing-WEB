import { createApp } from 'vue'
import App from './App.vue'
import Router from './router/index.js'
import 'element-plus/theme-chalk/dark/css-vars.css'

import './assets/main.css'
import axios from "axios";

const app = createApp(App)
app.use(Router)
app.mount('#app')
