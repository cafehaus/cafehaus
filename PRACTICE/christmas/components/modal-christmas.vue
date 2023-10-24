<template>
  <u-popup :value="value" mode="center" width="600" :border-radius="32" @close="onClose">
    <div class="modal" @click="goto">
      <i class="iconfont icon-close" @click.stop="onClose" />
    </div>
  </u-popup>
</template>

<script>
  import dayjs from 'dayjs'
  export default {
    props: {
      value: Boolean,
    },
    data() {
      return {}
    },

    methods: {
      goto() {
        this.$Router.push({
          path: '/pages/christmas/christmas',
        })
        this.onClose()
      },

      onClose() {
        // 缓存到本地判断每天弹一次
        let date = dayjs().format('YYYY-MM-DD')
        this.$storage('christmas', date)

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
  height 560rpx
  box-sizing border-box
  border-radius 32rpx
  overflow hidden
  background transparent url($assets + '/active-nov/christmas/poster.png') no-repeat center top / contain
  .iconfont
    position absolute
    right 20rpx
    top 20rpx
    font-size 48rpx
    color #FFF

</style>
