// 数组合并了相同类型的对象，而元组（Tuple）合并了不同类型的对象
let tomcat: [string, number] = ['Tom', 25]

let smallTom: [string, number]
smallTom[0] = 'xiaoHei'
smallTom[1] = 30

// 也可以只赋值其中一项
let bigTom: [string, number]
bigTom[1] = 21

// 越界的元素
// 当添加越界的元素时，它的类型会被限制为元组中每个类型的联合类型
let niceTom: [string, number]
niceTom = ['zhou', 18]

niceTom.push('male')
// niceTom.push(true) // error

