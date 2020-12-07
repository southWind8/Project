<template>
	<view>
		<!-- 未登录 -->
		<template v-if="!loginStatus">
			<view class="flex align-center p-2" hover-class="bg-light">
				<image :src="avatar" style="width: 100rpx;height: 100rpx;" class="rounded-circle"></image>
				<view class="flex flex-column flex-1 px-2">
					<text class="font text-muted">登录，体验更多功能</text>
				</view>
				<text class="iconfont icon-jinru" @click="login"></text>
			</view>
			<view class="px-3 py-2">
				<image 
					src="/static/demo/banner1.jpg"
					mode="aspectFill"
					style="height: 170rpx;width: 100%;"
					class="rounded"></image>
			</view>
		</template>
		
		<!-- 已登录 -->
		<template v-else>
			<view class="flex align-center p-2" hover-class="bg-light">
				<image :src="avatar" style="width: 100rpx;height: 100rpx;" class="rounded-circle"></image>
				<view class="flex flex-column flex-1 px-2">
					<text class="font-lg font-weight-bold text-dark">{{ user.nickname }}</text>
					<text class="font text-muted">总帖子{{ myData[0].num }} 今日发帖{{ myData[1].num }}</text>
				</view>	
				<text class="iconfont icon-jinru" @click="userSet"></text>
			</view>
			<view class="flex align-center px-3 py-2">
				<view class="flex-1 flex flex-column align-center justify-center"
					  v-for="(item, index) in myData"
					  :key="index">
					<text class="font-lg font-weight-bold">{{ item.num }}</text>
					<text class="font text-muted">{{ item.name }}</text>
				</view>
			</view>
			<view class="px-3 py-2">
				<image src="/static/demo/banner1.jpg" 
					   mode="aspectFill"
					   style="height: 170rpx;width: 100%;"
					   class="rounded"></image>
			</view>
			<uni-list-item title="浏览历史" showExtraIcon>
				<text slot="icon" class="iconfont icon-liulan"></text>
			</uni-list-item>
			<uni-list-item title="社区认证" showExtraIcon>
				<text slot="icon" class="iconfont icon-huiyuanvip"></text>
			</uni-list-item>
			<uni-list-item title="审核帖子" showExtraIcon>
				<text slot="icon" class="iconfont icon-keyboard"></text>
			</uni-list-item>
		</template>
	</view>
</template>

<script>
	import uniListItem from "@/components/uni-ui/uni-list-item/uni-list-item.vue";
	import { mapState } from 'vuex';
	export default {
		components: {
			uniListItem
		},
		data() {
			return {
				myData: [
					{
						name: '帖子',
						num: 1
					},
					{
						name: '动态',
						num: 1
					},
					{
						name: '评论',
						num: 2
					},
					{
						name: '粉丝',
						num: 0
					}
				]
			};
		},
		computed: {
			...mapState({
				loginStatus: state => state.loginStatus,
				user: state => state.user
			}),
			//用户头像
			avatar() {
				return this.user.avatar ? this.user.avatar : '/static/defaul.jpg';
			}
		},
		methods: {
			login() {
				uni.navigateTo({
					url: '../login/login'
				});
			},
			userSet() {
				uni.navigateTo({
					url: '../user-set/user-set'
				})
			}
		}
	};
</script>

<style></style>