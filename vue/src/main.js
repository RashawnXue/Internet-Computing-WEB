import { createApp } from 'vue'
import App from './App.vue'
import Router from './router/index.js'

import './assets/main.css'
import axios from "axios";

const app = createApp(App)
app.use(Router)
app.mount('#app')
