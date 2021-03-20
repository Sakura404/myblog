<template>
	<v-container>
		<v-card>
			<v-card-title>文章管理</v-card-title>
			<v-data-table :headers="headers" :items="desserts">
				<template v-slot:item.opreation >
					<v-icon >mdi-pencil-outline</v-icon>
					<v-icon >mdi-delete</v-icon>
				</template>
			</v-data-table>
		</v-card>
	</v-container>
</template>

<script>
	export default {
		name: 'post',
		data() {
			return {
				iw:1,
				loading: false,
				loadingmeg: "please wait",
				headers: [{
						text: '名字',
						value: 'postname'
					},
					{
						text: '编号',
						value: 'postid'
					},
					{
						text: '作者',
						value: 'postauthor'
					},
					{
						text: '评论数',
						value: 'commentsum'
					}, {
						text: '发表时间',
						value: 'time',
						align:'center',
					},
					{
						text: '最后修改时间',
						value: 'lasttime',
						align:'center',
					},
					{
						text: '操作',
						value: 'opreation',
						align:'center',
						sortable:false
					}
				],
				desserts: [{
						postname: '奥尔加',
						postid: 123,
						postauthor: 'orga',
						commentsum: 3,
						time: '2021-1-2 18:00:00',
						lasttime: '2021-1-2 18:00:00'
					},
					{
						postname: '奥尔加',
						postid: 123,
						postauthor: 'orga',
						commentsum: 1,
						time: '2021-1-2 18:00:00',
						lasttime: '2021-1-2 17:00:00'
					},
					{
						postname: '奥尔加',
						postid: 123,
						postauthor: 'orga',
						commentsum: 1,
						time: '2021-1-2 18:00:00',
						lasttime: '2021-1-2 18:00:00'
					},
					{
						postname: '奥尔加',
						postid: 123,
						postauthor: 'orga',
						commentsum: 1,
						time: '2021-1-2 18:00:00',
						lasttime: '2021-1-2 18:00:00'
					}
				]
			}
		},
		methods:{
			delect(item){
				this.iw=item;
			}
		}
	}
</script>

<style>
</style>
