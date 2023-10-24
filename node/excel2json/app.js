// 导入模块
const express = require('express')
const path = require('path')
const fs = require("fs")
const bodyParser = require('body-parser')
const multer = require('multer')
const xlsx = require('xlsx')
const tool = require('./utils/tool')

// 创建服务器
const app = express()

// 处理静态资源
app.use(express.static('public'))
// 默认文件目录不会出现在路径中，可以自己添加
// app.use('/public', express.static('public'))

app.use(bodyParser.urlencoded({ extended: false })) // 判断请求体是不是json，不是的话把请求体转化为对象

// 处理 multipart/form-data 类型的表单数据，主要用于上传文件，通过配置 dest 属性，将文件储存在项目下文件夹中
app.use(multer({ dest: './public/upload' }).any())

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, './template/index.html'))
 })

app.get('/upload', function (req, res) {
   res.sendFile(path.join(__dirname, './template/upload.html'))
})

app.post('/upload', (req, res) => { // 上传接口
    // console.log(req)
    let old = req.files[0].path // 获取path: 'public\\upload\\0f625978d5d1a783b12e149718f8b634'
    let name = req.files[0].path + path.parse(req.files[0].originalname).ext
    fs.renameSync(old, './public/upload/' + req.files[0].originalname)
    let excelFilePath = './public/upload/' + req.files[0].originalname

    // 读取excel中所有工作表的数据
    let workbook = xlsx.readFile(excelFilePath)
    let sheetNames = workbook.SheetNames // 获取表名
    let sheet = workbook.Sheets[sheetNames[0]] // 通过表名得到表对象
    let excelData = xlsx.utils.sheet_to_json(sheet) // 通过工具将表对象的数据读出来并转成json
    // console.log(excelData)
    // res.setHeader('Content-Type', 'text/html;charset=utf-8')
    let html = tool.jsonHtmlTmp(excelData)

    // 写入解析成功的页面展示模板
    let tmpPath = path.join(__dirname, './template/excel-success.html')
    fs.writeFileSync(tmpPath, html)

    // 写入可下载的 json 文件
    let tmpJson = path.join(__dirname, './public/upload/excel.json')
    let txtJson = '解析数据出错'
    try {
        txtJson = JSON.stringify(excelData, null, 2)
        // 周小黑笔记
        // JSON.stringify 的第 3 个参数可以用来格式化写入的 json 数据，不传写入的会在一行上
        // JSON.stringify(value[, replacer[, space]])
        // value:
        // 必需， 要转换的 JavaScript 值（通常为对象或数组）。

        // replacer:
        // 可选。用于转换结果的函数或数组。

        // 如果 replacer 为函数，则 JSON.stringify 将调用该函数，并传入每个成员的键和值。使用返回值而不是原始值。如果此函数返回 undefined，则排除成员。根对象的键是一个空字符串：""。

        // 如果 replacer 是一个数组，则仅转换该数组中具有键值的成员。成员的转换顺序与键在数组中的顺序一样。

        // space:
        // 可选，文本添加缩进、空格和换行符，如果 space 是一个数字，则返回值文本在每个级别缩进指定数目的空格，如果 space 大于 10，则文本缩进 10 个空格。space 也可以使用非数字，如：\t
    } catch(err) {
        console.log(err)
    }
    fs.writeFileSync(tmpJson, txtJson)

    res.send({
        code: '200',
        msg: '处理成功',
        data: `/excel-success?from=${req.url.replace(/^\//, '')}`
    })

    // * 坑：因为文件有变动 nodemon 会自动触发重启，所以定时器时间大于 300 ms 时会失效
    // 删掉临时文件
    setTimeout(() => {
        // 删掉展示模板
        fs.unlinkSync(tmpPath)

        // 删掉可下载的 JSON 文件
        fs.unlinkSync(tmpJson)

        // 删掉上传的原文件
        fs.readdir(path.join(__dirname, './public/upload'), (err, files) => {
            if (err) return console.log(err)

            files.map(m => {
                // excel.xls 留着测试
                if(m !== 'excel.xls') fs.unlinkSync(path.join(__dirname, './public/upload/') + m)
            })
        })
    }, 30 * 1000)
})

app.get('/upload-country', function (req, res) {
    res.sendFile(path.join(__dirname, './template/upload-country.html'))
})

app.post('/upload-country', (req, res, next) => {
    console.log(req)
    let old = req.files[0].path
    let name = req.files[0].path + path.parse(req.files[0].originalname).ext
    fs.renameSync(old, './public/upload/' + req.files[0].originalname)
    let excelFilePath = './public/upload/' + req.files[0].originalname

    let workbook = xlsx.readFile(excelFilePath)
    let sheetNames = workbook.SheetNames
    let sheet = workbook.Sheets[sheetNames[0]]
    let excelData = xlsx.utils.sheet_to_json(sheet)
    let newExcel = tool.fmtCountry(excelData)
    let html = tool.jsonHtmlTmp(newExcel)

    // 写入解析成功的页面展示模板
    let tmpPath = path.join(__dirname, './template/excel-success.html')
    fs.writeFileSync(tmpPath, html)

    // 写入可下载的 json 文件
    let tmpJson = path.join(__dirname, './public/upload/excel.json')
    let txtJson = '解析数据出错'
    try {
        txtJson = JSON.stringify(newExcel, null, 2)
    } catch(err) {
        console.log(err)
    }
    fs.writeFileSync(tmpJson, txtJson)

    res.send({
        code: '200',
        msg: '处理成功',
        data: `/excel-success?from=${req.url.replace(/^\//, '')}`
    })

    // * 坑：因为文件有变动 nodemon 会自动触发重启，所以定时器时间大于 300 ms 时会失效
    // 删掉临时文件
    setTimeout(function() {
        // 删掉展示模板
        fs.unlinkSync(tmpPath)

        // 删掉上传的原文件
        fs.readdir(path.join(__dirname, './public/upload'), (err, files) => {
            if (err) return console.log(err)

            files.map(m => {
                // excel.xls 留着测试
                if(m !== 'excel.xls') fs.unlinkSync(path.join(__dirname, './public/upload/') + m)
            })
        })
    }, 30 * 1000)
})

app.get('/excel-success', function (req, res) {
    console.log(req.url)
    const url = path.join(__dirname, './template/excel-success.html')

    // 判断是否被删除
    // note：判断文件是否存在 fs.stat、fs.access，同步版 fs.statSync、fs.accessSync 失败时会直接抛出异常，所以不建议使用
    fs.stat(url, (err, stat) => {
        if(stat && stat.isFile()) {
            res.sendFile(url)
        } else {
            console.log('文件已被删除，请重新上传解析')
            let url = req.url
            let tmpName = url.split('=')[1]
            let htmlName = tmpName || 'index'
            res.sendFile(path.join(__dirname, `./template/${htmlName}.html`))
        }
    })
})

// 启动服务器
app.listen(10123, () => {
    console.log('express runing')
})
