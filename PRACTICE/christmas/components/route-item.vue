<template>
  <div class="route-item" :class="{'route-item-black': isBlackFriday}" id="route-item">
    <div class="route-item-inner">
      <div class="header">
        <div class="title">
          <p class="name"><span>{{ info.channelName }}</span></p>
          <p v-if="info.score" class="star">
            <i class="iconfont icon-star" />
            <span>{{ info.score }}</span>
          </p>
        </div>

        <!-- 角标 -->
        <div class="corner" @click="previewCoupon"><span>补贴</span></div>
      </div>

      <!-- 标签 -->
      <div class="tag" v-if="info.label">
        <span v-for="(label, idx) in info.label.split(' ')" :key="idx">{{ label }}</span>
      </div>

      <div class="company">
        <p class="company-name">{{ info.supplierName }}</p>
        <p @click="goDetail">方案详情></p>
      </div>

      <!-- 方案参数介绍 -->
      <div class="intro">
        <div class="intro-item">
          <span class="label">时效</span>
          <span class="txt">{{ info.globalTime || '--' }}</span>
        </div>
        <div class="intro-item">
          <span class="label">可接</span>
          <span class="txt">{{ info.allowCategory || '--' }}</span>
        </div>
        <div class="intro-item">
          <span class="label">计重</span>
          <span class="txt">{{ info.weightRule || '--' }}</span>
        </div>
        <div class="intro-item" v-if="info.volumeWeightRatio && info.weightRule !== '实重'">
          <span class="label">分泡</span>
          <span class="txt">{{ info.volumeWeightRatio }}</span>
        </div>
      </div>

      <!-- 价格 -->
      <div class="price">
        <span class="real">单价 ￥<strong>{{ info.price }}</strong>/kg起</span>
        <span class="original">原价￥{{ info.oldPrice }}/kg</span>
      </div>

      <div class="btn-buy" @click="goOrder">立即下单</div>
      <div class="tip">即刻抢舱享<strong class="num">9</strong>折优惠!</div>
    </div>
  </div>
</template>
<script>
  import { mapState } from 'vuex'
  export default {
    props: {
      info: {
        type: Object,
        default: () => ({}),
      },
      isBlackFriday: {
        type: Boolean, // true 黑色星期五  false 圣诞活动
        default: true,
      },
    },
    data() {
      return {}
    },

    computed: {
      ...mapState(['islogin']),
    },

    methods: {
      // 下单
      goOrder() {
        if (!this.islogin) {
          this.$user.login('/pages/christmas/christmas')
          return
        }
        let c = this.info.countryList
        if (c && c.length > 1) { // 多个目的国家
          this.$emit('pick-country', this.info)
          return
        }

        let params = {
          name: '11月黑五和圣诞双节活动-点击立即下单',
          service_provider_name: this.info.supplierName,
        }
        this.$report(params) // 新增埋点
        let cityId = this.info.cityId || 249
        let countryCode = this.info.countryCode || 'US'
        this.$Router.push({
          path: '/pages/place-order/index?channelId=' + this.info.schemeId + '&cityId=' + cityId + '&countryCode=' + countryCode + '&from=christmas', // from=christmas 本次活动统计上报用
        })
      },
      goDetail() {
        if (!this.islogin) {
          this.$user.login('/pages/christmas/christmas')
          return
        }
        let params = {
          name: '11月黑五和圣诞双节活动-点击方案详情',
          service_provider_name: this.info.supplierName,
        }
        this.$report(params) // 新增埋点

        // 将城市id存一下，方案详情没这个字段，跳转下单要用
        this.$storage('homeData', {
          city: { value: this.info.cityId },
        })

        this.$Router.push({
          path: '/pages/quoted-price-quick-order/trans-detail-new?channelId=' + this.info.schemeId + '&price=' + this.info.price + '&unit=' + this.info.priceUnit + '&from=christmas&cityId=' + this.info.cityId,
        })
      },

      // 查看补贴券
      previewCoupon() {
        this.$emit('preview-coupon')
      },
    },
  }
</script>

<style lang="stylus" scoped>
.route-item
  background #B77F38
  border 16rpx dashed #701E16
  border-radius 24rpx
  margin-top 20rpx
  // padding 30rpx
  box-sizing border-box
  .route-item-inner // 多加一层为了制作边框大间距效果
    margin-top -12rpx
    margin-bottom -12rpx
    margin-left -12rpx
    margin-right -12rpx
    background #EFD5AE
    border-radius 20rpx
    padding 30rpx
    .header
      display flex
      justify-content space-between
      align-items center
      width 100%
      .title
        display flex
        align-items center
        .name
          font-size 30rpx
          font-weight 900
          color #604625
          line-height 1.5
          max-width 440rpx
          white-space nowrap
          overflow hidden
          text-overflow ellipsis
        .star
          border 2rpx solid #1E6C5A
          border-radius 8rpx
          height 34rpx
          display flex
          align-items center
          position relative
          padding 0 6rpx 0 40rpx
          margin-left 8rpx
          &:before
            content ''
            height 100%
            width 36rpx
            background #1E6C5A
            position absolute
            top 0
            left 0
            z-index 1
          .iconfont
            font-size 26rpx
            color #EFD5AE
            line-height 1
            position absolute
            top 50%
            left 5rpx
            z-index 2
            // transform scale(.9) translateY(-50%)
            transform translateY(-50%)
          span
            font-size 24rpx
            font-weight bold
            color #1E6C5A
      .corner
        background #EB1834
        margin 0 -30rpx 0 20rpx
        height 56rpx
        padding 0 32rpx 0 4rpx
        position relative
        &::before
          content ''
          border-bottom 56rpx solid transparent
          border-right 12rpx solid #ad0206
          position absolute
          bottom 0
          left -12rpx
        &::after
          content ''
          border-top 56rpx solid transparent
          border-right 12rpx solid #EB1834
          position absolute
          bottom 0
          left -12rpx
        span
          font-size 26rpx
          font-weight bold
          color #FFF
          line-height 56rpx
          position relative
          &::before
            content ''
            border-top 6rpx solid transparent
            border-bottom 6rpx solid transparent
            border-left 8rpx solid #FFF
            position absolute
            top 50%
            right -24rpx
            margin-top -4rpx
          &::after
            content ''
            border-top 6rpx solid transparent
            border-bottom 6rpx solid transparent
            border-left 8rpx solid #FFF
            position absolute
            top 50%
            right -14rpx
            margin-top -4rpx

    .tag
      margin 4rpx 0 12rpx
      span
        font-size 24rpx
        font-weight bold
        color #1E6C5A
        border 2rpx solid #1E6C5A
        border-radius 8rpx
        margin-right 12rpx
        padding 4rpx 10rpx

    .company
      font-size 26rpx
      color #9A7341
      line-height 1.5
      display flex
      align-items center
      justify-content space-between
      margin 12rpx 0
      .company-name
        max-width 462rpx
        white-space nowrap
        overflow hidden
        text-overflow ellipsis

    .intro
      background #D9B784
      padding 16rpx 24rpx
      border-radius 16rpx
      .intro-item
        display flex
        justify-content space-between
        font-size 24rpx
        line-height 1.5
        &:not(:first-child)
          margin-top 4rpx
        .label
          color #88704C
          flex-shrink 0
          margin-right 60rpx
        .txt
          color #5D4B2F
          text-align right

    .price
      margin 20rpx 0
      .real
        font-size 32rpx
        font-weight 900
        color #EB1834
        strong
          font-size 50rpx
      .original
        font-size 26rpx
        color #9A7341
        text-decoration line-through
        margin-left 20rpx

    .btn-buy
       background #1E6C5A
       border-radius 16rpx
       height 74rpx
       font-size 30rpx
       font-weight 700
       color #EFD5AF
       line-height 74rpx
       text-align center

    .tip
      margin-top 14rpx
      font-size 26rpx
      font-weight bold
      color #C83A35
      display flex
      justify-content center
      align-items center
      .num
        font-size 30rpx
        font-weight 900
        color #FFF
        text-align center
        line-height 36rpx
        display inline-block
        height 36rpx
        min-width 36rpx
        border-radius 18rpx
        background #C83A35
        margin 0 4rpx
        position relative
        &::before
          content ''
          border-bottom 10rpx solid transparent
          border-left 22rpx solid #C83A35
          position absolute
          bottom 0
          left 50%
          transform rotate(-45deg)
        &::after
          content ''
          border-bottom 10rpx solid transparent
          border-right 22rpx solid #C83A35
          position absolute
          bottom 0
          left 50%
          transform rotate(-45deg)

  // ------------------------------- 黑色星期五活动-黑色主题
  &.route-item-black
    background #303234
    border 16rpx solid #A3A49C
    .route-item-inner
      background #303234
      .header
        .title
          .name
            color #ECC98F
          .star
            border 2rpx solid #515155
            &:before
              background #515155
            .iconfont
              color #FAE0C3
            span
              color #FAE0C3

      .tag
        span
          color #D8D8D8
          border 2rpx solid #A2A2A3

      .company
        color #D8D8D8
        .company-name
          color #A2A2A3

      .intro
        background #3E3E41
        .intro-item
          .label
            color #A2A2A3
          .txt
            color #C1C1C1

      .price
        .real
          color #FFF
        .original
          color #A2A2A3

      .btn-buy
        background linear-gradient(180deg, #FDF2DD 0%, #EAC486 100%)
        color #202223

      .tip
        color #F0CC99
        .num
          color #303234
          background #EDCB93
          &::before
            border-left 22rpx solid #EDCB93
          &::after
            border-right 22rpx solid #EDCB93
</style>
