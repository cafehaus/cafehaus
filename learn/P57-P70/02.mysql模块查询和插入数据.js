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

  
// 插入数据
// ? 标识占位符
let insertSql = 'INSERT INTO users (username, password) VALUES (?, ?)'
db.query(insertSql,['xiaocui', '910522'], (err, result) => {
    if (err) return console.log(err.message)
    
    console.log(result)
    if (result.affectedRows === 1) {
        console.log('插入数据成功')
    }
})

// 便捷方式插入数据  SET ?
let info = { username: 'xiaobo', password: '123456' }
let insertSqlPro = 'INSERT INTO users SET ?'
db.query(insertSqlPro, info, (err, result) => {
  if (err) return console.log(err.message)

  if (result.affectedRows === 1) {
    console.log('便捷方式插入成功')
  }
})