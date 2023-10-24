// 字符串字面量类型用来约束取值只能是某几个字符串中的一个
// 注意，类型别名与字符串字面量类型都是使用 type 进行定义
type EventNames = 'click' | 'scroll' | 'mousemove'
function handleEvent(ele: Element, event: EventNames) {
    // do something
}

handleEvent(document.getElementById('hello'), 'click')
// handleEvent(document.getElementById('db'), 'dbclick') // error: Argument of type '"dblclick"' is not assignable to parameter of type 'EventNames'

// 不一定要字符串，数值、布尔值...都是可以的
type Options = 'zhou' | 18 | false
interface PersonNew {
    name: Options,
    age: Options,
    isBoss: Options
}
let userZhou: PersonNew = {
    name: 'zhou',
    age: 18,
    isBoss: false
}
