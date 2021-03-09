<template>
	<div>
		<blmenu></blmenu>
		<br />
		<login></login>
		<br />
		<counter></counter>
		<br />
	</div>
</template>

<script>
	import login from './components/login.vue';
	import Counter from './components/counter.vue'
	import blmenu from './components/blmenu.vue'
	import 'mdui';
	export default {
		name: 'App',
		components: {
			Counter,
			login,
			blmenu,
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
		color: #2c3e50;
	}
</style>
