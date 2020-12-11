<template>
  <v-row>
    <v-col cols="12" md="4" v-for="(card,index) in cards" :key="index" class="cards">
      <v-card class="mx-auto" max-width="400">
        <v-img class="white--text align-end" height="200px" :src="card.bgImg" >
          <v-card-title>{{ cards.title}}</v-card-title>
        </v-img>
        <v-card-text class="text--primary">
          <div>{{card.content}}</div>
        </v-card-text>
        <v-card-actions>
          <v-btn color="skyblue" text>
            Share
          </v-btn>
          <v-btn color="skyblue" text>
            Explore
          </v-btn>
        </v-card-actions>
      </v-card>

    </v-col>
  </v-row>
</template>

<script>
export default {
  name:'Index',
  data:() =>({
    cards:[]
  }),
  created(){
    this.axios.get('/cards').then((res)=>{
      console.log(res.data.data)
      this.cards=res.data.data
    })
  }
}
</script>

<style>

</style>