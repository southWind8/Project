<template>
<view>
	<view class="flex align-center p-2" hover-class="bg-light">
			<image :src="avatar" style="width: 100rpx; height: 100rpx;" class="rounded-circle"></image>
			<view class="flex flex-column flex-1 px-2">
				<text class="font-md">{{user.nickname}}</text>
				<text class="font-sm font-weight-light">{{article.publishDate}}</text>
			</view>
		 </view>
	<uni-card
		:title="article.title"
		mode="style"
		:is-shadow="true"
		:thumbnail="article.cover"
		:extra="article.category"
		note="true"
	>
		<template v-slot:footer>
			<view class="footer-box">
				<span class="bg-color mr-3" v-for="(tag, index) in article.tagList" :key="index">
					{{tag.tagName}}
				</span>
			</view>
		</template>
	</uni-card>
	<view style="background:#FFF; padding:30rpx;"><rich-text :nodes="article.content"></rich-text></view>
</view>
</template>

<script>
	import uniCard from '@/components/uni-card/uni-card.vue'
	import { mapState } from 'vuex';
	export default {
		data() {
			return {
				id:'',
				article:{}
				
			};
		},
		components:{uniCard},
		computed:{
			...mapState({
				loginStatus:state =>state.loginStatus,
				user:state=>state.user
			}),
			//用户头像
			avatar(){
				return this.user.avatar ? this.user.avatar :'/static/default.jpg';
			}
			
		},
		onLoad(option){
			console.log(option.id);
			this.id=option.id;
			this.getData();
		},
		methods: {
			getData(){
				this.$H.get('/article/'+this.id).then(res =>{
					this.article=res;
				});
			}
			
		}
	}
</script>

<style lang="scss" scoped>
	.bg-color{
		color: #ffffff;
		background-image: linear-gradient(to right, #bf30ac 0%, #0f9d59 100%);
		opacity: 0.7;
		padding: 10rpx;
		border-radius: 20rpx;
	}

</style>
