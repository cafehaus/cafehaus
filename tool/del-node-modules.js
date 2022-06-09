/**
*  批量删除项目里的 node_modules 文件夹
*
*  如果项目过多自己一个一个手动来删除太麻烦了，全部放在那又太占电脑内存了
*  可在命令行里传要执行的目录路径参数
*/

const fs = require('fs')
const path = require('path')

// process是node里的全局变量，返回当前进程的所有命令行参数数组，前2个元素是node命令路径和被执行的文件路径
// 获取命令行里传入的要转换的格式，默认png
let argv = process.argv

// 读取目录有命令行参数就取参数里的，没有就默认当前目录
let src = argv[2] || __dirname

fs.readdir(src, function(err, files) {
	if (err) return console.log(err)

	let list = []
	files.map(m => {
		let modulesPath = path.join(src, m, 'node_modules')

		if (fs.existsSync(modulesPath) && fs.statSync(modulesPath).isDirectory()) {
			// 删除 node_modules
			delDir(modulesPath)
			console.log(`成功删除：${modulesPath}`)
		}
	})
})

// 递归删除文件夹(要先删除里面的文件)
function delDir(url) {
	if (!url || !fs.existsSync(url)) return

    if (fs.statSync(url).isDirectory()) {
		let files = fs.readdirSync(url) || []

		files.map(m => {
			let curPath = path.join(url, m)
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