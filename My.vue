<template>
  <v-row align="start">
    <v-col cols="12" md="3">
      <side-bar></side-bar>
    </v-col>
    <v-col cols="12" md="8" class="ml-12">
    <router-view />
    </v-col>
  </v-row>
</template>

<script>
import SideBar from '../components/SideBar.vue'
import VDistpicker from 'v-distpicker'

export default {
  name:'My',
  components:{
    SideBar
  },
  data(){
    return{}
  },
  created(){
    methods: {
      
    }
  }


}
</script>

<style lang="scss">

</style>