<template>
	<view class="container">
		<view v-for="(card,index) in cards" :key="index" class="cards">
			<text class="title">{{ card.title }}</text>
			<image :src="card.bgImg" class="cover"></image>
			<text>{{ card.content }}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
				cards: []
			};
		},
		onLoad() {
			uni.request({
				url:'http://localhost:8080/api/cards',
				success:res => {
					console.log(res.data.data);
					this.cards = res.data.data;
				}
			});
		},
		methods: {}
	}
</script>

<style>
.container {
	width: 700rpx;
	padding: 10rpx 10rpx;
}

.cards {
	width: 700rpx;
	background-color: rgb(153, 158, 207);
	padding: 20rpx;
	border-radius: 10rpx;
	margin-bottom: 20rpx;
}

.title {
	font-size: 36rpx;
}

.cover {
	width: 700rpx;
	height: 300rpx;
	border-radius: 10rpx;
}
</style>