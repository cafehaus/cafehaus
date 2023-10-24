// 实现（implements）是面向对象中的一个重要概念
// 一般来讲，一个类只能继承自另一个类，有时候不同类之间可以有一些共有的特性，这时候就可以把特性提取成接口（interfaces），用 implements 关键字来实现

interface Alarm {
    alert(): void
}

class Door {
    open() {
        console.log('open')
    }
}

class SecurityDoor extends Door implements Alarm {
    alert() {
        console.log('SecurityDoor alert')
    }
}