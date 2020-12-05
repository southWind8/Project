<template>
	<view>
		<uni-status-bar></uni-status-bar>
		<view>
			<view
				class="iconfont icon-guanbi flex align-center justify-center font-lg"
				style="width: 100rpx;height: 100rpx;"
				hover-class="bg-light"
				@click="back"></view>
		</view>
		
		<view class="text-center" style="padding-top: 130rpx;padding-bottom: 70rpx; font-size: 55rpx;">
			{{status?'手机验证码登录':'账号密码登录'}}
		</view>
		<view class="px-2">
			<template v-if="!status">
				<view class="mb-2">
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2"/>
				</view>
				<view class="mb-2 flex align-stretch">
					<input type="text" v-model="password" placeholder="请输入密码" class="border-bottom p-2 flex-1" />
					<view class="border-bottom flex align-center justify-center font text-muted" style="width: 150rpx;">
						忘记密码？
					</view>
				</view>
			</template>
			<template v-else>
				<view class="mb-2 flex align-stretch">
					<view class="border-bottom flex align-center justify-center font px-2">+86</view>
					<input type="text" v-model="phone" placeholder="手机号" class="border-bottom p-2 flex-1" />
				</view>
				<view class="mb-2 flex align-stretch">
					<input type="text" v-model="code" placeholder="请输入验证码" class="border-bottom p-2 flex-1" />
					<view class="border-bottom flex align-center justify-center font-sm text-white"
					:class="codeTime > 0 ? 'bg-main-disabled':'bg-main'"
					style="width: 180rpx;"
					@click="getCode">
						{{codeTime > 0 ?codeTime +'s':'获取验证码'}}
					</view>
				</view>
			</template>
		</view>
		
		<view class="py-2 px-3">
			<button class="bg-main text-white"
			 style="border-radius: 50rpx; border: 0;"
			 type="primary"
			 :disabled="disabled"
			 :class="disabled ? 'bg-main-disabled':'bg-main'"
			 @click="submit"
			 :loading="loading">
			 {{loading?'登录中...':'登录'}}
			 </button>
		</view>
		<view class="flex align-center justify-center pt-2 pb-4">
			<view class="text-primary font-sm" @click="changeStatus">{{status?'账号密码登录':'验证码登录'}}</view>
			<text class="text-muted mx-2">|</text>
			<view class="text-primary font-sm">登录遇到问题</view>
		</view>
		
		<view class="flex align-center justify-center text-muted">
			注册即代表同意
			<text class="text-primary">《xxx协议》</text>
		</view>
	</view>
</template>

<script>
	import uniStatusBar from '@/components/uni-ui/uni-status-bar/uni-status-bar.vue';
	export default {
		components:{
			uniStatusBar
		},
		data() {
			return {
				status:false,
				password:'',
				phone:'',
				code:'',
				codeTime:0,
				loading:false
			};
		},
		onLoad(){},
		computed:{
			disabled() {
				if((this.phone ===''||this.password==='')&&(this.phone===''||this.code==='')){
					return true;
				}
				return false;
			}
		},
		methods: {
			back(){
				uni.navigateBack({
					delta:1
				})
			},
			//初始化表单
			initForm(){
				this.phone = '';
				this.password = '';
				this.code = '';
			},
			//切换登录方式
			changeStatus(){
				this.initForm();
				this.status = !this.status;
			},
			//获取验证码
			getCode(){
				//防止重复获取
				if(this.codeTime>0){
					return;
				}
				//验证手机号
				if(!this.validate()) return;
				//请求数据
				this.$H
					.post('./user/sendcode',
					{
						phone:this.phone
					},{
						native:true
					}).then(res=>{
						uni.showToast({
							title:res.data.msg,
							icon:'none'
						});
						//倒计时
						this.codeTime = 60;
						let timer = setInterval(()=>{
							if(this.codeTime>=1){
								this.codeTime--;
							}else{
								this.codeTime=0;
								clearInterval(timer)
							}
						},1000);
					});
			},
			//表单验证
			validate(){
				//手机号正则表达式
				var mPattern = /^1[34578]\d{9}$/;
				if(!mPattern.test(this.phone)){
					uni.showToast({
						title:'手机号格式不正确',
						icon:'none'
					});
					return false;
				}
				//更多验证
				return true;
			},
			submit(){
				//表单验证
				let url='';
				let data= '';
				if(this.status){
					//手机验证码登录
					if(!this.validate())
					return;
					url = '/user/phonelogin';
					data={
						phone:this.phone,
						code:this.code
					};
				}else{
					//账号密码登录
					url='/user/login'
					data={
						phone:this.phone,
						password:this.password
					};
				}
				this.loading = true;
				this.$H
					.post(url,data)
					.then(res =>{
						this.loading=false;
						console.log(res);
						//修改vuex的state.持久化存储
						this.$store.commit('login',res);
						//提示和跳转
						uni.navigateBack({
							delta:1
						});
						uni.showToast({
							title:'登录成功',
							icon:'none'
						})
					}).catch(err=>{
						//登录失败
						this.loading = false;
					});
			}
			
		}
	}
</script>

<style>

</style>
