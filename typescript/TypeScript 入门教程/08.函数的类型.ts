// 一个函数有输入和输出，要在 TypeScript 中对其进行约束，需要把输入和输出都考虑到

// 1、函数声明
function sum(x: number, y: number): number {
    return x + y
}

// 注意，输入多余的（或者少于要求的）参数，是不被允许的
// sum(1, 2, 3)

// 2、函数表达式
let mySum = function(x: number, y: number): number {
    return x + y 
}
// 上面是可以通过编译的，不过事实上，上面的代码只对等号右侧的匿名函数进行了类型定义，而等号左边的 mySum，是通过赋值操作进行类型推论而推断出来的。如果需要我们手动给 mySum 添加类型，则应该是这样：
let mySumNew: (x: number, y: number) => number = function(x: number, y: number) {
    return x + y
}
// 注意不要混淆了 TypeScript 中的 => 和 ES6 中的 =>
// 在 TypeScript 的类型定义中，=> 用来表示函数的定义，左边是输入类型，需要用括号括起来，右边是输出类型

// 3、用接口定义函数的形状
interface SearchFunc {
    (source: string, subString: string): boolean
}

let mySearch: SearchFunc = function(source: string, subString: string) {
    return source.search(subString) !== -1
}

// 可选参数
// 注意可选参数必须接在必需参数后面
function buildName(firstName: string, lastName?: string): string {
    return lastName ? (firstName + lastName) : firstName
}

buildName('xiaoHei')

// 参数默认值
// 在 ES6 中，我们允许给函数的参数添加默认值，TypeScript 会将添加了默认值的参数识别为可选参数
// 添加了默认值的参数「可选参数必须接在必需参数后面」的限制
function buildNameNew(firstName: string, lastName: string = 'Zhou'): string {
    return firstName + ' ' + lastName
}

buildNameNew('xiaoHei')

// 剩余参数
// ES6 引入 rest 参数（形式为...变量名），用于获取函数的多余参数，这样就不需要使用arguments对象了。rest 参数搭配的变量是一个数组，该变量将多余的参数放入数组中
function pushArr(array: any[], ...items: any[]) {
    items.map(m => {
        array.push(m)
    })
}

let a = [1, 2]
pushArr(a, 0, 'zhou')

// 重载
// 重载允许一个函数接受不同数量或类型的参数时，作出不同的处理
function reverse(x: number): number
function reverse(x: string): string
function reverse(x: number | string): number | string | void {
    let str = x.toString().split('').reverse().join('')
    if (typeof x === 'number') {
        return Number(str)
    } else if (typeof x === 'string') {
        return str
    }
}