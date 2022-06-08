const exec = require('child_process').exec

// 通过终端 cmd 命令打开本地应用
// 跟自己在 cmd 小黑框里敲终端命令一样的效果
function openApplication(cmd) {
    return new Promise((resolve, reject) => {
        exec(cmd, {
            maxBuffer: 1024 * 2000
        }, (err, stdout, stderr) => { // err 调用是否出错  stdout 子进程的标准输出  stderr 子进程的标准错误输出
            if (err) {
                console.log(err)
                reject(err)
            } else if (stderr.lenght > 0) {
                reject(new Error(stderr.toString()))
            } else {
                console.log(stdout)
                resolve(stdout)
            }
        })
    })
}

// 打开浏览器
function openBrowser(url) {
    exec(`start ${url}`)
}

// 记事本：notepad
// 计算器：calc
// 画图板：mspaint
// 放大镜：magnify
// DVD播放器：dvdplay
// 写字板：write
// 屏幕键盘：osk
// 任务管理器：taskmgr
// 控制面板：control
// 远程桌面连接：mstsc
// 打开PS：D:/soft/AdobePhotoshopCC2019/Photoshop.exe
// * 注意打开某个应用文件夹名里不能带空格，目录里的斜杠自己换成反斜杠 /，否则会被当成转义符忽略了
openApplication('calc')

// openBrowser('https://www.baidu.com')