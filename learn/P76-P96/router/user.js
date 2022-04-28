const express = require('express')
const router = express.Router()

// 导入用户路由处理函数
const userHandler = require('../router_handler/user')

// 注册
router.post('/register', userHandler.register)

// 登录
router.post('/login', userHandler.login)

module.exports = router