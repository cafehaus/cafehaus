<template>
  <div class="page" :class="{'page-black': tabIndex === 0}">
    <div class="header">
      <p class="btn-rule" @click="goto('/pages/christmas/rule')">活动规则</p>
      <p class="btn-coupon" @click="getCoupon">领取补贴券<i class="iconfont icon-play" /></p>
    </div>

    <!-- 侧边按钮 -->
    <div class="sider">
      <div v-if="seaList.length && tabIndex === 1" class="sider-item" :class="{active: tabSider === 'sea'}" @click="changeSider('sea')">海运</div>
      <div v-if="airList.length" class="sider-item" :class="{active: tabSider === 'air'}" @click="changeSider('air')">空运</div>
      <div v-if="expressList.length" class="sider-item" :class="{active: tabSider === 'express'}" @click="changeSider('express')">快递</div>
    </div>

    <!-- 列表 -->
    <div class="content">
      <div class="tab">
        <div class="tab-item" :class="{active: tabIndex === 0}" @click="changeTab(0)">
          <p>“黑色星期五”</p>
          <p>上架抢舱计划</p>
        </div>
        <div class="tab-item" :class="{active: tabIndex === 1}" @click="changeTab(1)">
          <p>“圣诞老人”</p>
          <p>海运抢舱计划</p>
        </div>
      </div>

      <!-- 介绍图 -->
      <div class="img-christmas-intro" :class="{'img-christmas-intro-friday': tabIndex === 0}" />

      <!-- 城市选项卡 -->
      <div class="tab-city">
        <div class="tab-city-inner">
          <div
            v-for="(city, idx) in cityList"
            :key="idx"
            class="tab-item"
            :class="{active: tabCity === idx}"
            @click="changeTabCity(idx)"
          >
            <span>{{ city.name }}</span>
          </div>

          <div class="tab-item-bg" :style="{left: tabCity * 120 + 'rpx'}" />
        </div>
      </div>

      <!-- 列表 -->
      <div class="list">
        <!-- 海运 -->
        <template v-if="tabIndex === 1">
          <div class="sub-title" id="sea" v-if="seaList.length">
            <p class="name">海运</p>
            <!-- 圣诞-青岛-海运-显示单独的文案 -->
            <div v-if="tabIndex === 1 && tabCity === 3" class="des">
              <p>老牌日韩专线，清关能力强，为日韩发货保驾护航</p>
            </div>
            <div v-else class="des">
              <p>美森限时达·超时最敢赔·*元/KG/工作日</p>
              <p>真美森·圣诞愿望才成真·服务商精选<strong>假一赔三</strong>系列产品</p>
            </div>
          </div>

          <RouteItem
            v-for="(item, index) in seaList"
            :key="index + 'sea'"
            :info="item"
            :is-black-friday="tabIndex === 0"
            @preview-coupon="onShowTip('1')"
            @pick-country="onPickCountry"
          />
        </template>

        <!-- 空运 -->
        <div class="sub-title" id="air" v-if="airList.length">
          <p class="name">空运</p>
          <div class="des">
            <p>欧美空运圣诞专线·起飞快才是真的快</p>
          </div>
        </div>

        <RouteItem
          v-for="(item, index) in airList"
          :key="index + 'air'"
          :info="item"
          :is-black-friday="tabIndex === 0"
          @preview-coupon="onShowTip('1')"
          @pick-country="onPickCountry"
        />

        <!-- 快递 -->
        <div class="sub-title" id="express" v-if="expressList.length">
          <p class="name">快递</p>
          <div class="des">
            <p>极速UPS红单专送·圣诞抢跑专用·快到不可思议</p>
          </div>
        </div>

        <RouteItem
          v-for="(item, index) in expressList"
          :key="index + 'express'"
          :info="item"
          :is-black-friday="tabIndex === 0"
          @preview-coupon="onShowTip('1')"
          @pick-country="onPickCountry"
        />

        <Empty v-if="empty" />
      </div>
    </div>

    <!-- 领券弹窗 -->
    <ModalCoupon v-model="showCoupon" @on-success="onShowTip('2')" />

    <!-- 查看补贴券/领取成功弹窗 -->
    <ModalCouponTip v-model="showCouponTip" :tip-type="tipType" />

    <!-- 2021圣诞双节活动弹窗 -->
    <ModalChristmas v-model="showChristmas" />

    <!-- 多个目的国选择国家弹窗 -->
    <ModalCountry v-model="showCountry" :info="curRoute" />
  </div>
</template>
<script>
  import dayjs from 'dayjs'
  import { mapState } from 'vuex'
  import Empty from '@/components/empty.vue'
  import ModalCouponTip from './components/modal-coupon-tip'
  import ModalCoupon from './components/modal-coupon'
  import RouteItem from './components/route-item'
  import ModalChristmas from '@/pages/christmas/components/modal-christmas'
  import ModalCountry from '@/pages/christmas/components/modal-country'

  export default {
    components: {
      Empty,
      ModalCouponTip,
      ModalCoupon,
      RouteItem,
      ModalChristmas,
      ModalCountry,
    },

    data() {
      return {
        assets: this.$assets,

        tabSider: '',
        tabIndex: 0,
        tabCity: 0,
        cityList: [
          { name: '深圳', value: '深圳' },
          { name: '广州', value: '广州' },
          { name: '义乌', value: '义乌' },
          { name: '青岛', value: '青岛' },
          { name: '杭州', value: '杭州' },
        ],

        showCountry: false,
        showCoupon: false,
        showCouponTip: false,
        showChristmas: false,
        tipType: '1', // 1 查看补贴券  2 补贴券领取成功

        seaList: [],
        airList: [],
        expressList: [],
        curRoute: {}, // 当前操作的线路
      }
    },

    computed: {
      ...mapState(['islogin']),

      empty() {
        let friday = !this.airList.length && !this.expressList.length
        let christmas = !this.seaList.length && !this.airList.length && !this.expressList.length
        return this.tabIndex === 0 ? friday : christmas
      },
    },

    created() {
      this.islogin && this.queryIsGetted()
      this.getListData()

      // 埋点
      this.onReport()
    },

    // 下拉刷新
    async onPullDownRefresh() {
      this.reset()
      await this.getListData()
      uni.stopPullDownRefresh()
    },

    methods: {
      // 获取活动线路
      async getListData() {
        uni.showLoading({
          title: '加载中...',
        })
        const res = await this.$api.getChristmasList({
          activityType: this.tabIndex === 0 ? 1 : 2,
          cityName: this.cityList[this.tabCity].value,
        })
        setTimeout(() => {
          uni.hideLoading()
        }, 500)

        if (res.code !== '200') {
          this.$tips.toast(res.msg)
          return
        }

        let data = res.data || {}
        this.seaList = data.shippingList || []
        this.airList = data.airList || []
        this.expressList = data.expressList || []
      },

      reset(resetAll) {
        if (resetAll) {
          this.tabSider = ''
          this.tabCity = 0
        }

        this.seaList = []
        this.airList = []
        this.expressList = []
      },

      changeTab(e) {
        if (e === this.tabIndex) return
        this.tabIndex = e
        this.reset(true)

        this.getListData()
      },

      // 切换城市
      changeTabCity(e) {
        if (e === this.tabCity) return
        this.tabCity = e
        // this.reset()

        this.getListData()
      },

      // 切换运输方式
      changeSider(e) {
        // if (e === this.tabSider) return
        this.tabSider = e
        // uni.pageScrollTo({
        //   selector: '#sea', // 在h5中无效
        //   // scrollTop: 1000,
        //   duration: 0,
        // })
        let id = `#${this.tabSider}`
        uni.createSelectorQuery().select('.page').boundingClientRect(data => {
          uni.createSelectorQuery().select(id).boundingClientRect(res => {
            uni.pageScrollTo({
              scrollTop: res.top - data.top - 15,
            })
          }).exec()
        }).exec()
      },

      // 跳转
      goto(path) {
        this.$Router.push({
          path,
        })
      },

      // 领取优惠券
      getCoupon() {
        this.showCoupon = true
      },

      // 查看补贴券说明/领取成功
      onShowTip(e) {
        if (e === '2') this.showCoupon = false

        this.tipType = e
        this.showCouponTip = true
      },

      // 选择国家
      onPickCountry(e) {
        this.curRoute = e
        this.showCountry = true
      },

      // 查询是否已领取过
      async queryIsGetted() {
        // 登录了首次进入每天弹一次
        let date = this.$storage('christmasDetail')
        let now = dayjs().format('YYYY-MM-DD')
        let isBefore = dayjs().isBefore(dayjs('2021-12-01 00:00:00'))
        if (!isBefore || (date && date === now)) return

        const res = await this.$api.isGetChristmasCoupon({
          couponId: '11mouthactive01',
        })
        if (res.code !== '200') {
          this.$tips.toast(res.msg)
          return
        }
        let data = res.data || {}
        let list = data.records || []
        this.showCoupon = !list.length
      },

      onReport() {
        let p = {
          name: '11月黑五和圣诞双节活动-进入落地页',
          active_type: 1,
        }
        let t = this.$Route.query.form_type
        t && (p.form_type = t)
        this.$report(p)
      },
    },

    onShareAppMessage() {
      return {
        title: '有“备”无患双节抢舱季',
        path: '/pages/christmas/christmas',
        image: this.$assets + '/active-nov/christmas/banner.png',
      }
    },
  }
</script>

<style lang="stylus" scoped>
.page
  background #005347
  width 100vw
  overflow-x hidden
  // min-height 100vh
  height auto !important
  &.page-black
    background #202223
  .header
    // height 492rpx
    height 532rpx
    position relative
    background url($assets + '/active-nov/christmas/banner.png') no-repeat center top / contain
    .btn-rule
      position absolute
      right 28rpx
      top 18rpx
      width 144rpx
      height 54rpx
      font-size 26rpx
      line-height 54rpx
      color #B4AEAE
      font-weight bold
      text-align center
      background url($assets + '/active-nov/christmas/btn-rule.png') center top / contain
    .btn-coupon
      position absolute
      right 30rpx
      bottom 40rpx
      display flex
      align-items center
      background #F34A41
      border-radius 24rpx 24rpx 0 0
      padding 16rpx 14rpx
      font-size 26rpx
      font-weight bold
      color #FFF
      .iconfont
        margin-left 6rpx

  .sider
    position fixed
    top 50%
    right 16rpx
    z-index 3
    .sider-item
      background url($assets + '/active-nov/christmas/btn-circle.png') center top / contain
      width 98rpx
      height 98rpx
      font-size 26rpx
      font-weight 900
      color #D1D2D2
      text-align center
      line-height 98rpx
      &:not(:first-child)
        margin-top 12rpx
      &.active
        position relative
        &::before
          content ''
          position absolute
          top 50%
          left 6px
          transform translateY(-50%)
          border-top 6rpx solid transparent
          border-bottom 6rpx solid transparent
          border-right 8rpx solid #DADADB
        &::after
          content ''
          position absolute
          top 50%
          right 6px
          transform translateY(-50%)
          border-top 6rpx solid transparent
          border-bottom 6rpx solid transparent
          border-left 8rpx solid #DADADB

  .content
    .tab // 活动选项卡
      height 100rpx
      overflow hidden
      display flex
      border-radius 30rpx 30rpx 0 0
      margin-top -40rpx
      .tab-item
        font-size 30rpx
        font-weight 900
        color #5E5E5F
        line-height 1.2
        flex 1
        height 100rpx
        display flex
        flex-direction column
        align-items center
        justify-content center
        background #141516
        position relative
        z-index 2
        &:first-child
          background #005347
          &::after
            content ''
            width 100%
            height 100%
            position absolute
            background #141516
            z-index -1
            border-radius 0 0 30rpx 0
          &.active
            color #FFF
            &::after
              border-radius 0 30rpx 0 0
        &:last-child
          background #141516
          color #528981
          &::after
            content ''
            width 100%
            height 100%
            position absolute
            background #005347
            z-index -1
            border-radius 0 30rpx 0 30rpx
          &.active
            color #FFF
            &::after
              border-radius 30rpx 30rpx 0 0

    .img-christmas-intro // 活动介绍图
      width 710rpx
      height 303rpx
      text-align center
      margin 20rpx auto 0
      background url($assets + '/active-nov/christmas/christmas-intro.png') no-repeat center center / contain
      position relative
      z-index 1
      &.img-christmas-intro-friday
        background url($assets + '/active-nov/christmas/friday.png') no-repeat center center / contain

    .tab-city // 城市选项卡
      padding 15rpx 42rpx
      border-radius 60rpx
      background #F0CC99
      margin 20rpx 28rpx 0
      .tab-city-inner
        width 686rpx
        display flex
        align-items center
        margin 0 auto
        position relative
      .tab-item-bg
        position absolute
        left 0
        height 70rpx
        width 120rpx
        background #FFF6E6
        box-shadow 0 6rpx 12rpx 0 rgba(32, 34, 35, 0.4)
        border-radius 60rpx
        z-index 1
        transition all .3s
      .tab-item
        height 70rpx
        width 120rpx
        font-size 30rpx
        font-weight bold
        color #998365
        line-height 70rpx
        text-align center
        position relative
        z-index 2
        &.active
          color #202223

    .list
      padding 0 30rpx 30rpx
      height auto
      .sub-title
        width 710rpx
        height 180rpx
        margin 0 auto
        background url($assets + '/active-nov/christmas/title-bg.png') no-repeat center center / contain
        color #FFF
        margin-top 30rpx
        .name
          font-size 36rpx
          font-weight 900
          padding 20rpx 0 0 30rpx
        .des
          font-size 24rpx
          font-weight bold
          padding 0 0 0 30rpx
          height 100rpx
          display flex
          flex-direction column
          justify-content center
          strong
            font-size 30rpx
            color #FFF994

</style>
