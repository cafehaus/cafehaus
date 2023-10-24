let webp = require('webp-converter')
let fs = require('fs')
let src = 'img'
let outPath = `${src}/new`

// process是node里的全局变量，返回当前进程的所有命令行参数数组，前2个元素是node命令路径和被执行的文件路径
// 获取命令行里传入的要转换的格式，默认png
let argv = process.argv

fs.readdir(src, function(err, files) {
	let list = []
	files.map(m => {
		console.log(m)
		let names = m.split('.')
		let img = names[names.length - 1]
		if (['webp', 'WEBP'].includes(img)) {
			list.push(m)
		}
	})

	// 导出的目录已经存在就删除
	delDir(outPath)

	// 创建目录
	fs.mkdir(outPath, (err) => {
		if (err) {
			return console.error(err)
		}

		onConverter(list)
	})
})

// 删除文件夹(要先删除里面的文件)
function delDir(url) {
	if (!url || !fs.existsSync(url)) return

    if (fs.statSync(url).isDirectory()) {
		let files = fs.readdirSync(url) || []

		files.map(m => {
			let curPath = url + '/' + m
			if (fs.statSync(curPath).isDirectory()) {
				delDir(curPath)
			} else {
				fs.unlinkSync(curPath)
			}
		})

		// 删除目录
		fs.rmdirSync(url)
	} else {
		fs.unlinkSync(url)
	}
}

// 遍历转换
function onConverter(arr) {
	arr.map((m, i) => {
		let format = argv[2]
		if (!format || !['jpg','JPG', 'jpeg', 'JPEG', 'png', 'PNG'].includes(format)) {
			format = 'png'
		}

		let names = m.split('.')
		let path  = src + '/' + m
		let newPath = outPath + '/' + names[0] + '.' + format

		const result = webp.dwebp(path, newPath, '-o', logging = '-v')
		result.then((res) => {
		  console.log(res)
	    })
	})
}