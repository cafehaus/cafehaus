// 从 Node.js v13.2 版本开始，Node.js 已经默认打开了 ES6 模块支持
// Node.js 要求 ES6 模块采用.mjs后缀文件名。也就是说，只要脚本文件里面使用import或者export命令，那么就必须采用.mjs后缀名
import user from './03.module_commonjs.cjs'
console.log(user)

export default user

// 一个ES6 模块里只能有一个 export default