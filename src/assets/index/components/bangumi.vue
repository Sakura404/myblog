<template>
  <div>
    <div
      v-for=" (bangumidata,index) in bangumi1"
      :key="index"
      class="mdui-card bangumicard"
    >
      <div class="mdui-card-media">
        <a
          :href="bangumidata.url"
          target="_blank"
        ><img
          :src="bangumidata.cover"
          crossorigin="anonymous"
        ></a>
        <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
          <div class="mdui-card-primary">
            <div class="mdui-card-primary-title">
              {{ bangumidata.title }}
            </div>
            <div class="mdui-card-primary-subtitle">
              {{ bangumidata.evaluate }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
		name: 'Bangumi',
		data() {
			return {
				bangumi1: null
			}
		},
		mounted() {
			this.addMeta("referrer","no-referrer")
			this.$http.get("https://api.catbk.cn/list?vmid=35497273&type=1").then(res => {
				this.bangumi1 = res.data.data.list
			})
		},
		methods: {
			addMeta(name, content) { //手动添加mate标签
				let meta = document.createElement('meta');
				meta.content = content;
				meta.name = name;
				document.getElementsByTagName('head')[0].appendChild(meta);
			}
		}
	}
</script>

<style>
	.bangumicard {
		margin: 40px;
		max-width: 300px;
		display: inline-block;
	}
	.bangumicard .mdui-card-primary {
		background-color: rgba(0,0,0,0.5);
		transform: translateY(300px);
		transition: 1s;
	}
	.bangumicard :hover .mdui-card-primary{
		transform: translateY(0);
	}
</style>
