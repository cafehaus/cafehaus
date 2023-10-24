<template>
  <div class="page">
    <div class="action">
      <p class="btn" @click="buyGift">买礼物</p>
      <p class="btn" @click="reset">重新生</p>
    </div>

    <div class="tip">
      <p v-if="children && salary">生了一个：{{ children === 'girl' ? '女儿' : '儿子' }}，领了工资：{{ salary }}</p>
      <h3>{{ tip }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  name: 'gift',
  props: {
    msg: String,
  },
  data() {
    return {
      children: '',
      salary: '',
      tip: '',
    }
  },
  methods: {
    createChildren() {
      const c = Math.random() > 0.5 ? 'girl' : 'boy'
      this.children = c

      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve(c)
        }, 1000)
      })
    },

    getSalary() {
      const s = Math.floor(Math.random() * 1000) + 3000
      this.salary = s

      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve(s)
        }, 3000)
      })
    },

    buyGift() {
      this.reset()

      const c = this.createChildren()
      const s = this.getSalary()
      Promise.all([c, s]).then((res) => {
        // console.log(res)

        if (this.children === 'girl') {
          if (this.salary > 3500) {
            this.tip = '给女儿买个钢琴'
          } else {
            this.tip = '给女儿买个芭比娃娃'
          }
        } else {
          if (this.salary > 3500) {
            this.tip = '给儿子买个遥控飞机'
          } else {
            this.tip = '给儿子买个奥特曼'
          }
        }
      })
    },

    reset() {
      this.children = ''
      this.salary = ''
      this.tip = ''
    },
  },
}
</script>

<style lang="stylus" scoped >
.page
  min-height calc(100vh - 16px)
  display flex
  flex-direction column
  align-items center
  justify-content center
  .action
    display flex
    align-items center
    .btn
      cursor pointer
      display inline-block
      margin 20px
      padding 10px 20px
      border-radius 12px
      background #57BC78
      color #FFF
  .tip
    text-align center
</style>
