const md =  '# 我是一级标题\n' + 
            '## 我是二级标题\n' +
            '### 我是三级标题\n' +
            '[**Showdown**](http://www.showdownjs.com) is *great*\n' +
            '\nbecause:\n' +
            ' - it\'s easy to use\n' +
            ' - it\'s extensible\n' +
            ' - works in the server and in the browser';
 
const html = `
  <div style="margin-bottom: 10px;">
    字体标签
  </div>
  <h1>h1</h1>
  <h2>h2</h2>
  <h3>h3</h3>
  <h4>h4</h4>

  <div style="margin-bottom: 10px;">
  audio标签
  </div>
  <audio title="我是标题" desc="我是小标题" src="https://media.lycheer.net/lecture/25840237/5026279_1509614610000.mp3?0.1"></audio>

  <div style="margin-top: 10px;">
  一. 转义字符
  </div>&lt;div style=&quot;color:red&quot;&gt;我是转义字符&lt;/div&gt; 

  <div style="margin-top: 10px;">
  二. a标签跳转
  </div> 
  <a href="https://www.baidu.com" target="_blank">a标签跳转</a>&nbsp; 

  <div style="margin-top: 10px;">
  三. 内联标签
  </div> 
  <span style="background-color: rgb(139, 170, 74);">我是内联标签</span>
  <br />
  <p></p>
  <p></p>

  <div style="margin-top: 10px">
  四. ul无序列表
  </div>
  <ul>
  <li style="text-align: center;"><span style="background-color: rgb(139, 170, 74);">1</span></li>
  </ul>

  <div style="margin-top: 10px;">
  五. ol有序列表
  </div>
  <ol>
  <li style="text-align: center;"><span style="background-color: rgb(139, 170, 74);">test</span></li>
  <li><span style="background-color: rgb(139, 170, 74);">test2</span></li>
  </ol>

  <div style="margin-top: 10px;">
  六. 图片渲染
  </div>
  <p><img src="https://dev-sit-1251698455.cos.ap-guangzhou.myqcloud.com/ds/22/363/20200401/3c9e7798e3204756b9e0f3263882b81f.jpeg" /><img src="https://mmbiz.qpic.cn/mmbiz_png/1gmcynicwloGkVMTr6wTHdDXlFUSaSxOSRELianAFGJYVzvXJKoM2xbbFMqKe6ONy5zoHHejNbibTJn5gdEOc1aIA/0?wx_fmt=png" /></p>
`

Page({
  data: {
    htmlText: html,
    mdText: md,
  },

  handleTagATap(url) {
    console.log(url)
  }
})