const express = require('express')
const router = express.Router()

router.get('/user', (req, res) => {
    console.log(req)
    res.send('请求路径' + req.url)
})

module.exports = router
