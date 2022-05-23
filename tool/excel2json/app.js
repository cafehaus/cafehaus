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

    let tmpPath = path.join(__dirname, './template/excel-success.html')
    fs.writeFileSync(tmpPath, html)
    res.send({
        code: '200',
        msg: '处理成功',
        data: '/excel-success'
    })

    // 删掉临时文件
    setTimeout(() => {
        fs.unlinkSync(tmpPath)

        // 删掉上传的原文件
        fs.readdir(path.join(__dirname, './public/upload'), (err, files) => {
            if (err) return console.log(err)

            files.map(m => {
                // excel.xls 留着测试
                if(m !== 'excel.xls') fs.unlinkSync(path.join(__dirname, './public/upload/') + m)
            })
        })
    }, 5000)
})

app.get('/upload-country', function (req, res) {
    res.sendFile(path.join(__dirname, './template/upload-country.html'))
})

app.post('/upload-country', (req, res) => {
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

    let tmpPath = path.join(__dirname, './template/excel-success.html')
    fs.writeFileSync(tmpPath, html)
    res.send({
        code: '200',
        msg: '处理成功',
        data: '/excel-success'
    })

    // 删掉临时文件
    setTimeout(() => {
        fs.unlinkSync(tmpPath)
    }, 10000)
})

app.get('/excel-success', function (req, res) {
    res.sendFile(path.join(__dirname, './template/excel-success.html'))
 })

// 启动服务器
app.listen(10123, () => {
    console.log('express runing')
})
