const express = require('express')
const app = express()

// 配置跨域
const cors =  require('cors')
app.use(cors())

// 配置解析 application/x-www-form-urlencoded 格式表单数据
app.use(express.urlencoded({ extended: false }))

// 封装的响应数据全局中间件
app.use((req, res, next) => {
    // 失败
    res.err = function(err, code = '0') {
        res.send({
            data: '',
            code,
            msg: err instanceof Error ? err.message : err,
            timestamp: new Date()
        })
    }

    // 成功
    res.success = function(data = '', msg = '处理成功', code = '200') {
        res.send({
            data,
            code,
            msg,
            timestamp: new Date()
        })
    }
    next()
})

// 导入用户路由模块
const userRouter = require('./router/user')
app.use('/api', userRouter)

app.listen(8888, () => {
    console.log('server run success')
})