<template>
  <div class="page">
    <!-- <p class="btn" @click="goto('img-load')">图片加载示例</p>
    <p class="btn" @click="goto('gift')">生娃送礼物示例</p>
    <p class="btn" @click="goto('traffic-light')">红绿灯示例</p> -->

    <VPoptip />
  </div>
</template>

<script>
import VPoptip from '@/components/v-poptip'
export default {
  name: 'home',
  props: {
    msg: String,
  },
  components: {
    VPoptip,
  },
  data() {
    return {}
  },
  methods: {
    goto(path) {
      this.$router.push({
        path,
      })
    },
  },
}
</script>

<style lang="stylus" scoped >
.page
  min-height calc(400vh - 16px)
  display flex
  align-items center
  justify-content center
  .btn
    cursor pointer
    display inline-block
    margin 20px
    padding 10px 20px
    border-radius 12px
    background #57BC78
    color #FFF
</style>
