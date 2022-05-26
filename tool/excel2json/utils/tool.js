const pinyin = require('pinyin')

/**
 * 格式化国家数据
 * 根据国家名中文获取第一个汉字首字母，生成 A-Z 索引分组用
 * {'国家中文名称': '美国','国家英文名称': 'USA','国家二字码': 'US'}
 * =>
 * {nameCn: '美国', nameEn: 'USA', secondWord: 'US', firstLetter: 'M', id: 0}
 * @param {*} countryList 原始数据
 */
exports.fmtCountry = function(countryList) {
  let arr = []
  // let test = []
  countryList.map((m, idx) => {
    let py = pinyin(m['国家中文名称'], {
      style: pinyin.STYLE_NORMAL,
      heteronym: true, // 启用多音字模式
    })

    let firstLetter = (py && py[0] && py[0][0] && py[0][0][0] && py[0][0][0].toUpperCase()) || ''
    if (m['国家中文名称'] === '朝鲜') { // bug：zhao xian
        firstLetter = 'C'
    }
    arr.push({
        nameCn: m['国家中文名称'],
        nameEn: m['国家英文名称'],
        secondWord: m['国家二字码'],
        firstLetter,
        id: idx + 1
    })
    // test.push(m['国家中文名称'] + '：' + firstLetter)

    return m
  })

  // test.sort((a, b) => {
  //     let x1 = a.split('：')[1]
  //     let x2 = b.split('：')[1]
  //     if (x1 < x2) {
  //         return -1
  //     }
  //     if (x1 > x2) {
  //         return 1
  //     }
  //     return 0
  // })
  // console.log(test)

  return arr
}

/**
 * 转换好的 JSON 数据页面展示模板
 * @param {*} data 原始数据
 */
exports.jsonHtmlTmp = function(data) {
  let html = ''
  let originData = ''
  try {
    html = data.reduce((pev, cur, index) => {
      pev += `<tr><td>${index + 1}</td><td>${JSON.stringify(cur)}</td></tr>`

      return pev
    }, '')

    html = `<table border="0" cellpadding="16" cellspacing="0"><tr class="thead"><td width="40">序号</td><td>数据</td></tr>${html}</table>`

    originData = JSON.stringify(data)
  } catch(err) {
    console.err(err)
  }
  

  return `<!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Excel2JSON</title>
        <style>
            body {
                text-align: center;
                padding-top: 100px;
            }
            .tittle {
                color: #57bc78;
            }
            .box {
                max-width: 60%;
                margin: 40px auto;
                text-align: left;
            }
            .action {
              display: flex;
              align-items: center;
              justify-content: center;
            }
            #button {
              border: none;
              background:#57bc78;
              color: #FFF;
              cursor: pointer;
              padding: 6px 12px;
            }
            #button:hover {
              opacity: .8;
            }
            .btn-download {
              display: inline-block;
              box-sizing: border-box;
              font-size: 13px;
              color: #57bc78;
              line-height: normal;
              cursor: pointer;
              margin-left: 10px;
              padding: 6px 12px;
              border: 1px solid #57bc78;
              text-decoration: none;
            }
            .btn-download:hover {
              opacity: .8;
            }
            table {
              border-top: 1px solid #EEE;
              border-left: 1px solid #EEE;
              margin: 0 auto;
            }
            table td {
              border-bottom: 1px solid #EEE;
              border-right: 1px solid #EEE;
            }
            .thead {
              font-weight: bold;
              background: #f5f7f7;
            }
        </style>
    </head>
    <body>
        <h2 class="tittle">解析后的 JSON 数据</h2>
        <div class="action">
          <button id="button">复制原始 JSON 数据</button>
          <a class="btn-download" href="/upload/excel.json" download="excel">下载 JSON 文件</a>
        </div>
        <div class="box">${html}</div>
    </body>
    </html>
    <script>
      button.addEventListener('click', function () {
        // 这里不再 JSON.stringify 一下，复制出来的是 [[Object Object],[Object Object]...]
        var text = ${JSON.stringify(originData)};
        if (navigator.clipboard) {
            // clipboard api 复制
            navigator.clipboard.writeText(text);
        } else {
            var textarea = document.createElement('textarea');
            document.body.appendChild(textarea);
            // 隐藏此输入框
            textarea.style.position = 'fixed';
            textarea.style.clip = 'rect(0 0 0 0)';
            textarea.style.top = '10px';
            // 赋值
            textarea.value = ${JSON.stringify(originData)};
            // 选中
            textarea.select();
            // 复制
            document.execCommand('copy', true);
            // 移除输入框
            document.body.removeChild(textarea);
        }
        alert('复制成功！')
      })
    </script>`
}