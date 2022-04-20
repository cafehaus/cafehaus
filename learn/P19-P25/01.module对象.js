// 要直接打印 module 变量，必须要js文件里包含 module.exports 或 exports，才会被当成一个模块
console.log(module)

module.exports = {}
// exports = {}