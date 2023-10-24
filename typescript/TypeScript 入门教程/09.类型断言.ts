// 类型断言（Type Assertion）可以用来手动指定一个值的类型
// 语法：① 值 as 类型 ② <类型>值
interface Cat {
    name: string,
    run(): void
}

interface Fish {
    name: string,
    swim(): void
}

function isFish(animal: Cat | Fish) {
    if (typeof (animal as Fish).swim === 'function') {
        return true
    }
    return false
}

// 需要注意的是，类型断言只能够「欺骗」TypeScript 编译器，无法避免运行时的错误，反而滥用类型断言可能会导致运行时错误

// 将一个父类断言为更加具体的子类
class ApiError extends Error {
    code: number = 0
}

class HttpError extends Error {
    statusCode: number = 200
}

function isApiError(error: Error) {
    if (typeof (error as ApiError).code === 'number') {
        return true
    }
    return false
}

// 将任何一个类型断言为 any
// window.foo = 2 // error: Property 'foo' does not exist on type 'Window & typeof globalThis'
(window as any).foo = 1
// 需要注意的是，将一个变量断言为 any 可以说是解决 TypeScript 中类型问题的最后一个手段，它极有可能掩盖了真正的类型错误，所以如果不是非常确定，就不要使用 as any

// 将 any 断言为一个具体的类型
function getCacheData(key: string): any {
    return (window as any).cache[key]
}

interface Cat {
    name: string,
    run(): void
}

const tom = getCacheData('tom') as Cat
tom.run()

// 类型断言的限制
// 联合类型可以被断言为其中一个类型
// 父类可以被断言为子类
// 任何类型都可以被断言为 any
// any 可以被断言为任何类型
interface Animal {
    name: string
}

interface Pig {
    name: string,
    sheep(): void
}

function testAnimal(animal: Animal) {
    return (animal as Cat)
}

function testPig(pig: Pig) {
    return (pig as Animal)
}

// 双重断言
// 除非迫不得已，千万别用双重断言
// 'xx' as any as 'oo'