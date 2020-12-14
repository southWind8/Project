<template>
  <v-main>
    <v-carousel cycle height="900" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet height="100">
          <v-row class="fill-height header">
            <img :src="slide.src" class="slider-img"/>
            <!-- 引入封装的顶部导航组件 -->
            <div class="nav">
              <nav-bar></nav-bar>
            </div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
    <v-row style="width:80%;margin:0 auto;margin-top:=-80px;">
      <v-col cols="12" md="6" v-for="(card, index) in cards" :key="index">
        <v-card v-if="index < 4">
          <v-img class="white--text" height="450px" :src="card.bgImg">
            <v-card-title>{{card.title}}</v-card-title>
          </v-img>
          <v-card-text class="text--primary">
            <div>{{card.content}}</div>
          </v-card-text>
          <v-card-actions>
            <v-btn color="orange" text>
              Share
            </v-btn>
            <v-btn color="orange" text>
              Explore
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    
    <v-row style="width:80%;margin:0 auto;">
      <v-col cols="12" md="4" v-for="(card, index) in cards" :key="index">
        <v-card>
          <v-img class="white--text align-end" height="300px" :src="card.bgImg">
            <v-card-text class="text--primary">
              <div>{{card.content}}</div>
            </v-card-text>
            <v-card-actions>
              <v-btn color="orange" text>
                Share
              </v-btn>
              <v-btn color="orange" text>
                Explore
              </v-btn>
            </v-card-actions>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
import {mapState} from 'vuex'
import NavBar from '../components/NavBar'
export default {
  name: 'Index',
  data:() =>({
    cards:[],
    slides:[
      {
        src:'https://southwind8.oss-cn-hangzhou.aliyuncs.com/%C3%8F%C3%AE%C3%84%C2%BF/09224464-659b-41fe-b1fe-747cadd97143.1-131213115232.jpg'
      },
      {
        src:'https://southwind8.oss-cn-hangzhou.aliyuncs.com/%C3%8F%C3%AE%C3%84%C2%BF/29e5daa7-5329-4fff-a65a-6df9d53b99cf.08-13-24-55325876_p0.jpg'
      },
      {
        src:'https://southwind8.oss-cn-hangzhou.aliyuncs.com/%C3%8F%C3%AE%C3%84%C2%BF/4489b8ac-d409-4cf7-a73a-b63e46352fc1.1920x1080_f4340acb45c7a02.jpg'
      },
      {
        src:'https://southwind8.oss-cn-hangzhou.aliyuncs.com/%E9%AC%BC%E5%88%80.jpg'
      },
      {
        src:'https://southwind8.oss-cn-hangzhou.aliyuncs.com/%C3%8F%C3%AE%C3%84%C2%BF/f4534269-1661-4155-a903-29c56612f154.1-131030140531.jpg'
      }
    
    ]
  }),
  components:{
    NavBar
  },
  computed:{
    ...mapState({
      loginStatus: (state) =>state.loginStatus,
      user:(state) => state.user

    })
  },
  created(){
    this.axios.get('/cards').then((res)=>{
      console.log(res.data.data)
      this.cards=res.data.data
    })
  }

}
</script>
<style lang="scss" scoped>
.header{
  display: flex;
  justify-content: center;
  align-items: state;
  .nav{
    position: fixed;
    top: 10;
    z-index: 1000;
  }
}

</style>