let fs = require("fs")
const fileStream = fileName => fs.createWriteStream(`file/${Date.now()}${fileName}`)

let express = require('express')
let axios = require('axios').default
let cors = require('cors')

const app = express()
app.use(cors()) // 允许跨域
app.use(express.urlencoded({
	extended: false
}))
app.use(express.json())

app.get('/api/user', async (req, res) => {
	console.log(req)
	const header = req.headers
	const token = header.token
	const body = header.body || {}
	// const result = await axios.post('http://xxxx:9999/api/take_order', body, {
	//   'Content-Type': 'application/json;charset=UTF-8',
 //      'token': tokend
	// })
	// res.send(result.data)
	// res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'}) // 解决中文乱码
	// res.write('<script>alert("段小胖是狗屎")</script>')
	res.send('<h1>我是一个</h2>')
})

app.post('/upload', async (req, res) => {
  // 定义一个缓存区	
  const arr = []	
  req.on('data', buffer => {	
    // 将前端传来的数据进行存储进缓存区	
    arr.push(buffer);	
  })	
  	
  req.on('end', () => {	
    // 前端请求结束后进行数据解析 处理	
    const buffer = Buffer.concat(arr);	
    // 将数据变成string类型	
    const content = buffer.toString();	
    // 从传来的数存进test的文件里	
    fileStream('test').write(buffer);	
    // 返回前端请求完成	
    res.writeHead(200, {  'Content-Type': 'text/html; charset=utf-8' });	
    res.end('上传完成');	
  })
})


/**	
 * @step1 过滤第一行	
 * @step2 过滤最后一行	
 * @step3 过滤最先出现Content-Disposition的一行	
 * @step4 过滤最先出现Content-Type:的一行	
 */	
const decodeContent = content => {	
  let lines = content.split('\n');	
  const findFlagNo = (arr, flag) => arr.findIndex(o => o.includes(flag));	
  // 查找 ----- Content-Disposition Content-Type 位置并且删除	
  const startNo = findFlagNo(lines, '------');	
  lines.splice(startNo, 1);	
  const ContentDispositionNo = findFlagNo(lines, 'Content-Disposition');	
  lines.splice(ContentDispositionNo, 1);	
  const ContentTypeNo = findFlagNo(lines, 'Content-Type');	
  lines.splice(ContentTypeNo, 1);	
  // 最后的 ----- 要在数组末往前找	
  const endNo = lines.length - findFlagNo(lines.reverse(), '------') - 1;	
  // 先反转回来	
  lines.reverse().splice(endNo, 1);	
  return Buffer.from(lines.join('\n'));	
}

app.post('/upload-form-data', async (req, res) => {
  //文件类型	
	const arr = []	
	req.on('data', (buffer) => {	
	  arr.push(buffer);	
	})	
	req.on('end', () => {	
	  const buffer = Buffer.concat(arr);	
	  const content = buffer.toString();	
	  const result = decodeContent(content);	
	  const fileName = content.match(/(?<=filename=").*?(?=")/)[0];	
	  fileStream(fileName).write(result);	
	  res.writeHead(200, {  'Content-Type': 'text/html; charset=utf-8' });	
	  res.end('上传完成')	
	})
})



app.listen(3000, () => {
	console.log(`运行在3000端口`)
})