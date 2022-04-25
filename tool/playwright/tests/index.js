const { chromium } = require('@playwright/test')
const path  = require('path')
const main = async () => {
  const browser = await chromium.launch({ headless: false, slowMo: 50 })

  const page = await browser.newPage();

  // 进入百度的网页
  await page.goto('https://kaiwu.lagou.com/course/courseInfo.htm?courseId=885&sid=20-h5Url-0&buyFrom=2&pageId=1pz4#/content')
  
  // path 文件保存路径
  await page.screenshot({ path: path.format({
    dir: '../tests', 
    name: 'img',
    ext: '.png'
  }), fullPage: true  })

  // browser.close()
  // await page.locator('.handle-button-wrap').click()
}

main()