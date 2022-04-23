const express = require('express')
const app = express()

// 配置解析表单数据
app.use(express.urlencoded({ extended: false }))

// jsonp 接口要放在 cors 之前，否则会被当成 cors 处理了
app.get('/api/jsonp', (req, res) => {
    let funcName = req.query.callback
    let data = { name: 'jsonp', ginder: 'mole' }
    let str = `${funcName}(${JSON.stringify(data)})`
    console.log('req-jsonp')
    console.log(req)

    res.send(str)
})

// 注意 cors 要调用一下
const cors = require('cors')
app.use(cors())

const Router = require('./01.router')
app.use('/api', Router)

app.listen(8080, () => {
    console.log('server runing success')
})