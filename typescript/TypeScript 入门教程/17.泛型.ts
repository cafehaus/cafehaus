// 泛型（Generics）是指在定义函数、接口或类的时候，不预先指定具体的类型，而在使用的时候再指定类型的一种特性

function createArray<T>(length: number, value: T): Array<T> {
    let result: T[] = []
    for (let i = 0; i < length; i++) {
        result[i] = value
    }
    return result
}

createArray<string>(3, 'z')
// 上例中，我们在函数名后添加了 <T>，其中 T 用来指代任意输入的类型，在后面的输入 value: T 和输出 Array<T> 中即可使用了。
// 接着在调用的时候，可以指定它具体的类型为 string。当然，也可以不手动指定，而让类型推论自动推算出来
createArray(4, true)

// 多个类型参数
function swap<T, U>(tuple: [T, U]) {
    return [tuple[1], tuple[0]]
}

swap([7, 'seven'])