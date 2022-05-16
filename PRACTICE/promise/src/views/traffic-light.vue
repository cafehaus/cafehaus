<template>
  <div class="page">
    <!-- 红灯亮3s，黄灯亮2s，绿灯亮1s -->
    <div class="light-box">
      <p class="circle" :class="{red: active === 'red'}" />
      <p class="circle" :class="{yellow: active === 'yellow'}" />
      <p class="circle" :class="{green: active === 'green'}" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'traffic-light',
  data() {
    return {
      active: 'red',
    }
  },
  created() {
    this.init()
  },

  methods: {
    init() {
      const promise = new Promise((resolve, reject) => {
        resolve()
      })

      // 这样写是错误的，持续时间结束后应该切换到下一个状态
      // promise.then(() => {
      //   return this.onLight(3000, 'red')
      // }).then(() => {
      //   return this.onLight(2000, 'yellow')
      // }).then(() => {
      //   return this.onLight(1000, 'green')
      // }).then(() => {
      //   this.init()
      // })

      promise.then(() => {
        // 3秒后变黄（红灯持续3s）
        return this.onLight(3000, 'yellow')
      }).then(() => {
        // 2秒后变绿（黄灯持续2s）
        return this.onLight(2000, 'green')
      }).then(() => {
        // 1秒后变红（绿灯持续1s）
        return this.onLight(1000, 'red')
      }).then(() => {
        this.init()
      })
    },

    onLight(timer, active) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          this.active = active
          resolve()
        }, timer)
      })
    },
  },
}
</script>

<style lang="stylus" scoped >
.page
  min-height calc(100vh - 16px)
  background #8ae9cf
  display flex
  align-items center
  justify-content center
  .light-box
    width 120px
    padding 40px
    border-radius 100px
    background #21372a
    color #FFF
    .circle
      width 120px
      height 120px
      border-radius 60px
      margin 20px 0
      background #455944
      transition all .3s
      &.red
        background red
      &.yellow
        background yellow
      &.green
        background green
</style>
