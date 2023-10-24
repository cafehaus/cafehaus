
const express = require('express')
const app = express()

const mw = function(req, res, next) {
    console.log('我是一个简单的中间件')
    res.send('我是一个简单的中间件')

    next()
}
app.use(mw)

app.listen(8080, () => {
    console.log('success')
})