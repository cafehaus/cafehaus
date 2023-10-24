<template>
  <div></div>
</template>

<script>
export default {
  data() {
    return {}
  },

  methods: {
    isString(e) {
      let flag = typeof e
      if (flag) {
        return e.trim()
      } else {
        return false
      }
    },

    // 转换大小写 toUpperCase toLowerCase
    changeString(e, type = 'toUpperCase') {
      let txt = this.isString(e)
      if (!txt) {
        console.error('参数不是字符串')
        return e
      }

      if (!/^[A-Za-z]/.test(txt)) {
        console.error('参数去收尾空格后第一个字符不是字母')
        return e
      }

      if (!type || !['toUpperCase', 'toLowerCase'].includes(type)) {
        type = 'toUpperCase'
      }

      let first = txt[0][type]()
      let newTxt = ''

      // 方法一：for遍历
      for (let i = txt.length - 1; i > 1; i--) {
        newTxt = txt[i] + newTxt

        // 最后一次把首字母也拼进去
        if (i === 1) newTxt = first + newTxt
      }


      // 方法二：截取后面的字符出来
      // let end = txt.slice(1)
      // let end = txt.substring(1)
      let end = txt.substr(1) // *ECMAscript没有对该方法进行标准化，因此反对使用它
      newTxt = first + end
      // slice、substring和substr的区别
      // slice(start, end)
      // substring(start, end)
      // substr(start, length)


      // 方法三：正则替换
      // newTxt = txt.replace(/^\S/, first)
      newTxt = txt.replace(/^\S/, s => s[type]())

      return newTxt
    }
  }
}
</script>
<style lang='stylus' scoped>
@import '~@/styles/var'
</style>