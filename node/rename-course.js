let fs = require('fs')
let src = __dirname

fs.readdir(src, function(err, files) {
	if (err) return console.log(err)

	let list = []
	files.map(m => {
		let names = m.split('.')
		let img = names[names.length - 1]
		if (['jpg','JPG', 'jpeg', 'JPEG', 'png', 'PNG'].includes(img)) {
			list.push(m)
		}
	})

	// FireShot Capture 129 - 开篇词 - 为什么你要学习编译原理？ - time.geekbang.org.png
	list.map((m, i) => {
		let name = m.replaceAll(/FireShot Capture \d{1,} - | - time.geekbang.org/g, '')

		let path  = src + '/' + m
		let newPath = src + '/' +  name

		fs.rename(path, newPath, err => {
			if (err) return console.log(`第${i}个【${m}】修改失败`)

			console.log(`第${i}个修改成功：${name}`)
		})
	})
})
