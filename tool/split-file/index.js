const fs = require('fs')
const path = require('path')

let src = path.join(__dirname, './index.html')
let outPath = `${src}/new`

// process是node里的全局变量，返回当前进程的所有命令行参数数组，前2个元素是node命令路径和被执行的文件路径
let argv = process.argv

fs.readFile(src, function(err, file) {
	if (err) return console.log(err)
    
    onSplitFiles(file)		
	// 导出的目录已经存在就删除
	// delDir(outPath)

	// 创建目录
	// fs.mkdir(outPath, (err) => {
	// 	if (err) return console.error(err)

	// 	onSplitFiles(file)
	// })
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

function onSplitFiles(file) {
	console.log(file.toString())
}
