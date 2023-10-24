// 导入 express
const express = require('express')

// 创建服务器
const app = express()

// 处理静态资源
app.use(express.static('public'))
// 默认文件目录不会出现在路径中，可以自己添加
// app.use('/public', express.static('public'))

app.get('/user', (req, res) => {
    // 获取路径参数
    let query = req.query

    res.send(query)
})

app.get('/goods/:id', (req, res) => {
    // 获取动态参数
    let params = req.params

    res.send(params)
})

app.post('/user', (req, res) => {
    res.send('接受到了一个post请求')
})

// 启动服务器
app.listen(8080, () => {
    console.log('express runing')
})
