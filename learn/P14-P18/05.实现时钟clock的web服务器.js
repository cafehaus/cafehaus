const http = require('http')
const path = require('path')
const fs = require('fs')

const server = http.createServer()
server.on('request', (req, res) => {
    let url = req.url
    // 加上下面这句解决中文乱码的，css会不生效
    // res.setHeader('Content-Type', 'text/html;charset=utf-8')

    // 请求的文件路径
    let fileUrl = path.join(__dirname, url)

    // 优化：
    // ① 如果直接访问根路径，返回 clock文件夹下的 clock.html
    // ② 让路径可以省略输入 clock 文件下
    if (url === '/') {
        fileUrl = path.join(__dirname, './clock/clock.html')
    } else {
        fileUrl = path.join(__dirname, './clock', url)
    }

    // 读取文件内容
    fs.readFile(fileUrl, 'utf-8', (err, dataStr) => {
        if (err) return res.end('Not Found')

        res.end(dataStr)
    })
})

server.listen(8080, () => {
    console.log('server run success')
})