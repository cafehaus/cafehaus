let fs = require('fs')

let src = 'IMAGE'

fs.readdir(src, function(err, files) {
	let list = []
	files.map(m => {
		console.log(m)
		let names = m.split('.')
		let img = names[names.length - 1]
		if (['jpg','JPG', 'jpeg', 'JPEG', 'png', 'PNG'].includes(img)) {
			list.push(m)
		}
	})
	console.log(list)

	list.map((m, i) => {
		let names = m.split('.')
		let img = names[names.length - 1]
		let path  = src + '/' + m
		let newPath = src + '/' +  (i + 1) + '.' + img

		fs.rename(path, newPath, err => {
			if (!err) {
				console.log(m + '文件名修改成功')
			}
		})
	})
})
