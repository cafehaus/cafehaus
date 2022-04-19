
const express = require('express')
const router = require('./01.router.js')

const app = express()
app.use(router)

app.listen(8080, () => {
    console.log('success')
})