const mysql = require('mysql')

const db = mysql.createPool({
    host: '127.0.0.1',
    // port: 3306, // 默认3306，可以不写，注意数值型，mysql修改了默认端口号后，要重启后才会生效
    user: 'root',
    password: 'root',
    database: 'node'
})

module.exports = db


// windows 上 node 链接 mysql 报错：Client does not support authentication protocol requested by server; consider upgrading MySQL client
// 原因：node中的 mysql 模块不支持 mysql 8 中的 caching_sha2_password 默认的严格加密模式加密方式，所以修改加密规则为普通模式就可以了
// 解决方案：
// ① 进到安装目录 mysql/bin，登录 mysql -u root -p，输入密码
// ② 再输入命令：ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '你的密码';
// ③ 接着输入命令刷新权限：FLUSH PRIVILEGES;

// * 如果后面两条命令输入后显示：Query OK, 0 rows affected (0.01 sec)，就是成功了，注意命令后面那个分号不能少