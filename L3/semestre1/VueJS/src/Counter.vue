<template>
  <div class="counter">
    {{ count }}
    <div class="counter_button" @click="increment">+</div>
  </div>
</template>

<script>
  export default {
    data (){
      return {
        count: 0
      }
    },
    methods: {
      increment (){
        this.count++
      }
    }
  }
</script>
