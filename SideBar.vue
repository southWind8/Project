<template>
  <v-card min-width="320">
      <v-img :src="imgSrc" height="300px" class="px-3 py-3" dark ref="img">
          <input type="file" @change="change" ref="input" style="display:none" />
          <v-btn dark icon @click="handleClick">
              <v-icon large>mdi-camera</v-icon>
          </v-btn>
      </v-img>
      <v-row class="mt-4">
          <v-btn class="mx-2" fab dark color="cyan" @click="uploadAvatar">
              <v-icon dark>
                  mdi-upload
              </v-icon>
          </v-btn>
      </v-row>

      <v-list two-line>
          <v-list-item>
              <v-list-item-icon>
                  <v-icon color="indigo">
                      mdi-phone
                  </v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                  <v-list-item-title>{{ user.phone }}</v-list-item-title>
                  <v-list-item-subtitle>{{ genderTxt }}</v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-icon>
                  <v-icon>mdi-message-text</v-icon>
              </v-list-item-icon>
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item>
              <v-list-item-icon>
                  <v-icon color="indigo">
                      mdi-map-marker
                  </v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                  <v-list-item-title>{{ user.address }}</v-list-item-title>
              </v-list-item-content>
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item @click="$router.push('/my/usersafe')">
            <v-list-item-icon>
                <v-icon color="indigo">
                    mdi-account
                </v-icon>
            </v-list-item-icon>

            <v-list-item-content>
                <v-list-item-title>账户安全</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item @click="$router.push('/my/feedback')">
            <v-list-item-icon>
                <v-icon color="indigo">
                    mdi-email
                </v-icon>
            </v-list-item-icon>

           <v-list-item-content>
                <v-list-item-title>意见反馈</v-list-item-title>   
            </v-list-item-content> 
          </v-list-item>

          <v-divider inset></v-divider>

          <v-list-item @click="$router.push('/my/about')">
            <v-list-item-icon>
                <v-icon color="indigo">
                    mdi-home
                </v-icon>
            </v-list-item-icon>

            <v-list-item-content>
                <v-list-item-title>关于社区</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider inset></v-divider>
      </v-list>
  </v-card>
</template>

<script>
import { mapState } from 'vuex'
export default {
    data: () => ({
        imgSrc: '',
        elInput: null,
        file: '',
        selectedItem: 0,
        items: [
            { text: '资料编辑', icon: 'mdi-folder', path: '/my/userinfo'},
            { text: '账户安全', icon: 'mdi-account-multiple', path: '/my/usersafe'},
            { text: '意见反馈', icon: 'mdi-star', path: '/my/feedback'},
            { text: '关于社区', icon: 'mdi-history', path: '/my/about'}
        ],
        genders: ['保密','男','女']
    }),
    filters: {
        format: function(value) {
            alert(this.genders[0])
            if(!value) return ''
            return this.genders[0]
        }
    },
    created() {
        this.imgSrc = this.user.avatar
    },
    computed: {
        ...mapState({
            loginStatus: (state) => state.loginStatus,
            user: (state) => state.user
        }),
        //头像计算属性
        avatar: {
            get: function() {
                return this.user.avatar
            },
            set: function(newValue) {
                this.$store.commit('setAvatar', newValue)
            }
        },
        genderTxt() {
            switch (this.user.gender) {
                case 0:
                    return '保密'
                case 1:
                    return '男'
                case 2:
                    return '女'
                default:
                    return ''
            }
        }
    },
    methods: {
        handleClick() {
            //点击照相机图标等同于点击file选择组件
            this.$refs.input.click()
        },
        //点击头像，选择图片并预览
        change() {
            const refs = this.$refs
            const elInput = refs.input
            const elImg = refs.img
            const reader = new FileReader()
            reader.onload = (e) => {
                elImg.src = e.target.result
            }
            if(elInput.files && elInput.files[0]) {
                this.file = elInput.files[0]
                reader.readAsDataURL(elInput.files[0])
            }
        },
        uploadAvatar() {
            console.log('upload');
            let formData = new FormData()
            formData.append('file', this.file)
            //调用上传文件接口
            this.axios({
                method: 'post',
                url: '/user/upload',
                headers: {
                    'content-Type': 'multipart/form-data'
                },
                data: formData              
            }).then((res) => {
                console.log(res.data.data)
                let newUser = this.user
                newUser.avatar = res.data.data
                this.$store.commit('editUserInfo', newUser)
                //调用修改用户信息的接口
                this.axios({
                    method: 'post',
                    url: '/user/update',
                    data: newUser
                }).then((res) => {
                    console.log(res.data.data);
                    this.imgSrc = this.user.avatar
                })
            })
        }
    }
}
</script>

<style lang="scss">
a:link {
    text-decoration: none;
}
* {
    font-size: 16px;
}
</style>