// 联合类型（Union Types）表示取值可以为多种类型中的一种
let myFavoriteNum: string | number
myFavoriteNum = 'seven'
myFavoriteNum = 7

// 当 TypeScript 不确定一个联合类型的变量到底是哪个类型的时候，我们只能访问此联合类型的所有类型里共有的属性或方法
// 下面的会报错，因为 length 不是 string 和 number 共有的方法
// myFavoriteNum.length // error: Property 'length' does not exist on type 'string | number'. Property 'length' does not exist on type 'number'
myFavoriteNum.toString()

// 联合类型的变量在被赋值的时候，会根据类型推论的规则推断出一个类型
myFavoriteNum = 'seven'
console.log(myFavoriteNum.length) // 5