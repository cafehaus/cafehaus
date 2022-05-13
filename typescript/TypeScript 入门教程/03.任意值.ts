// let myNumber: string = 'seven'
// myNumber = 7 // error: Type 'number' is not assignable to type 'string'

let myNumber: any = 'seven'
myNumber = 7

// 在任意值上访问任何属性都是允许的
let myNum: any = 'seven'
myNum.setName('Tom') // 这样写在 js 运行时会报错：TypeError: myNum.setName is not a function
myNum.setName('Tom').sayHello()
myNum.setName('Tom').sayHello().xx

// 变量如果在声明的时候，未指定其类型，那么它会被识别为任意值类型
let anything
anything = 'xxoo'
anything = 7
anything.setName('cui')

// 注意下面的写法并不会被识别为任意值类型，TypeScript 会依照类型推论（Type Inference）的规则推断出一个类型
// 下面 anything 的类型会被推论出 string，等价于：let anything: string = 'xxoo'
// let anything = 'xxoo'