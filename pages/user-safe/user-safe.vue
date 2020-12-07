<template>
	<view>
			<uni-list-item title="修改密码" @click="open('changePassword')" ></uni-list-item>
			<uni-list-item title="绑定手机"></uni-list-item>
			<uni-list-item title="绑定微信"></uni-list-item>
			<uni-list-item title="绑定QQ"></uni-list-item>
	</view>
</template>

<script>
	import uniListItem from'@/components/uni-ui/uni-list-item/uni-list-item.vue';
	import {mapState} from 'vuex';
	export default {
		components:{
			uniListItem
		},
		data() {
			return {
				
			}
		},
		methods: {
			open(path){
				uni.navigateTo({
					url:`../${path}/${path}`
				});
			}
			
		}
	}
</script>

<style>

</style>
