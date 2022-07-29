# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


# def print_hi(name):
#     # Use a breakpoint in the code line below to debug your script.
#     print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
# if __name__ == '__main__':
#     print_hi('PyCharm')

# See PyCharm help at https://www.jetbrains.com/help/pycharm/

import uvicorn
from fastapi import FastAPI,Header,Form,Request
from fastapi.responses import JSONResponse,HTMLResponse,FileResponse
from fastapi.templating import Jinja2Templates

app=FastAPI()

#添加首页
@app.get("/")
def index():
    "我是接口备注"
    return "我是周小黑~"

@app.get("/love")
def love():
    return "爱你哟~"

@app.post("/login")
def login():
    return {"msg": "登录成功"}

# 路径传参
@app.get("/user/{id}")
def user(e):
    return {
        "msg": "获取用户成功",
        "id": e
    }

# header
@app.get("/news/{id}")
def news(e, token=Header(None)):
    return {
        "msg": "获取成功",
        "id": e,
        "token": token
    }

# form 表单数据，需安装：pip install python-multipart
@app.post("/buy")
def buy(number=Form(None),price=Form(None)):
    return {"data":{"number":number,"price":price}}

# 自定义 response 返回信息
@app.get("/json")
def json():
    return JSONResponse(content={"msg":"我是个自定义json"},
                        status_code=202,
                        headers={"name":"zhouxiaohei"})

# 返回 html 标签
@app.get("/html")
def html():
    # python 中三个引号：① 多行注释 ② 多行字符串（类似 javscript 中的模板字符串）
    html="""
    <html>
        <body>
          <h3>我是周小黑</h3>
        </body>
    </html>
    """
    return HTMLResponse(content=html)

# 返回 html 模板文件：需安装 jinja2
# 定义 html 模板指向
template = Jinja2Templates("static")
@app.get("/html-template")
def htmlTemplate(username,req:Request):
    hobby = ["写代码","咖啡","设计"]
    return template.TemplateResponse("index.html",context={"request":req,"username":username,"hobby":hobby})

# 返回 文件
@app.get("/avatar")
def html():
    avatar = "./static/avatar.png"
    return FileResponse(avatar)

# 定义多种请求方式
@app.api_route("/hello", methods=["GET","POST","PUT"])
def login():
    return {"msg": "你好呀"}

if __name__ == '__main__':
    uvicorn.run(app)