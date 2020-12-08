<template>
	<view>
		<input class="uni-input" type="text" placeholder="请输入密码" v-model="newpassword"/>
		<input class="uni-input" type="text" placeholder="请确认密码" v-model="renewpassword" />
		<view class="py-2 px-3">
			<button class="bg-main text bg-white"
			style="border-radius: 50rpx;border:0;"
			type="primary"
			:disabled="disabled"
			:class="disabled?'bg-main-disabled':''"
			@click="submit">设置</button>
		</view>
	</view>
</template>

<script>
	import{mapState} from 'vuex'
		import uniListItem from'@/components/uni-ui/uni-list-item/uni-list-item.vue';
	export default {
		components:{
			uniListItem
			
		},
		computed:{
			...mapState({
				user:state =>state.user
			}),
			disabled(){
				return this.newpassword==''||this.renewpassword =='';
			}
		},
	
		data() {
			return {
				newpassword:'',
				renewpassword:''
			};
		},
		methods: {
			//验证	
			check(){
				if(this.newpassword!==this.renewpassword){
					uni.showToast({
						title:'两次密码不一致',
						icon:'none'
					});
					return false;
				}
				return true;
			},
			submit(){
				if(!this.check()){
					return;
				}
				let data={
					phone:this.user.phone,
					password:this.newpassword,
					nickname:this.user.nickname,
					avatar:this.user.avatar,
					gender:this.user.gender,
					birthday:this.user.birthday,
					address:this.user.address
				};
				this.$H.post('/user/update',data).then(res=>{
					this.$store.commit('editUserInfo',data);
					uni.navigateBack({
						delta:1
					});
					uni.showToast({
						title:'修改密码成功',
						icon:'none'
					});
				});
			},
			
		}
	}
</script>

<style>

</style>
