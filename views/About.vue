<template>
  <v-footer
    dark
    padless  
  >
    <v-card
      flat
      tile
      class="indigo lighten-1 white--text text-center"
    >
      <v-card-text>
        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
     营业执照 信息网络传播视听节目许可证：xxxx 网络文化经营许可证 沪网文xxxx,广播电视节目制作经营许可证:xxxxxxxx<br>
     增值电信业务经营许可证 沪B2xxxxx <br>
     营业性演出许可证 沪市文演（经）xxxx<br>
     违法不良信息举报邮箱：xxxx@xx.com |违法不良信息举报电话：xxxxx<br>
     亲爱的市民朋友，上海警方反诈劝阻电话“96110”系专门针对避免您财产被骗受损,请您一旦收到来电，立即接听。<br>
     公司名称：xxxxxxx|公司地址：xxxxxx|电话：xxxxx
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} — <strong>南风的博客</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
  export default {
    data: () => ({
      icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-linkedin',
        'mdi-instagram',
      ],
    }),
  }
</script>