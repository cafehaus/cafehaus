// 导入 express
const express = require('express')

// 创建服务器
const app = express()

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
