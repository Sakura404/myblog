<template>
	<div>
		<div class="mdui-appbar mdui-appbar-fixed ">
			<div class="mdui-toolbar mdui-color-white">
				<a href="javascript:;" class=" mdui-hidden-md-up mdui-btn mdui-btn-icon" mdui-drawer="{target: '#app-menu','overlay': true,
					'swipe': true}"><i class="mdui-icon material-icons">menu</i></a>
				<span id="menutitle" class="mdui-typo-title">散<span id="rotate">の</span>華</span>
				<div class="mdui-toolbar-spacer" />
				<div id="pc-menu" class="mdui-hidden-sm-down ">
					<navlink></navlink>
				</div>
				<div class="mdui-toolbar-spacer" />
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">search</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
						class="mdui-icon material-icons">refresh</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
						class="mdui-icon material-icons">more_vert</i></a>
			</div>
		</div>
		<div id="app-menu" class="mdui-drawer-close mdui-hidden-md-up mdui-drawer mdui-color-white ">
			<drawercard :bgsrc="require('../assets/dcbg.jpg')" />
			<navlink></navlink>
			<nightmod></nightmod>
		</div>
	</div>
</template>

<script>
	import mdui from 'mdui';
	import drawercard from './drawercard.vue'
	import nightmod from './nigthmod.vue'
	import navlink from './navlink.vue'
	export default {
		name: 'Blmenu',
		components: {
			drawercard,
			nightmod,
			navlink

		},
		data() {
			return {
				ints: null,
			}
		},
		mounted() {},
		methods: {
			inits() {
				this.ints = new mdui.Drawer("#drawer", {
					'overlay': true,
					'swipe': true
				})
				this.ints.open();
			}
		},
	}
</script>
<style>
	@font-face {
		font-family: 'PensinkaiEG26ba75699b230f9';
		src: url('//cdn.repository.webfont.com/webfonts/nomal/143609/46235/6046fcf6f629d84198f3e0bc.gif?r=163658614280');
		src: url('//cdn.repository.webfont.com/webfonts/nomal/143609/46235/6046fcf6f629d84198f3e0bc.gif?r=163658614280?#iefix') format('embedded-opentype'), url('//cdn.repository.webfont.com/webfonts/nomal/143609/46235/6046fcf6f629d84198f3e0bc.png?r=163658614280') format('woff2'), url('//cdn.repository.webfont.com/webfonts/nomal/143609/46235/6046fcf6f629d84198f3e0bc.bmp?r=163658614280') format('woff'), url('//cdn.repository.webfont.com/webfonts/nomal/143609/46235/6046fcf6f629d84198f3e0bc.jpg?r=163658614280') format('truetype');
		font-weight: normal;
		font-style: normal;
	}

	.css26ba75699b230f9 {
		font-family: 'PensinkaiEG26ba75699b230f9';
	}

	#menutitle {
		font-family: 'PensinkaiEG26ba75699b230f9';
		font-weight: 700;
		font-size: 30px;
		user-select: none;
	}

	#menutitle:hover span {
		transform: rotate(360deg);
		color: orange;
	}

	#rotate {
		transition-duration: 2s;
		display: inline-block;
	}

	#pc-menu ul li {

		display: inline-flex;
		height: 100%;
	}
</style>
