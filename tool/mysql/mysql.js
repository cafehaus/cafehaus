let mysql = require('mysql')
let connection = mysql.createConnection({
	host: '192.168.0.191',
	port: 3306,
	user: 'root',
	password: '123456',
	database: 'kkaccount'
})

connection.connect()

let sql = 'SELECT * FROM franchisee_user'
connection.query(sql, (err, res) => {
	if (err) {
		console.log(err.message)
		return
	}

	console.log('-------------------res-----------------------')
	console.log(res)
})