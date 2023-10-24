<template>
  <div class="rule">
    <div class="part">
      <div class="title-part">备货补贴券</div>
      <div class="title">100元补贴券</div>
      <p class="text">9张，共900元（三张使用期限：2021年11月11日起-11月30日，2021年12月1日-12月31日，2022年1月1日-1月31日）使用条件：满3000元可用</p>
      <div class="title">150元补贴券</div>
      <p class="text">使用期限：2021年11月11日起-2021年11月30日   使用条件：满5000元可用</p>
      <div class="title">250元补贴券</div>
      <p class="text">使用期限：2021年11月11日起-2021年11月30日   使用条件：满8000元可用</p>
      <div class="title">450元补贴券</div>
      <p class="text">使用期限：2021年11月11日起-2021年11月30日   使用条件：满15000元可用</p>
      <div class="title">750元补贴券</div>
      <p class="text">使用期限：2021年11月11日起-2021年11月30日   使用条件：满30000元可用</p>
      <div class="title">1500元补贴券</div>
      <p class="text">使用期限：2021年11月11日起-2021年11月30日   使用条件：满50000元可用</p>
    </div>

    <div class="part">
      <div class="title-part">活动规则</div>
      <p class="text">1、活动期限：2021年11月11日至11月30日（补贴券有效期内即可使用）；</p>
      <p class="text">2、活动价格：点击参与活动后，领补贴券，实际成交价格以平台规则为准；</p>
      <p class="text">3、活动权益：点击参与活动，领取后客户账户直接入账共4000元补贴券，可与新人专属奖、老客户专属奖叠加使用；</p>
      <p class="text">4、活动规则：单票计算使用补贴券额度，不累计叠加，活动期间不限单，符合补贴券额度即可直接抵用；</p>
      <p class="text">5、使用规则：自营店铺所有线路可用，服务商店铺仅限活动页面线路可用；</p>
      <p class="text">*在法律允许范围内，平台保留对规则的修改和解释权</p>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {}
    },
  }
</script>

<style lang="stylus" scoped>
@import '~@/styles/var'

.rule
  background #fff
  padding 0 40rpx 40rpx
  font-size 28rpx
  color #161718
  .part
    margin-top 40rpx
  .title-part
    font-size 34rpx
    font-weight 700
    line-height 50rpx
    margin-bottom 12rpx
  .title
    font-weight bold
    line-height 50rpx
  .text
    line-height 50rpx
    text-align justify

</style>
