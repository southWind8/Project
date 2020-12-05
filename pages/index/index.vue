<template>
	<view>
		<!-- #ifdef MP -->
		<uni-nav-bar :shadow="false" :border="false" @click-left="clickLeft" @click-right="clickRight">
			<!-- 左边图标 -->
			<block slot="left">
				<view class="iconfont icon-qiandao ml-2 mr-2" style="font-size: 22px;color: #FF9619;"></view>
			</block>
			<!-- 中间搜索框 -->
			<view class="flex justify-center align-center rounded text-muted bg-light flex-1 mt-1"
				  style="margin-left: -46upx;height: 60upx;" @top="openSearch">
				<view class="iconfont icon-suosuo mr-1"></view>
				搜索帖子
			</view>
			<!-- 右边图标 -->
			<block slot="right">
				<view class="icon iconfont icon-bianji1 text-dark" style="font-size: 22px;"></view>
			</block>
		</uni-nav-bar>
		<!-- #endif -->
		<view>
			<view v-for="(card,index) in cards" :key="index" class="cards">
				<text class="font-lg">{{ card.title }}</text>
				<image :src="card.bgImg" class="cover" />
				<text class="font-md">{{ card.content }}</text>
			</view>
		</view>
	</view>
</template>

<script>
	import uniNavBar from '@/components/uni-ui/uni-nav-bar/uni-nav-bar.vue';
	export default {
		components: {
			uniNavBar
		},
		data() {
			return {
				cards: []
			};
		},
		onLoad() {
			this.getData();
		},
		methods: {
			//获取数据
			getData() {
				//获取数据
				this.$H.get('/cards').then(res => {
					console.log(res);
					this.cards = res;
				});
			}
		}
	};
</script>

<style scoped>
.cards {
	height: 600rpx;
	display: flex;
	flex-direction: column;
	background-color: rgb(153, 158, 207);
	padding: 20rpx;
	border-radius: 10rpx;
	margin: 10rpx 10rpx 20rpx 10rpx;
}
.cover {
	width: 700rpx;
	height: 700px;
	border-radius: 10rpx;
	margin-bottom: 20rpx;
}
</style>