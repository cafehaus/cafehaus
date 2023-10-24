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

  
// 更新数据
// ? 标识占位符
let updateSql = 'UPDATE users SET username = ?, password = ? WHERE id = ?'
db.query(updateSql,['damei', '000000', 15], (err, result) => {
    if (err) return console.log(err.message)
    
    console.log(result)
    if (result.affectedRows === 1) {
        console.log('更新数据成功')
    }
})

// 便捷方式更新数据  SET ?
let infoPro = { username: 'xiaoma', password: '111111'}
let updateSqlPro = 'UPDATE users SET ? WHERE id = ?'
db.query(updateSqlPro,[infoPro, 18], (err, result) => {
    if (err) return console.log(err.message)
    
    console.log(result)
    if (result.affectedRows === 1) {
        console.log('更新数据成功')
    }
})

// 删除数据
let delSql = 'DELETE FROM users where id = 20 or id = 21'
db.query(delSql, (err, result) => {
  if (err) return console.log(err)

  console.log(result)
})