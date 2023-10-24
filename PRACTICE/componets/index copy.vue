<template>
  <view class="page">
    <!-- <u-empty v-if="!islogin" class="emptybox" text="哎呀，您还未登录" mode="list">
      <u-button slot="bottom" class="button" size="medium" @click="login">去登录</u-button>
    </u-empty>
    <u-no-network />-->
    <div>箱量统计</div>
    <div class="donut-chart">
      <div class="donut-item">
        <ff-canvas id="donutChart" canvas-id="donutChart" :opts="opts" />
      </div>
      <div class="donut-item">
        <ff-canvas id="donutChart2" canvas-id="donutChart2" :opts="opts" />
      </div>
      <div class="donut-item">
        <ff-canvas id="donutChart3" canvas-id="donutChart3" :opts="opts" />
      </div>
      <div class="donut-item">
        <ff-canvas id="donutChart4" canvas-id="donutChart4" :opts="opts" />
      </div>
      <div class="donut-item">
        <ff-canvas id="donutChart5" canvas-id="donutChart5" :opts="opts" />
      </div>
    </div>

    <div>门吊作业量</div>
    <div class="bar-chart">
      <ff-canvas id="barChart" canvas-id="barChart" :opts="opts" />
    </div>

    <div>箱归属统计</div>
    <div class="pie-chart">
      <ff-canvas id="pieChart" canvas-id="pieChart" :opts="opts" />
    </div>
  </view>
</template>

<script>
  import { mapState, mapMutations } from 'vuex'
  // eslint-disable-next-line no-unused-vars
  import F2 from '../../wxcomponents/f2-canvas/lib/f2'

  F2.Util.addEventListener = function (source, type, listener) {
    source.addListener(type, listener)
  }
  F2.Util.removeEventListener = function (source, type, listener) {
    source.removeListener(type, listener)
  }
  F2.Util.createEvent = function (event, chart) {
    const type = event.type
    let x = 0
    let y = 0
    const touches = event.touches
    if (touches && touches.length > 0) {
      x = touches[0].x
      y = touches[0].y
    }
    return {
      type,
      chart,
      x,
      y,
    }
  }

  const Shape = F2.Shape
  // 注册自定义Shape——极坐标下的条形
  Shape.registerShape('interval', 'tick', {
    draw: function draw(cfg, container) {
      const points = this.parsePoints(cfg.points)
      const style = F2.Util.mix({
        stroke: cfg.color,
      }, F2.Global.shape.interval, cfg.style)

      let newPoints = points.slice(0)
      if (this._coord.transposed) {
        newPoints = [points[0], points[3], points[2], points[1]]
      }

      const center = cfg.center
      const x = center.x
      const y = center.y

      const v = [1, 0]
      const v0 = [newPoints[0].x - x, newPoints[0].y - y]
      const v1 = [newPoints[1].x - x, newPoints[1].y - y]
      const v2 = [newPoints[2].x - x, newPoints[2].y - y]

      let startAngle = F2.G.Vector2.angleTo(v, v1)
      let endAngle = F2.G.Vector2.angleTo(v, v2)
      const r0 = F2.G.Vector2.length(v0)
      const r = F2.G.Vector2.length(v1)

      if (startAngle >= 1.5 * Math.PI) {
        startAngle = startAngle - 2 * Math.PI
      }

      if (endAngle >= 1.5 * Math.PI) {
        endAngle = endAngle - 2 * Math.PI
      }

      const lineWidth = r - r0
      const newRadius = r - lineWidth / 2

      return container.addShape('Arc', {
        className: 'interval',
        attrs: F2.Util.mix({
          x,
          y,
          startAngle,
          endAngle,
          r: newRadius,
          lineWidth,
          lineCap: 'round',
        }, style),
      })
    },
  })

  let barChart = null
  function initBarChart(canvas, width, height) {
    const data = [{
      year: '1951 年',
      sales: 38,
    }, {
      year: '1952 年',
      sales: 52,
    }, {
      year: '1956 年',
      sales: 61,
    }, {
      year: '1957 年',
      sales: 145,
    }, {
      year: '1958 年',
      sales: 48,
    }, {
      year: '1959 年',
      sales: 38,
    }, {
      year: '1960 年',
      sales: 38,
    }, {
      year: '1962 年',
      sales: 38,
    }]
    barChart = new F2.Chart({
      el: canvas,
      width,
      height,
    })

    barChart.source(data, {
      sales: {
        tickCount: 5,
      },
    })
    barChart.coord({
      transposed: true,
    })
    // barChart.tooltip({
    //   showItemMarker: false,
    //   onShow: function onShow(ev) {
    //     const list = ev.items
    //     list[0].name = null
    //     list[0].name = list[0].year
    //     list[0].value = '¥ ' + list[0].sales
    //   },
    // })
    barChart.interval().position('year*sales')
    barChart.render()

    // barChart.interaction('interval-select', {
    //   startEvent: 'tap',
    //   animate: {
    //     duration: 300,
    //     easing: 'backOut',
    //   },
    //   cancelable: true,
    // })
    return barChart
  }

  let dountChart = null
  function initDonutChart(canvas, width, height) {
    const data = [{
      x: '1',
      y: 85,
    }]

    dountChart = new F2.Chart({
      el: canvas,
      width,
      height,
    })
    dountChart.source(data, {
      y: {
        max: 100,
        min: 0,
      },
    })
    dountChart.axis(false)
    dountChart.tooltip(false)
    dountChart.coord('polar', {
      transposed: true,
      innerRadius: 0.8,
      // radius: 0.85,
    })
    dountChart.guide().arc({
      start: [0, 0],
      end: [1, 99.98],
      top: false,
      style: {
        lineWidth: 10,
        stroke: '#eee',
      },
    })
    dountChart.guide().text({
      position: ['50%', '50%'],
      content: '85%',
      style: {
        fill: '#1890FF',
      },
    })
    dountChart.interval()
      .position('x*y')
      .shape('tick')
      .size(10)
      // .animate({
      //   appear: {
      //     duration: 1200,
      //     easing: 'cubicIn',
      //   },
      // })
      .animate({
        appear: {
          duration: 1100,
          easing: 'linear',
          animation: function animation(shape, animateCfg) {
            const startAngle = shape.attr('startAngle')
            let endAngle = shape.attr('endAngle')
            if (startAngle > endAngle) {
              endAngle += Math.PI * 2
            }
            shape.attr('endAngle', startAngle)
            shape.animate().to(F2.Util.mix({
              attrs: {
                endAngle,
              },
            }, animateCfg))
          },
        },
      })
    dountChart.render()

    return dountChart
  }

  let pieChart = null
  function initPieChart(canvas, width, height) {
    const map = {
      芳华: '40%',
      妖猫传: '20%',
      机器之血: '18%',
      心理罪: '15%',
      寻梦环游记: '5%',
      其他: '2%',
    }
    const data = [{
      name: '芳华',
      percent: 0.4,
      a: '1',
    }, {
      name: '妖猫传',
      percent: 0.2,
      a: '1',
    }, {
      name: '机器之血',
      percent: 0.18,
      a: '1',
    }, {
      name: '心理罪',
      percent: 0.15,
      a: '1',
    }, {
      name: '寻梦环游记',
      percent: 0.05,
      a: '1',
    }, {
      name: '其他',
      percent: 0.02,
      a: '1',
    }]
    pieChart = new F2.Chart({
      el: canvas,
      width,
      height,
    })
    pieChart.source(data, {
      percent: {
        formatter: function formatter(val) {
          return val * 100 + '%'
        },
      },
    })
    pieChart.legend({
      position: 'right',
      itemFormatter: function itemFormatter(val) {
        return val + '  ' + map[val]
      },
    })

    // pieChart.tooltip({
    //   showItemMarker: false,
    //   onShow: function onShow(ev) {
    //     const arr = ev.items
    //     // items[0].name = null
    //     // items[0].name = items[0].title
    //     arr[0].value = arr[0].percent + '%'
    //   },
    // })
    pieChart.coord('polar', {
      transposed: true,
      radius: 0.85,
    })
    pieChart.axis(false)
    pieChart.interval()
      .position('a*percent')
      .color('name', ['#1890FF', '#13C2C2', '#2FC25B', '#FACC14', '#F04864', '#8543E0'])
      .adjust('stack')
      .style({
        lineWidth: 1,
        stroke: '#fff',
        lineJoin: 'round',
        lineCap: 'round',
      })
      .animate({
        appear: {
          duration: 1200,
          easing: 'bounceOut',
        },
      })

    pieChart.interaction('pie-select', {
      startEvent: 'touchstart',
      animate: {
        duration: 300,
        easing: 'backOut',
      },
      // offset: 1.2,
      cancelable: true,
    })
    pieChart.render()
    return pieChart
  }

  export default {
    data: () => ({
      opts: {
        // 使用延时初始化 -- 重要
        lazyLoad: true,
      },
    }),

    computed: {
      ...mapState(['islogin', 'inquiryInfo', 'inquiryForms']),
    },

    mounted() {
      // 找到该组件, 调用该组件的初始化方法
      let a = this.$mp.page.selectComponent('#barChart')
      if (a) a.init(initBarChart)

      let d = this.$mp.page.selectComponent('#donutChart')
      if (d) d.init(initDonutChart)
      let d2 = this.$mp.page.selectComponent('#donutChart2')
      if (d2) d2.init(initDonutChart)
      let d3 = this.$mp.page.selectComponent('#donutChart3')
      if (d3) d3.init(initDonutChart)
      let d4 = this.$mp.page.selectComponent('#donutChart4')
      if (d4) d4.init(initDonutChart)
      let d5 = this.$mp.page.selectComponent('#donutChart5')
      if (d5) d5.init(initDonutChart)

      let p = this.$mp.page.selectComponent('#pieChart')
      if (p) p.init(initPieChart)
    },

    onLoad() {
      console.log(F2)
      // this.refresh()
    },

    onPullDownRefresh() {
      this.refresh()
    },

    methods: {
      ...mapMutations(['setInquiryInfo', 'setInquiryForms']),
      async refresh() {
        await this.getOrders(true)
        this.$wx.stopPullDownRefresh()
      },

      login() {
        this.$user.login()
      },

      // 获取报价列表
      async getOrders(isRefresh) {
        if (!this.islogin) return
        let tabData = this.tabData

        // 非加载更多
        if (isRefresh) {
          tabData.list = []
          tabData.pageIndex = 1
          tabData.empty = false
        }

        tabData.loadStatus = 'loading'
        const res = await this.$api.getOrderList({
          inquiryStatus: this.inquiryStatus,
          page: tabData.pageIndex,
          size: tabData.pageSize,
        })
        tabData.loadStatus = 'loadmore'
        if (res.code !== '200') {
          this.$tips.toast(res.msg)
          return
        }
        let { data = {} } = res
        let records = data.records || []
        let totalPage = Math.ceil(data.total / data.size || 0)
        tabData.list.push(...records)
        tabData.empty = data.total <= 0
        tabData.nomore = tabData.pageIndex >= totalPage
        tabData.loadStatus = tabData.nomore ? 'nomore' : 'loadmore'
        tabData.pageIndex++
      },

      // scroll-view 到底部加载更多
      onreachBottom() {
        !this.tabData.nomore && this.getOrders()
      },
    },
  }
</script>

<style lang="stylus" scoped>
.page
  .donut-chart
    display flex
    flex-wrap wrap
    .donut-item
      width 33.3%
      height 300rpx
  .bar-chart
    width 100%
    height 600rpx
  .pie-chart
    width 100%
    height 600rpx
</style>
