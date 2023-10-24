const mysql = require('mysql')

// 连接数据库
const db = mysql.createPool({
    host: '127.0.0.1',
    user: 'root',
    password: 'jiaoshi123456',
    database: 'my_db_01'
})

// 查询测试
db.query('select * from users', (err, result) => {
    if (err) return console.log(err.message)
    
    console.log(result)
})