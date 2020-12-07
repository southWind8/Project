<template>
	<view>
		<view class="px-3 py-2 flex justify-center">
			<image src="/static/common/nothing.png"  style="width: 200rpx; height: 200rpx;"
			class="rounded-circle"></image>
		</view>
		<view class="flex align-center justify-center text-muted">
			version 1.0.0
		</view>
		<uni-list-item title="新版本检测"></uni-list-item>
		<uni-list-item title="社区用户协议"></uni-list-item>
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
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
