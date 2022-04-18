const fs = require('fs')
const path = require('path')
const cp = require('child_process') // 可自动打开浏览器模块

const regStyle = /<style>[\s\S]*<\/style>/
const regScript = /<script>[\s\S]*<\/script>/

// 读取文件
// 第一个参数：文件路径  第二个参数：文件格式(不指定utf-8，会是默认的二进制数据)  第三个参数：回调
fs.readFile(path.join(__dirname, './index.html'), 'utf-8', async (err, data) => {
    if (err) return console.error(err)

    console.log(data)
    // 这个地方加了await也没啥效果，原因不明
    await resolveCss(data)
    await resolveJs(data)
    resolveHtml(data)
})

// 处理css样式
function resolveCss(data) {
    let txt = regStyle.exec(data)

    // 去掉style标签
    // 注意：正则的exec匹配方法返回的是数组，同时去除首尾标签记得要加 g
    let newTxt = txt[0].replace(/^<style>|<\/style>$/g, '')
    fs.writeFile(path.join(__dirname, './clock.css'), newTxt, (err, data) => {
        if (err) return console.error(err)

        console.log('css写入成功')
    })
}

// 处理js
function resolveJs(data) {
    let txt = regScript.exec(data)

    // 去掉style标签
    // 注意：正则的exec匹配方法返回的是数组，同时去除首尾标签记得要加 g
    let newTxt = txt[0].replace(/^<script>|<\/script>$/g, '')
    fs.writeFile(path.join(__dirname, './clock.js'), newTxt, (err, data) => {
        if (err) return console.error(err)

        console.log('js写入成功')
    })
}

// 处理html
function resolveHtml(data) {
    // 内联css和js
    // 注意 script 标签不能写成自闭合标签，会导致js不执行的
    let css = '<link type="text/css" rel="stylesheet" href="./clock.css" />'
    let js = '<script type="text/javascript" src="./clock.js"></script>'

    let newTxt = data.replace(regStyle, css)
    newTxt = newTxt.replace(regScript, js)

    fs.writeFile(path.join(__dirname, './clock.html'), newTxt, (err, data) => {
        if (err) return console.error(err)

        console.log('html处理成功')
        // 想要自动在浏览器中打开html要用到 child_process 模块，不是 fs.open
        // fs.open(path.join(__dirname, './clock.html'), 'r+', (err,fd) => {
        //     if (err) return console.error(err)

        //     console.log(fd)
        // })
        openWeb(path.join(__dirname, './clock.html'))
    })
}

// 自动在浏览器中打开
function openWeb(url) {
  // 判断平台
  switch (process.platform) {
    // Mac 使用open
    case "darwin":
      cp.spawn('open', [url])
      break;
    // Windows使用start
    case "win32":
      cp.spawn('start', [url])
      break;
    // Linux等使用xdg-open
    default:
      cp.spawn('xdg-open', [url])
  }
}
