<template>
  <div class="page">
    <h3>加载10张图片，图片地址已知，但是同时最多加载3张图片，要求用promise实现</h3>

    <div class="img-box">
      <div class="img-item" v-for="(img, i) in imgList" :key="i" >
        <img class="img" :src="img.url" />

        <div class="info">
          <h3>第{{ i + 1 }}名</h3>
          <p>原数组第 <strong>{{ img.index }}</strong> 张图</p>
          <p>加载出来的时间</p>
          <p>{{ img.date }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import dayjs from 'dayjs'

export default {
  name: 'home',
  props: {
    msg: String,
  },
  data() {
    return {
      imgList: [],
    }
  },

  created() {
    this.init()
  },

  methods: {
    // 加载10张图片，图片地址已知，但是同时最多加载3张图片，要求用promise实现
    init() {
      this.loadFn([
        'https://images.pexels.com/photos/10251858/pexels-photo-10251858.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=1',
        'https://images.pexels.com/photos/10778455/pexels-photo-10778455.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=2',
        'https://images.pexels.com/photos/10469127/pexels-photo-10469127.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=3',
        'https://images.pexels.com/photos/2463815/pexels-photo-2463815.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=4',
        'https://images.pexels.com/photos/9330649/pexels-photo-9330649.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=5',
        'https://images.pexels.com/photos/10429564/pexels-photo-10429564.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=6',
        'https://images.pexels.com/photos/10549951/pexels-photo-10549951.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=7',
        'https://images.pexels.com/photos/10701375/pexels-photo-10701375.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=8',
        'https://images.pexels.com/photos/10322283/pexels-photo-10322283.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=9',
        'whttps://images.pexels.com/photos/10754501/pexels-photo-10754501.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500&index=10',
      ], 3, () => { console.log('全部加载完毕') })
    },

    loadImg(url, i) {
      // 服务器太好加载太快了看不出来效果，加个随机定时器慢一点
      return new Promise((resolve, reject) => {
        // setTimeout(() => {
        //   const image = new Image()
        //   image.onload = () => {
        //     const date = dayjs().format('YYYY-MM-DD HH:mm:ss')
        //     const index = url.split('=').reverse()[0]
        //     this.imgList.push({
        //       url,
        //       date,
        //       index,
        //     })
        //     resolve(i)
        //   }
        //   image.onerror = () => {
        //     reject(i)
        //   }
        //   image.src = url + '&date=' + dayjs().valueOf()
        //   image.setAttribute('crossOrigin', 'anonymous')
        // }, Math.random() * 10000)
        const image = new Image()
        image.onload = () => {
          // const date = dayjs().format('YYYY-MM-DD HH:mm:ss')
          const date = new Date().toLocaleString()
          const index = url.split('=').reverse()[0]
          this.imgList.push({
            url,
            date,
            index,
          })
          resolve(i)
        }
        image.onerror = () => {
          reject(i)
        }
        image.src = url + '&date=' + new Date().valueOf()
        image.setAttribute('crossOrigin', 'anonymous')
      })
    },

    loadFn(urls, limit, cb) {
      const promiseInfo = {}
      const idxs = {}
      let curIdx = 0

      const onLoad = () => {
        if (Object.keys(idxs).length === urls.length) {
          const list = Object.keys(promiseInfo).map(i => promiseInfo[i])
          // const list = Object.keys(promiseInfo).reduce((arr, m) => {
          //   arr.push(promiseInfo[m])
          //   return arr
          // }, [])

          Promise.all(list).then(res => {
            cb && cb()
          }).catch(err => {
            console.log('err:' + err)
          })
        } else {
          while (Object.keys(promiseInfo).length <= limit) {
            console.log('正在加载的图片')
            console.log(promiseInfo)

            for (let i = curIdx; i < urls.length; i++) {
              if (idxs[i] === undefined) {
                idxs[i] = false
                promiseInfo[i] = this.loadImg(urls[i], i)

                curIdx = i
                break
              }
            }
          }

          const list = Object.keys(promiseInfo).map(i => promiseInfo[i])
          Promise.race(list).then(i => {
            idxs[i] = true
            delete promiseInfo[i]
            onLoad()
          }).catch(e => {
            // 加载失败了继续
            console.log(`第${e + 1}张图片加载出错了~`)
            // delete idxs[e]
            delete promiseInfo[e]
            onLoad()
          })
        }
      }

      onLoad()
    },
  },
}
</script>

<style lang="stylus" scoped >
.page
  text-align center
  .img-box
    display flex
    flex-wrap wrap
    .img-item
      text-align center
      margin 20px
      .img
        width 100px
        height 100px
        object-fit cover
      .info
        font-size 12px
</style>
