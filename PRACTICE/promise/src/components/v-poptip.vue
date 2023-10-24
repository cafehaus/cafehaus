<template>
  <div class="v-poptip">
    <div class="v-poptip-rel" ref="vpoptiprel" @click="visible = !visible">占位内容</div>
    <div v-show="visible" ref="vpoptipcontent" class="v-poptip-content" v-transfer-dom :data-transfer="true">
      无发热维吾尔文清空
    </div>
  </div>
</template>

<script>
import TransferDom from './transfer-dom'
import { createPopper } from '@popperjs/core'
export default {
  name: 'VPoptip',
  directives: { TransferDom },
  props: {
    msg: String,
  },
  data() {
    return {
      visible: false,
    }
  },
  watch: {
    visible(v) {
      if (v) {
        document.body.appendChild(this.$refs.vpoptipcontent)
      } else {
        document.body.removeChild(this.$refs.vpoptipcontent)
      }
    },
  },

  mounted() {
    const popcorn = this.$refs.vpoptiprel
    const tooltip = this.$refs.vpoptipcontent
    createPopper(popcorn, tooltip, {
      placement: 'bottom-start',
    })
  },
  methods: {

  },
}
</script>

<style scoped lang="stylus">
.v-poptip-content
  background pink
  padding 40px
</style>
