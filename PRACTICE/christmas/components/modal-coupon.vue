<template>
  <u-popup :value="value" mode="center" width="600" :border-radius="32" @close="onClose">
    <div class="modal">
      <i class="iconfont icon-close" @click="onClose" />

      <div class="content">
        <scroll-view scroll-y="true" style="height: 610rpx;">
          <image class="coupon-list" :src="imgSrc" />
        </scroll-view>
      </div>

      <div class="footer">
        <p v-if="isGetted" class="btn btn-disabled">已领取</p>
        <p v-else class="btn" @click="getCoupon"><span>一键领取</span></p>
      </div>
    </div>
  </u-popup>
</template>

<script>
  import { mapState } from 'vuex'
  import dayjs from 'dayjs'

  export default {
    props: {
      value: Boolean,
      title: {
        type: String,
        default: '备货补贴券',
      },
      rules: {
        type: String,
        default: '',
      },
    },
    data() {
      return {
        isGetted: false, // 是否已领取
        imgSrc: this.$assets + '/active-nov/christmas/coupon-list.png',
      }
    },

    watch: {
      value(v) {
        if (v && this.islogin) {
          this.queryIsGetted()
        }
      },
    },

    computed: {
      ...mapState(['islogin']),
    },

    methods: {
      async getCoupon() {
        if (!this.islogin) {
          this.$user.login('/pages/christmas/christmas')
          return
        }
        const res = await this.$api.getChristmasCoupon({
          couponList: [
            { couponId: '11mouthactive01', mun: 3 },
            { couponId: '11mouthactive02', mun: 3 },
            { couponId: '11mouthactive03', mun: 3 },
            { couponId: '11mouthactive04', mun: 1 },
            { couponId: '11mouthactive05', mun: 1 },
            { couponId: '11mouthactive06', mun: 1 },
            { couponId: '11mouthactive07', mun: 1 },
            { couponId: '11mouthactive08', mun: 1 },
          ],
        })
        if (res.code !== '200') {
          this.$tips.toast(res.msg)
          return
        }

        // 已领取
        // if (res.data) {
        //   this.isGetted = true
        //   return
        // }

        this.$emit('on-success')
      },

      // 查询是否已领取过
      async queryIsGetted() {
        const res = await this.$api.isGetChristmasCoupon({
          couponId: '11mouthactive01',
        })
        if (res.code !== '200') {
          this.$tips.toast(res.msg)
          return
        }
        let data = res.data || {}
        let list = data.records || []
        this.isGetted = list.length > 0
      },

      onClose() {
        // 缓存到本地判断每天弹一次
        let date = dayjs().format('YYYY-MM-DD')
        this.$storage('christmasDetail', date)

        this.$emit('input', false)
      },
    },
  }
</script>

<style lang="stylus" scoped>
>>>.u-mode-center-box
  background  transparent
.modal
  position relative
  width 600rpx
  height 860rpx
  overflow hidden
  background transparent url($assets + '/active-nov/christmas/coupon-pop-bg.png') no-repeat center top / contain
  border-radius 32rpx
  .iconfont
    position absolute
    right 20rpx
    top 20rpx
    font-size 48rpx
    color #FFF

  .content
    position relative
    width 546rpx
    height 610rpx
    margin 150rpx auto 0
    overflow hidden
    &::before
      content ''
      border-top 80rpx solid transparent
      border-right 546rpx solid #151617
      position absolute
      bottom 0
      left 0
      z-index 2
    .coupon-list
      width 546rpx
      height 1090rpx
      padding-bottom 80rpx

  .footer
    height 100rpx
    position absolute
    bottom 0
    left 0
    right 0
    background #151617
    padding 0 80rpx
    z-index 3
    .btn
      width 100%
      height 80rpx
      border-radius 12rpx
      background #FFF
      color #161718
      font-size 30rpx
      font-weight bold
      text-align center
      line-height 80rpx
      margin-top -24rpx
      &.btn-disabled
        font-weight normal
        color #A2A2A3
      span
        position relative
        padding-right 28rpx
        &::before
          content ''
          border-top 6rpx solid transparent
          border-bottom 6rpx solid transparent
          border-left 10rpx solid #151617
          position absolute
          top 50%
          right 0rpx
          margin-top -4rpx
        &::after
          content ''
          border-top 6rpx solid transparent
          border-bottom 6rpx solid transparent
          border-left 10rpx solid #151617
          position absolute
          top 50%
          right 12rpx
          margin-top -4rpx
</style>
