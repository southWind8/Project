		<template >
				<view class="mb-2 flex align-stretch">
					<view class="border-bottom flex align-center justify-center font px-2">+86</view>
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="mb-2 flex align-stretch">
					<input type="text" v-model="code" placeholder="请输入验证码" class="border-bottom p-2 flex-1" />
					<view
						class="border-bottom flex align-center justify-center font-sm text-white"
						:class="codeTime > 0 ? 'bg-main-disabled' : 'bg-main'"
						style="width: 180rpx;"
						@click="getCode"
					>
						{{ codeTime > 0 ? codeTime + 's' : '获取验证码' }}
					</view>
				</view>
			</template>
<script>
	import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
	export default {
		components:{
			uniListItem;
		},
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
