<template>
	<view>
		<uni-list-item title="标题">
			<view class="flex align-center" slot="right">
				<input class="uni-input text-right" v-model="title" />
				<text class="iconfont icon-bianji1 ml-2"></text>
			</view>
		</uni-list-item>
		<uni-list-item title="类型" @click="selectCategory">
			<view class="flex align-center" slot="right">
				<text>{{ category }}</text>
				<text class="iconfont icon-bianji1 ml-2"></text>
			</view>
		</uni-list-item>
		
		<view class="flex justify-start align-center px-3 flex-wrap">
			<span style="font-size: 32upx;" class="my-3 mr-2">标签</span>
			<uni-tag
				v-for="(tag, index) in tagList"
				:key="index"
				:text="tag.tagName"
				:type="types[index % 4]"
				:circle="true"
				@click="removeTag(tag, index)"
				style="display: inline-block;"
				class="mr-1 mb-1">
			</uni-tag>
		</view>
		
		<uni-collapse accordion="true">
			<uni-collapse-item title="请选择" class="ml-1 font-lg">
				<view class="flex justify-start align-center px-3 flex-wrap">
					<uni-tag
						v-for="(tag, index) in tags"
						:key="index"
						:text="tag.tagName"
						:type="types[index % 4]"
						:circle="true"
						@click="addTag(tag, index)"
						style="display: inline-block;"
						class="mr-1 mb-1">
					</uni-tag>
				</view>
			</uni-collapse-item>
		</uni-collapse>
		
		<jinEdit
			placeHolder="请输入内容"
			@editOk="submit"
			fileKeyName="file"
			uploadFileUrl="http://ldk0.cn.utools.club/api/user/upload">
		</jinEdit>
	</view>
</template>

<script>
import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
import uniCollapse from '@/components/uni-ui/uni-collapse/uni-collapse.vue';
import uniCollapseItem from '@/components/uni-ui/uni-collapse-item/uni-collapse-item.vue';
import jinEdit from '../../components/jin-edit/jin-edit.vue';
import uniTag from '@/components/uni-tag/uni-tag.vue';
import { mapState } from 'vuex';
const categoryArray = ['数学','深度学习','编程算法','计算机知识','随笔']
	export default {
		data() {
			return {
				title: '',
				categoryIndex: 0,
				form: {
					desc: ''
				},
				tags: [],
				tagList: [],
				types: ['primary','success','warning','error']
			};
		},
		computed: {
			...mapState({
				user: state => state.user
			}),
			category() {
				return categoryArray[this.categoryIndex];
			}
		},
		onLoad() {
			this.$H.get('/tag/all').then(res => {
				// console.log(res);
				this.tags = res;
				this.tags = this.tags.slice(0, 10);
			});
		},
		components: {
			jinEdit,
			uniListItem,
			uniCollapse,
			uniCollapseItem,
			uniTag
		},
		methods: {
			//选择类别
			selectCategory() {
				uni.showActionSheet({
					itemList: categoryArray,
					success: res => {
						this.categoryIndex = res.tapIndex;
						this.category = categoryArray[this.categoryIndex];
					}
				});
			},
			//点击标签增加到选择区
			addTag(tag, index) {
				this.tags.splice(index, 1);
				this.tagList.push(tag);
			},
			//点击已经选择的标签，移除
			removeTag(tag, index) {
				this.tagList.splice(index, 1); //从tagList数组移除第index个元素
				this.tags.push(tag); //将tag对象加入tags数组尾部
			},
			generateUUID() {
				let d = new Date().getTime();
				let uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
					let r = (d + Math.random() * 16) % 16 | 0;
					d = Math.floor(d / 16);
					return (c == 'x' ? r : (r & 0x7) | 0x8).toString(16);
				});
				return uuid;
			},
			//点击发布
			submit(res) {
				console.log(res);
				//res.html为富文本编辑器的HTML内容
				console.log(res.html);
				let id = this.generateUUID();
				let list = [];
				//注意element为Tag对象，需要取出其tagName属性
				this.tagList.forEach(element => {
					let articleTag = { articleId: id, tagName: element.tagName };
					list.push(articleTag);
				});
				
				let article = {
					id: id,
					userId: this.user.id,
					title: this.title,
					category: this.category,
					summary: '文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要文章摘要',
					content: res.html,
					tagList: list
				};
				this.$H.post('/article/post', article).then(res => {
					console.log(res);
					uni.switchTab({
						url: '../index/index'
					});
				});
			}
		}
	};
</script>

<style>

</style>
