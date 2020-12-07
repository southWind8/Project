<template>
	<view>
		<view class="mb-2">
			<input type="text" v-model="newPsd" placeholder="请输入新密码" class="border-bottom p-2"/>
		</view>
		<view class="mb-2">
			<input type="text" v-model="surePsd" placeholder="请确认新密码" class="border-bottom p-2"/>
		</view>
			<button class="bg-main text-white" style="border-radius: 50rpx;
			border: 0;" type="primary" @click="checkPsd">设置</button>
		</view>
		</view>
		
	</view>
</template>

<script>
		import uniListItem from'@/components/uni-ui/uni-list-item/uni-list-item.vue';
	export default {
		components:{
			uniListItem
		},
		data() {
			return {
				newPsd:'',
				surePsd:''
			}
		},
		methods: {
			checkPsd(){
				if(this.newPsd.trim()==this.surePsd.trim()){
					uni.showToast({
						title:"修改密码成功",
						icon:'none'
					})
				}
				else{
					uni.showToast({
						title:"修改密码失败",
						icon:'none'
					})
				}
			}
		}
	}
</script>

<style>

</style>
