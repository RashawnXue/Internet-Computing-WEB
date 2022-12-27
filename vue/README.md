# 前端日志
这个 readme 不是 github 仓库主目录下的那个 readme，或许前端交流内容都可以写在这个 readme 里(

## 关于添加页面操作 by ysh
-   各页面的根组件放在 views 文件夹下
-   子组件放在 components 文件夹下
-   src/router/index.js 里是路由，需要导入页面的 component，在 routes 数组里写页面 path 和参数等
-   还要在 NavigationBar 里把页面 path 加到 el-menu-item 里

## 关于配色方案 by ysh
elementui 的默认配色是蓝色为主，有点土，样式也不好看，可以根据个人需要改一下组件的样式(可以参照 SearchBar 的修改方式，在 style 里修改变量，变量名可以按 f12 在浏览器里找)，例会时再统一配色

## 关于支持深色模式 by ysh
我错了，其实 element-plus 官方有支持黑暗模式，
现在只要无脑用--el-bg-color（纯黑白）和--el-bg-color-page（稍浅一点）这两个颜色变量就好了

## 关于进行登录验证 by wms
参考：https://blog.csdn.net/weixin_47230082/article/details/114581904
- 创建了一个新目录：`vue/src/utils`,文件 `LocalStorage.js`记录了进行本地存储的set,get,remove方法
- 登录成功后，会在本地存储中新建一个`{key:(String)"userID",value:(String)<username>,expireTime:6000000ms}`数据
- 如果需要检查是否已登录，可以先`import storage from '../utils/LocalStorage.js'（相对路径看具体情况）`之后使用`storage.get("userID")==null`的值判断是否登录,未登录直接用router跳转到login界面；
已登录仍可以用`storage.get("userID")`获取到的用户id进行操作
- 其他页面也可以通过`storage.set()``storage.get()``storage.remove()`来向本地存储、查找、删除有时限的数据，具体用法见`LocalStorage.js`中的注释


## 主题色 by ysh
main.css中的--color-main变量是主题色(目前是青绿色)，所有用到主题色的地方都应该用这个变量而非指定的颜色，此外还有深一点浅一点的颜色，如果要用到其他颜色，也应该声明为变量写到main.css里，方便后期统一配色

## 关于响应式布局 by ysh
如果页面上只有一个中间的部分就不用做响应式布局(反正不管宽度多少都是放中间)
简易方法：参考main.css，先定义变量，再通过媒体查询修改
貌似宽度最小大概有36rem
