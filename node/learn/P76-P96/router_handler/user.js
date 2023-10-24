const db = require('../db/index')
const bcrypt = require('bcryptjs')

// 注册
exports.register = (req, res) => {
    const userInfo = req.body || {}

    // 校验用户名和密码
    if (!userInfo.userName || !userInfo.password) {
        // return res.send({
        //     code: '0',
        //     msg: '用户名和密码不能为空',
        //     data: ''
        // })
        return res.err('用户名和密码不能为空')
    }

    // 检测用户名是否重复
    const sql = 'SELECT * FROM user WHERE username=?'
    db.query(sql, userInfo.userName, (err, results) => {
        // if (err) return res.send({ code: '0', msg: err.message })
        if (err) return res.err(err)

        // 被占用
        if (results.length) {
            // return res.send({ code: '0', msg: '用户名被占用，请更换其他用户名' })
            return res.err('用户名被占用，请更换其他用户名')
        }

        // 注意：下面的代码不能写在外面，写在外面依然会执行，一次请求响应两次 res.send 会报错
        // 密码加密 bcrypt.hashSync(明文密码, 随机盐的长度)
        userInfo.password = bcrypt.hashSync(userInfo.password, 10)

        // 新用户信息插入数据库
        const sqlInsert = 'INSERT INTO user set ?'
        db.query(sqlInsert, { username: userInfo.userName, password: userInfo.password }, (err, results) => {
            if (err) return res.send({ code: '0', msg: err.message })

            // SQL 执行失败
            if (results.affectedRow !== 1) {
                return res.send({ code: '0', msg: '注册失败，请稍后再试' })
            }

            // 注册成功
            res.send({ code: '200', msg: '注册成功' })
        })
    })

    // // 密码加密 bcrypt.hashSync(明文密码, 随机盐的长度)
    // userInfo.password = bcrypt.hashSync(userInfo.password, 10)

    // // 新用户信息插入数据库
    // const sqlInsert = 'INSERT INTO user set ?'
    // db.query(sqlInsert, { username: userInfo.userName, password: userInfo.password }, (err, results) => {
    //     if (err) return res.send({ code: '0', msg: err.message })

    //     // SQL 执行失败
    //     if (results.affectedRow !== 1) {
    //         return res.send({ code: '0', msg: '注册失败，请稍后再试' })
    //     }

    //     // 注册成功
    //     res.send({ code: '200', msg: '注册成功' })
    // })

}

// 登录
exports.login = (req, res) => {

}