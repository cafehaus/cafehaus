const express = require('express')
const router = express.Router()

router.get('/get', (req, res) => {
    let query = req.query
    console.log(query)
    res.send({
        status: '1',
        msg: '请求成功',
        data: query
    })
})

router.post('/post', (req, res) => {
    let body = req.body
    console.log(body)
    res.send({
        status: '1',
        msg: '请求成功',
        data: body
    })
})

router.put('/put', (req, res) => {
    let body = req.body
    console.log(body)
    res.send({
        status: '1',
        msg: '请求成功',
        data: body
    })
})

router.delete('/delete', (req, res) => {
    console.log(req)
    res.send({
        status: '1',
        msg: '请求成功'
    })
})

module.exports = router