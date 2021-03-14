<template>
	<div id="app">
		<blmenu></blmenu>
		<headinfo></headinfo>
		<bangumi></bangumi>
	</div>
</template>

<script>
	import blmenu from './components/blmenu.vue'
	import headinfo from'./components/headinfo.vue'
	import bangumi from './components/bangumi.vue'
	import 'mdui';
	export default {
		name: 'App',
		components: {
			blmenu,
			headinfo,
			bangumi,
		},
		methods: {
			nameless() {
				console.log("hello world");
			},
		},
		data() {
			return {}
		},
	}
</script>

<style>
	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}
</style>
