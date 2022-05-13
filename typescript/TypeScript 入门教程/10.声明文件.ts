// jQuery 获取元素
// jQuery('#app') // ERROR: Cannot find name 'jQuery'

declare var jQuery: (selector: string) => any
jQuery('#app')

// 声明文件
// 通常我们会把声明语句放到一个单独的文件（jQuery.d.ts）中，这就是声明文件
// 声明文件必需以 .d.ts 为后缀

// src/jQuery.d.ts
declare var jQuery: (selector: string) => any
// src/index.ts
jQuery('#app')