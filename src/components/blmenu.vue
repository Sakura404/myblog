<template>
	<div>
		<div class="mdui-appbar mdui-appbar-inset">
			<div class="mdui-toolbar ">
				<a href="javascript:;" class="mdui-btn mdui-btn-icon " mdui-drawer="{target: '#drawer','overlay': true,
					'swipe': true}"><i class="mdui-icon material-icons">menu</i></a>
				<span class="mdui-typo-title" id="menutitle">散<span id='rotate'>の</span>華</span>
				<div class="mdui-toolbar-spacer"></div>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">search</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
						class="mdui-icon material-icons">refresh</i></a>
				<a href="javascript:;" class="mdui-btn mdui-btn-icon"><i
						class="mdui-icon material-icons">more_vert</i></a>
			</div>
		</div>
		<div id="drawer" class="mdui-drawer-close mdui-drawer mdui-color-white">
			<drawercard :bgsrc="require('../assets/dcbg.jpg')"></drawercard>
			<ul class="mdui-list">
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">sort</i>
					<div class="mdui-list-item-content">归档</div>
				</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">store</i>
					<div class="mdui-list-item-content">资源站</div>
				</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">send</i>
					<div class="mdui-list-item-content">Sent mail</div>
				</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">drafts</i>
					<div class="mdui-list-item-content">Drafts</div>
				</li>
				<li class="mdui-subheader">Subheader</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">email</i>
					<div class="mdui-list-item-content">All mail</div>
				</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">delete</i>
					<div class="mdui-list-item-content">Trash</div>
				</li>
				<li class="mdui-list-item mdui-ripple">
					<i class="mdui-list-item-icon mdui-icon material-icons">error</i>
					<div class="mdui-list-item-content">Spam</div>
				</li>
				<nightmod></nightmod>
			</ul>
		</div>
	</div>

</template>

<script>
	import mdui from 'mdui';
	import drawercard from './drawercard.vue'
	import nightmod from './nigthmod.vue'
	export default {
		name: 'blmenu',
		components: {
			drawercard,
			nightmod,
		},
		methods: {
			inits() {
				this.ints = new mdui.Drawer("#drawer", {
					'overlay': true,
					'swipe': true
				})
				this.ints.open();
			}
		},
		data() {
			return {
				ints: null,
			}
		},
		mounted() {},
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
	}

	#menutitle:hover span {
		transform: rotate(360deg);
		color: orange;
	}

	#rotate {
		transition-duration: 2s;
		display: inline-block;
	}
</style>
