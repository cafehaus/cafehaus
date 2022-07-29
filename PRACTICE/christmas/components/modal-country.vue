<template>
  <u-popup :value="value" mode="center" width="600" :border-radius="20" @close="onClose">
    <div class="modal">
      <i class="iconfont icon-close" @click="onClose" />
      <div class="top">
        <div class="title">选择目的国</div>
      </div>

      <div class="content">
        <p>该线路有多个目的国，请选择一个进行下单</p>
        <div class="list">
          <p
            v-for="(c, i) in countryList"
            :key="i"
            class="item"
            :class="{active: country === c.countryCode}"
            @click="onPick(c)"
          >
            <span>{{ c.countryName }}</span>
          </p>
        </div>
      </div>

      <div class="footer">
        <p class="btn btn-cancel" @click="onClose">取消</p>
        <p class="btn" @click="ok">确定</p>
      </div>
    </div>
  </u-popup>
</template>

<script>
  export default {
    props: {
      value: Boolean,
      info: {
        type: Object,
        default: () => ({}),
      },
    },
    data() {
      return {
        country: '',
      }
    },

    computed: {
      countryList() {
        let list = this.info.countryList || []
        return !list.length ? [] : list.map(m => {
          m.countryName = m.countryName || m.name
          m.countryCode = m.countryCode || m.id
          return m
        })
      },
    },

    watch: {
      value(v) {
        if (v) this.country = ''
      },
    },

    methods: {
      ok() {
        if (!this.country) {
          this.$tips.toast('请选择一个国家')
          return
        }
        this.goOrder()
        this.onClose()
      },

      // 下单
      goOrder() {
        let params = {
          name: '11月黑五和圣诞双节活动-点击立即下单',
          service_provider_name: this.info.supplierName,
        }
        this.$report(params) // 新增埋点
        let cityId = this.info.cityId || 249
        let countryCode = this.country || 'US'
        this.$Router.push({
          path: '/pages/place-order/index?channelId=' + this.info.schemeId + '&cityId=' + cityId + '&countryCode=' + countryCode + '&from=christmas', // from=christmas 本次活动统计上报用
        })
      },

      onPick(c) {
        let cur = c.countryCode
        if (cur === this.country) {
          this.country = ''
        } else {
          this.country = cur
        }
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
    .list
      display grid
      grid-template-columns 1fr 1fr 1fr
      grid-row-gap 20rpx
      grid-column-gap 20rpx
      margin-top 32rpx
      .item
        background #F1F3F5
        border-radius 12rpx
        text-align center
        overflow hidden
        white-space nowrap
        text-overflow ellipsis
        padding 0 8rpx
        span
          font-size 28rpx
          color #161718
          line-height 80rpx
        &.active
          background #FFD121
          font-weight bold

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
