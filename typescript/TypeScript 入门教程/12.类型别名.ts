// 类型别名用来给一个类型起个新名字
type Name = string
type NameResolver = () => string
type NameOrResolver = Name | NameResolver

function getName(n: NameOrResolver): Name {
    if (typeof n === 'string') {
        return n
    } else if (typeof n === 'function') {
        return n()
    }
}