# 前端日志
这个readme不是github仓库主目录下的那个readme，或许前端交流内容都可以写在这个readme里(
## 关于添加页面操作 by ysh
- 各页面的根组件放在views文件夹下
- 子组件放在components文件夹下
- src/router/index.js里是路由，需要导入页面的component，在routes数组里写页面path和参数等
- 还要在NavigationBar里把页面加到el-menu里
## 关于配色方案 by ysh
elementui的默认配色是蓝色为主，有点土，样式也不好看，可以根据个人需要改一下组件的样式(可以参照SearchBar的修改方式，在style里修改变量，变量名可以按f12在浏览器里找)，例会时再统一配色