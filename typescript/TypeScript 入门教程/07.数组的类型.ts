// 1、「类型 + 方括号」表示法
let myList: number[] = [0, 1, 3]

// 数组的一些方法的参数也会根据数组在定义时约定的类型进行限制
// 如上面数组 push 的时候也只能数值
// myList.push('8') // error

// 2、数组泛型
// 使用数组泛型（Array Generic） Array<elemType> 来表示数组
let myArr: Array<number> = [1, 2, 3]

// 3、用接口表示数组
// 虽然接口也可以用来描述数组，但是我们一般不会这么做，因为这种方式比前两种方式复杂多了
interface NumberArray {
    [index: number]: number
}
let myNumList: NumberArray = [1, 2, 3, 5]

// 4、类数组
// 类数组（Array-like Object）不是数组类型，比如函数的 arguments

// arguments 实际上是一个类数组，不能用普通的数组的方式来描述，而应该用接口
// function sum() {
//     let args: number[] = arguments
// }

// any 在数组中的应用
// 一个比较常见的做法是，用 any 表示数组中允许出现任意类型
let list: any[] = ['xiaoHei', 24, { avatar: 'https: www.cafe123.cn'}]