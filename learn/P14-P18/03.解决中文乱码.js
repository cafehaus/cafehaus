// 1、导入http模块
const http = require('http')

// 2、创建服务器实例
const server = http.createServer()

// 2、监听客户端请求
server.on('request', (req, res) => {
    console.log('dog visit our web server')
    // url 请求路径  method 请求方式
    // console.log(`收到请求${req.url}，请求方式是：${req.method}`)

    // res.setHeader()，设置 Content-Type 响应头，解决乱码问题
    res.setHeader('Content-Type', 'text/html;charset=utf-8')

    // res.end 向客户端发送响应内容
    // 注意不是 res.send
    res.end('welcome!欢迎光临')
})

// 4、启动服务器
server.listen(8080, () => {
    console.log('server runing at http://127.0.0.1:8080')
})