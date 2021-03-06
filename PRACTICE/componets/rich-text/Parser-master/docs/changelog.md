## 更新日志 {docsify-ignore} ##

#### 2020.07.28 ####
1. `A` 增加了 `search` 的扩展包，可以进行关键词搜索并高亮显示 [详细](/instructions#search)  
2. `U` 优化了解析过程，含有换行的空字符串将被去除以减小大小    
3. `U` 优化了 `uni-app` 包 `config.js` 的写法，避免格式化后可能报错的问题  
4. `F` 修复了 `getText` 方法可能无法使用的问题  
5. `F` 修复了 `ul` 中的 `li` 的黑块可能被复制的问题  
6. `F` 修复了通过 `document` 扩展包进行修改时设置了懒加载的图片可能闪一下的问题  
7. `F` 修复了 `uni-app` 包从一个文本节点变为元素节点时可能不显示的问题  
8. `F` 修复了 `uni-app` 包 `NVUE` 页面编译到小程序时列表可能显示不正常的问题  

#### 2020.07.19 ####
`npm` 包：`1.0.0`  

1. `A` 发布了微信端的 `npm` 包 [详细](https://www.npmjs.com/package/parser-wx)  
2. `U` `uni-app` 包 `H5` 端图片设置的宽度超出屏幕宽度时自动将高度设置为 `auto`，避免变形  
3. `U` 优化了 `uni-app` 包支付宝端的处理方式，减少了层级  
4. `F` 修复了 `svg` 的 `viewBox` 属性小写不生效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/171)  
5. `F` 修复了图片层级过高，无法被遮盖的问题  
6. `F` 修复了 `uni-app` 包 `NVUE` 端多次设置数据可能闪烁的问题  

#### 2020.07.11 ####
1. `A` 增加了 `in` 的 `api`，可以将锚点跳转的范围限定在一个 `scroll-view` 内 [详细](/instructions#in)  
2. `U` 支持识别 `xml` 声明（`<?xml`）  
3. `U` 优化了 `uni-app` 包 `NVUE` 端的显示模式（避免显示不全和内部滚动）  
4. `F` 修复了 `audio` 扩展包设置 `autoplay` 时状态不正确的问题  
5. `F` 修复了微信和 `QQ` 端 `sub` 和 `sup` 标签可能被错误换行的问题  
6. `F` 修复了 `uni-app` 包 `NVUE` 端无法触发 `click` 事件的问题  

#### 2020.06.30 ####
1. `F` 修复了个别情况下图片样式异常的问题 [详细](https://github.com/jin-yufeng/Parser/issues/163)  
2. `F` 修复了个别情况下会出现多余的换行的问题  

#### 2020.06.15 ####
1. `U` 文档添加 [性能优化建议](/question#性能优化建议) 和 [体验优化建议](/question#性能优化建议)  
2. `D` `html` 属性不再支持 `Array` 类型（传入 `Array` 的优化程度有限（解析时间基本 `<50ms`）；但相同的内容，解析为 `Array` 后会增加大小，进而导致网络传输时间增加；因此大部分情况下传入 `Array` 起到的优化效果不大，甚至可能负优化，还增加了处理复杂度）  

#### 2020.06.11 ####
1. `A` 增加支付宝小程序原生包 [详细](/instructions#插件包说明)  
2. `U` `uni-app` 包适配华为快应用  
3. `U` `uni-app` 包编译到 `App` 时实现了 `lazy-load` 属性（图片懒加载）  
4. `F` 修复了 `ios` 端图片长按可能导致页面失去响应的问题 [详细](https://github.com/jin-yufeng/Parser/issues/130)  
5. `F` 修复了 `uni-app` 包多次设置 `html` 内容时可能出现一些不正确情况的问题  
6. `D` 移除了 `trustAttrs` 的配置项，改为自动移除 `data-` 开头的属性  

#### 2020.05.28 ####
1. `U` `uni-app` 包适配 `360` 小程序（由于 `360` 小程序在浏览器中运行，和 `H5` 处理方式相同）  
2. `F` 修复了属性名后有空格会无法识别的问题 [详细](https://github.com/jin-yufeng/Parser/issues/152)  
3. `F` 修复了 `img` 没有设置 `src` 会报错的问题  
4. `F` 修复了 `uni-app` 包部分情况下 `errorImg` 失效的问题  
5. `F` 修复了 `uni-app` 包编译到 `NVUE` 时若 `html` 中含有换行符可能无法显示的问题  
6. `F` 修复了 `uni-app` 包编译到 `App` 时前几秒点击视频无法播放的问题  

#### 2020.05.24 ####
1. `A` 增加 `loading-img` 属性，可以设置图片加载完成前的占位图 [详细](/#设置占位图)  
2. `A` 增加 `errorImg` 的配置项，可以设置图片出错时的占位图 [详细](/#设置占位图)  
3. `F` 修复了 `uni-app` 包使用 [audio 扩展包](/instructions#audio) 后编译到百度小程序出错的问题  
4. `D` `error` 事件中不再返回 `context` 对象  

#### 2020.05.21 ####
1. `U` 支持 `embed` 标签（`type` 中含 `video` 或后缀名为 `.mp4`、`.3gp`、`.m3u8` 的将被转为视频；`type` 中含 `audio` 或后缀名为 `.m4a`、`.wav`、`.mp3`、`.aac` 的将被转为音频；其余不支持）  
2. `U` 音视频如果既没有设置 `autoplay` 也没有设置 `controls` 将自动设置 `controls`，避免无法播放  
3. `F` 修复了锚点无法跳转到 `li` 和 `a` 标签的问题 [详细](https://github.com/jin-yufeng/Parser/issues/142)  
4. `F` 修复了部分情况下 `svg` 标签 `style` 中的 `vertical-align` 无法生效的问题  
5. `F` 修复了未闭合的标签如果是 `rich-text` 不支持的标签可能无法显示的问题 [详细](https://ask.dcloud.net.cn/question/96579)  
6. `F` 修复了 `error` 事件中通过 [setSrc](/instructions#关于-error-事件) 重设图片地址后无法预览的问题  
7. `F` 修复了微信包个别情况下可能出现 `null is not an object` 错误的问题 [详细](https://github.com/jin-yufeng/Parser/issues/146)  
8. `F` 修复了百度包部分情况下预览时无法左右滑动查看所有图片的问题
9. `F` 修复了 `uni-app` 包编译到百度小程序安卓真机可能无法显示的问题 [详细](https://github.com/jin-yufeng/Parser/issues/139)  
10. `F` 修复了 `uni-app` 包编译到 `NVUE` 时通过 `v-if` 切换可能无法显示的问题 [详细](https://github.com/jin-yufeng/Parser/issues/147)  
11. `F` 修复了 `uni-app` 包编译到 `app` 时 `iframe` 无法全屏的问题  

#### 2020.05.13 ####
1. `A` 添加了 `autoscroll` 属性，可以给所有表格添加一个滚动层 [详细](/instructions#autoscroll)  
2. `U` `a` 标签可以跳转到 `tabbar` 页面  
3. `U` 通过 `stylelint` 规范 `css` 写法  
4. `U` 为避免在一些框架中使用出现文件不存在的错误，扩展包不再默认引入（原来是在 `try` 中 `require`）  
5. `U` `uni-app` 包编译到百度小程序中实现了 `autopause` 属性  
6. `U` `uni-app` 包添加了组件文档注释，输入时可以有提示  
7. `D` 移除了 `gesture-zoom` 属性  
8. `D` 移除了 `preLoad` 的 `api`

#### 2020.05.11 ####
1. `A` 增加百度小程序原生包 [详细](/instructions#插件包说明)  
2. `F` 修复了微信小程序电脑端 `rpx` 可能换算不正确的问题  
3. `F` 修复了上一版本个别情况下可能出现 `Cannot read property 'name' of undefined` 的问题  
4. `F` 修复了 `uni-app` 包编译到百度小程序时 `br` 标签可能不生效的问题  

#### 2020.05.08 ####
1. `F` 修复了个别情况下空格被错误过滤的问题 [详细](https://github.com/jin-yufeng/Parser/issues/135)  
2. `D` 移除了 `xml` 属性（`svg` 标签默认按 `xml` 方式解析，可以以 `<svg />` 方式结束）  
3. `D` 取消对 `picture` 标签的支持  

#### 2020.05.06 ####
1. `F` 修复了头条小程序真机图片可能无法显示的问题 [详细](https://github.com/jin-yufeng/Parser/issues/133)  
2. `F` 修复了 [CssHandler 扩展包](/instructions#CssHandler) 后代选择器优先级低于 `id` 选择器的问题 [详细](https://github.com/jin-yufeng/Parser/issues/125)  

#### 2020.04.26 ####
1. `F` 修复了个别情况下图片表现不正常的问题  

#### 2020.04.25 ####
1. `U` 优化了图片的显示方式（可解决加载完毕时大小突变的问题，但不再支持云文件 `ID` 和 `webp` 图片）[详细](/instructions#图片处理)  
2. `U` 支持在链接中使用实体编码  
3. `U` 模拟实现的列表（内含图片链接）支持 `list-style:none`  
4. `U` `navigateTo` 的 `api` 增加一个 `offset` 参数，可设置偏移量  
5. `U` `uni-app` 包支持使用 `audio` 扩展包  
6. `F` 修复了个别情况下图片宽度过宽的问题  
7. `F` 修复了 [CssHandler](/instructions#CssHandler) 扩展包 `class` 匹配错误的问题 [详细](https://github.com/jin-yufeng/Parser/issues/122)  
8. `F` 修复了 `uni-app` 包编译到 `NVUE` 时在 `ready` 前设置数据可能无法显示的问题  
9. `F` 修复了 `uni-app` 包编译到 `App` 时视频可能无法显示的问题  
10. `F` 修复了 `uni-app` 包编译到 `H5` 时 `tag-style` 中的 `rpx` 失效的问题  

#### 2020.04.19 ####
1. `F` 修复了 原生包 部分情况下 `table` 中在 `td` 外有文本节点会导致解析错误的问题  
2. `F` 修复了 `uni-app` 包无法使用 [document 扩展包](/instructions#document) 的问题（并增加了一些方法） [详细](https://github.com/jin-yufeng/Parser/issues/119)  

#### 2020.04.17 ####
1. `F` 修复了 `uni-app` 包 `NVUE` 端打包到安卓后可能白屏的问题（另外由于不再通过本地文件中转，显示速度应该更快）  

#### 2020.04.16 ####
1. `U` `uni-app` 包用通过 `image`（经过一些处理后）来显示图片（替代 `rich-text`），可以实现以下优化：
   1. 百度、支付宝（1.9.0）、头条小程序支持 `lazy-load`，微信和 `App` 也采用 `image` 自带的 `lazy-load`，可能性能更好  
   2. `img` 出错时也会触发 `error` 事件，且可以通过 `context` 重设 `src` [详细](/instructions#关于-error-事件)  
   3. 微信端还可以支持云文件 `ID`（2.3.0），长按弹出菜单（2.7.0），支持 `webp` 图片（2.9.0）  
2. `F` `uni-app` 包修复了 `NVUE` 中使用可能空白的问题  

#### 2020.04.14 ####
1. `A` 增加 `QQ` 小程序原生包 [详细](/instructions#插件包说明)  
2. `U` 头条小程序包优化实体编码处理  
3. `U` `uni-app` 包 `nvue` 端实现了 `navigateTo` 和 `preLoad` 的 `api`  

#### 2020.04.13 ####
1. `A` 增加头条小程序原生包 [详细](/instructions#插件包说明)  
2. `U` 针对头条小程序事件无法传递函数的问题，提供一种新的接收方式（`global.Parser.onxxx`）[详细](/instructions#事件)  
3. `F` 修复了自动移除空 `div` 导致换行失效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/111)  
4. `F` 修复了使用多个并列 `rpx` 时可能失效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/112)  
5. `F` 修复了 `uni-app` 包 `getText` 方法出错的问题 [详细](https://github.com/jin-yufeng/Parser/issues/110)  
6. `F` 修复了 `audio` 扩展包音乐名太长会导致样式错乱的问题  

#### 2020.04.12 ####
一周年撒花 🎉🎉  
1. `U` `uni-app` 包支持 `NVUE` 端  
   说明：  
   1. 实现方式  
      通过 `web-view` 实现，因为 `nvue` 不支持很多 `css`，无法直接实现和 `html` 相同的效果（最多只能和 `nvue` 中的 `rich-text` 一样），因此只能通过 `web-view` 渲染，但其渲染性能显然也不如 `nvue` 原生组件，仅应在个别场景使用，如果整个页面使用，可能性能与 `vue` 近似  

   2. 功能限制  
      - 不支持懒加载  
        `nvue` 中的 `web-view` 必须指定高度，目前在加载完毕后将总高度设置成 `web-view` 的高度（使得使用时可以不用设置），若使用图片懒加载，总高度会动态变化，会导致一些不正确的情况（另外，`nvue` 上使用的富文本应该比较短小，如果很长可能性能与 `vue` 接近，原因见上，因此懒加载必要性也不大）  
      - 不支持 `context`  
        通过 `PostMessage` 传送的数据会被 `json` 化，`context` 对象无法传送，因此不支持 `getVideoContext`，也无法在 `error` 事件中通过 `context` 重设源  
      - 其他  
        因为通过 `web-view` 实现，在表现上和 `H5` 端比较类似（支持所有浏览器支持的标签，但是在解析过程中处理的一些方法无法生效（因为不进行解析））  

   3. 注意事项  
      如果要限制富文本的高度，需要在 `parser` 标签外加一个 `scroller`，并限定 `scroller` 的高度，直接限定 `parser` 标签的高度会导致无法滚动  
      若开启 `fast` 启动模式，首页上可能无法显示  
   
   ps：如果要用 `rich-text`（若 `rich-text` 就可以实现的效果应直接使用 `rich-text`），因为 `nvue` 中仅支持 `Array`，可以通过本组件的解析脚本解析：  
   ```javascript
   var parser = require('@components/jyf-parser/libs/MpHtmlParser.js');
   new parser(html, options).parse(); // 同步方法即可获得结果
   ```

2. `U` `uni-app` 包 `App` 端支持直接通过 `plus` 打开外链  

#### 2020.03.28 ####
1. `F` 修复了 `uni-app` 包 `App(v3)` 端 `iframe` 标签无法使用的问题  

#### 2020.03.26 ####
1. `A` 增加了 `xml` 属性，可以以 `xml` 方式解析 *附：20200508版本中被删除*  
2. `F` 修复了使用自闭合 `svg` 标签会导致死循环的问题 [详细](https://github.com/jin-yufeng/Parser/issues/94)  
3. `F` 修复了设置 `domain` 属性时 `data:image` 和 `cloud://` 的链接会被错误填充的问题  

#### 2020.03.23 ####
1. `A` 增加了 `audio` 的扩展包（替代被废弃的原生 `audio`）[详细](/instructions#audio)  
2. `U` 通过 `eslint` 检查规范和修复了一些问题  

#### 2020.03.21 ####
1. `U` 没有使用 `colspan` 和 `rowspan` 的表格里的链接可以点击  

#### 2020.03.20 ####
1. `U` 所有标签支持 `align` 属性  
2. `U` 微信包将不用于渲染的属性声明为 [纯数据字段](https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/pure-data.html)，可以提升性能  
3. `F` 修复了设置 `domain` 时背景图片的链接会被错误填充的问题  
4. `F` `uni-app` 包修复了一个样式错误 [详细](https://github.com/jin-yufeng/Parser/issues/92)  
5. `F` `uni-app` 包修复了 `video` 中使用 `source` 可能无法播放的问题 [详细](https://github.com/jin-yufeng/Parser/issues/93)  

#### 2020.03.17 beta ####
1. `U` 通过 `image`（经过一些处理后）来显示图片（替代 `rich-text`），可以实现以下优化（仅微信包）：  
   1. `2.3.0` 起支持云文件 `ID`  
   2. `2.7.0` 起支持长按弹出菜单（可以识别小程序码，同时去除了 `imglongtap` 事件）  
   3. `2.9.0` 起支持 `webp` 图片  
   4. 使用 `image` 原生的 `lazy-load`，可能具有更好的性能  
   5. 加载错误时能够触发 `error` 事件，且可以重设 `src` [详细](/instructions#事件)  
   
   可能存在的问题：  
   若没有设置大小图片会在加载完成后突然从默认大小（300 × 50）变为原大小（图片较大，加载较慢时较明显），可以在 `trees.wxss` 的 `._img` 中调整默认大小  
2. `U` `a` 标签支持 `:visited` 效果（默认变为紫色，可在 `trees.wxss` 中调整）  
3. `F` 修复了 `a` 标签所在段落使用一些特殊实体编码时可能导致错误换行的问题 [详细](https://github.com/jin-yufeng/Parser/issues/87)  
4. `F` 修复了 `uni-app` 包 `H5` 端在创建时设置数据无法显示的问题 [详细](https://github.com/jin-yufeng/Parser/issues/89)  
  

#### 2020.03.12 ####
1. `A` 增加了 `compress` 属性，可以设置压缩等级 [详细](/instructions#compress)  
2. `A` 配置项中增加了 `filter` 和 `onText` 方法，可以在解析过程中进行自定义处理 [详细](/instructions#配置项)  
3. `A` 增加了 `rect` 的 `api`，可以获取内容的大小和位置 [详细](/instructions#rect)  
4. `U` `setContent` 的 `api` 支持传入 `append` 参数表示是否在尾部追加（用于加载更多）[详细](/instructions#setContent)  
5. `U` 支持通过 `base` 标签设置主域名（同 `domain` 属性，但优先级更低）  
6. `F` 修复了在 `ready` 事件触发前再次设置数据会导致 `ready` 事件不停触发的问题  

#### 2020.03.07 ####
1. `A` 增加了 `preLoad` 的 `api`，可以预加载富文本中的图片 *附：20200513版本中被删除*
2. `A` 增加了 `bindload` 事件（`dom` 加载完成时触发，即原 `ready` 事件，`ready` 事件更改为所有图片(除懒加载)加载完毕时触发，可以获取准确大小）[详细](/instructions#事件)  
3. `U` 优化了不开启 `lazy-load` 属性时的加载速度；另外开启懒加载时，首图（较大概率直接进入视野）也将不经过判断直接加载，避免因懒加载判断拖慢加载速度

#### 2020.03.01 ####
1. `U` 支持 `picture` 标签，可以在不同大小的屏幕上显示不同链接的图片 *附：20200508版本中被删除*  
2. `U` 支持在 `sub`、`sup` 标签中使用 `a` 标签  
3. `U` 给 `document` 扩展包添加和修改了一些方法 [详细](/instructions#document)  
4. `F` 修复了由于自动压缩带来的一些问题（主要是 `background-image`）  
5. `F` 修复了使用 `show-with-animation` 属性时个别情况下会白屏的问题 [详细](https://github.com/jin-yufeng/Parser/issues/82)  

#### 2020.02.26 ####
1. `A` 添加了 `parser-group` 扩展包 [详细](/instructions#parser-group)  
2. `U` `uni-app` 包 `App` 端支持 `a` 标签链接为文档时自动下载和打开，`v3` 支持 `embed` 标签  
3. `F` 修复了部分情况下连续实体编码失效的问题  

#### 2020.02.23 ####
1. `U` 支持自动压缩 `style` 属性，移除重复的样式，可以减少解析结果大小  
2. `U` 支持预览 `base64` 图片（通过暂存到本地实现）  
3. `U` `CssHandler` 扩展包支持属性选择器和 `@media`，伪类中的 `content` 支持 `attr()` [详细](/instructions#CssHandler)  
4. `U` 精简了部分代码  
5. `U` `uni-app` 包 `APP(v3)` 端支持 `iframe` 标签  

#### 2020.02.17 ####
1. `A` 增加了 `imglongtap` 事件，图片被长按时触发，可用于显示自定义菜单 *附：20200317版本中被删除，使用原生的长按菜单*  
2. `U` 优化了双击缩放的效果
3. `U` 图片设置的宽度超出屏幕宽度时自动将高度设置为 `auto`，避免变形（同时移除了 `img-mode` 属性）
4. `U` 修改了部分文件和文件夹的命名（**引入路径有变化**）[详细](/instructions#在原生框架中使用)
5. `D` 移除了 `autocopy` 和 `autopreview` 属性，如果需要禁用自动预览/复制链接，请使用  `linkpress` 和 `imgtap` 事件中的 `ignore` 函数
6. `D` 移除了 `versionHigherThan`、`parseHtml`、`parseCss` 的 `api`  
7. `D` 废弃了后端加强包

*此版本移除了部分冗余功能，与之前版本存在部分不兼容情况，请注意*

#### 2020.02.12 ####
1. `A` 增加了 `gesture-zoom` 属性，可以设置双击缩放 *附：20200513版本中被删除*
2. `U` `uni-app` 包修改命名使得符合 `easycom` 规则（`HBuilder X 2.5.5` 以上可以直接使用，无需引入；**之前版本的引入路径有变化**）[详细](https://github.com/jin-yufeng/Parser/issues/79)

#### 2020.01.23 ####
1. `A` 添加了一个打包工具 [详细](/instructions#打包工具)  
2. `U` 支持 `rpx`单位  

#### 2020.01.19 ####
1. `U` `video` 标签增加支持 `poster` 属性  
2. `F` 修复了部分情况下表格处理出错的问题 [详细](https://github.com/jin-yufeng/Parser/issues/77)  
3. `F` 修复了使用单独的 `</p>` 出错的问题  
4. `F` 修复了 `uni-app` 包 `width` 属性处理出错的问题  

#### 2020.01.18 ####
1. `U` `domain` 属性支持自动填充所有 `src` 属性的值（包括视频、音频、图片；协议名默认 `http`）  
2. `U` 优化了实体的处理（支持所有形如 `&#123;` 的实体编码）  
3. `F` 修复了图片一开始裂开之后又好了的问题

#### 2020.01.07 ####
1. `U` 支持模拟显示 `li`, `ol`, `ul` 标签（即可以在其中放图片、链接、视频等，支持 `ol` 的 `type` 属性，支持多层 `ul`，暂不支持 `list-style` 的 `css` 样式）
2. `D` 删除了 `list` 扩展包（在主包中已经默认支持）  
3. `F` 修复了传入的 `html` 为数组时预览图片会出现预览顺序颠倒的问题

#### 2020.01.05 ####
1. `U` `uni-app` 支持 `APP` 的 `v3` 模式编译  
2. `U` 精简和优化了部分代码  

#### 2019.12.30 ####
1. `A` 增加支持 `svg` 系列标签（通过转换为 `img` 实现，不可预览，不可响应点击事件） [详细](/features#svg-支持)  
2. `U` 减小了解析结果的大小（去除了一些不必要的内容，约减小 `3%`），减小了包的大小  
3. `U` `h1-6` 标签支持通过组件递归显示（即可以在其中使用图片、链接等）  
4. `U` 解决了 `Audits` 测评中 `a` 标签可点击元素的响应区域过小的问题  
5. `F` 修复了一个样式优先级的错误（属性样式的优先级应该最低）[详细](https://github.com/jin-yufeng/Parser/issues/67)  
 
#### 2019.12.21 ####
1. `F` 修复了使用 `font` 标签的 `size` 属性报错的问题 [详细](https://github.com/jin-yufeng/Parser/issues/63)

#### 2019.12.15 ####
1. `A` 增加 `setContent` 的 `api`，用于设置 `string` 类型的数据，可以减少一次 `setData` [详细](/instructions#setContent)  
2. `A` 增加 `imgList` 的 `api`，可以获取封面、设置缩略图等 [详细](/instructions#imgList)
3. `U` `a` 标签支持了 `app-id` 和 `path` 属性，可以跳转其他小程序（需要在 `app.json` 中配置跳转名单）  
4. `U` `domain` 属性支持自动补全 `css` 中 `url` 的路径
5. `U` `cache-id` 属性更名为 `use-cache`，只用选择是否使用缓存即可，缓存 `id` 会自动通过 `hash` 函数获取  
6. `U` `html` 属性传入 `array` 类型时即使没有设置 `continue`，组件也会自动进行设置（即可以传入和 `rich-text` 完全相同的格式）[详细](/instructions#组件属性)  
7. `U` 所有内置样式选择器名改为以下划线开头，避免与自定义样式的选择器冲突  
8. `U` `document` 扩展包增加 `getStyle` 和 `setStyle` 方法（返回值格式有更改） [详细](/instructions#document)  
9. `D` 废弃了 `html` 属性的 `object` 类型，请直接将 `html` 设置成原 `object.nodes`（即 `array` 类型，`imgList` 等其他信息可直接从 `nodes` 中获取） [详细](/instructions#组件属性)  
10. `D` 删除了 `animation-duration` 属性，需要修改动画时长的，可直接在 `index.js` 中修改  
11. `D` 不再对百度版插件包进行维护，如有需要可从过去版本获取 *附：20200511版本中重新支持*  

#### 2019.12.10 ####
1. `A` 增加了 `cache-id` 属性，可以将解析结果缓存到 `globalData` 中，多次打开不用重复解析 [详细](/instructions#组件属性) *注：20191215版本中更改为 use-cache*  
2. `A` 增加了 `getText` 的 `api`，可以获取到一个富文本中的所有文本内容 [详细](/instructions#getText)  
3. `A` 增加了 `getVideoContext` 的 `api`，可以获取到视频的 `context` 对象，用于操作播放状态 [详细](/instructions#getVideoContext)  
4. `A` 增加了 `highlight` 代码高亮处理接口 [详细](/instructions#配置项)  
5. `A` 增加了长内容的解决方案 [详细](/instructions#长内容处理)  
6. `U` 重构了解析脚本，提高了解析速度，减小了包的大小  
7. `U` 解决了微信最新版开发者工具会报 `wx:key="" does not look like a valid key name` 的警告的问题  
8. `U` `error` 事件将返回该视频的 `context` 对象，可以修改播放源 [详细](/instructions#事件)  
9. `F` 修复了 `uni-app` 包编译到 `H5` 时在微信内置浏览器中无法显示、存在多个 `parser` 标签时相互覆盖等问题 [详细](https://github.com/jin-yufeng/Parser/issues/59)

*此版本较之前版本在 `api` 和扩展包的引入方式上有不兼容的地方，请注意*

#### 2019.12.03 ####
1. `A` 增加了 `domain` 属性，可以设置主域名，设置后对于图片链接将自动拼接上主域名或协议名 [详细](https://github.com/jin-yufeng/Parser/issues/56)
2. `A` 增加了 `use-anchor` 属性，可以设置页面内锚点 [详细](https://github.com/jin-yufeng/Parser/issues/55)
3. `U` `CssHandler` 扩展包增加支持 `before` 和 `after` 伪类选择器 [详细](/instructions#CssHandler)

#### 2019.11.29 ####
1. `U` `linkpress`, `imgtap` 事件中增加一个 `ignore` 函数，在事件中调用此函数将不自动进行链接跳转/图片预览操作，可以屏蔽指定的链接/图片或进行自定义操作 [详细](https://github.com/jin-yufeng/Parser/issues/51)  

#### 2019.11.28 ####
1. `U` `table` 标签支持了 `border`, `cellpadding`, `cellspacing` 属性 [详细](https://github.com/jin-yufeng/Parser/issues/49)
2. `U` 重构了 `uni-app` 包编译到 `H5` 时的显示方式，采用 `html` 原生的标签渲染，实现了以下优化（仅针对 `H5` 平台，其他**不变**）：
   - 支持所有浏览器支持的标签和属性  
   - `style` 标签支持所有浏览器支持的选择器
   - `error` 事件增加支持 `img`，且返回组件的 `DOM` 实例，修改属性后可以直接对页面生效  
     另外，通过一些转换，原来的属性和事件依然全部支持（不再有 `parse` 事件，因为不进行解析）  

#### 2019.11.23 #### 
1. `A` 添加了新版文档 [详细](https://jin-yufeng.github.io/Parser/#/)

#### 2019.11.09 #### 
1. `F` 修复了 `uni-app` 包编译到 `H5` 时 `html` 的初值为空时报 `Cannot read property 'name' of undefined` 的错误的问题  

#### 2019.11.05 #### 
1. `F` 修复了 `uni-app` 包编译到 `APP` 时多个连续实体空格失效的问题  

#### 2019.11.03 #### 
1. `F` 修复了 `uni-app` 包编译到 `H5` 时多个行内标签并列会被错误换行的问题 [详细](https://github.com/jin-yufeng/Parser/issues/43)  

#### 2019.11.01 #### 
1. `U` 优化了多张相同图片的预览方式  

#### 2019.10.29 #### 
1. `F` 修复了部分行内标签被错误换行的问题  

#### 2019.10.27 #### 
1. `F` 修复了部分情况下多张相同的图片仅第一张可显示的问题  

#### 2019.10.24 #### 
1. `U` `uni-app` 包支持在 `APP` 端使用  

#### 2019.10.17 #### 
1. `A` 增加了 `CssHandler` 扩展包（可支持多层的 `css` 选择器）[详细](/instructions#CssHandler)  
2. `U` `uni-app` 包支持在 `H5` 端使用  

#### 2019.09.28 #### 
1. `A` 增加了 `lazy-load` 属性（可用于图片懒加载）[详细](https://github.com/jin-yufeng/Parser/issues/30)  

#### 2019.09.25 #### 
1. `A` 增加了 `uni-app` 插件包（可以编译到所有小程序平台）[详细](#插件包说明)    
2. `F` 修复了部分情况下样式显示错误的问题 [详细](https://github.com/jin-yufeng/Parser/issues/31)  

#### 2019.09.22 #### 
1. `U` 支持引入`wxss` / `css`文件中的外部样式 [详细](/instructions#使用外部样式)  

#### 2019.09.21 #### 
1. `A` 增加了百度小程序插件包 [详细](/instructions#插件包说明)
2. `U` 为与百度小程序包统一，所有事件的返回值改为 `object` 类型（影响 `bindimgtap` 和 `bindlinkpress`）[详细](/instructions#事件)
3. `U` 优化了扩展包的引入方式
4. `F` 修复了 `autopause` 属性在某些情况下会失效的问题  

#### 2019.09.18 #### 
1. `A` 增加了在 `wepy` 中的使用方法 [详细](/instructions#在-wepy-中使用)  
2. `F` 修复了部分情况下 `style` 标签解析时由于缺少 `;` 导致错误样式匹配失败的问题
3. `F` 修复了 `0917` 版本中 `a` 标签失效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/28)  

#### 2019.09.17 #### 
1. `A` 增加了 `list` 扩展包（可用于模拟列表） *附：20200107版本中被删除，改为插件包默认支持 *  
2. `A` `video` 组件增加支持 `unit-id` 属性（前插视频广告）  
3. `F` 修复了部分情况下图片会被 `text-indent` 错误缩进的问题  

#### 2019.09.15 #### 
1. `A` 增加了 `document` 扩展包（可用于动态操作 `DOM`）[详细](/instructions#document)  
2. `A` 增加支持小程序广告 `ad` 组件（可显示文中广告）  

#### 2019.09.13 #### 
1. `A` 增加了 `emoji` 扩展包（可用于解析小表情）[详细](/instructions#emoji)
2. `A` 增加了 `autopreview` 属性（可用于控制点击图片时是否自动预览，默认 `true`）和 `imgtap` 事件（图片被点击时触发）[详细](https://github.com/jin-yufeng/Parser/issues/23)
3. `U` 缩小了节点深度（约 `15%~35%`，主要是通过合并一些只有一个子节点的标签以及优化排版方式），优化了性能 [详细](#智能压缩)
4. `U` 缩小了解析结果的大小（约 `3%~5%`）[详细](/features#智能压缩)
5. `F` 修复了解析完成后传入的 `tagStyle` 会被修改的问题
6. `F` 修复了存在多张相同 `url` 图片时，进行预览会出现定位错误的问题 [详细](https://github.com/jin-yufeng/Parser/issues/21)
7. `F` 修复了部分情况下 `html` 中的换行符会被显示的问题

#### 2019.08.22 #### 
1. `U` 支持了 `font` 标签的 `size` 属性

#### 2019.08.21 #### 
1. `F` 修复了部分情况下实体编码内容无法显示的问题 [详细](https://github.com/jin-yufeng/Parser/issues/19)

#### 2019.08.17 #### 
1. `F` 修复了形如 `class="a b"`（多个 `class`）时样式匹配失效的问题

#### 2019.08.10 #### 
1. `U` 优化了 `a` 标签的点击态效果
2. `F` 修复了部分情况下 `span` 标签样式出错的问题

#### 2019.08.02 #### 
1. `F` 修复了部分情况下 `display:flex` 显示出错的问题

#### 2019.07.24 #### 
1. `A` 增加了 `autosetTitle` 属性，可设置是否自动将 `title` 标签的值设置到页面标题上（默认 `true`）[详细](/features#自动设置标题)
2. `F` 修复了 `margin:auto` 失效的问题

#### 2019.06.15 #### 
1. `F` 修复了部分情况下 `br` 标签换行格式不正确的问题

#### 2019.06.10 #### 
1. `A` 适配了`rich-text`组件在2.7.1基础库新增加的标签，其中`big`、`small`、`mark`、`cite`、`s`等标签在低版本都可以兼容；`bdi`、`bdo`、`caption`、`rt`、`ruby` 标签必须基础库2.7.1及以上才能正常显示，低版本会被转为`span` [详细](/features#支持丰富的标签)
2. `A` 增加了 `html2nodes`（解析`html`）、`css2object`（解析`css`）、`versionHigherThan`（比较和判断基础库版本）、`String.splice`（对字符串指定位置进行删改）等 `api` 函数 [详细](/instructions#Api) *附：20200217版本中被删除*
3. `A` 增加了 `img-mode` 属性，可以设置为 `default` 或 `widthFix`，设置为 `widthFix` 时，宽度不变，高度自动变化，可用于解决部分情况下图片变形的问题（但设置的高度会失效）[详细](/instructions#组件属性) *附：20200217版本中被删除*
4. `U` 优化了样式匹配的优先级：`tag-style` &lt; `style` 标签 &lt; 内联 `style`样式
5. `F` 修复了 `style` 标签中`,`前后有空格时导致该样式被忽略的问题

#### 2019.06.03 ####
1. `A` 增加了 `autocopy` 属性，用于控制是否允许 `a` 标签受到点击时自动复制链接（仅限 `http` 开头的网址链接；默认 `true`；接近于原 `selectable` 属性的功能）[详细](/instructions#组件属性)
2. `A` 增加了 `selectable` 属性，可用于控制是否允许长按复制任意内容（默认 `false`）[详细](/instructions#组件属性)
3. `F` 修复了 `style` 标签内容过长时安卓机可能出现栈溢出的问题

#### 2019.06.01 ####
1. `F` 修复了部分情况下 `width` 设置为百分比时失效的问题

#### 2019.05.24 ####
1. `U` 通过以自定义组件递归的方式替代了模板循环，精简包的大小至`28.1KB`，且不再受层数限制
2. `D` 删除了 `html-class` 和 `html-style` 属性，支持直接对 `Parser` 标签设置 `class` 和 `style`，默认的 `display` 是 `block`
3. `F` 修复了部分情况下节点的 `display` 和 `float` 可能不生效的问题
4. `F` 修复了背景音乐无法播放的问题（设置 `autoplay`），并支持对多个 `audio` 设置 `autoplay`

#### 2019.05.22 ####
1. `U` `bindready` 事件将返回整个组件的 `NodeRef` 结构体，包含了宽度、高度、位置等信息 [详细](/instructions#事件)
2. `U` 提高了传入的 `html` 类型为 `Array` 或 `Object` 时的渲染速度（约10%）
3. `U` 解析时若存在 `video` 或 `audio` 组件既没有 `controls` 属性也没有 `autoplay` 属性，会向控制台打印“可能不能播放”的警告

#### 2019.05.20 ####
1. `A` 增加支持 `source` 标签（仅限用于 `audio` 和 `video` 标签中），设置多个 `source` 的，会按顺序进行加载，加载失败的，自动加载下一条链接 [详细](/features#多媒体多资源加载)
2. `U` `video` 标签增加支持 `autoplay` 和 `muted` 属性
3. `U` `audio` 标签增加支持 `autoplay` 属性（仅允许自动播放一首音乐，若设置多首将仅自动播放第一首）
4. `F` 修复了视频数量超过三个时，后面的视频无法播放的问题

#### 2019.05.19 ####
1. `A` 增加了 `html-style` 属性，可以对整个富文本容器设置 `style` 样式，可通过 `wxml` 的数据绑定实现动态修改（直接在 `style` 中设置可能不生效） *附：20190524版本中被删除*
2. `A` 增加了 `show-with-animation` 和 `animation-duration` 属性，支持在显示时使用渐显动画 [详细](/features#动画显示效果) *附：20191212版本中 animation-duration 属性被删除*

#### 2019.05.17 ####
1. `A` 增加了 `ready` 事件，渲染完成时调用 [详细](/instructions#事件)
2. `A` 增加了 `error` 事件，解析错误或加载多媒体资源出错时调用 [详细](/instructions#事件)

#### 2019.05.15 ####
1. `F` 修复了一个页面内存在多个 `Parser` 组件时，`imgList` 被覆盖而导致预览失效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/4)
2. `F` 修复了图片设置 `float` 属性无效的问题

#### 2019.05.14 ####
1. `A` 增加了 `html-class` 属性，可以对整个富文本容器设置样式，包括 `display`、`margin`、`padding` 等 *附：20190524版本中被删除*
2. `D` 删除了 `scroll` 属性，默认内容宽度超出页面时允许横向滚动，如要禁止滚动可在 `html-class` 中设置 `overflow:hidden !important`
3. `F` 修复了实体编码的空格 `&nbsb;` 在部分情况下失效的问题 [详细](https://github.com/jin-yufeng/Parser/issues/2)

#### 2019.05.10 ####
1. `A` 增加了 `autopause` 属性，支持选择是否允许在播放视频时自动暂停其他视频播放 [详细](/instructions#组件属性)
2. `U` 在视频数量超过三个时，仅加载前三个，其他的由图片取代，在受到点击时再进行加载和播放，避免页面卡顿 [详细](/features#懒加载)
3. `U` 在完成样式匹配后移除了节点的 `class` 和 `id` 属性，减小了 `nodes` 数组的大小，加快渲染速度

#### 2019.05.06 ####
1. `A` 发布了后端 `node.js` 支持包 [详细](https://www.npmjs.com/package/parser-wxapp) *附：20200218版本中被废弃*
2. `U` 支持在 `Parser` 组件内加入加载提示或动画，将在未加载完成或内容为空时显示，加载完成后自动隐藏 [详细](/features#设置加载提示)

#### 2019.04.29 ####
1. `A` 增加支持将 `title` 标签中的内容设置到页面标题上，并在 `bindparse` 事件中返回（可用于转发分享）[详细](/features#自动设置标题)
2. `A` 增加`scroll`属性，可以选择是否允许页面横向滚动 *附：20190514版本中被删除*
3. `U` `style` 标签中的样式支持更多匹配模式（多个并列 `.demo1,.demo2` 等，另外对于伪类、多层的以及含有@或*的将直接忽略）[详细](/features#匹配-style-标签)
4. `F` 修复了已知 `bug`

#### 2019.04.28 ####
1. `U` 优化图片显示效果，对没有设置宽高的图片，按原大小显示（最大不超过100%）；设置了宽度或高度之一的，按比例进行缩放；同时设置了宽度和高度的，按设置的值进行拉伸；图片无法显示时，可以显示 `alt` 属性中的文本；但由于这些特性需要通过 `rich-text` 显示，因此取消了 `lazyload` 属性 *附：20190928版本重新添加（实现方式不同）*
2. `U` 优化了 `a` 标签的内联效果

#### 2019.04.26 ####
1. `A` 增加支持 `pre`, `u`, `center`, `source` 等标签 [详细](/features#支持丰富的标签)
2. `A` 增加 `bindlinkpress` 事件，在链接受到点击时触发，开发者可以在此事件中进行进一步操作（如下载和打开文档等）[详细](/instructions#事件)
3. `U` 对于不在支持列表中的标签，除个别直接移除外，都会被转为 `div` 标签，因此可以使用一些语义化标签，如 `article`, `address` 等 *附：20190610版本后不在列表中的标签会被转为行内标签*
4. `U` 提高了解析效率和渲染效率（约 `10%`）
5. `D` 删除了 `preview` 属性，默认允许图片预览 *附：20190913版本重新添加，更名为autopreview*
6. `D` 删除了 `space` 属性，由于设置连续空格会使得标签间的空格都被显示，导致错误的效果，因此取消了这一属性；如需要显示连续空格，请使用实体编码的空格或设置 `white-space` 属性
7. `F` 修复了已知 `bug`

#### 2019.04.21 ####
1. `A` 增加了 `tag-style` 属性，支持对标签设置自定义样式 [详细](/features#设置默认的标签样式)
2. `A` 发布了 `demo` 小程序 [详细](/features#案例体验)
3. `U` 降低了最低基础库的要求 [详细](/instructions#基础库要求)  
4. `F` 修复了已知 `bug`

#### 2019.04.18 #### 
1. `A` 增加支持 `audio` 标签 [详细](/features#支持丰富的标签)
2. `A` 增加支持图片懒加载（`lazyload` 属性）*附：20190428版本中被删除，20190928版本重新添加*
3. `U` 优化 `a`，`code`，`blockquote` 等标签显示效果
4. `F` 修复了已知 `bug`

#### 2019.04.16 ####
1. `U` 精简插件包的大小
2. `F` 修复已知 `bug`

#### 2019.04.14 ####
1. `U` `style` 标签中的样式支持按标签名匹配，如 `body{ Object }` [详细](/features#匹配-style-标签)
