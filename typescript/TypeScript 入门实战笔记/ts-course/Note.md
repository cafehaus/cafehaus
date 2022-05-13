# TypeScript 入门实战笔记

JavaScript 解释性语言、动态语言，没有编译阶段，在运行时才会进行类型检查，这种语言的类型错误往往会导致运行时错误
TypeScript 在运行前需要先编译为 JavaScript，而在编译阶段就会进行类型检查，所以 TypeScript 是静态类型

## 01：如何快速搭建 TypeScript 学习开发环境？

安装 typescript
```bash
npm i -g typescript
```
查看版本：tsc -v
运行输入：tsc xxx.ts

直接在终端中运行 TypeScript 代码(node侧代码，会直接运行里面的代码)
```bash
npm i -g ts-node
```
运行输入：ts-node xxx.ts

初始化项目，会自动生成 tsconfig.json 文件
```bash
tsc --init
```

配置 vscode 默认使用应用目录下安装的 TypeScript 版本


