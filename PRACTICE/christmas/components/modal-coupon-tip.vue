<template>
  <u-popup :value="value" mode="center" width="600" :border-radius="20" @close="onClose">
    <div class="modal">
      <i class="iconfont icon-close" @click="onClose" />
      <div class="top">
        <div class="title">
          {{ title }}
        </div>
      </div>

      <div class="content">
        <template v-if="tipType === '1'">
          <p>「4000元」补贴券，不限单！！</p>
          <p>新人专属奖励最高得3200元返现！！</p>
          <p>老客户专属奖励最高再领25000元返现！！</p>
        </template>

        <p v-if="tipType === '2'">领取成功！补贴券已发放至"个人中心-补贴中心"</p>
      </div>

      <div class="footer">
        <p v-if="tipType === '1'" class="btn" @click="onClose">我知道了</p>
        <template v-if="tipType === '2'">
          <p class="btn btn-cancel" @click="onClose">取消</p>
          <p class="btn" @click="goto">点击查看</p>
        </template>
      </div>
    </div>
  </u-popup>
</template>

<script>
  export default {
    props: {
      value: Boolean,
      rules: {
        type: String,
        default: '',
      },
      tipType: {
        type: String, // 1 查看补贴券  2 补贴券领取成功
        default: '1',
      },
    },
    data() {
      return {}
    },

    computed: {
      title() {
        if (this.tipType === '1') return '补贴'
        if (this.tipType === '2') return '备货补贴券'
        return '提示'
      },
    },

    methods: {
      goto() {
        this.$Router.push({
          path: '/pages/mine/subsidy-center/subsidy-list',
        })
        this.onClose()
      },

      onClose() {
        this.$emit('input', false)
      },
    },
  }
</script>

<style lang="stylus" scoped>
.modal
  background #fff
  position relative
  padding 40rpx
  box-sizing border-box
  border-radius 20rpx
  overflow hidden
  .iconfont
    position absolute
    right 20rpx
    top 20rpx
    font-size 48rpx
  .top
    .title
      font-size 34rpx
      font-weight bold
      color #161718
      padding-bottom 32rpx
      border-bottom 1px solid #E9E9E9
  .content
    margin 32rpx 0 40rpx
    font-size 30rpx
    color #5B5C5D
    line-height 1.5
    text-align justify

  .footer
    display flex
    .btn
      width 100%
      height 80rpx
      border-radius 8rpx
      background #FFD121
      color #161718
      font-size 30rpx
      text-align center
      line-height 80rpx
      &.btn-cancel
        margin-right 20rpx
        border 1px solid #E3E3E3
        background #FFF
        font-size 28rpx
        color #5B5C5D
</style>
