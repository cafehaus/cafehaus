// 1、导入http模块
const http = require('http')

// 2、创建服务器实例
const server = http.createServer()

// 2、监听客户端请求
server.on('request', (req, res) => {
    console.log('dog visit our web server')
    // console.log('req')
    // console.log(req)
    // console.log('res')
    // console.log(res)
})

// 4、启动服务器
server.listen(8080, () => {
    console.log('server runing at http://127.0.0.1:8080')
})