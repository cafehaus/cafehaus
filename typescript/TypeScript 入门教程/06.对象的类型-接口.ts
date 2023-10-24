// 在 TypeScript 中，我们使用接口（Interfaces）来定义对象的类型

// 什么是接口
// 在面向对象语言中，接口（Interfaces）是一个很重要的概念，它是对行为的抽象，而具体如何行动需要由类（classes）去实现（implement）
// TypeScript 中的接口是一个非常灵活的概念，除了可用于对类的一部分行为进行抽象以外，也常用于对「对象的形状（Shape）」进行描述
interface Person {
    name: string,
    age: number
}

let Tom: Person = {
    name: 'Tom',
    age: 18
}

// 定义的变量比接口少/多了一些属性都是不允许的
// let Zhou: Person = {
//     name: 'Zhou'
// }
// let Cui: Person = {
//     name: 'Cui',
//     age: 19,
//     sex: 'female'
// }

// 有时我们希望不要完全匹配一个形状，那么可以用可选属性(只能少)
interface Student {
    name: string,
    age?: number
}

let Hua: Student = {
    name: 'xiaoHua'
}

// 允许有任意的属性
interface Worker {
    name: string,
    age?: number,
    [propName: string]: string
}

// 会报错，因为 age 的类型不是任意属性规定的类型
// 一旦定义了任意属性，那么确定属性和可选属性的类型都必须是它的类型的子集
// let Ming: Worker = {
//     name: 'xiaoMing',
//     age: 10,
//     gender: 'male'
// }

interface Employee {
    name: string,
    age?: number,
    [propName: string]: string | number
}

let Wei: Employee = {
    name: 'xiaoWei',
    age: 21,
    girlFriend: 'xiaoHua'
}

// 只读属性
// 有时候我们希望对象中的一些字段只能在创建的时候被赋值，那么可以用 readonly 定义只读属性
interface Dog {
    readonly id: Number,
    name: string,
    age: number,
    color: String
}

// 注意，只读的约束存在于第一次给对象赋值的时候，而不是第一次给只读属性赋值的时候
let redDog: Dog = {
    id: 234,
    name: 'redDog',
    age: 3,
    color: 'red'
}

// redDog.id = 345 // error：只读属性不能赋值
