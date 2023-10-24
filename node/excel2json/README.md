# Excel 小工具合集


## 构建说明

#### 安装依赖
```bash
npm install
```

#### 启动服务
```bash
node app.js
```

#### 已知问题
1、多人同时使用，会出现数据错误问题，前后端数据模板共用了同一个文件
2、用 nodemon 启动时，因为接口返回数据成功后有用 setTimeout 去删除文件，因为文件有变动会自动触发重启，所以的定时器时间大于 300 ms 时会失效，所以建议实际运行用 node app.js，开发的时候可以用 nodemon app.js