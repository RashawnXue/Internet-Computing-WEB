# 前端日志

这个 readme 不是 github 仓库主目录下的那个 readme，或许前端交流内容都可以写在这个 readme 里(

## 关于添加页面操作 by ysh

-   各页面的根组件放在 views 文件夹下
-   子组件放在 components 文件夹下
-   src/router/index.js 里是路由，需要导入页面的 component，在 routes 数组里写页面 path 和参数等
-   还要在 NavigationBar 里把页面 path 加到 el-menu-item 里

## 关于配色方案 by ysh

elementui 的默认配色是蓝色为主，有点土，样式也不好看，可以根据个人需要改一下组件的样式(可以参照 SearchBar 的修改方式，在 style 里修改变量，变量名可以按 f12 在浏览器里找)，例会时再统一配色

## 关于响应式布局 by ysh

好难，摆了

## 关于支持深色模式 by ysh

我错了，其实 element-plus 官方有支持黑暗模式，
现在只要无脑用--el-bg-color（纯黑白）和--el-bg-color-page（稍浅一点）这两个颜色变量就好了
