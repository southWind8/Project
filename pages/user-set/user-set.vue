<template>
	<view>
		<template v-if="loginStatus">
			<uni-list-item title="账号与安全" @click="open('user-safe')"></uni-list-item>
			<uni-list-item title="资料编辑" @click="open('user-info')"></uni-list-item>
		</template>
		<uni-list-item title="清除缓存" @click="clear">
			<view slot="right" class="text-muted">{{ currentSize | format }}</view>
		</uni-list-item>
		<uni-list-item v-if="loginStatus" title="意见反馈" @click="open('user-feedback')"></uni-list-item>
		<uni-list-item title="关于社区" @click="open('about')"></uni-list-item>
		<view class="py-2 px-3" v-if="loginStatus">
			<button type="primary" class="bg-main text-white" style="border-radius: 50rpx;border: 0;" @click="logout">
				退出登录
			</button>
		</view>
	</view>
</template>

<script>
	import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
	import { mapState } from 'vuex';
	export default {
		components: {
			uniListItem
		},
		data() {
			return {
				currentSize: 0
			};
		},
		onLoad() {
			//调用methods中的getStorageInfo方法，获取本地存储中的信息
			this.getStorageInfo();
		},
		//过滤器，按照缓存大小显示不同单位
		filters: {
			format(value) {
				return value > 1024 ? (value / 1024).toFixed(2) + 'MB' : value.toFixed(2) + 'KB';
			}
		},
		//通过辅助函数，从Vuex解构得到计算属性loginStatus登陆状态
		computed: {
			...mapState({
				loginStatus: state => state.loginStatus
			})
		},
		methods: {
			//获得本地存储内容，并将其大小赋值给当前缓存容量
			getStorageInfo() {
				let res = uni.getStorageInfoSync();
				this.currentSize = res.currentSize;
			},
			//封装根据path跳转页面的方法
			open(path) {
				uni.navigateTo({
					url: `../${path}/${path}`
				});
			},
			//清空缓存
			clear() {
				uni.showModal({
					title: '提示',
					content: '是否要清除所有缓存？',
					cancelText: '不清除',
					success: res => {
						if(res.confirm) {
							uni.clearStorageSync();
							this.getStorageInfo();
							uni.showToast({
								title: '清除成功',
								icon: 'none'
							});
						}
					}
				});
			},
			//退出登录
			logout() {
				uni.showModal({
					content: '是否要退出登录',
					success: res => {
						if(res.confirm) {
							this.$store.commit('logout');
							//返回上一级页面
							uni.navigateBack({
								delta: 1
							});
							uni.showToast({
								title: '退出登录成功',
								icon: 'none'
							});
						}
					}
				});
			}
		}
	};
</script>

<style>

</style>
