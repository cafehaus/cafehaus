// 05：函数类型：返回值类型和参数类型到底如何定义？
// 函数返回值不能显示申明 undefined，会报错，要用 void
{
    function undFunc(x: string): void {
        console.log(x)
    }
}

// ts 中的 => 用来表示函数的定义，左侧是输入参数类型，右侧是函数的返回值类型，和 ES6 中的箭头函数是不一样的
{
    type Adder = (a: number, b:number) => number
    const add: Adder = (a, b) => a + b // 此处是箭头函数
}

// 可缺省参数
{
    function func(name?: string) {
        
    }
}

// 默认参数
{
    function func(name: string | number = 'zhou') {
        
    }
}

// 剩余参数
// ES6 中的剩余参数 ...rest 是一个数组
{
    function func(a: string, ...params: number[]) {
        
    }
    function funcTwo(...params: (number | string)[]) {
        
    }
}

// this：在函数的第一个参数中声明 this 指代的对象
{
    function say(this: Window, params: string) {
        alert(params)
    }
}

