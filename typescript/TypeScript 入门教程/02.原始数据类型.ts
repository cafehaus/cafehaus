// 原始数据类型：布尔值、数值、字符串、null、undefined + Symbol(ES6) + BigInt(Es10)

// 1、布尔值
let isDone: boolean = false 

// 用构造函数 Boolean 创造的对象不是布尔值，返回的是一个 Boolean 对象
// let isHave: boolean = new Boolean(1)

// 直接调用 Boolean 可以返回一个 boolean 类型
let createdByBoolean: boolean = Boolean(1)

// 2、数值
let notNumber: number = NaN
let myInt: number = 12

// 3、字符串
let myName: string = 'zhou'

// 4、Null 和 Undefined
let u: undefined = undefined
let n: null = null
// undefined 和 null 是所有类型的子类型
// 也就是说 undefined 和 null 类型的变量，可以赋值给 number、string、boolean 类型的变量
let num: number = null
let str: string = null
let bl: boolean = undefined

// 5、空值
// JavaScript 没有空值（Void）的概念，在 TypeScript 中，可以用 void 表示没有任何返回值的函数
// 声明一个 void 类型的变量没有什么用，因为你只能将它赋值为 undefined 和 null
let unusable : void = undefined