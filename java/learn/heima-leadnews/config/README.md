# 项目各项配置文件和说明

## Docker 服务

### nacos
```shell
# 拉取镜像
docker pull nacos/nacos-server:1.2.0

# 启动
docker run --env MODE=standalone --name nacos --restart=always  -d -p 8848:8848 nacos/nacos-server:1.2.0
```
nacos 各个微服务的配置文件即当前目录下对应的 yml 文件

注意 nacos 服务的前端访问地址是 http://localhost:8848/nacos，直接用端口号会报：HTTP Status 404 – Not Found，不要少写了后面的 /nacos

### minio
```shell
# 拉取镜像
docker pull minio/minio

# 启动 mac 版本
docker run -d -p 9000:9000 -p 9001:9001 --name minio -v /Users/xx/zhou/java/minio/data:/data -v /Users/xx/zhou/java/minio/config:/root/.minio -e "MINIO_ROOT_USER=minio" -e "MINIO_ROOT_PASSWORD=minio123" minio/minio server /data --console-address ":9001"

# 启动 windows 版本，数据卷直接放到项目里的 config 配置目录里，git 提交忽略了
docker run -d -p 9000:9000 -p 9001:9001 --name minio -v D:/github/node/java/learn/heima-leadnews/config/minio/data:/data -v D:/github/node/java/learn/heima-leadnews/config/minio/config:/root/.minio -e "MINIO_ROOT_USER=minio" -e "MINIO_ROOT_PASSWORD=minio123" minio/minio server /data --console-address ":9001"
```

### redis
```shell
# 拉取镜像
docker pull redis

# 启动
docker run -d --name redis --restart=always -p 6379:6379 redis --requirepass "leadnews"
```

### 前端
```shell
# 拉取镜像
docker pull nginx
```
启动容器直接在前端项目 wemedia-web、app-web 目录下输入命令：docker-compose up，里面已经编写好了docker-compose.yml 文件


## SQL
当前目录下的 sql 文件
