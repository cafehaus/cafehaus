let request = require('request')
let fs = require('fs')

request.post('https://www.xxx.com/api/kjsc-user/qrcode/share/v2', {
	method: 'POST',
	json: true,
	headers: {
		'content-type': 'application/json',
	},
	body: {
		scene: 'jmsId=1464151114075185154',
		page: 'pages/login/login',
		is_hyaline: true,
		width: 1280,
		env_version: 'develop'
	}
}, (err, res, body) => {
	if (err) {
		console.log(err)
		return
	}
	console.log(body)
	let img = body.data.invitePic
	let base64 = img.replace(/^data:image\/\w+;base64,/, '')
	let buffer = new Buffer(base64, 'base64')

	let imgNname = '小程序码' + new Date().valueOf() + '.png'
	fs.writeFile(imgNname, buffer, (err) => {
		if (!err) {
			console.log('保存成功')
		}
	})
})