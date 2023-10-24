
    let box = null
    let timer = null

    window.onload = function () {
        if (timer) clearInterval(timer)
        initDate()
    }

    initDate = function() {
        timer = setInterval(() => {
            let date = new Date()
            let year = date.getFullYear()
            let mouth = date.getMonth() + 1
            let day = date.getDate()
            let hour = date.getHours()
            let minute = date.getMinutes()
            let second = date.getSeconds()

            let text = year + '年' + mouth + '月' + day + ' ' + hour + ':' + minute + ':'  + second
            // toLocaleString 方法可以直根据本地时间把 Date 对象转换为字符串
            let textTwo = date.toLocaleString()
            let htmlTxt = `
                <p>北京时间：${text}<p>
                <small>时间字符串：${textTwo}<small>
            `

            if (!box) {
                box = document.getElementById('box')
            }

            box.innerHTML = htmlTxt
        }, 1000)
    }
