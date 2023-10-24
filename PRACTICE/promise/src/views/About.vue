<template>
  <div class="about">
    <button @click="start">冒泡排序</button>
    <p>原数组：[ {{ oldArr.join(', ')}} ]</p>
    <p>新数组：[ {{ newArr.join(', ')}} ]</p>
    <p>总次数：{{ total }}</p>
  </div>
</template>
<script>
export default {
  name: 'About',
  data() {
    return {
      oldArr: [],
      newArr: [],
      total: 0,
    }
  },
  methods: {
    start() {
      const arr = JSON.stringify([3, 1, 0, 3, 5])
      this.oldArr = JSON.parse(arr)
      const newArr = this.bubbleSort(JSON.parse(arr))
      this.newArr = newArr
    },

    bubbleSort(arr) {
      const len = arr.length
      let total = 0
      for (let i = 0; i < len - 1; i++) {
        for (let j = 0; j < len - 1 - i; j++) {
          console.log(`i: ${i}, j:${j}`)
          total += 1
          if (arr[j] > arr[j + 1]) {
            const temp = arr[j]
            arr[j] = arr[j + 1]
            arr[j + 1] = temp
          }
        }
      }

      this.total = total
      console.log(arr)
      return arr
    },
  },
}
</script>
