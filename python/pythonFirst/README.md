# FastAPI Python 项目初体验

## 安装 pyhton 环境

## 安装 fastapi 插件
File | Settings | Project: pythonProject | Python Interpreter

## 安装 uvicorn 
uvicorn-轻量快速的 Python ASGI 框架

note：用来启动服务的，应该跟 node 里的 http 模块、express 框架类似

```bash
pip install uvicorn
```

## 运行服务
```bash
pyhton .\main.py
```

FastAPI 支持自动生成接口文档：http://127.0.0.1:8000/docs#

## 包管理文件
node 里有 package.json 文件来管理项目所有依赖，python 中对应的是 requirements.txt

```bash
#生成包管理文件，类似 npm init
pip freeze > requirements.txt

# 通过配置文件安装包，类似 npm install
pip install -r requirements.txt
```