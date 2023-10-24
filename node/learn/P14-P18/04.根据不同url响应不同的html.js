const http = require('http')

const server = http.createServer()

server.on('request', (req, res) => {
    let url = req.url
    let content = '<h3>Not Found 404</h3>'

    if (url === '/' || url === '/index.html') {
        content = `
            <div style="font-size:40px;color:#57bc78;text-align:center;padding:100px;">
                <h2>我是首页</h2>
                <p>欢迎来到我的网站</p>
            </div>
        `
    } else if (url === '/about.html') {
        content = `
            <div style="font-size:40px;color:#57bc78;text-align:center;padding:100px;">
                <h2>关于我</h2>
                <p>我叫周小黑，来自美丽的四川，今年18岁，很高兴认识你</p>
            </div>
        `
    }

    res.setHeader('Content-Type', 'text/html;charset=utf-8')
    res.end(content)
})

server.listen(8080, () => {
    console.log('server runing')
})