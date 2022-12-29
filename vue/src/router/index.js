import { createRouter, createWebHashHistory } from 'vue-router'

// 定义路由组件.
import Home from '../views/Home.vue'
import Search from '../views/Search.vue'
import detail from '../views/Detail.vue'
import Login from '../views/Login.vue'
import Account from '../views/Account.vue'
import rank from '../views/Rank.vue'
import upload from '../views/Upload.vue'
import Class from '../views/Class.vue'


// 定义一些路由
// 每个路由都需要映射到一个组件。
const routes = [
    { path: '/', component: Home },
    { path: '/search', component: Search },
    { path: '/detail', component: detail },
    { path: '/login', component: Login },
    { path: '/account', component: Account },
    { path: '/rank', component: rank },
    { path: '/upload', component: upload },
    { path: '/class', component: Class },
]

// 创建路由实例并传递 `routes` 配置
const Router = createRouter({
    // 使用 hash 模式。
    history: createWebHashHistory(),
    routes, // `routes: routes` 的缩写
})

export default Router