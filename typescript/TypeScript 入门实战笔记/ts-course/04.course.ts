{
    let str = 'this is string'
    let num = 2
    let boolean = true
}

// 字面量类型：字符串、数字、布尔字面量类型
{
    const str = 'this is string'
    const num = 2
    const boolean = true
}

{
    const str: 'this is string' = 'this is string'
    const num: 2 = 2
    const boolean: true = true
}

// 字面量类型是对应集合类型的子类型
// 一般用来限定参数为具体的某些值
{
    type Direction = 'up' | 'down'
    function move(direction: Direction) {
        console.log(direction)
    }

    move('up')
    // move('left') // error
}