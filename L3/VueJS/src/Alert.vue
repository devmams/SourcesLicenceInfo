<template >
  <div>
    <transition name="fade">
      <div class="ui message success" v-if="visible">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </div>
    </transition>
    <button @click="toggle">Toggle</button>

    <ul>
      <transition-group name="fade" tag="ul">
        <li v-for="item in items" :key="item"> {{item}} </li>
      </transition-group>
    </ul>
    <button class="ui button" @click="add">Ajouter</button>

  </div>
</template>

<script>
  export default{
    data () {
      return {
        visible: true,
        items: [1,2,3,4,5]
      }
    },
    methods:{
      add (){
        this.items.push(Math.random())
      },
      toggle (){
        this.visible = !this.visible
      }
    }
  }
</script>

<style>
  .fade-enter-active, .fade-leave-active{
    transition: opacity 1s, transform 1s;
  }
  .fade-enter, .fade-leave-active{
    opacity: 0;
    transforms: translateX(20px);
  }
</style>
