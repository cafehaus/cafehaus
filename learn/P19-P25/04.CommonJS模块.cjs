// 从 Node.js v13.2 版本开始，Node.js 已经默认打开了 ES6 模块支持
// Node.js 要求 ES6 模块采用.mjs后缀文件名。也就是说，只要脚本文件里面使用import或者export命令，那么就必须采用.mjs后缀名

// 如果原始模块是 ES6 格式，那么需要给出一个整体输出接口，比如export default obj，使得 CommonJS 可以用import()进行加载
import('./04.module_es6.mjs').then(m => {
    console.log(1)
    console.log(m)
})

// CommonJS 的require()命令不能加载 ES6 模块，会报错，只能使用import()这个方法加载
// (async () => {
//     await import('./04.module_es6.mjs')
//     console.log(2)
// })()

// console.log(3)