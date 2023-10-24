// exports 是 module.exports 的一个简写，两个最开始默认指向同一个对象
// 但最终暴露出去的永远都是以 module.exports 为准
console.log(module.exports === exports) // true

module.exports.name = 'zhou'
console.log(module.exports === exports) // true，只是往里面添加属性，指向的还是同一个对象

exports = {}
// module.exports = {}
console.log(module.exports === exports)  // false，因为上面不管哪个改变了指向，最终都不等了

// 注意：为了避免混乱，不要在一个模块里同时使用 module.exports 和 exports