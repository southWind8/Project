<template>
  <v-row>
      <v-btn color="primary" class="ma-2 white--text" @click="$router.push('/index')">
      <v-icon right dark class="mr-2">
          mdi-home
      </v-icon>
      主页
      </v-btn>

      <v-btn color="secondary" class="ma-2 white--text" @click="$router.push('/category')">
      <v-icon right dark class="mr-2">
          mdi-format-list-bulleted-square
      </v-icon>
      分类
      </v-btn>
      <v-btn color="accent" class="ma-2 white--text" @click="$router.push('/tag')">
      <v-icon right dark class="mr-2">
          mdi-tag
      </v-icon>
      标签
      </v-btn> 
      <v-btn color="primary" class="ma-2 white--text" @click="$router.push('/achieve')">
      <v-icon right dark class="mr-2">
          mdi-message
      </v-icon>
      归档
      </v-btn>
      
      <v-btn color="secondary" class="ma-2 white--text" @click="$router.push('/friend')">
      <v-icon right dark class="mr-2">
          mdi-link
      </v-icon>
      友链
      </v-btn>
      <v-btn color="accent" class="ma-2 white--text" @click="$router.push('/my')">
      <v-icon right dark class="mr-2">
          mdi-account
      </v-icon>
    我的
      </v-btn>
  </v-row>
</template>

<script>
import {mapState} from 'vuex'
export default {
    name:'NavBar',
    computed:{
        ...mapState({
            loginStatus:(state) =>state.loginStatus,
            user:(state) =>state.user
        })
    }

}
</script>

<style lang="scss" scoped>

</style>