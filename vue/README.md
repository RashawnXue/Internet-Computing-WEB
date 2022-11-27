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

看上去其实就是把css里自己写的白色黑色这样的极端点的颜色改成变量
base.css中有自带的响应浅/深色模式的color变量(这个文件好像vue初始化的时候就有了)：

- --color-background：深色时就是纯黑，浅色时是纯白
- --color-background-soft：比前者更柔和点，没那么黑，没那么白
- --color-background-mute：跟上一个差不多
- --color-border：边框颜色，就是灰色，深色模式下颜色更深一些，反正遇到边框颜色都无脑用这个
- --color-border-hover：就是指针移到元素上方时边框的颜色，没什么用

颜色覆盖方式可以参考SearchBar.vue和NavigationBar.vue的`<style>`标签内容，
好像两种方式都可以(存疑)，但是在:root里改会影响到其他样式(其实也无所谓，反正配色要统一，改了正好)，
此外，几种覆盖element-ui的样式的方法不一定都能成功，很烦




