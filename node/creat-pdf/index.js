const puppeteer = require('puppeteer');

(async () => {
  const browser = await puppeteer.launch();
  const page = await browser.newPage();
  await page.goto(__dirname+'/pdf.html', { //这个可以渲染出图片甚至跨域的图片
    waitUntil: 'networkidle2',
  });
  await page.pdf({ path: 'hn1.pdf', format: 'a4' }); // 如果已有该文件会报错 

  await browser.close();
})()